package com.baizeyv;

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
        for (int i = 0; i < 11; i++) {
            new Thread(() -> {
                try {
                    int tmp = 1;
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
                    int tmp = 1;
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
                int tmp = 1;
                blockQueue.putInto(tmp);
            } catch (Exception e) {
            }
        }).start();
    }

}
