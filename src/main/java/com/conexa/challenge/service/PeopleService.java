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

    /**
     * Este método devuelve todas las personas
     * @param page la cantidad de páginas
     * @param limit cantidad de personas por página
     * @return de forma paginada la cantidad existente de personas a través de una entidad parametrizada
     */
    public SWApiResponsePaged<People> getPagedPeople(int page, int limit) {
        return sendRequestService.getPagedEntity(page, limit, PEOPLE, new ParameterizedTypeReference<SWApiResponsePaged<People>>() {
        });
    }

    /**
     * Este método devuelve una persona en particular por id
     * @param id el id correspondiente a la persona que buscamos
     * @return de forma parametrizada una entidad de Persona con ciertas características(color de ojos, altura, entre otros)
     */
    public SWApiResponse<PeopleDetail> getPeopleById(int id) {
        return sendRequestService.getEntityBy(PEOPLE, id, new ParameterizedTypeReference<SWApiResponse<PeopleDetail>>() {
        });
    }

    /**
     * Este método devuelve una lista de personas a través de una entidad parametrizada con sus características
     * @param name el nombre correspondiente a la persona que buscamos(filtra por las letras que contiene)
     * @return de forma paramtrizada una lista de entidades Persona con ciertas características(color de ojos, altura, entre otros)
     */
    public SWApiResponseList<PeopleDetail> getPeopleByName(String name) {
        return sendRequestService.getEntityName(PEOPLE, name, new ParameterizedTypeReference<SWApiResponseList<PeopleDetail>>() {
        });
    }
}
