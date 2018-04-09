package com.kygo.mongo.service;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by lwy on 2017/12/15.
 */
public interface MongoManagerService {

    /**
     * 保存对像到mongoDB 没有异常的
     * @param o
     */
    void saveObjectNoException(Object o);

    /**
     * 抛出异常的
     * @param o
     */
    void saveObject(Object o);

    /**
     * 根据条件查询
     * @param field  字段
     * @param fieldValue  值
     * @param entityClass 类
     * @param <T>
     * @return
     */
    <T> T findOne(String field, Object fieldValue, Class<T> entityClass);
    
    /**
     * 根据ID查询
     * @param _id
     * @param entityClass
     * @return
     */
    <T> T findById(String _id, Class<T> entityClass);
    
    <T> List<T> findList(Class<T> entityClass);
    
    <T> List<T> findList(Query query, Class<T> entityClass);
    
    <T> List<T> findAllAndRemoveList(Query query, Class<T> entityClass);
    
    <T> void remove(Query query, Class<T> entityClass);
}
