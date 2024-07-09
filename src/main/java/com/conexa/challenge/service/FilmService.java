package com.conexa.challenge.service;

import com.conexa.challenge.model.Film;
import com.conexa.challenge.model.SWApiResponse;
import com.conexa.challenge.model.SWApiResponseList;
import com.conexa.challenge.model.SWApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.conexa.challenge.service.SendRequestService.Resource.FILM;

@Service
public class FilmService {

    private final SendRequestService sendRequestService;

    @Autowired
    public FilmService(SendRequestService sendRequestService) {
        this.sendRequestService = sendRequestService;
    }

    /**
     * Este método devuelve todas las películas existentes
     * @return una lista con todas las personas existentes a través de una entidad parametrizada
     */
    public SWApiResponseList<Film> getFilms() {
        SWApiResponseList<Film> films = sendRequestService.getPagedEntity(FILM, new ParameterizedTypeReference<SWApiResponseList<Film>>() {
        });
        if (Objects.nonNull(films)) {
            for (SWApiResult<Film> film : films.getResult()) {
                film.getProperties().replaceUrl();
            }
        }
        return films;
    }

    /**
     * Este método devuelve una película en particular
     * @param id el Id que deseamos buscar
     * @return la película correspondiente a ese id
     */
    public SWApiResponse<Film> getFilmById(int id) {
        SWApiResponse<Film> film = sendRequestService.getEntityBy(FILM, id, new ParameterizedTypeReference<SWApiResponse<Film>>() {
        });
        if (Objects.nonNull(film)) {
            film.getResult().getProperties().replaceUrl();
        }
        return film;
    }
}