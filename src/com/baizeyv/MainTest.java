package com.baizeyv;

import java.util.Scanner;
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
                    System.out.println("put:" + tmp);
                } catch (Exception e) {
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            try {
                Integer tmp = blockQueue.getFrom();
                System.out.println("get:" + tmp);
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
                    System.out.println("put:" + tmp);
                } catch (Exception e) {
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Integer tmp = blockQueue.getFrom();
                    System.out.println("get:" + tmp);
                } catch (Exception e) {
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {
            try {
                Integer tmp = blockQueue.getFrom();
                System.out.println("get:" + tmp);
            } catch (Exception e) {
            }

        }).start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println("-----------");

        new Thread(() -> {
            try {
                int tmp = 1;
                blockQueue.putInto(tmp);
                System.out.println("put:" + tmp);
            } catch (Exception e) {
            }
        }).start();
    }

}
