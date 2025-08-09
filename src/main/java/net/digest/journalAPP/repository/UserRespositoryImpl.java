package net.digest.journalAPP.repository;

import net.digest.journalAPP.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRespositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA(){
        Query query = new Query();
//        query.addCriteria(Criteria.where("userName").is("ram"));
//        query.addCriteria(Criteria.where("age").gte(20));
        query.addCriteria(Criteria.where("email").exists(true));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
