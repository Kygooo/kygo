package com.kygo.service.ftp;

import com.kygo.service.exception.FTPClientException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * refer to :https://my.oschina.net/u/657390/blog/638507
 * 
 * @author Keith Wang (王 杰)
 * @email wangjie01vcredit.com
 * @date 2017年8月4日
 * @version 1.0
 */
public class FTPClientFactory extends BasePooledObjectFactory<FTPClient> {
	
	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

	private FTPConfig ftpConfig;

	public FTPClientFactory(FTPConfig config) {
		this.ftpConfig = config;
	}

	/**
	 * Creates an object instance, to be wrapped in a {@link PooledObject}.
	 * <p>
	 * This method <strong>must</strong> support concurrent, multi-threaded activation.
	 * </p>
	 *
	 * @return an instance to be served by the pool
	 * @throws Exception
	 *             if there is a problem creating a new instance, this will be propagated to the code requesting an object.
	 */
	public FTPClient create() throws Exception {
		logger.debug("cal create");
		FTPClient client = new FTPClient();
		client.connect(ftpConfig.getHost(), ftpConfig.getPort());
		//client.login(ftpConfig.getUsername(), ftpConfig.getPassword());
		int reply = client.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			client.disconnect();
			logger.warn("FTPServer refused connection");
		}
		boolean result = client.login(ftpConfig.getUsername(), ftpConfig.getPassword());
		if (!result) {
			logger.error("call create hit error .");
			throw new FTPClientException("ftpClient登陆失败! userName:" + ftpConfig.getUsername() + " ; password:" + ftpConfig.getPassword());
		}
		//client.setFileType(fileType)
		client.setConnectTimeout(1800000);//-- 半小时
		// client.setCharset(charset);
		// client.setActiveExternalIPAddress(ipAddress);
		// client.setActivePortRange(minPort, maxPort);
		//client.setAutodetectUTF8(autodetect);
		// client.setBufferSize(bufSize);
		// client.setCharset(charset);
		// client.setControlEncoding(encoding);
		// client.setControlKeepAliveReplyTimeout(timeout);
		// client.setCopyStreamListener(listener);
		// client.setDataTimeout(timeout);
		// client.setKeepAlive(keepAlive);
		return client;
	}

	/**
	 * Wrap the provided instance with an implementation of {@link PooledObject}.
	 *
	 * @param obj
	 *            the instance to wrap
	 * @return The provided instance, wrapped by a {@link PooledObject}
	 */
	public PooledObject<FTPClient> wrap(FTPClient obj) {
		logger.debug("call wrap ");
		return new DefaultPooledObject<FTPClient>(obj);
	}

	/**
	 * destroy object
	 */
	@Override
	public void destroyObject(PooledObject<FTPClient> p) throws Exception {
		logger.debug("call destroyObject ");
		FTPClient ftpClient = p.getObject();
		ftpClient.logout();
		ftpClient.disconnect();
	}
	
	/**
	 * 
	 * author：周忠友
	 * 在从对象池获取对象或归还对象到对象池时，会调用这个方法，判断对象是否有效，如果无效就会销毁。
	 * 
	 * 每个对象创建后即建立连接，但是连接有可能过时失效，此方法判断出失效的链接并销毁或重连
	 * 
	 */
	@Override
	public boolean validateObject(PooledObject<FTPClient> p) {
		try{
			logger.debug("call validateObject");
			FTPClient ftpClient = p.getObject();
			if(ftpClient == null){//--其实源码已做了判断
				logger.debug("ftpClient is null");
				return false;
			}
			
			if(!ftpClient.isConnected()){
				logger.debug("ftpClient is disconnected");
				return false;
			}
			
			if(!ftpClient.isAvailable()){
				logger.debug("ftpClient is disavailable");
				return false;
			}
			
			if(!ftpClient.sendNoOp()){
				return false;
			}
			
		}catch(Exception e){//-- 调用sendNoOp方法如果连接关闭会报异常：FTP response 421 received.  Server closed connection
			logger.error("call validateObject error："+e.getMessage());
			return false;
		}
		
		return true;
		
	}

}
