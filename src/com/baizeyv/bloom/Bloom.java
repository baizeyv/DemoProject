package com.baizeyv.bloom;

import java.util.BitSet;

public class Bloom {

    public static class Hash {
        private int capacity;
        private int seed;

        public Hash(int capacity, int seed) {
            this.capacity = capacity;
            this.seed = seed;
        }

        /**
         * calc hash value
         */
        public int hash(Object value) {
            if (value == null) {
                return 0;
            }
            int tmp = value.hashCode();
            return Math.abs(seed * (capacity - 1) & tmp ^ (tmp >>> 16));
        }
    }

    /**
     * 位数组大小
     */
    private static final int BIT_SIZE = 2 << 32;

    /**
     * 根据该数组可创建6个不同的 Hash Func
     */
    private static final int[] SEEDS = new int[]{7, 11, 13, 31, 37, 61};

    /**
     * 位数组，元素只能是0或1
     */
    private BitSet bits = new BitSet(BIT_SIZE);

    /**
     * 存放包含 hash 函数的类的数组
     */
    private Hash[] hashFuncs = new Hash[SEEDS.length];

    /**
     * 初始化多个包含 Hash 函数的类的数组，每个类中的hash函数不同
     */
    public Bloom() {
        for(int i = 0; i < SEEDS.length; i ++) {
            hashFuncs[i] = new Hash(BIT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组
     * @param value
     */
    public void add(Object value) {
        for(Hash func : hashFuncs) {
            bits.set(func.hash(value), true);
        }
    }

    /**
     * 判断指定元素是否存在于位数组
     * @param value
     * @return
     */
    public boolean contains(Object value) {
        boolean res = true;
        for(Hash func : hashFuncs) {
            res = res && bits.get(func.hash(value));
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "baizeyv@Gmail.com";
        Bloom bloomFilter = new Bloom();
        System.out.println(bloomFilter.contains(str));
        bloomFilter.add(str);
        System.out.println(bloomFilter.contains(str));
    }

}
