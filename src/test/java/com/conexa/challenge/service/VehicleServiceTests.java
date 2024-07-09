package com.conexa.challenge.service;

import com.conexa.challenge.factory.VehicleFactory;
import com.conexa.challenge.model.SWApiResponse;
import com.conexa.challenge.model.SWApiResponseList;
import com.conexa.challenge.model.VehicleDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTests {

    @InjectMocks
    VehicleService vehicleService;

    @Mock
    SendRequestService sendRequestService;

    @Test
    void getVehicleById_returnVehicle() {
        SWApiResponse<VehicleDetail> expected = VehicleFactory.sampleSWApiResponse();
        ParameterizedTypeReference<SWApiResponse<VehicleDetail>> responseType = new ParameterizedTypeReference<SWApiResponse<VehicleDetail>>() {
        };

        when(sendRequestService.getEntityBy(any(SendRequestService.Resource.class), anyInt(), eq(responseType)))
                .thenReturn(expected);

        SWApiResponse<VehicleDetail> result = vehicleService.getVehicleById(1);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void getVehicleById_VehicleNull_returnNull() {
        ParameterizedTypeReference<SWApiResponse<VehicleDetail>> responseType = new ParameterizedTypeReference<SWApiResponse<VehicleDetail>>() {
        };

        when(sendRequestService.getEntityBy(any(SendRequestService.Resource.class), anyInt(), eq(responseType)))
                .thenReturn(null);

        SWApiResponse<VehicleDetail> result = vehicleService.getVehicleById(1);

        assertNull(result);
    }

    @Test
    void getVehicleByName_returnVehicle() {
        SWApiResponseList<VehicleDetail> expected = VehicleFactory.sampleSWApiResponseList();
        ParameterizedTypeReference<SWApiResponseList<VehicleDetail>> responseType = new ParameterizedTypeReference<SWApiResponseList<VehicleDetail>>() {
        };

        when(sendRequestService.getEntityName(any(SendRequestService.Resource.class), anyString(), eq(responseType)))
                .thenReturn(expected);
        SWApiResponseList<VehicleDetail> result = vehicleService.getVehicleByName("Cr");

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void getVehicleByName_VehicleNull_returnNull() {
        ParameterizedTypeReference<SWApiResponseList<VehicleDetail>> responseType = new ParameterizedTypeReference<SWApiResponseList<VehicleDetail>>() {
        };

        when(sendRequestService.getEntityName(any(SendRequestService.Resource.class), anyString(), eq(responseType)))
                .thenReturn(null);

        SWApiResponseList<VehicleDetail> result = vehicleService.getVehicleByName("Cr");

        assertNull(result);
    }
}
