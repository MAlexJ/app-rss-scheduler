package com.malexj.sheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RssProcessingScheduler {

  @Async
  //  @Transactional
  @Scheduled(cron = "${scheduled.rss.processing}")
  public void rssProcessing() {
    log.info("Start rss processing - {}", Thread.currentThread().getName());
  }
}
