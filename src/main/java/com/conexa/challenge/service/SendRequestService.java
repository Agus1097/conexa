package com.conexa.challenge.service;

import com.conexa.challenge.exception.InternalServerErrorException;
import com.conexa.challenge.exception.NotFoundException;
import com.conexa.challenge.model.SWApiResponsePaged;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SendRequestService {

    @Value("${API_URL}")
    private String apiUrl;
    private final RestTemplate restTemplate;
    private final Map<Resource, String> baseResources = new EnumMap<>(Resource.class);

    public SendRequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        baseResources.put(Resource.FILM, "films");
        baseResources.put(Resource.PEOPLE, "people");
        baseResources.put(Resource.VEHICLE, "vehicles");
        baseResources.put(Resource.STARSHIP, "starships");
    }

    /**
     * Este método devuelve una entidad genérica
     * @param resource el tipo de elemento definido por un enum(FILM, PEOPLE,etc)
     * @param typeResponse define qué tipo de entidad quiero traer
     * @return el tipo de entidad genérico
     */
    public <T> T getPagedEntity(Resource resource, ParameterizedTypeReference<T> typeResponse) {
        return sendRequest("", resource, typeResponse);
    }

    /**
     * Método que devuelve una entidad genérica de forma paginada
     * @param page cantidad de páginas a devolver
     * @param limit cantidad de elementos por página
     * @param resource el tipo de elemento definido por un enum(FILM, PEOPLE,etc)
     * @param typeResponse define qué tipo de entidad quiero traer
     * @return de forma paginada el tipo de entidad de acuerdo al typeResponse que le enviamos
     */
    public <T> T getPagedEntity(int page, int limit, Resource resource, ParameterizedTypeReference<T> typeResponse) {
        String uri = String.format("?page=%d&limit=%d", page, limit);
        T entity = sendRequest(uri, resource, typeResponse);
        if (Objects.nonNull(entity)) {
            replaceUrl(entity);
        }
        return entity;
    }

    /**
     * Este método devuelve una entidad de acuerdo al id proporcionado
     * @param resource el tipo de elemento definido por un enum(FILM, PEOPLE, etc)
     * @param id el id correspondiente a la entidad que buscamos
     * @param typeResponse define qué tipo de entidad quiero traer
     * @return la entidad de acuerdo al typeResponse que le enviamos
     */
    public <T> T getEntityBy(Resource resource, int id, ParameterizedTypeReference<T> typeResponse) {
        String uri = String.format("/%d", id);
        return sendRequest(uri, resource, typeResponse);
    }

    /**
     * Este método devuelve una entidad de acuerdo al nombre proporcionado
     * @param resource el tipo de elemento definido por un enum(FILM, PEOPLE, etc)
     * @param name El nombre de
     * @param typeResponse define qué tipo de entidad quiero traer
     * @return la entidad completa dependiendo el typeResponse que enviamos
     */
    public <T> T getEntityName(Resource resource, String name, ParameterizedTypeReference<T> typeResponse) {
        String uri = String.format("/?name=%s", name);
        return sendRequest(uri, resource, typeResponse);
    }

    /**
     * Este método devuelve una entidad de la api de Star Wars. En caso de que devuelva 200 se retorna correctamente
     * y en caso contrario se tira una excepción
     * @param uri son los parámetros que se le envían en la url
     * @param resource el tipo de elemento definido por un enum(FILM, PEOPLE, etc)
     * @param typeResponse define qué tipo de entidad quiero traer
     * @return la entidad completa dependiendo el typeResponse que enviamos
     */
    protected <T> T sendRequest(String uri, Resource resource, ParameterizedTypeReference<T> typeResponse) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0");

            String baseResource = getBaseResource(resource);

            String url = String.format("%s/%s%s", apiUrl, baseResource, uri);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    typeResponse
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                throw new NotFoundException(String.format("%s not found", resource.name()));
            }
            throw new InternalServerErrorException(String.format("Server error when showing %s", resource.name()));
        }
    }

    /**
     * Este método arma la url completa que se le envía a la api de Star Wars
     * @param resource el tipo de elmento que está definido por un enum(FILM, PEOPLE, etc)
     * @return la url ya armada
     */
    private String getBaseResource(Resource resource) {
        return baseResources.get(resource);
    }

    /**
     * Este método reemplaza la URL de la Api de Star Wars por la que vamos a exponer en la api actual del
     * challenge
     * @param entity es una entidad genérica
     */
    private <T> void replaceUrl(T entity) {
        if (entity instanceof SWApiResponsePaged) {
            ((SWApiResponsePaged<?>) entity).replaceUrl();
        }
    }

    public enum Resource {
        FILM,
        PEOPLE,
        STARSHIP,
        VEHICLE
    }
}
