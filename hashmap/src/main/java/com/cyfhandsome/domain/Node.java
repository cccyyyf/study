package com.cyfhandsome.domain;

/**
 * @author cyf
 * @date 2021/11/28 19:29
 */
public class Node<K,V> {


    /**
     * key
     */
    private K key;

    /**
     * value
     */
    private V value;

    /**
     * 后续节点
     */
    private Node<K,V> next;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node(K key, V value, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
