package com.aasencios.springbootcinelogtmdb.repository;

import com.aasencios.springbootcinelogtmdb.model.PeliculaFavorita;
import com.aasencios.springbootcinelogtmdb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeliculaFavoritaRepository extends JpaRepository<PeliculaFavorita, Long> {

    List<PeliculaFavorita> findByUsuario(Usuario usuario);
    Optional<PeliculaFavorita> findByUsuarioAndTmdbId(Usuario usuario, Long tmdbId);
}
