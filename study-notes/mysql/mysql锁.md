-- 查看当前锁数量
show status like 'innodb_row_lock_%'
-- Innodb_row_lock_current_waits : 当前等待锁的数量
-- Innodb_row_lock_time : 系统启动到现在，锁定的总时间长度
-- Innodb_row_lock_time_avg : 每次平均锁定的时间
-- Innodb_row_lock_time_max : 最长一次锁定时间
-- Innodb_row_lock_waits : 系统启动到现在总共锁定的次数

#### -- 按照时间粒度划分锁

##### -- 开启全局锁 让整个库处于只读状态，无法进行增删改

-- 一般在进行备份的时候会加全局锁 mysqldump 主库备份时会将所有的业务处理停止 从库备份时会导致主从不一致
flush TABLES with read lock

select * from water_acount_customer
-- 失败
update water_acount_customer set customer_id = 3 where id = 1;

-- 关闭全局锁
unlock tables

-- 成功
update water_acount_customer set customer_id = 3 where id = 1;

##### -- 表锁  对整张表的数据进行加锁 读锁/写锁  innoDB & MyISAM都支持 两种 1、表锁 2、元数据锁

-- lock tables ... read/write

###### -- 添加表读锁

lock tables water_acount_customer read
-- 线程1 & 线程2 执行
select * from water_acount_customer

-- 线程1 报错 线程2 阻塞
insert into water_acount_customer (id,water_acount_id,customer_id) VALUES (4,'测试03',4)
-- 解锁 线程2 语句执行成功
unlock tables

###### -- 添加表写锁

lock tables water_acount_customer write;
-- session1成功 session2 阻塞
update water_acount_customer set water_acount_id = '测试13' where id = 4
-- 线程1,线程2 执行 阻塞
select * from water_acount_customer

###### -- 元数据锁 （MDL）

1. sessionA:
   begin;
   select* from water_acount_customer limit 1;
2. sessionB:
   select* from water_acount_customer limit 1;
3. sessionC:
   alter table water_acount_customer add f int;
   #会mdl锁住
4. sessionD:
   select* from water_acount_customer limit 1;
   sessionA启动时会加一个MDL读锁，因为sessionB需要的也是个MDL读锁，所以可以正常拿到数据
   但是sessionC会被阻塞，因为sessionA的MDL读锁还没有被释放 C需要MDL写锁，所以被阻塞
   D申请MDL读锁的时候被C阻塞到了（按照并发来说，C、D可能同时拿到锁
   但是申请MDL锁的操作会形成一个队列，队列中写锁获取优先级高于读锁。
   所以一旦出现写锁等待，不但当前操作会被阻塞，同时还会阻塞后续该表的所有操作）

所有对表的增删改查操作都需要先申请MDL 读锁，而这时读锁没有释放，对表alter ，产生了mdl写锁，把表锁住了，这时候就对表完全不可读写了。

事务中的 MDL 锁，在语句执行开始时申请，但是语句结束后并不会马上释放，而会等到整个事务提交后再释放。
一般行锁都有锁超时时间。但是MDL锁没有超时时间的限制，只要事务没有提交就会一直锁住
-- 解决办法
首先我们要解决长事务，事务不提交，就会一直占着 MDL 锁。在 MySQL 的information_schema 库的 innodb_trx 表中
可以查到当前执行中的事务。如果要做 DDL 变更的表刚好有长事务在执行，要考虑先暂停 DDL，或者 kill 掉这个长事务。这也是为什么需要在低峰期做ddl 变更。

##### -- 行锁

-- 行级锁是粒度最低的锁，发生锁冲突的概率也最低、并发度最高。但是加锁慢、开销大，容易发生死锁现象。
-- MySQL中只有InnoDB支持行级锁，行级锁分为共享锁和排他锁。
session1:
set autocommit=0;
select * from water_acount_customer;
update water_acount_customer set customer_id = 0 where id = 1

-- 被阻塞 但是session1提交之后 session2执行成功，session2也可以操作id ！= 1 的行数据
session2:
set autocommit=0;
select * from water_acount_customer;
update water_acount_customer set customer_id = 0 where id = 1

在MySQL中，行级锁并不是直接锁记录，而是锁索引。索引分为主键索引和非主键索引两种，如果一条sql语句操作了主键索引，MySQL就会锁定这条主键索引；
如果一条语句操作了非主键索引，MySQL会先锁定该非主键索引，再锁定相关的主键索引。
在UPDATE、DELETE操作时，MySQL不仅锁定WHERE条件扫描过的所有索引记录，而且会锁定相邻的键值，即所谓的next-key locking。

##### -- 页锁

页级锁是 MySQL 中锁定粒度介于行级锁和表级锁中间的一种锁。表级锁速度快，但冲突多，行级冲突少，但速度慢。
因此，采取了折衷的页级锁，一次锁定相邻的一组记录。BDB 引擎支持页级锁。

### 按照模式分类

#### -- 乐观锁

-- 乐观锁是相对悲观锁而言的，乐观锁假设数据一般情况下不会造成冲突，所以在数据进行提交更新的时候，才会正式对数据的冲突与否进行检测，如果发现冲突了，则返回给用户错误的信息，让用户决定如何去做。
-- 适用于读多写少，因为如果出现大量的写操作，写冲突的可能性就会增大，业务层需要不断重试，会大大降低系统性能。
-- 一般使用数据版本（version）来实现，读取数据时，将version拿出，每更新一次version+1，提交更新的时候，判断当前数据版本与之前数据版本是否一致，一致则更新，不一致则认为数据过期，不更新

#### -- 悲观锁

