package com.hemtest.webserver.configurations;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cache Configuration Class
 *
 * @author anton lekedal (Doldas)
 *
 */
@Configuration
@EnableCaching
public class CacheConfig {
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("count"); // caching for /count requests
	}
}
