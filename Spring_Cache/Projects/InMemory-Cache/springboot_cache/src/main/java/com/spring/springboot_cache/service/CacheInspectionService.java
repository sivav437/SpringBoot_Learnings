package com.spring.springboot_cache.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheInspectionService {
	
	@Autowired
	private CacheManager cacheManager;
	
	public String printCacheContents(String cacheName) throws Exception {
		Cache cache=cacheManager.getCache(cacheName);
		System.out.println(cache.getName());
		System.out.println(cache.getClass()); //ConcurrentMapCache
		System.out.println(cache.getNativeCache().getClass()); //class java.util.concurrent.ConcurrentHashMap
		
//		System.out.println(cache.);
		if(cache != null) {
			System.out.println("Cache Contents: ");
			System.out.println(Objects.requireNonNull(cache.getNativeCache()).toString());
			return Objects.requireNonNull(cache.getNativeCache()).toString();
		}else {
			throw new Exception("No Cache is created for cacheName :"+cacheName);
		}
	}

}
