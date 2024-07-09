package com.conexa.challenge.service;

import com.conexa.challenge.factory.FilmFactory;
import com.conexa.challenge.model.Film;
import com.conexa.challenge.model.SWApiResponse;
import com.conexa.challenge.model.SWApiResponseList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilmServiceTests {

    @InjectMocks
    FilmService filmService;

    @Mock
    SendRequestService sendRequestService;

    @Test
    void getPagedFilms_returnFilms() {
        SWApiResponseList<Film> expected = FilmFactory.sampleSWApiResponseList();
        ParameterizedTypeReference<SWApiResponseList<Film>> responseType = new ParameterizedTypeReference<SWApiResponseList<Film>>() {
        };

        when(sendRequestService.getPagedEntity(any(SendRequestService.Resource.class), eq(responseType)))
                .thenReturn(expected);

        SWApiResponseList<Film> result = filmService.getPagedFilms();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void getPagedFilms_FilmsAreNull_returnNull() {
        ParameterizedTypeReference<SWApiResponseList<Film>> responseType = new ParameterizedTypeReference<SWApiResponseList<Film>>() {
        };

        when(sendRequestService.getPagedEntity(any(SendRequestService.Resource.class), eq(responseType)))
                .thenReturn(null);

        SWApiResponseList<Film> result = filmService.getPagedFilms();

        assertNull(result);
    }

    @Test
    void getFilmById_returnFilm() {
        SWApiResponse<Film> expected = FilmFactory.sampleSWApiResponse();
        ParameterizedTypeReference<SWApiResponse<Film>> responseType = new ParameterizedTypeReference<SWApiResponse<Film>>() {
        };

        when(sendRequestService.getEntityBy(any(SendRequestService.Resource.class), anyInt(), eq(responseType)))
                .thenReturn(expected);

        SWApiResponse<Film> result = filmService.getFilmById(1);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void getFilmById_FilmIsNull_returnNull() {
        ParameterizedTypeReference<SWApiResponse<Film>> responseType = new ParameterizedTypeReference<SWApiResponse<Film>>() {
        };

        when(sendRequestService.getEntityBy(any(SendRequestService.Resource.class), anyInt(), eq(responseType)))
                .thenReturn(null);

        SWApiResponse<Film> result = filmService.getFilmById(1);

        assertNull(result);
    }
}
