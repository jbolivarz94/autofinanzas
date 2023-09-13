package com.jbolivar.autofinanzas.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import com.jbolivar.autofinanzas.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioSQSService {

    private AmazonSQS clientSQS;

    public UsuarioSQSService(AmazonSQS clientSQS) {this.clientSQS = clientSQS;}

    public String createQueue(String queueName){
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        return clientSQS.createQueue(createQueueRequest).getQueueUrl();
    }

    public void publishStandarQueueMessage(String urlQueue, Integer delaySeconds, Usuario usuario){
        Map<String, MessageAttributeValue> atributosMensaje = new HashMap<>();

        atributosMensaje.put("id", new MessageAttributeValue().withStringValue(usuario.getId().toString()).withDataType("Number"));
        atributosMensaje.put("identificacion", new MessageAttributeValue().withStringValue(usuario.getIdentificacion()).withDataType("String"));
        atributosMensaje.put("nombre", new MessageAttributeValue().withStringValue(usuario.getNombre()).withDataType("String"));
        atributosMensaje.put("apellido", new MessageAttributeValue().withStringValue(usuario.getApellido()).withDataType("String"));
        atributosMensaje.put("email", new MessageAttributeValue().withStringValue(usuario.getEmail()).withDataType("String"));
        atributosMensaje.put("telefono", new MessageAttributeValue().withStringValue(usuario.getTelefono()).withDataType("String"));

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(urlQueue)
                .withMessageBody(usuario.getIdentificacion())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(atributosMensaje);

        clientSQS.sendMessage(sendMessageRequest);
    }

    public void publishStandarQueueMessage(String urlQueue, Integer delaySeconds, List<Usuario> usuarios){
        for(Usuario usuario: usuarios){
            publishStandarQueueMessage(urlQueue, delaySeconds, usuario);
        }
    }

    public List<Message> receiveMessageFromQueue(String urlQueue, Integer maxNumberMessages, Integer waitTimeSeconds){
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(urlQueue)
                .withMaxNumberOfMessages(maxNumberMessages)
                .withWaitTimeSeconds(waitTimeSeconds);

        return clientSQS.receiveMessage(receiveMessageRequest).getMessages();
    }
}
