package com.example.pregateway.repository;

import com.example.pregateway.models.CustomerInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@EnableMongoRepositories
public interface CustomerInfoRepository extends MongoRepository<CustomerInfo,String > {

    CustomerInfo findByUserEmail(String userEmail);

}
