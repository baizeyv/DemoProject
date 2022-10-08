package com.baizeyv.bloom;

import java.util.BitSet;

public class BloomFilter {

    public static void main(String[] args) {
        String str = "baizeyv@Gmail.com";
        BloomFilter bloomFilter = new BloomFilter();
        System.out.println(bloomFilter.contains(str));
        bloomFilter.add(str);
        System.out.println(bloomFilter.contains(str));
    }

    private static final int DEFAULT_SIZE = 2 << 32;

    private static final int[] seeds = new int[]{7, 11, 13, 31, 37, 61};

    private BitSet bits = new BitSet(DEFAULT_SIZE);

    private HashFunc[] hashFuncs = new HashFunc[seeds.length];

    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            hashFuncs[i] = new HashFunc(DEFAULT_SIZE, seeds[i]);
        }
    }
    
    public void add(String str) {
        for (HashFunc hf :
                hashFuncs) {
            bits.set(hf.hash(str), true);
        }
    }

    public boolean contains(String str) {
        if (str == null) {
            return false;
        }
        boolean res = true;
        for(HashFunc hf : hashFuncs) {
            res = res && bits.get(hf.hash(str));
        }
        return res;
    }

    public static class HashFunc {

        private int seed;

        private int capacity;

        public HashFunc(int capacity, int seed) {
            this.seed = seed;
            this.capacity = capacity;
        }

        public int hash(String str) {
            int res = 0;
            int len = str.length();
            for (int i = 0; i < len; i ++) {
                res = seed * res + str.charAt(i);
            }
            return (capacity - 1) & res;
        }

    }

}
