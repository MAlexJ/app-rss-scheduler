package com.malexj.sheduler;

import com.malexj.webservice.SubscriptionWebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionProcessingScheduler {

  private final SubscriptionWebService subscriptionWebService;

  @Async
  //  @Transactional
  @Scheduled(cron = "${scheduled.subscriptions.processing}")
  public void subscriptionsProcessing() {
    log.info("Start subscriptions processing - {}", Thread.currentThread().getName());
    subscriptionWebService
        .findAllActiveRssSubscriptions()
        .doOnNext(resp -> log.info("Response subscritpions -{}", resp))

        .subscribe();
  }
}
