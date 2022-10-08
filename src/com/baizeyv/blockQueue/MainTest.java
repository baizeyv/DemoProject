package com.baizeyv.blockQueue;

import com.baizeyv.blockQueue.BlockQueue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainTest {

    public static void main(String[] args) throws Exception {
        moreThanMaxTest();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("**************");
        lessThanMaxTest();
    }

    public static void moreThanMaxTest() throws Exception {
        BlockQueue<Integer> blockQueue = new BlockQueue<>(10);
        for (int i = 0; i < 12; i++) {
            new Thread(() -> {
                try {
                    Random rd = new Random();
                    int tmp = rd.nextInt(100);
                    blockQueue.putInto(tmp);
                } catch (Exception e) {
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println("-----------");
        new Thread(() -> {
            try {
                blockQueue.getFrom();
            } catch (Exception e) {
            }

        }).start();

    }

    public static void lessThanMaxTest() throws Exception {
        BlockQueue<Integer> blockQueue = new BlockQueue<>(10);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Random rd = new Random();
                    int tmp = rd.nextInt(100);
                    blockQueue.putInto(tmp);
                } catch (Exception e) {
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    blockQueue.getFrom();
                } catch (Exception e) {
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {
            try {
                blockQueue.getFrom();
            } catch (Exception e) {
            }

        }).start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println("-----------");

        new Thread(() -> {
            try {
                Random rd = new Random();
                int tmp = rd.nextInt(100);
                blockQueue.putInto(tmp);
            } catch (Exception e) {
            }
        }).start();
    }

}