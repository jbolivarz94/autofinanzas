package com.jbolivar.autofinanzas.services;

import com.jbolivar.autofinanzas.models.Usuario;
import com.jbolivar.autofinanzas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Flux<Usuario> findAll() { return repository.findAll(); }

    public Flux<Usuario> findByNombre(String nombre){
        return repository.findByNombreContaining(nombre);
    }

    public Mono<Usuario> findById(Integer id){ return repository.findById(id); }

    public Mono<Usuario> save(Usuario usuario){
        if(validacionCreacionActualizacionUsuario(usuario)){
            return repository.save(usuario);
        }
        return Mono.empty();
    }

    public Mono<Void> deleteById(Integer id){
        return repository.findById(id)
                .flatMap(usuario -> repository.deleteById(usuario.getId()).then());
    }

    public Mono<Void> deleteAll(){ return repository.deleteAll(); }

    private boolean validacionCreacionActualizacionUsuario(Usuario usuario) {
        return !usuario.getIdentificacion().isEmpty() &&
                !usuario.getNombre().isEmpty() && !usuario.getApellido().isEmpty();
    }
}
