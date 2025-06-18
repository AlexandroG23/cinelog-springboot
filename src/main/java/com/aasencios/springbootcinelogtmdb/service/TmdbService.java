package com.aasencios.springbootcinelogtmdb.service;

import com.aasencios.springbootcinelogtmdb.dto.Movie;
import com.aasencios.springbootcinelogtmdb.dto.PopularMoviesResponse;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Collectors;

@Service
public class TmdbService {

    private final String apiKey;

    public TmdbService() {
        Dotenv dotenv = Dotenv.configure().load();
        this.apiKey = dotenv.get("TMDB_API_KEY");
    }

    public String getApiKey() {
        return apiKey;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    // Obtener peliculas populares

    public PopularMoviesResponse getPopularMovies(int page) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.themoviedb.org")
                .path("/3/movie/popular")
                .queryParam("api_key", apiKey)
                .queryParam("language", "es-ES")
                .queryParam("page", page)
                .build()
                .toUriString();

        ResponseEntity<PopularMoviesResponse> response = restTemplate.getForEntity(url, PopularMoviesResponse.class);
        PopularMoviesResponse body = response.getBody();

        if (body != null && body.getResults() != null) {
            body.setResults(body.getResults().stream()
                    .filter(p -> p.getPosterPath() != null)
                    .filter(p -> p.getOverview() != null && !p.getOverview().isBlank())
                    .peek(p -> {
                        String trailerKey = obtenerTrailerYoutubeKey(p.getId());
                        p.setYoutubeTrailerKey(trailerKey);
                    })
                    .collect(Collectors.toList()));
        }

        return body;
    }

    // Buscar peliculas

    public PopularMoviesResponse searchMovies(String query){
        String url = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.themoviedb.org")
                .path("/3/search/movie")
                .queryParam("api_key", apiKey)
                .queryParam("language", "es-ES")
                .queryParam("query", query)
                .build()
                .toUriString();

        ResponseEntity<PopularMoviesResponse> response = restTemplate.getForEntity(url, PopularMoviesResponse.class);
        PopularMoviesResponse body = response.getBody();

        if (body != null && body.getResults() != null) {
            body.setResults(body.getResults().stream()
                    .filter(p -> p.getPosterPath() != null)
                    .filter(p -> p.getOverview() != null && !p.getOverview().isBlank())
                    .peek(p -> {
                        String trailerKey = obtenerTrailerYoutubeKey(p.getId());
                        p.setYoutubeTrailerKey(trailerKey);
                    })
                    .collect(Collectors.toList()));
        }

        return body;
    }

    // Nuevas Peliculas lanzadas recientemente

    public PopularMoviesResponse getNowPlayingMovies(int page) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.themoviedb.org")
                .path("/3/movie/now_playing")
                .queryParam("api_key", apiKey)
                .queryParam("language", "es-ES")
                .queryParam("page", page)
                .build()
                .toUriString();

        ResponseEntity<PopularMoviesResponse> response = restTemplate.getForEntity(url, PopularMoviesResponse.class);
        PopularMoviesResponse body = response.getBody();

        if (body != null && body.getResults() != null) {
            body.setResults(body.getResults().stream()
                    .filter(p -> p.getPosterPath() != null)
                    .filter(p -> p.getOverview() != null && !p.getOverview().isBlank())
                    .peek(p -> {
                        String trailerKey = obtenerTrailerYoutubeKey(p.getId());
                        p.setYoutubeTrailerKey(trailerKey);
                    })
                    .collect(Collectors.toList()));
        }

        return body;
    }

    public String obtenerTrailerYoutubeKey(Long movieId) {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "/videos?api_key=" + apiKey + "&language=es-ES";
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            JsonNode results = response.getBody().get("results");
            if (results != null && results.isArray()) {
                for (JsonNode video : results) {
                    String site = video.get("site").asText();
                    String type = video.get("type").asText();
                    if ("YouTube".equals(site) && "Trailer".equalsIgnoreCase(type)) {
                        return video.get("key").asText(); // este es el ID de YouTube
                    }
                }
            }
        }

        return null; // si no encuentra trailer
    }

    public Movie obtenerDetallesPelicula(Long movieId) {
        String url = "https://api.themoviedb.org/3/movie/" + movieId +
                "?api_key=" + apiKey + "&language=es-ES";

        ResponseEntity<Movie> response = restTemplate.getForEntity(url, Movie.class);
        Movie movie = response.getBody();

        if (movie != null) {
            String trailerKey = obtenerTrailerYoutubeKey(movieId);
            movie.setYoutubeTrailerKey(trailerKey);
        }

        return movie;
    }


}
