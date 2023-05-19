package com.example.analyticservice.service;

import com.example.analyticservice.model.MeteringData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.beans.Expression;
import java.util.List;


@Service
public class MeteringDataService {

    private static final Logger log =  LogManager.getLogger(MeteringDataService.class);




    private final MongoTemplate mongoTemplate;


    public List<MeteringData> groupByDay(DateTime startDate,DateTime endDate) {
        return groupByCriteria(startDate, endDate, "day");
    }

    public List<MeteringData> groupByWeek(DateTime startDate, DateTime endDate) {
        return groupByCriteria(startDate, endDate, "week");
    }

    public List<MeteringData> groupByMonth(DateTime startDate, DateTime endDate) {
        return groupByCriteria(startDate, endDate, "month");
    }

    public List<MeteringData> groupByYear(DateTime startDate, DateTime endDate) {
        return groupByCriteria(startDate, endDate, "year");
    }
    @Autowired
    public MeteringDataService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private List<MeteringData> groupByCriteria(DateTime startDate, DateTime endDate, String criteria) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("eventTime").gte(startDate).lte(endDate)),
                Aggregation.project()
                        .and(getDatePartExpression(criteria)).as("datePart"),
                Aggregation.group(Fields.from(Fields.field("datePart", "$datePart")))
                        .first("smartMeterId").as("smartMeterId")
                        .first("eventTime").as("eventTime")
                        .first("processTime").as("processTime")
                        .first("timeZone").as("timeZone")
                        .avg("energyTotal").as("averageEnergyTotal")
                        .avg("powerTotal").as("averagePowerTotal")
                        .avg("energyTotalNeg").as("averageEnergyTotalNeg")
                        .avg("energyTotalPos").as("averageEnergyTotalPos"),
                Aggregation.project()
                        .and("datePart").as("date")
                        .and("smartMeterId").as("smartMeterId")
                        .and("eventTime").as("eventTime")
                        .and("processTime").as("processTime")
                        .and("timeZone").as("timeZone")
                        .and("averageEnergyTotal").as("averageEnergyTotal")
                        .and("averagePowerTotal").as("averagePowerTotal")
                        .and("averageEnergyTotalNeg").as("averageEnergyTotalNeg")
                        .and("averageEnergyTotalPos").as("averageEnergyTotalPos")
                        .and("_id").as("id")
        );

        AggregationResults<MeteringData> results = mongoTemplate.aggregate(aggregation, "Meter_S01", MeteringData.class);
        return results.getMappedResults();
    }

    private AggregationExpression getDatePartExpression(String criteria) {
        AggregationExpression datePartExpression = null;

        switch (criteria) {
            case "day":
                datePartExpression = DateOperators.DayOfMonth.dayOfMonth("$eventTime");
                break;
            case "week":
                datePartExpression = DateOperators.Week.week("$eventTime");
                break;
            case "month":
                datePartExpression = DateOperators.Month.month("$eventTime");
                break;
            case "year":
                datePartExpression = DateOperators.Year.year("$eventTime");
                break;
        }

        return datePartExpression;
    }
}
