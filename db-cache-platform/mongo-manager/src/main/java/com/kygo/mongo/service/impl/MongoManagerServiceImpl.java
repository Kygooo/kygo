package com.kygo.mongo.service.impl;

import com.kygo.mongo.service.MongoManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */
@Service
public class MongoManagerServiceImpl implements MongoManagerService {

    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void saveObjectNoException(Object o) {
        try{
            mongoTemplate.save(o);
        }catch (Exception e){
            logger.error("保存到mogonDB出错");
            e.printStackTrace();
        }
    }

    @Override
    public void saveObject(Object o) {
        mongoTemplate.save(o);
    }

    @Override
    public <T> T findOne(String field, Object fieldValue, Class<T> entityClass) {
        Query query=new Query(Criteria.where(field).is(fieldValue));
        return mongoTemplate.findOne(query,entityClass);
    }

	@Override
	public <T> T findById(String _id, Class<T> entityClass) {
		return mongoTemplate.findById(_id, entityClass);
	}
	
	@Override
	public <T> List<T> findList(Class<T> entityClass) {
		return mongoTemplate.findAll(entityClass);
	}
	
	@Override
	public <T> List<T> findList(Query query, Class<T> entityClass){
		return mongoTemplate.find(query, entityClass);
	}
	
	@Override
	public <T> List<T> findAllAndRemoveList(Query query, Class<T> entityClass){
		return mongoTemplate.findAllAndRemove(query, entityClass);
	}
	
	@Override
	public <T> void remove(Query query, Class<T> entityClass){
		mongoTemplate.remove(query, entityClass);
	}
}
