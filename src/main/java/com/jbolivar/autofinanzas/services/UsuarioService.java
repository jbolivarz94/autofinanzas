package com.jbolivar.autofinanzas.services;

import com.jbolivar.autofinanzas.models.Usuario;
import com.jbolivar.autofinanzas.repository.UsuarioRepository;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Flux<Usuario> findAll() {
        return repository.findAll();
    }

    public Flux<Usuario> findByNombre(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    public Mono<Usuario> findById(Integer id) {
        return repository.findById(id).onErrorResume(throwable -> {
            LOGGER.error("Error al consultar el usuario con id:" + id, throwable);
            return Mono.empty();
        }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Usuario con id = " + id + " no encontrado")));
    }

    public Mono<Usuario> save(Usuario usuario) {
        if (validacionCreacionActualizacionUsuario(usuario)) {
            return repository.save(usuario).onErrorResume(throwable -> {
                LOGGER.error("Error al crear el usuario con identificación: " + usuario.getIdentificacion(), throwable);
                return Mono.empty();
            }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Usuario con id = " + usuario.getIdentificacion() + " no registrado")));
        }
        return Mono.empty();
    }

    public Mono<Usuario> deleteById(Integer id) {
        return repository.findById(id)
                .flatMap(usuario -> repository.deleteById(usuario.getId()).thenReturn(usuario))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error al borrar el Usuario con Id: " + id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuario con Id=" + id + " no borrado").getMostSpecificCause()));
    }

    public Mono<Void> deleteAll() {
        return repository.deleteAll().onErrorResume(throwable -> {
                    LOGGER.error("Error al borrar los Usuarios", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuarios no borrados").getMostSpecificCause()));
    }

    private boolean validacionCreacionActualizacionUsuario(Usuario usuario) {
        return !usuario.getIdentificacion().isEmpty() &&
                !usuario.getNombre().isEmpty() && !usuario.getApellido().isEmpty();
    }
}
