package com.yesun.sample.lock;

import java.util.concurrent.CyclicBarrier;

public class BenchmarkTest {
	
	private Counter counter;
	private CyclicBarrier barrier;
	private int threadNum;
	
	public BenchmarkTest(Counter counter, int threadNum) {
		this.counter = counter;
		this.barrier = new CyclicBarrier(threadNum + 1);
		this.threadNum = threadNum;
	}
	
	public void test() {
		try {
            for (int i = 0; i < threadNum; i++) {
                new TestThread(counter).start();
            }
            long start = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " aaa...");
            barrier.await(); // 等待所有任务线程创建
            System.out.println(Thread.currentThread().getName() + " bbb...");
            barrier.await(); // 等待所有任务计算完成
            System.out.println(Thread.currentThread().getName() + " ccc...");
            long end = System.currentTimeMillis();
            System.out.println("count value:" + counter.getValue());
            System.out.println("花费时间:" + (end - start) + "毫秒");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	class TestThread extends Thread {
        private Counter counter;

        public TestThread(final Counter counter) {
            this.counter = counter;
        }

        public void run() {
            try {
            	System.out.println(Thread.currentThread().getName() + " run...");
                barrier.await();//等待所有线程准备就绪，然后同时执行下面代码
                for (int i = 0; i < 100; i++)
                    counter.increment();
                System.out.println(Thread.currentThread().getName() + " 111...");
                barrier.await();//等待所有线程执行完毕
                System.out.println(Thread.currentThread().getName() + " 222...");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
	
	
	 public static void main(String args[]) {
	        new BenchmarkTest(new SynchronizeSample(), 5000).test();
	        //new BenchmarkTest(new ReentrantLockSample(), 5).test();
	    }



}
