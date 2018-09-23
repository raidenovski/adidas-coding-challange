package com.adidas.demo.productreview.scheduler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CacheEventScheduler {

	@Scheduled(cron = "0 0 * * * *")
	@CacheEvict(value = "productReviews", allEntries = true)
	public void purgeCache() {
		log.info("Cache purged");
	}
}
