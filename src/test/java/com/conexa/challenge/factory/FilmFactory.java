package com.conexa.challenge.factory;

import com.conexa.challenge.model.Film;
import com.conexa.challenge.model.SWApiResponse;
import com.conexa.challenge.model.SWApiResponseList;
import com.conexa.challenge.model.SWApiResult;

import java.util.ArrayList;
import java.util.Collections;

public class FilmFactory {

    public static SWApiResponseList<Film> sampleSWApiResponseList() {
        SWApiResponseList<Film> swApiResponseList = new SWApiResponseList<>();
        SWApiResult<Film> filmResult = new SWApiResult<>();
        Film film = new Film();
        film.setCharacters(new ArrayList<>());
        film.setStarships(new ArrayList<>());
        film.setVehicles(new ArrayList<>());
        filmResult.setProperties(film);
        swApiResponseList.setResult(Collections.singletonList(filmResult));

        return swApiResponseList;
    }

    public static SWApiResponse<Film> sampleSWApiResponse() {
        SWApiResponse<Film> swApiResponse = new SWApiResponse<>();
        SWApiResult<Film> filmResult = new SWApiResult<>();
        Film film = new Film();
        film.setCharacters(new ArrayList<>());
        film.setStarships(new ArrayList<>());
        film.setVehicles(new ArrayList<>());
        filmResult.setProperties(film);
        swApiResponse.setResult(filmResult);

        return swApiResponse;
    }
}
