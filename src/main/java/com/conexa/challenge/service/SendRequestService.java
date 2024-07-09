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

    public <T> T getPagedEntity(Resource resource, ParameterizedTypeReference<T> typeResponse) {
        return sendRequest("", resource, typeResponse);
    }

    public <T> T getPagedEntity(int page, int limit, Resource resource, ParameterizedTypeReference<T> typeResponse) {
        String uri = String.format("?page=%d&limit=%d", page, limit);
        T entity = sendRequest(uri, resource, typeResponse);
        if (Objects.nonNull(entity)) {
            replaceUrl(entity);
        }
        return entity;
    }

    public <T> T getEntityBy(Resource resource, int id, ParameterizedTypeReference<T> typeResponse) {
        String uri = String.format("/%d", id);
        return sendRequest(uri, resource, typeResponse);
    }

    public <T> T getEntityName(Resource resource, String name, ParameterizedTypeReference<T> typeResponse) {
        String uri = String.format("/?name=%s", name);
        return sendRequest(uri, resource, typeResponse);
    }

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

    private String getBaseResource(Resource resource) {
        return baseResources.get(resource);
    }

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
