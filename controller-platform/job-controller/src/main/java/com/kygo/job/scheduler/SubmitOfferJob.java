package com.kygo.job.scheduler;

import com.kygo.mongo.service.MongoManagerService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 提交报价任务
 * @author Keith Wang (王 杰)
 * @email wangjie01vcredit.com
 * @date 2017年12月28日
 * @version 1.0
 */
@Component
@DisallowConcurrentExecution
public class SubmitOfferJob implements BaseJob {  

    private Logger log = LoggerFactory.getLogger(getClass().getSimpleName());  
    
    @Autowired
    private MongoManagerService mongoService;
    
    /*@Autowired
    private InsureTaskService insureTaskService;*/

    public void execute(JobExecutionContext context)  
        throws JobExecutionException {  
    	/*log.debug("mongoService = {}, insureTaskService = {}", mongoService, insureTaskService);
    	List<MongoTaskId> list = mongoService.findList(MongoTaskId.class);
    	int size = list == null ? 0 : list.size();
    	log.debug("执行报价任务list.size = {}", size);
    	if(CollectionUtils.isEmpty(list)){
    		return ;
    	}
    	for(MongoTaskId mongoTaskId : list){
    		try{
    			log.debug("执行报价任务taskId = {}", mongoTaskId.getTaskId());
    			Criteria criteria = Criteria.where("taskId").is(mongoTaskId.getTaskId());
    			mongoService.remove(new Query(criteria), MongoTaskId.class);
    			insureTaskService.submitOffer(mongoTaskId.getCustomerId(), mongoTaskId.getInsureRelationId(), mongoTaskId.getTaskId());
    		}catch(Exception e){
    			log.warn("提交报价任务失败：taskId = {}", mongoTaskId.getTaskId());
    		}
    		
    	}*/
    }

	public MongoManagerService getMongoService() {
		return mongoService;
	}

	public void setMongoService(MongoManagerService mongoService) {
		this.mongoService = mongoService;
	}

    
}
