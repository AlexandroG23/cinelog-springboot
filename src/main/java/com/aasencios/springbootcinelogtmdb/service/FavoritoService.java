package com.aasencios.springbootcinelogtmdb.service;

import com.aasencios.springbootcinelogtmdb.dto.Movie;
import com.aasencios.springbootcinelogtmdb.model.PeliculaFavorita;
import com.aasencios.springbootcinelogtmdb.model.Usuario;
import com.aasencios.springbootcinelogtmdb.repository.PeliculaFavoritaRepository;
import com.aasencios.springbootcinelogtmdb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PeliculaFavoritaRepository favoritoRepository;

    public void agregarFavorito(Long tmdbId, String email, Movie movie){
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));

        boolean yaExiste = favoritoRepository.findByUsuarioAndTmdbId(usuario, tmdbId).isPresent();

        if(!yaExiste){
            PeliculaFavorita fav = new PeliculaFavorita();
            fav.setTmdbId(tmdbId);
            fav.setTitulo(movie.getTitle());
            fav.setPosterPath(movie.getPosterPath());
            fav.setOverview(movie.getOverview());
            fav.setReleaseDate(movie.getReleaseDate());
            fav.setYoutubeTrailerKey(movie.getYoutubeTrailerKey());
            fav.setUsuario(usuario);

            favoritoRepository.save(fav);
        }
    }

    public List<PeliculaFavorita> listarFavoritos(String email){
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();
        return favoritoRepository.findByUsuario(usuario);
    }

    public void eliminarFavorito(Long id, String email){
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();
        PeliculaFavorita favorita = favoritoRepository.findById(id)
                .filter(f -> f.getUsuario().getId().equals(usuario.getId()))
                .orElseThrow();
        favoritoRepository.delete(favorita);
    }
}
