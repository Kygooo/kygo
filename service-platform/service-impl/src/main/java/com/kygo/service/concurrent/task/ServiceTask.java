package com.kygo.service.concurrent.task;

import com.kygo.common.base.ErrorCode;
import com.kygo.common.base.exception.BaseException;
import com.kygo.common.base.exception.BusinessException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.ExecutionException;

public abstract class ServiceTask<V> extends RunnableFutureTask<V> {
	
	private RequestAttributes requestAttributes;
	
	public ServiceTask() {
		super();
		this.requestAttributes = RequestContextHolder.getRequestAttributes();
	}
	
	@Override
	public void run() {
		RequestContextHolder.setRequestAttributes(requestAttributes);
		super.run();
		RequestContextHolder.resetRequestAttributes();
	}
	
	public V getResult() throws BaseException {
		
		V v = null;
		try {
			v = super.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.SERVER_ERROR.getMsg(), e.getMessage());
		} catch (ExecutionException e) {
			Throwable throwable = e.getCause();
			if (throwable != null && throwable instanceof BaseException) {
				throw (BaseException) throwable;
			} else {
				e.printStackTrace();
				throw new BusinessException(ErrorCode.SERVER_ERROR.getMsg(), e.getMessage());
			}
		}
		
		return v;
	}
}
