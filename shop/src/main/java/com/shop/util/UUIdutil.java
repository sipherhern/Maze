package com.shop.util;

import java.util.UUID;

public class UUIdutil {
	//返回一个不重复的字符串
		public static String uuid() {
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		}
}
