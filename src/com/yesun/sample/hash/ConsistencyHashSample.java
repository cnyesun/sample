package com.yesun.sample.hash;

public class ConsistencyHashSample {

	 public static void main(String[] args) {

	        String[] servers = new String[] { "Server 1:192.168.1.1",
	                "Server 2:192.168.1.2", "Server 3:192.168.1.3",
	                "Server 4:192.168.1.4", "Server 5:192.168.1.5" };

	        ConsistencyHash<String> consHash = new ConsistencyHash<String>(servers);
	        System.out.println("服务器映射信息：");
	        consHash.printServerMapOrder();
	        System.out.println("数据映射信息：");
	        showDataMap(consHash);
	        // 移除server2
	        consHash.removeServer(servers[2]);
	        System.out.println("移除server 3后数据映射信息：");
	        showDataMap(consHash);

	    }

	    public static void showDataMap(ConsistencyHash<String> consHash) {
	        for (int i = 0; i < 5; i++) {
	            System.out.println("Data" + i + " mapped at "
	                    + consHash.getServerNode(ConsistencyHash.hash("Data" + i)));
	        }
	    }
}
