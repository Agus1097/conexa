package com.conexa.challenge.service;

import com.conexa.challenge.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import static com.conexa.challenge.service.SendRequestService.Resource.STARSHIP;

@Service
public class StarshipService {

    private final SendRequestService sendRequestService;

    @Autowired
    public StarshipService(SendRequestService sendRequestService) {
        this.sendRequestService = sendRequestService;
    }

    public SWApiResponsePaged<Starship> getStarshipsPaged(int page, int limit) {
        return sendRequestService.getPagedEntity(page, limit, STARSHIP, new ParameterizedTypeReference<SWApiResponsePaged<Starship>>() {
        });
    }

    public SWApiResponse<StarshipDetail> getStarshipById(int id) {
        return sendRequestService.getEntityBy(STARSHIP, id, new ParameterizedTypeReference<SWApiResponse<StarshipDetail>>() {
        });
    }

    public SWApiResponseList<StarshipDetail> getStarshipByName(String name) {
        return sendRequestService.getEntityName(STARSHIP, name, new ParameterizedTypeReference<SWApiResponseList<StarshipDetail>>() {
        });
    }
}
