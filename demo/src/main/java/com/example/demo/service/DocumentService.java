package com.example.demo.service;

import com.example.demo.entity.mongo.AccountInfo;
import com.example.demo.entity.mongo.Gender;
import com.example.demo.entity.mongo.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DocumentService {
    private static final String COLLECTION_NAME = "limited";
    private static final AccountInfo accountInfo = new AccountInfo("username1","password1",123);
    private static final AccountInfo accountInfo2 = new AccountInfo("username2","password2",123);
    private static final User user = new User("1","user1 ", Gender.FEMALE, 11,accountInfo);
    private static final User user2 = new User("2","user2 ", Gender.MALE, 18,accountInfo2);

    @Resource
    private MongoTemplate mongoTemplate;
    List<User> userList= new ArrayList<>(Arrays.asList(user, user2));



    public void insert(){
        mongoTemplate.insert(userList,COLLECTION_NAME);
    }

    public void delete(){
        mongoTemplate.dropCollection(COLLECTION_NAME);
    }


    public List<User> findWithCondition(int num){
        Criteria criteria = Criteria.where("gender").is(Gender.FEMALE);
        Criteria criteria2 = Criteria.where("age").gt(15);
        Criteria mergedCriteria = new Criteria().andOperator(criteria,criteria2);

        Query query = num== 1 ? new Query(criteria): new Query(mergedCriteria);
        return mongoTemplate.find(query,User.class,COLLECTION_NAME);
    }

    public List<User> findAgeAndSort(int num) {
        Criteria criteria = Criteria.where("age").gt(15);
        Query query = new Query(criteria).with(Sort.by("age")).skip(num);
        return mongoTemplate.find(query, User.class, COLLECTION_NAME);
    }

    public List<User> findAllDocuments() {
        return mongoTemplate.findAll(User.class,COLLECTION_NAME);
    }

}
