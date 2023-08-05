package com.jbolivar.autofinanzas.services;

import com.jbolivar.autofinanzas.models.Transaccion;
import com.jbolivar.autofinanzas.repository.TransaccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Date;

@Service
public class TransaccionService {

    private TransaccionRepository repository;
    private final Logger LOGGER = LoggerFactory.getLogger(TransaccionService.class);

    public TransaccionService(TransaccionRepository repository) {
        this.repository = repository;
    }

    public Flux<Transaccion> findByIdCuenta(Integer id) {
        return repository.findByIdCuenta(id).onErrorResume(throwable -> {
            LOGGER.error("Error al consultar la transacción con id:" + id, throwable);
            return Mono.empty();
        }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Transacción con id = " + id + " no encontrado")));
    }

    public Flux<Transaccion> findByDescripcion(String descripcion) {
        return repository.findByDescripcionContaining(descripcion).onErrorResume(throwable -> {
            LOGGER.error("Error al consultar la transacción con descripcion:" + descripcion, throwable);
            return Mono.empty();
        }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Transacción con descripcion = " + descripcion + " no encontrado")));
    }

    public Flux<Transaccion> findByIdCuentaFecha(Integer idCuenta, Date fecha) {
        return repository.findByIdCuentaAndFecha(idCuenta, fecha).onErrorResume(throwable -> {
            LOGGER.error("Error al consultar la transacción con id:" + idCuenta, throwable);
            return Mono.empty();
        }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Transacción con id = " + idCuenta + " no encontrado")));
    }

    public Mono<Transaccion> save(Transaccion transaccion) {
        if (validarTransaccion(transaccion)) {
            return repository.save(transaccion).onErrorResume(throwable -> {
                        LOGGER.error("Error al crear una transaccion: " + transaccion, throwable);
                        return Mono.empty();
                    })
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Transaccion no creada").getMostSpecificCause()));
        }
        return Mono.empty();
    }

    private boolean validarTransaccion(Transaccion transaccion) {
        return transaccion.getMonto() > 0 && transaccion.getIdCuenta() > 0;
    }

    public Mono<Void> deleteById(Integer id) {
        return repository.findById(id).flatMap(transaccion -> repository.deleteById(transaccion.getId()).then())
                .onErrorResume(throwable -> {
                    LOGGER.error("Error al intentar borrar la transacción con id = " + id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Error al intentar borrar la transaccion").getMostSpecificCause()));
    }
}
