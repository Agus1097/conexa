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

    /**
     * Este método obtiene de forma paginada una entidad pametrizada de Nave Espaciales
     * @param page la cantidad de páginas
     * @param limit cantidad de naves por página
     * @return de forma paginada las naves espaciales
     */
    public SWApiResponsePaged<Starship> getStarshipsPaged(int page, int limit) {
        return sendRequestService.getPagedEntity(page, limit, STARSHIP, new ParameterizedTypeReference<SWApiResponsePaged<Starship>>() {
        });
    }

    /**
     * Este método obtiene una lista de vehículos de forma parametrizada con sus características
     * @param id el id correspondiente a la nave que buscamos(filtra por las letras que contiene)
     * @return de forma parametrizada una entidad de Nave Espcial con ciertas características(tipo de nave, longitud, entre otros)
     */
    public SWApiResponse<StarshipDetail> getStarshipById(int id) {
        return sendRequestService.getEntityBy(STARSHIP, id, new ParameterizedTypeReference<SWApiResponse<StarshipDetail>>() {
        });
    }

    /**
     * Este método devuelve una lista de naves espaciales de forma parametrizada con sus características
     * @param name el nombre correspondiente a la nave que buscamos(filtra por las letras que contiene)
     * @return de forma parametrizada una lista de entidad Nave Espacial con ciertas características(tipo de nave, longitud, entre otros)
     */
    public SWApiResponseList<StarshipDetail> getStarshipByName(String name) {
        return sendRequestService.getEntityName(STARSHIP, name, new ParameterizedTypeReference<SWApiResponseList<StarshipDetail>>() {
        });
    }
}
