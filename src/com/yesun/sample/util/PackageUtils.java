package com.yesun.sample.util;

public class PackageUtils {

	public static void main(String[] args) {

		Package pack = PackageUtils.class.getPackage();
		System.out.println(pack.toString());
		System.out.println(pack.getImplementationTitle());
		

	}

}
