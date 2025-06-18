package com.aasencios.springbootcinelogtmdb.controller;

import com.aasencios.springbootcinelogtmdb.dto.PopularMoviesResponse;
import com.aasencios.springbootcinelogtmdb.service.TmdbService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

    private final TmdbService tmdbService;

    public MovieController(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @GetMapping("/peliculas")
    public String mostrarPeliculas(@RequestParam(name = "page", defaultValue = "1") int page, Model model){
        PopularMoviesResponse response = tmdbService.getPopularMovies(page);
        model.addAttribute("peliculas", response.getResults());
        model.addAttribute("paginaActual", page);
        model.addAttribute("totalPaginas", Math.min(response.getTotalPages(), 5));
        return "peliculas"; // Thymeleaf buscara el archivo peliculas.html en la carpeta /templates
    }

    @GetMapping("/buscar")
    public String buscarPelicula(@RequestParam("q") String query, Model model){
        PopularMoviesResponse response = tmdbService.searchMovies(query);

        model.addAttribute("peliculas", response.getResults());
        model.addAttribute("busqueda", query);
        return "peliculas";
    }

    @GetMapping("/")
    public String mostrarInicio(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
        PopularMoviesResponse response = tmdbService.getNowPlayingMovies(page);

        model.addAttribute("peliculas", response.getResults());
        model.addAttribute("paginaActual", page);
        model.addAttribute("totalPaginas", Math.min(response.getTotalPages(), 5));
        model.addAttribute("modo", "inicio"); // Para controlar el título en la vista
        return "peliculas";
    }

}
