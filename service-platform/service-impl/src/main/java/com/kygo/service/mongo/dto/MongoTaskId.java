package com.kygo.service.mongo.dto;

public class MongoTaskId {
	
	private String taskId;
	private Long insureRelationId;
	private Long customerId; 

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Long getInsureRelationId() {
		return insureRelationId;
	}

	public void setInsureRelationId(Long insureRelationId) {
		this.insureRelationId = insureRelationId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
}
