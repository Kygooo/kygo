package com.kygo.mapper.generator;

import com.kygo.mapper.generator.core.CustomerGenerator;

public class MpGenerator {
	
	public static void test(){
		String outpath = "E:\\data\\gen";
    	String mapperTempatePath = "templates/mapper.xml.vm";
    	String author = "Huanle Project Team";
    	String tableName = "t_vcar_insure_relation";
    	String customerPackagePath = "com.kygo.mybatis";
    	String xmlPath = "E:\\data\\gen\\";
		CustomerGenerator customerGenerator = new CustomerGenerator();
		customerGenerator.create(outpath, mapperTempatePath, author, tableName, customerPackagePath,xmlPath);
	}
	
	 /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
    	test();
    }

}


