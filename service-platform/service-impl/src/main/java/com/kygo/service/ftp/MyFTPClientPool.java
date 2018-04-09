package com.kygo.service.ftp;

import com.kygo.service.exception.FTPClientException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.ObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

public class MyFTPClientPool {
	
	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
	
	private FTPConfig ftpConfig;
	private ObjectPool<FTPClient> ftpClientPool;

	public ObjectPool<FTPClient> getFtpClientPool() {
		return ftpClientPool;
	}

	public void setFtpClientPool(ObjectPool<FTPClient> ftpClientPool, FTPConfig ftpConfig) {
		this.ftpClientPool = ftpClientPool;
		this.ftpConfig = ftpConfig;
	}
	
	public FTPClient borrowObject() throws NoSuchElementException, IllegalStateException, Exception{
		if(ftpClientPool == null){
			return null;
		}
		FTPClient ftpClient = ftpClientPool.borrowObject();
		logger.debug("call create borrowObject.");
		if(!ftpClient.sendNoOp()){
			ftpClient.connect(ftpConfig.getHost(), ftpConfig.getPort());
			//client.login(ftpConfig.getUsername(), ftpConfig.getPassword());
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				logger.warn("FTPServer refused connection");
			}
			boolean result = ftpClient.login(ftpConfig.getUsername(), ftpConfig.getPassword());
			if (!result) {
				logger.error("call create hit error .");
				throw new FTPClientException("ftpClient登陆失败! userName:" + ftpConfig.getUsername() + " ; password:" + ftpConfig.getPassword());
			}
		}
		return ftpClient;
	}
	
	public void returnObject(FTPClient ftpClient){
		if(ftpClientPool == null){
			return ;
		}
		if(ftpClient == null){
			return ;
		}
		try {
			ftpClientPool.returnObject(ftpClient);
		} catch (Exception e) {
			logger.error("call returnObject hit error ." +e);
			e.printStackTrace();
		}
	}

}
