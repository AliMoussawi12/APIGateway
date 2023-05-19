package com.example.analyticservice.controller;


import com.example.analyticservice.dto.MeteringDataAnalyticsDto;
import com.example.analyticservice.model.MeteringData;
import com.example.analyticservice.service.MeteringDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MeteringDataAnalyticsController {
    @Autowired
    MeteringDataService meteringDataService;

    MeteringDataAnalyticsController(MeteringDataService meteringDataService){this.meteringDataService=meteringDataService;}
    @GetMapping("/analytics1221")
    public String getInfo() {
        return "test analytics api";
    }
    @GetMapping("/analytics12121")
    public String getInfo12() {
        return "test analytics api111";
    }

  /*  @GetMapping("/analytic-smartmeterid")
   public ResponseEntity<List<MeteringData>> getMeteringData(
           @RequestBody MeteringDataAnalyticsDto meteringDataAnalyticsDto) {
           List<MeteringData> meteringDataList =meteringDataService.getMeteringDataBySmartMeterIdAndDateRange(meteringDataAnalyticsDto.getSmartMeterId(), meteringDataAnalyticsDto.getStartDate(), meteringDataAnalyticsDto.getEndDate());
          return new ResponseEntity<>(meteringDataList, HttpStatus.OK);
    }*/
}
