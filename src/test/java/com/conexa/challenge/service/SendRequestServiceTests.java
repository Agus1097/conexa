package com.conexa.challenge.service;

import com.conexa.challenge.exception.InternalServerErrorException;
import com.conexa.challenge.exception.NotFoundException;
import com.conexa.challenge.model.People;
import com.conexa.challenge.model.PeopleDetail;
import com.conexa.challenge.model.SWApiResponse;
import com.conexa.challenge.model.SWApiResponsePaged;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.conexa.challenge.service.SendRequestService.Resource.PEOPLE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SendRequestServiceTests {

    @Autowired
    private SendRequestService sendRequestService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void sendRequest_returnResponseEntityBody() {
        ParameterizedTypeReference<SWApiResponse<PeopleDetail>> typeResponse = new ParameterizedTypeReference<SWApiResponse<PeopleDetail>>() {
        };

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), eq(typeResponse)))
                .thenReturn(new ResponseEntity<>(new SWApiResponse<>(), HttpStatus.OK));

        SWApiResponse<PeopleDetail> result = sendRequestService.sendRequest("/1", PEOPLE, typeResponse);

        assertNotNull(result);
    }

    @Test
    void sendRequest_entityNotFound_NotFoundException() {
        ParameterizedTypeReference<SWApiResponse<PeopleDetail>> typeResponse = new ParameterizedTypeReference<SWApiResponse<PeopleDetail>>() {
        };

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), eq(typeResponse)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(NotFoundException.class, () -> sendRequestService.sendRequest("/500", PEOPLE, typeResponse));
    }

    @Test
    void sendRequest_apiError_InternalServerErrorException() {
        ParameterizedTypeReference<SWApiResponse<PeopleDetail>> typeResponse = new ParameterizedTypeReference<SWApiResponse<PeopleDetail>>() {
        };

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), eq(typeResponse)))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(InternalServerErrorException.class, () -> sendRequestService.sendRequest("/500", PEOPLE, typeResponse));
    }

    @Test
    void getPagedEntity_returnEntity() {
        ParameterizedTypeReference<SWApiResponsePaged<People>> typeResponse = new ParameterizedTypeReference<SWApiResponsePaged<People>>() {
        };
        SWApiResponsePaged<People> expected = new SWApiResponsePaged<>();
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), eq(typeResponse)))
                .thenReturn(ResponseEntity.ok(expected));

        SWApiResponsePaged<People> result = sendRequestService.getPagedEntity(1, 10, PEOPLE, typeResponse);
        assertEquals(expected, result);
    }

    @Test
    void getPagedEntity_entityNull_returnNull() {
        ParameterizedTypeReference<SWApiResponsePaged<People>> typeResponse = new ParameterizedTypeReference<SWApiResponsePaged<People>>() {
        };
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), eq(typeResponse)))
                .thenReturn(ResponseEntity.ok(null));

        SWApiResponsePaged<People> result = sendRequestService.getPagedEntity(1, 10, PEOPLE, typeResponse);
        assertNull(result);
    }
}
