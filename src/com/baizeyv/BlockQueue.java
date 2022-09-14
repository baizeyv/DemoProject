package com.baizeyv;

import java.util.LinkedList;

public class BlockQueue<T> {

    static Object put_lock = new Object();

    static Object getPut_lock = new Object();

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

    public synchronized void putInto(T data) throws Exception {
        while (this.list.size() >= this.max) {
            wait();
        }
        list.add(data);
        notifyAll();
    }

    public synchronized T getFrom() throws Exception {
        while (this.list.size() <= 0) {
            wait();
        }
        T res = list.remove(0);
        notifyAll();
        return res;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
