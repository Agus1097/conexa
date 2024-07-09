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

    public SWApiResponseList<Film> getPagedFilms() {
        SWApiResponseList<Film> films = sendRequestService.getPagedEntity(FILM, new ParameterizedTypeReference<SWApiResponseList<Film>>() {
        });
        if (Objects.nonNull(films)) {
            for (SWApiResult<Film> film : films.getResult()) {
                film.getProperties().replaceUrl();
            }
        }
        return films;
    }

    public SWApiResponse<Film> getFilmById(int id) {
        SWApiResponse<Film> film = sendRequestService.getEntityBy(FILM, id, new ParameterizedTypeReference<SWApiResponse<Film>>() {
        });
        if (Objects.nonNull(film)) {
            film.getResult().getProperties().replaceUrl();
        }
        return film;
    }
}