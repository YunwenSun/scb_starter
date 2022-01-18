package com.example.demo.service;

import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.validation.Validator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class CollectionService {

    @Resource
    private MongoTemplate mongoTemplate;
    String collectionName="";


    /* Create Collection*/
    public Object createUnlimitedCollection(){
        String collectionName = "unlimited";
        mongoTemplate.createCollection(collectionName);
        return mongoTemplate.collectionExists(collectionName)? "create collection successfully!" : "fail to create collection";
    }

    public String createLimitedCollection(){
        String collectionName = "limited";

        long size = 1024L;
        long max = 5L;
        CollectionOptions collectionOptions = CollectionOptions.empty()
                .capped()
                .size(size)
                .maxDocuments(max);

        mongoTemplate.createCollection(collectionName, collectionOptions);
        return mongoTemplate.collectionExists(collectionName)? "create collection successfully!" : "fail to create collection";
    }

//    效验级别：
//            *   - off：关闭数据校验。
//            *   - strict：(默认值) 对所有的文档"插入"与"更新"操作有效。
//            *   - moderate：仅对"插入"和满足校验规则的"文档"做"更新"操作有效。对已存在的不符合校验规则的"文档"无效。
//            * * 执行策略：
//            *   - error：(默认值) 文档必须满足校验规则，才能被写入。
//            *   - warn：对于"文档"不符合校验规则的 MongoDB 允许写入，但会记录一条告警到 mongod.log 中去。日志内容记录报错信息以及该"文档"的完整记录。
//            *
    public Object createValidatedCollection(){
        String collectionName = "validated";
        CriteriaDefinition criteria = Criteria.where("age").gt(20);
        CollectionOptions collectionOptions = CollectionOptions.empty()
                .validator(Validator.criteria(criteria))
                .strictValidation()
                .failOnValidationError();

        mongoTemplate.createCollection(collectionName);
        return mongoTemplate.collectionExists(collectionName)? "create collection successfully!" : "fail to create collection";
    }

    /* Query Collection */
    public Set<String> getCollectionNames(){
        return mongoTemplate.getCollectionNames();
    }

    /* Delete Collection */
    public String dropCollection(String collectionName){
        mongoTemplate.getCollection(collectionName).drop();
        return mongoTemplate.collectionExists(collectionName)? "fail to drop" : "drop collection successfully";
    }
}
