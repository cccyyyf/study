## 一、redis基础命令

keys *        获取当前库下的所有数据

exists key    查看当前键是否存在

type key      查看当前数据类型

del key       删除key

unlink key    删除key,异步删除

expire key 15 设置key过期时间

ttl key       查看key过期剩余时间 -1永久 -2已过期

select 1      切换redis库 0-15

dbsize        查看redis中有多少key

flushdb       清除当前库

flushall      清除所有库

## 二、redis存储String类型数据

String 是二进制安全的，一般用来存储字符串,也可以存储二进制文件，最大支持512M.
String底层的数据结构是简单的动态字符串，类似于ArrayList,占用内存<1M时,每次扩容扩容内存翻倍，>1M时每次扩容+1M，最大512M

set key value					设置key=value

get key						获取键key对应的值

append key value                                追加字符串

getrange key start end			        获取对应key中的子字符串

getset key value		  		新值替换旧值，并返回旧值

getbit key offset				返回存储在键位值的字符串值的偏移

mget key1 [key2..]				获取多个key的值

setbit key offset value			        设置或清除该位在存储在键的字符串值偏移

setex key seconds value			        键到期时设置值

setnx key value					当键不存在的时候设置键的值，存在则无法设置

setrange key offset value		        覆盖字符串的一部分从指定键的偏移

strlen key					得到存储在键的值的长度

mset key value [key value...]	                设置多个键和多个值

msetns key value [key value...]	                设置多个键多个值，存在则无法设置，一个失败全部失败

psetex key milliseconds value	                设置键的毫秒值和到期时间|

incr key					增加键的整数值一次 原子性

incrby key increment			        由给定的数量递增键的整数值

incrbyfloat key increment		        由给定的数量递增键的浮点值

decr key					递减键一次的整数值

decrby key decrement			        由给定数目递减键的整数值

## 三、redis存储List类型数据

存储list结构 可以单键多值，底层是一个有序的双向链表，两端操作性能高

List的数据结构是快速链表quickList。

当list中元素较少时，用的是一块连续的内存存储数据，这个结构是zipList即压缩列表，ziplist将所有的元素紧挨着存储，分配的是一个连续的内存

当元素变多的时候，会改成quicklist存储数据

redis将链表和ziplist结合起来一起使用，也就是将多个ziplist使用双向指针串联起来使用，可以快速的插入或删除数据，同时也不会出现太大的空间冗余

lpush/rpush key value1 [value2 ...]    从左/从右像当前key中插入数据

lrange key [start] [end]               获取范围内的value （0 -1获取全部）

lpop/rpop key                          从左/右获取list中的数据，顺序获取，list中没有数据则键也随之不存在

lindex key [index]                     获取指定下标的数据

llen key                               获取对应key list 长度

linsert key before/after [value] [newValue]  在指定value前面/后面插入数据

lrem key n value                       从左删除n个value

lset key index newValue                将列表key下标为index的替换成newValue

## 四、redis存储Set类型

set 与 list 类似,可以自动排重的, 是 string 类型的无序集合。它底层其实是一个 value 为 null 的 hash 表，所以添加，删除，查找的  复杂度都是 O (1)。

数据结构是 dict 字典，字典是用哈希表实现的。

sadd key value1 [value2...]       将一个或多个元素添加到集合中 已经存在就忽略

smembers key                      取出该集合中的所有值

sismember key value               判断集合key中是否含有该value值

scard key                         返回该集合中的元素个数

srem key value1 [value2...]       删除该集合中的对应元素

spop key                          随机从该集合中取出一个值 删除元素

srandmember key  n                随机从该集合中获取n个值 不会删除元素

sremove source target value       把集合中的值从一个集合移动到另一个集合中

sinter key1 key2                  返回两个集合的交集

sunion key1 key2                  返回两个集合的并集

sdiff key1 key2                   返回两个集合中的差集元素（不包含key2中的）

## 五、redis存储hash
