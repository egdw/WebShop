package com.web.shop.util;

public class FormatUtils {
	/**
	 * get file format
	 * 
	 * @return
	 */
	public static String getFormat(String file) {
		if (file != null) {
			int lastIndexOf = file.lastIndexOf(".");
			if (lastIndexOf != -1) {
				String substring = file.substring(lastIndexOf);
				return substring;
			}
		}
		return null;
	}
}
