package com.cyfhandsome.domain;

/**
 * @author cyf
 * @date 2021/11/28 19:32
 */
public class ThirdHashMap<K, V> {
    /**
     * 默认大小
     */
    final Integer DEFAULT_CAPACITY = 16;

    /**
     * 负载因子
     */
    final Float LOAD_FACTOR = 0.75f;

    /**
     * 大小
     */
    private Integer size;

    /**
     * 桶数组
     */
    Node[] buckets;

    /**
     * 无参构造默认初始容量为默认值
     */
    public ThirdHashMap() {
        this.buckets = new Node[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * 有参构造
     *
     * @param capacity capacity
     */
    public ThirdHashMap(int capacity) {
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    /**
     * 获取元素在数组中的位置
     *
     * @param key    key
     * @param length length 数组长度
     * @return 在数组中的位置
     */
    private int getIndex(K key, int length) {
        return Math.abs(key.hashCode() % length);
    }

    /**
     * 放置数据
     */
    public void put(K key, V value) {
        //如果当前容量大于等于既定容量，扩容
        if (size >= buckets.length * LOAD_FACTOR) {
            this.resize();
        }
        putVal(key, value, buckets);
    }

    /**
     * 获取数据
     *
     * @param key key
     * @return V
     */
    public V get(K key) {
        int index = getIndex(key, buckets.length);
        Node<K,V> node = buckets[index];
        while (node != null) {
            boolean flag = (node.getKey().hashCode() == key.hashCode())
                    && (node.getKey() == key || node.getKey().equals(key));
            if (flag) {
                return node.getValue();
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 扩容
     */
    public void resize() {
        //将当前数组容量扩大
        Node<K, V>[] newBuckets = new Node[buckets.length * 2];
        //将当前元素重新散列到新数组中
        this.rehash(newBuckets);
        this.buckets = newBuckets;
    }

    /**
     * 重新散列元素
     *
     * @param newBuckets 新列表
     */
    private void rehash(Node<K, V>[] newBuckets) {
        //将数据重新开始计算
        size = 0;

        for (Node<K, V> kvNode : buckets) {
            if (kvNode == null) {
                continue;
            }
            Node<K, V> bucket = kvNode;
            while (bucket != null) {
                this.putVal(bucket.getKey(), bucket.getValue(), newBuckets);
                bucket = bucket.getNext();
            }
        }
    }

    /**
     * 放置数据
     *
     * @param key     key
     * @param value   value
     * @param buckets 桶
     */
    private void putVal(K key, V value, Node<K, V>[] buckets) {
        //获取元素插入位置
        int index = this.getIndex(key, buckets.length);
        Node<K, V> node = buckets[index];
        //如果当前位置为null，直接插入
        if (node == null) {
            buckets[index] = new Node<>(key, value);
            size++;
            return;
        }
        //如果不为空，则发生冲突，使用链寻址方法，遍历列表
        while (node != null) {
            boolean flag = (node.getKey().hashCode() == key.hashCode())
                    && (node.getKey() == key || node.getKey().equals(key));
            if (flag) {
                node.setValue(value);
                return;
            }
            node = node.getNext();
        }
        //如果当前链表中找不到相同元素 插入到链表头部
        Node<K, V> kvNode = new Node<>(key, value, buckets[index]);
        buckets[index] = kvNode;
        size++;
    }


}
