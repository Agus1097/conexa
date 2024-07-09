package com.conexa.challenge.service;

import com.conexa.challenge.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import static com.conexa.challenge.service.SendRequestService.Resource.PEOPLE;

@Service
public class PeopleService {

    private final SendRequestService sendRequestService;

    @Autowired
    public PeopleService(SendRequestService sendRequestService) {
        this.sendRequestService = sendRequestService;
    }

    public SWApiResponsePaged<People> getPagedPeople(int page, int limit) {
        return sendRequestService.getPagedEntity(page, limit, PEOPLE, new ParameterizedTypeReference<SWApiResponsePaged<People>>() {
        });
    }

    public SWApiResponse<PeopleDetail> getPeopleById(int id) {
        return sendRequestService.getEntityBy(PEOPLE, id, new ParameterizedTypeReference<SWApiResponse<PeopleDetail>>() {
        });
    }

    public SWApiResponseList<PeopleDetail> getPeopleByName(String name) {
        return sendRequestService.getEntityName(PEOPLE, name, new ParameterizedTypeReference<SWApiResponseList<PeopleDetail>>() {
        });
    }
}
