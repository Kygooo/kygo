package com.kygo.service.ftp;

public class FTPConfig {
	
	private String host ;
	
	private int port;
	
	private String username;
	
	private String password;
	
	private PoolConfig poolConfig;
	
	public PoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(PoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public static class PoolConfig{
		
		private int minIdle;
		private int maxTotal;
		private boolean testOnBorrow;
		private Long maxWaitTime;
		private Long softMinEvictableIdleTime;
		
		public int getMinIdle() {
			return minIdle;
		}
		public void setMinIdle(int minIdle) {
			this.minIdle = minIdle;
		}
		public int getMaxTotal() {
			return maxTotal;
		}
		public void setMaxTotal(int maxTotal) {
			this.maxTotal = maxTotal;
		}
		public boolean isTestOnBorrow() {
			return testOnBorrow;
		}
		
		public Long getMaxWaitTime() {
			return maxWaitTime;
		}
		public void setMaxWaitTime(Long maxWaitTime) {
			this.maxWaitTime = maxWaitTime;
		}
		public Long getSoftMinEvictableIdleTime() {
			return softMinEvictableIdleTime;
		}
		public void setSoftMinEvictableIdleTime(Long softMinEvictableIdleTime) {
			this.softMinEvictableIdleTime = softMinEvictableIdleTime;
		}
		public void setTestOnBorrow(boolean testOnBorrow) {
			this.testOnBorrow = testOnBorrow;
		}
		
		@Override
		public String toString() {
			return "PoolConfig [minIdle=" + minIdle + ", maxTotal=" + maxTotal + ", testOnBorrow=" + testOnBorrow + ", maxWaitTime=" + maxWaitTime
					+ ", softMinEvictableIdleTime=" + softMinEvictableIdleTime + "]";
		}
		
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "FTPConfig [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password + ", poolConfig=" + poolConfig + "]";
	}

	
}
