package com.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @Date 2019/2/20 16:18
 */
public class SynchronousQueueTest {
    private static SynchronousQueue queue = new SynchronousQueue();

    public static void main(String[] args) throws InterruptedException {
//        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();

        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put thread start");
                try {
                    while (true) {
                        queue.put(1);
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                }
                System.out.println("put thread end");
            }
        });

        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take thread start");
                try {
                    while (true) {
                        System.out.println("take from putThread: " + queue.take());
                    }
                } catch (InterruptedException e) {
                }
                System.out.println("take thread end");
            }
        });

        putThread.start();
        Thread.sleep(1000);
        takeThread.start();

    }
}
