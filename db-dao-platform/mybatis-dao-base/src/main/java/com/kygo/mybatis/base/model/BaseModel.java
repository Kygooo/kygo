package com.kygo.mybatis.base.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings({ "rawtypes", "serial" })
public class BaseModel <T extends Model> extends Model<T> {
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	@TableField("created_time")
	private Timestamp createdTime;
	
	@TableField("updated_time")
	private Timestamp updatedTime;

	@Override
	protected Serializable pkVal() {
		return id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
