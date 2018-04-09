package com.kygo.common.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileUtil {
	
	public static String getFileSuffix(String originFileName){
		String[] arr = originFileName.split("\\.");
		if (arr.length <= 1) {
			return "";
		}
		if(arr.length == 2){
			return arr[1];
		}
		return arr[arr.length-1];
	}

	/**
	 * 获取指定文件的内容
	 * @param pathName 路径+文件名
	 * @return
	 */
	public static String readToString(String pathName) {
		StringBuilder result = new StringBuilder();
		try {
			InputStreamReader file = new InputStreamReader(new FileInputStream(pathName),"UTF-8");
			BufferedReader br = new BufferedReader(file);

			String s = null;
			while ((s = br.readLine()) != null) {
				result.append(s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result.toString();
	}

}
