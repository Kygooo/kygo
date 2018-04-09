package com.kygo.service.ftp;

import com.kygo.service.ftp.FTPConfig.PoolConfig;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FTPClientPoolInitConfiguration {
	
	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
	
	@ConfigurationProperties(prefix = "ftp")
	@Bean("ftpConfig")
	public FTPConfig ftpConfig(){
		FTPConfig config = new FTPConfig();
		//logger.debug("==================> ftpConfig : "+config);
		return config;
	}
	
	@Bean("ftpClientPool")
	public MyFTPClientPool buildFtpPool(FTPConfig config){
		logger.debug("=========buildFtpPool=========> ftpConfig : "+config);
		FTPClientFactory factory = new FTPClientFactory(config);
		PoolConfig myPoolConfig = config.getPoolConfig();
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMinIdle(myPoolConfig.getMinIdle());//-- 最小空闲数量
		poolConfig.setMaxTotal(myPoolConfig.getMaxTotal());//-- 最大连接个数
		//-- 对象池获取对象时是否检测对象有效
        poolConfig.setTestOnBorrow(myPoolConfig.isTestOnBorrow());
        //--1000*60*30 30分钟，当对象数超过最小空闲数量(minIdle)时，超过此时间移除该对象
		poolConfig.setSoftMinEvictableIdleTimeMillis(myPoolConfig.getSoftMinEvictableIdleTime()*1000L);
		//-- 当没有空闲连接时，获取一个对象的最大等待时间，一直等待直到可以获取空闲连接为止
		if(myPoolConfig.getMaxWaitTime() < 0){
			poolConfig.setMaxWaitMillis(-1L);
		}else{
			poolConfig.setMaxWaitMillis(myPoolConfig.getMaxWaitTime()*1000L);
		}
		ObjectPool<FTPClient> objectPool = new GenericObjectPool<FTPClient>(factory,poolConfig);
		MyFTPClientPool myFTPClientPool = new MyFTPClientPool();
		myFTPClientPool.setFtpClientPool(objectPool, config);
		return myFTPClientPool;
	}

}
