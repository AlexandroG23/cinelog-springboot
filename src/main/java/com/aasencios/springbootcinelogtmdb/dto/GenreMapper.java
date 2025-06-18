package com.aasencios.springbootcinelogtmdb.dto;

import java.util.HashMap;
import java.util.Map;

public final class GenreMapper {

    private static final Map<Integer, String> genreMap = new HashMap<>();

    static {
        genreMap.put(28, "Acción");
        genreMap.put(12, "Aventura");
        genreMap.put(16, "Animación");
        genreMap.put(35, "Comedia");
        genreMap.put(80, "Crimen");
        genreMap.put(99, "Documental");
        genreMap.put(18, "Drama");
        genreMap.put(10751, "Familiar");
        genreMap.put(14, "Fantasía");
        genreMap.put(36, "Historia");
        genreMap.put(27, "Terror");
        genreMap.put(10402, "Música");
        genreMap.put(9648, "Misterio");
        genreMap.put(10749, "Romance");
        genreMap.put(878, "Ciencia ficción");
        genreMap.put(10770, "Película de TV");
        genreMap.put(53, "Suspenso");
        genreMap.put(10752, "Guerra");
        genreMap.put(37, "Western");
    }

    private GenreMapper() {
        // Clase utilitaria, no se instancia
    }

    public static String getGenreName(Integer genreId) {
        return genreMap.getOrDefault(genreId, "Desconocido");
    }
}
