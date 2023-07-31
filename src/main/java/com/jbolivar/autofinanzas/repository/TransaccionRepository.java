package com.jbolivar.autofinanzas.repository;

import com.jbolivar.autofinanzas.models.Transaccion;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.sql.Date;

@Repository
public interface TransaccionRepository extends R2dbcRepository<Transaccion, Integer>{

    Flux<Transaccion> findByDescripcionContaining(String descripcion);

    Flux<Transaccion> findByDescripcionContainingAndFecha(String descripcion, Date fecha);

    Flux<Transaccion> findByIdCuenta(Integer idCuenta);

    Flux<Transaccion> findByIdCuentaAndFecha(Integer idCuenta, Date fecha);
}
