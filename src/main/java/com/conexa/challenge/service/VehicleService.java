package com.conexa.challenge.service;

import com.conexa.challenge.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.conexa.challenge.service.SendRequestService.Resource.VEHICLE;

@Service
public class VehicleService {

    private final SendRequestService sendRequestService;

    @Autowired
    public VehicleService(SendRequestService sendRequestService) {
        this.sendRequestService = sendRequestService;
    }

    public SWApiResponsePaged<Vehicle> getPagedVehicles(int page, int limit) {
        return sendRequestService.getPagedEntity(page, limit, VEHICLE, new ParameterizedTypeReference<SWApiResponsePaged<Vehicle>>() {
        });
    }

    public SWApiResponse<VehicleDetail> getVehicleById(int id) {
        SWApiResponse<VehicleDetail> vehicleDetail = sendRequestService.getEntityBy(VEHICLE, id, new ParameterizedTypeReference<SWApiResponse<VehicleDetail>>() {
        });
        if (Objects.nonNull(vehicleDetail)) {
            vehicleDetail.getResult().getProperties().replaceUrl();
        }
        return vehicleDetail;
    }

    public SWApiResponseList<VehicleDetail> getVehicleByName(String name) {
        SWApiResponseList<VehicleDetail> vehicleDetail = sendRequestService.getEntityName(VEHICLE, name, new ParameterizedTypeReference<SWApiResponseList<VehicleDetail>>() {
        });
        if (Objects.nonNull(vehicleDetail)) {
            for (SWApiResult<VehicleDetail> vehicle : vehicleDetail.getResult()) {
                vehicle.getProperties().replaceUrl();
            }
        }
        return vehicleDetail;
    }
}
