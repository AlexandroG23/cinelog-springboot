package com.aasencios.springbootcinelogtmdb.controller;

import com.aasencios.springbootcinelogtmdb.dto.Movie;
import com.aasencios.springbootcinelogtmdb.model.PeliculaFavorita;
import com.aasencios.springbootcinelogtmdb.service.FavoritoService;
import com.aasencios.springbootcinelogtmdb.service.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private TmdbService tmdbService;

    // GET: Listar favoritos del usuario actual

    @GetMapping
    public String listarFavoritos(@AuthenticationPrincipal UserDetails userDetails, Model model){

        if(userDetails == null){ return "redirect:/usuario/login"; }

        List<PeliculaFavorita> favoritos = favoritoService.listarFavoritos(userDetails.getUsername());
        model.addAttribute("favoritos", favoritos);

        return "favoritos";
    }

    // POST: Agregar un pelicula a favoritos

    @PostMapping("/agregar/{tmdbId}")
    public String agregarFavorito(@PathVariable Long tmdbId, @AuthenticationPrincipal UserDetails userDetails){
        if(userDetails == null) { return  "redirect:/usuario/login"; }

        Movie movie = tmdbService.obtenerDetallesPelicula(tmdbId);
        favoritoService.agregarFavorito(tmdbId, userDetails.getUsername(), movie);
        return "redirect:/peliculas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarFavorito(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        if(userDetails == null) { return "redirect:/usuario/login";}

        favoritoService.eliminarFavorito(id, userDetails.getUsername());
        return "redirect:/favoritos";
    }

}
