package com.web.shop.util;

import java.util.Random;
import java.util.UUID;

import org.springframework.security.core.codec.Hex;

public class RandomUtils {
	private static int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static String[] strs = { "a", "b", "c", "d", "e", "f", "g", "h",
			"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
			"v", "w", "x", "y", "z" };
	private static Random random = new Random();

	/**
	 * @return String 生成64位的随机数作为id
	 */
	public static String getCode() {
		String s = null;
		try {
			byte[] bytes = UUID.randomUUID().toString().getBytes();
			s = new String(Hex.encode(bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static String getRandomPassword() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 8; i++) {
			int nextInt = random.nextInt(3);
			int nextInt2 = 0;
			switch (nextInt) {
			case 0:
				nextInt2 = random.nextInt(nums.length);
				sb.append(nums[nextInt2]);
				break;
			case 1:
				nextInt2 = random.nextInt(strs.length);
				sb.append(strs[nextInt2]);
				break;
			case 2:
				nextInt2 = random.nextInt(strs.length);
				sb.append(strs[nextInt2].toUpperCase());
				break;
			default:
				break;
			}
		}
		return sb.toString();
	}
}
