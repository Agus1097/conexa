package com.conexa.challenge.factory;

import com.conexa.challenge.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class VehicleFactory {

    public static SWApiResponsePaged<Vehicle> sampleSWApiResponsePaged() {
        SWApiResponsePaged<Vehicle> swApiResponsePaged = new SWApiResponsePaged<>();
        SWApiResult<Vehicle> vehicleResult = new SWApiResult<>();
        Vehicle vehicle = new Vehicle();
        vehicleResult.setProperties(vehicle);
        swApiResponsePaged.setResults(Collections.singletonList(vehicle));

        return swApiResponsePaged;
    }

    public static SWApiResponseList<VehicleDetail> sampleSWApiResponseList() {
        SWApiResponseList<VehicleDetail> swApiResponseList = new SWApiResponseList<>();
        List<SWApiResult<VehicleDetail>> vehicleResultList = new ArrayList<>();

        SWApiResult<VehicleDetail> vehicleResult1 = new SWApiResult<>();
        VehicleDetail vehicleDetail1 = new VehicleDetail();
        vehicleDetail1.setName("Sand Crawler");
        vehicleResult1.setProperties(vehicleDetail1);

        SWApiResult<VehicleDetail> vehicleResult2 = new SWApiResult<>();
        VehicleDetail vehicleDetail2 = new VehicleDetail();
        vehicleDetail2.setName("C-9979 landing craft");
        vehicleResult2.setProperties(vehicleDetail2);

        vehicleResultList.add(vehicleResult1);
        vehicleResultList.add(vehicleResult2);
        swApiResponseList.setResult(vehicleResultList);

        return swApiResponseList;
    }

    public static SWApiResponseList<VehicleDetail> sampleSWApiResponseListEmpty() {
        SWApiResponseList<VehicleDetail> swApiResponseList = new SWApiResponseList<>();
        List<SWApiResult<VehicleDetail>> vehicleResultList = new ArrayList<>();
        swApiResponseList.setResult(vehicleResultList);

        return swApiResponseList;
    }

    public static SWApiResponse<VehicleDetail> sampleSWApiResponse() {
        SWApiResponse<VehicleDetail> swApiResponse = new SWApiResponse<>();
        SWApiResult<VehicleDetail> vehicleResult = new SWApiResult<>();
        VehicleDetail vehicleDetail = new VehicleDetail();
        vehicleResult.setProperties(vehicleDetail);
        swApiResponse.setResult(vehicleResult);

        return swApiResponse;
    }
}
