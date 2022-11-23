package br.com.cadastro.usuario.fila.consumer.impl;

import br.com.cadastro.usuario.fila.consumer.RegisterConsumer;
import br.com.cadastro.usuario.fila.service.RegisterService;
import br.com.register.commons.domain.v1.dto.ClientDTO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegisterConsumerImpl implements RegisterConsumer {

    @Autowired
    private RegisterService registerService;

    @Override
    @KafkaListener(
            topics = "Registrar.usuario",
            groupId = "${kafka.consumer-group}",
            concurrency = "${kafka.concurrency.quote-offer}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listener(ConsumerRecord<String, Object> consumerRecord) {

        try {

            ClientDTO clientDTO = new Gson().fromJson(consumerRecord.value().toString(),
                    ClientDTO.class);

            registerService.register(clientDTO);

        } catch (Exception e) {
            log.error("ERRO NO OBJETO RECEBIDO NA FILA: {}", e);
        }
    }

}
