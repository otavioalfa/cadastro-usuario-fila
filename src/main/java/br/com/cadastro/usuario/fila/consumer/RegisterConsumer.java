package br.com.cadastro.usuario.fila.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface RegisterConsumer {

	void listener(ConsumerRecord<String, Object> consumerRecord);
	
}