-- 悲观锁，正如其名，具有强烈的独占和排他特性，每次去拿数据的时候都认为别人会修改
-- 对数据被外界（包括本系统当前的其他事务，以及来自外部系统的事务处理）修改持保守态度，因此，在整个数据处理过程中，将数据处于锁定状态。
select ... for update
使用select…for update会把数据给锁住，不过我们需要注意一些锁的级别，MySQL InnoDB默认Row-Level Lock，所以只有「明确」地指定主键，MySQL 才会执行Row lock (只锁住被选取的数据)  ，否则MySQL 将会执行Table Lock (将整个数据表单给锁住)。

### -- 按照属性分类

#### -- 排他锁

-- 排它锁，又称之为写锁，简称X锁，当事务对数据加上写锁后，其他事务既不能对该数据添加读写，也不能对该数据添加写锁，写锁与其他锁都是互斥的
-- 只有当前数据写锁被释放后，其他事务才能对其添加写锁或者是读锁。
-- MySQL InnoDB引擎默认update,delete,insert都会自动给涉及到的数据加上排他锁，select语句默认不会加任何锁类型。
-- 写锁主要是为了解决在修改数据时，不允许其他事务对当前数据进行修改和读取操作，从而可以有效避免”脏读”问题的产生。
-- select .... for update

#### -- 共享锁

-- 又称之为读锁，简称S锁，当事务A对数据加上读锁后，其他事务只能对该数据加读锁，不能做任何修改操作，也就是不能添加写锁。只有当事务A上的读锁被释放后，其他事务才能对其添加写锁。
-- 共享主要是为了支持并发的读取数据而出现的，读取数据时，不允许其他事务对当前数据进行修改操作，从而避免”不可重读”的问题的出现。
-- select …lock in share mode
session1:
begin;
select * from water_acount_customer lock in share mode;
session2:
-- 成功
select * from water_acount_customer;
-- 阻塞
update water_acount_customer set water_acount_id = '测试10' where id = 4

### -- 意向共享锁和意向排它锁

1. 概念
   -- 意向锁是表锁，为了协调行锁和表锁的关系，支持多粒度（表锁与行锁）的锁并存。
2. 作用
   -- 当有事务A有行锁时，MySQL会自动为该表添加意向锁，事务B如果想申请整个表的写锁，那么不需要遍历每一行判断是否存在行锁，而直接判断是否存在意向锁，增强性能。
3. 意向锁的兼容互斥性

-- 实战注意：这里的排它 / 共享锁指的都是表锁！！！意向锁不会与行级的共享 / 排它锁互斥！！！
session1获取了某一行的排他锁，并未提交：
select*from water_acount_customer where id=1 for update;
此时 goods 表存在两把锁：goods 表上的意向排它锁与 id 为 1 的数据行上的排它锁。
session2 想要获取 goods 表的共享锁：
LOCK TABLES water_acount_customer READ;
此时session2 检测session1 持有goods 表的意向排他锁，就可以得知session1必然持有该表中某些数据行的排他锁，那么session2 对 goods 表的加锁请求就会被排斥（阻塞），而无需去检测表中的每一行数据是否存在排它锁。

### -- 根据算法分类

-- 记录锁、间隙锁、临键锁都是排它锁，而记录锁的使用方法跟排它锁介绍一致

#### -- 记录锁

记录锁是封锁记录，记录锁也叫行锁，例如：

select *from water_acount_customer where `id`=1 for update;

它会在 id=1 的记录上加上记录锁，以阻止其他事务插入，更新，删除 id=1 这一行。

#### -- 间隙锁

间隙锁基于非唯一索引，它锁定一段范围内的索引记录。使用间隙锁锁住的是一个区间，而不仅仅是这个区间中的每一条数据。
-- 查看间隙锁是否开启
show variables like 'innodb_locks_unsafe_for_binlog';
-- innodb_locks_unsafe_for_binlog：默认值为OFF，即启用间隙锁。因为此参数是只读模式，如果想要禁用间隙锁，需要修改 my.cnf（windows是my.ini） 重新启动才行。
session1:
begin;
select* from water_acount_customer where id between 1 and 10 for update;
session2:
-- 失败
insert into water_acount_customer (id,water_acount_id,customer_id) VALUES (4,'测试03',4)
即所有在（1，10）区间内的记录行都会被锁住，所有id 为 2、3、4、5、6、7、8、9 的数据行的插入会被阻塞，但是 1和 10 两条记录行并不会被锁住。
对于指定查询某一条记录的加锁语句，如果该记录不存在，会产生记录锁和间隙锁，如果记录存在，则只会产生记录锁，如：WHERE `id` = 5 FOR UPDATE;
对于查找某一范围内的查询语句，会产生间隙锁，如：WHERE `id` BETWEEN 5 AND 7 FOR UPDATE;

#### -- 临键锁

-- 临键锁，是记录锁与间隙锁的组合，它的封锁范围，既包含索引记录，又包含索引区间。
-- 注：临键锁的主要目的，也是为了避免幻读(Phantom Read)。如果把事务的隔离级别降级为RC，临键锁则也会失效。

##### 记录锁、间隙锁、临键锁，都属于排它锁；

##### 记录锁就是锁住一行记录；

##### 间隙锁只有在事务隔离级别 RR 中才会产生；

##### 唯一索引只有锁住多条记录或者一条不存在的记录的时候，才会产生间隙锁，指定给某条存在的记录加锁的时候，只会加记录锁，不会产生间隙锁；

##### 普通索引不管是锁住单条，还是多条记录，都会产生间隙锁；

##### 间隙锁会封锁该条记录相邻两个键之间的空白区域，防止其它事务在这个区域内插入、修改、删除数据，这是为了防止出现 幻读 现象；

##### 普通索引的间隙，优先以普通索引排序，然后再根据主键索引排序（多普通索引情况还未研究）；

##### 事务级别是RC（读已提交）级别的话，间隙锁将会失效。

#####
