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

    /**
     * Este método devuelve una lista de vehículos de forma parametrizada con sus características
     * @param id el id correspondiente al vehículo que buscamos
     * @return de forma parametrizada una entidad de Vehículo con ciertas características(tipo de vehículo, longitud, entre otros)
     */
    public SWApiResponse<VehicleDetail> getVehicleById(int id) {
        SWApiResponse<VehicleDetail> vehicleDetail = sendRequestService.getEntityBy(VEHICLE, id, new ParameterizedTypeReference<SWApiResponse<VehicleDetail>>() {
        });
        if (Objects.nonNull(vehicleDetail)) {
            vehicleDetail.getResult().getProperties().replaceUrl();
        }
        return vehicleDetail;
    }

    /**
     * Este método devuelve una lista de vehículos de forma parametrizada con sus características
     * @param name el nombre correspondiente al vehículo que buscamos(filtra por las letras que contiene)
     * @return de forma parametrizada una entidad de Vehículo con ciertas características(tipo de vehículo, longitud, entre otros)
     */
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
