package com.hemtest.webserver.configurations;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Async Tasks Configuration Class
 *
 * @author anton lekedal (Doldas)
 *
 */
@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {
	@Value("${async.max-poolsize:12}")
	private Integer maxPoolSize;
	@Value("${async.core-poolsize:3}")
	private Integer corePoolSize;

	@Bean(name = "wordexecutioner")
	public Executor wordTaskExecutioner() {
		ThreadPoolTaskExecutor execPool = new ThreadPoolTaskExecutor();
		execPool.setCorePoolSize(corePoolSize);
		execPool.setMaxPoolSize(maxPoolSize);
		execPool.setThreadNamePrefix("WordTaskThread-");
		execPool.initialize();
		return execPool;
	}
}
