package com.jbolivar.autofinanzas.services;

import com.jbolivar.autofinanzas.models.Cuenta;
import com.jbolivar.autofinanzas.repository.CuentaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class CuentaService {
    private final CuentaRepository repository;
    private final Logger LOGGER = LoggerFactory.getLogger(CuentaService.class);

    public CuentaService(CuentaRepository repository) {
        this.repository = repository;
    }

    public Flux<Cuenta> findAll() {
        return repository.findAll();
    }

    public Mono<Cuenta> findByNumero(String numero) {
        return repository.findByNumeroContaining(numero).onErrorResume(throwable -> {
            LOGGER.error("Error al consultar la cuenta con número:" + numero, throwable);
            return Mono.empty();
        }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cuenta con número = " + numero + " no encontrada")));
    }

    public Flux<Cuenta> findByIdUsuario(Integer id) {
        return repository.findByIdUsuario(id).onErrorResume(throwable -> {
            LOGGER.error("Error al consultar la cuenta del usuario con id:" + id, throwable);
            return Mono.empty();
        }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cuenta del usuario con id = " + id + " no encontrada")));

    }

    public Flux<Cuenta> findByTipo(String tipo) {
        return repository.findByTipoContaining(tipo);
    }

    public Mono<Cuenta> save(Cuenta cuenta) {
        if (validarCrearActualizarCuenta(cuenta)) {
            return validarCuentaExistente(cuenta)
                    .flatMap(cuentaExistente -> {
                        if (!cuentaExistente) {
                            return repository.save(cuenta).onErrorResume(throwable -> {
                                LOGGER.error("Error al crear la cuenta con número: " + cuenta.getNumero(), throwable);
                                return Mono.empty();
                            }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Cuenta con número = " + cuenta.getNumero() + " no registrada")));
                        } else {
                            return Mono.empty();
                        }
                    });
        }
        return Mono.empty();
    }

    private boolean validarCrearActualizarCuenta(Cuenta cuenta) {
        return !cuenta.getNumero().isEmpty() && !(cuenta.getIdUsuario() == null);
    }

    private Mono<Boolean> validarCuentaExistente(Cuenta cuenta) {
        return repository.existsByNumero(cuenta.getNumero());
    }

    public Mono<Void> deleteByNumero(String numero) {
        return repository.existsByNumero(numero).flatMap(cuenta -> repository.deleteByNumero(numero).then())
                .onErrorResume(throwable -> {
                    LOGGER.error("Error al intentar eliminar la cuenta con número = " + numero, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Error al intenter eliminar una cuenta").getMostSpecificCause()));
    }

    public Mono<Cuenta> update(Integer id, Cuenta cuenta){
        return repository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(auxCuenta -> {
                    if(auxCuenta.isPresent()){
                        cuenta.setId(id);
                        cuenta.setIdUsuario(cuenta.getIdUsuario());
                        cuenta.setNumero(auxCuenta.get().getNumero());
                        cuenta.setTipo(auxCuenta.get().getTipo());
                        cuenta.setMontoInicial((auxCuenta.get().getMontoInicial() == null? 0D: cuenta.getMontoInicial()));
                    }

                    return repository.save(cuenta)
                            .onErrorResume(throwable -> {
                                LOGGER.error("Error al intentar actualizar la cuenta " + cuenta, throwable);
                                return Mono.empty();
                            }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Cuenta = "+cuenta+" no actualizada").getMostSpecificCause()));
                });
    }
}
