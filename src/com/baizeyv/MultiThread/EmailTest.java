package com.baizeyv.MultiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class EmailTest {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Create Thread Pool
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<>(QUEUE_CAPACITY), new ThreadPoolExecutor.AbortPolicy());

        List<Future<String>> futureList = new ArrayList<>();

        List<Callable<String>> callableList = new ArrayList<>();

        for(int i = 0; i < 100; i ++) {
            callableList.add(new SendEmail("mail:" + i + i + i + i + "@Gmail.com", "code:" + i));
        }

        for (Callable<String> callable : callableList) {
            Future<String> future = executor.submit(callable);
            futureList.add(future);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {

        }

        for(Future<String> future : futureList) {
            System.out.println(future.get());
        }

        System.out.println("Finished All Thread!");

    }

}
