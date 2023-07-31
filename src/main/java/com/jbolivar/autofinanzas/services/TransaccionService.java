package com.jbolivar.autofinanzas.services;

import com.jbolivar.autofinanzas.models.Transaccion;
import com.jbolivar.autofinanzas.repository.TransaccionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.sql.Date;

@Service
public class TransaccionService {

    private TransaccionRepository repository;
    public TransaccionService(TransaccionRepository repository) { this.repository = repository; }

    public Flux<Transaccion> findByIdCuenta(Integer id){
        return repository.findByIdCuenta(id);
    }

    public Flux<Transaccion> findByDescripcion(String descripcion){
        return repository.findByDescripcionContaining(descripcion);
    }

    public Flux<Transaccion> findByIdCuentaFecha(Integer idCuenta, Date fecha){
        return repository.findByIdCuentaAndFecha(idCuenta, fecha);
    }

}
