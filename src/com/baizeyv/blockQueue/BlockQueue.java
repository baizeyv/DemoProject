package com.baizeyv.blockQueue;

import java.util.LinkedList;

public class BlockQueue<T> {

    static Object put_lock = new Object();

    static Object get_lock = new Object();

    /**
     * Block Queue Max Capacity
     */
    private int max;

    /**
     * Queue Link List
     */
    public LinkedList<T> list = new LinkedList<>();

    /**
     * Have An Argument Constructor
     *
     * @param max The Max Capacity Of Current Block Queue
     */
    public BlockQueue(int max) {
        this.max = max;
    }

    /**
     * No Arguments Constructor
     */
    public BlockQueue() {
        this.max = 10;
    }

    public void putInto(T data) throws Exception {
        synchronized (get_lock) {
            while (this.list.size() >= this.max) {
                System.out.println("PUT WAIT ?");
                get_lock.wait();
            }
            list.add(data);
            System.out.println("PUT: " + data);
        }
        synchronized (put_lock) {
            put_lock.notify();
        }
    }

    public T getFrom() throws Exception {
        T res;
        synchronized (put_lock) {
            while (this.list.size() <= 0) {
                put_lock.wait();
            }
            res = list.remove(0);
            System.out.println("GET: " + res);
        }
        synchronized (get_lock) {
            get_lock.notify();
        }
        return res;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
