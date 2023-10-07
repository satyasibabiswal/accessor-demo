package com.accessor.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages ="com.accessor.demo.service")
@EnableCaching
@Configuration
public class CacheConfiguration{
	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		List<Cache> cacheList = new ArrayList<>();
		cacheList.add(new ConcurrentMapCache("masterDataCache"));
		//so like that you can create as many as you want
		cacheManager.setCaches(cacheList);
		return cacheManager;
	}
}
