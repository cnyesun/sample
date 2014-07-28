package com.yesun.sample.thread;

public class InterrupteSample {

	public static void main(String[] args) {
		
		Thread thread = new Thread() {
            public void run() {
                 System.out.println( "1、进入run方法" );
                  while (true) {
                	  
                	  //费时运算
                	  String str = "";
                	  for(int j=0;j<100000;j++){
                		  str += j + ",";
                	  }
                	  System.out.println(str);
                	  
                       System.out.println( "线程在执行");
                       try {
                             Thread.sleep(1000);//也可使用lock.lockInterruptibly()
                       } catch (InterruptedException e) {
                             e.printStackTrace();
                              //这里回抛出线程中断异常，这里就可以做中断后处理了，比如break;
                              break;
                       }
                 }
                 System.out.println( "线程正常执行完毕" );
           }
     };
     thread.start();
     
      try {
           Thread.sleep(500);
     } catch (InterruptedException e) {
           e.printStackTrace();
     }
     thread.interrupt();
     
     try {
         Thread.sleep(3000);
   } catch (InterruptedException e) {
         e.printStackTrace();
   }
     System. out.println( "线程中断" );


	}

}
