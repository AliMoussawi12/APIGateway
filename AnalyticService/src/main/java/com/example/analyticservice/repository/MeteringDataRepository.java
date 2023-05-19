package com.example.analyticservice.repository;

import com.example.analyticservice.model.MeteringData;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

//@Repository
public interface MeteringDataRepository {
    List<MeteringData> findGroupedByWeek(String smartMeterId, DateTime startDate, DateTime endDate);
}
