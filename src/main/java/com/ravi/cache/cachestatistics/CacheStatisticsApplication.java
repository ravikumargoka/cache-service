package com.ravi.cache.cachestatistics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URI;

@SpringBootApplication
@Slf4j
public class CacheStatisticsApplication {

	@Value("classpath:ehcache.xml")
	private Resource cacheResource;

	public static void main(String[] args) {
		SpringApplication.run(CacheStatisticsApplication.class, args);
	}

	@Bean(name="cacheManager")
	public CacheManager cacheManager() {
		CacheManager cacheManager = null;
		try {
			URI uri = cacheResource.getURI();
			CachingProvider cachingProvider = Caching.getCachingProvider();
			cacheManager = cachingProvider.getCacheManager(uri, getClass().getClassLoader());
		}
		catch(Exception ioe){
			log.error("ERROR :: Error occurred while initializing cache manager. Cannot load the ehcache.xml file.", ioe);
		}
		return cacheManager;
	}
}

