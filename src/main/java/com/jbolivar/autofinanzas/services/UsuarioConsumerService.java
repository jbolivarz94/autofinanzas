package com.jbolivar.autofinanzas.services;

import com.jbolivar.autofinanzas.config.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class UsuarioConsumerService {

    private final Logger LOGGER = LoggerFactory.getLogger(UsuarioConsumerService.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public UsuarioConsumerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String obtenerNombreUltimoUsuario(String topico){
        ConsumerRecord<String, String> ultimoUsuario;
        KafkaConfig kafkaConfig = new KafkaConfig();
        kafkaTemplate.setConsumerFactory(kafkaConfig.consumerFactory());
        ultimoUsuario = kafkaTemplate.receive(topico, 0, 1);
        String usuarioRecibido = Objects.requireNonNull(ultimoUsuario.value());
        LOGGER.info("Nombre del ultimo usuario encontrado "+ Objects.requireNonNull(ultimoUsuario).value());
        return usuarioRecibido;
    }
}
