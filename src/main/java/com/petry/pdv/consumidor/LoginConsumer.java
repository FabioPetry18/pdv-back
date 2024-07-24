package com.petry.pdv.consumidor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class LoginConsumer {
	
	
	
	@KafkaListener(topics = "login-dono-topic", groupId = "dono")
	@RetryableTopic(backoff = @Backoff(value = 15000L),  attempts = "1" , autoCreateTopics = "true", include = Exception.class) 
	////se der um erro do tipo {EXCEPTION} ele pode automaticamente criar um topico como login-dono-topic-1 e depois de {15seg} do erro ele tenta novamente.
	public void consumirLoginDono(String retorno) {
		System.out.println("Consumido : " + retorno);
	}
}
