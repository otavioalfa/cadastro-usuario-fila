package br.com.cadastro.usuario.fila.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfiguration {

	@Value("${thread.poll.size}")
	private Integer poolSize;

	@Bean
	public Executor asyncThreadPool() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(poolSize);
		executor.setMaxPoolSize(Integer.MAX_VALUE);
		executor.setQueueCapacity(Integer.MAX_VALUE);
		executor.initialize();
		return executor;
	}

}
