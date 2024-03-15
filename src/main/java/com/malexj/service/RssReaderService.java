package com.malexj.service;

import com.apptasticsoftware.rssreader.RssReader;
import com.malexj.exception.RssReaderException;
import com.malexj.model.RssItem;
import java.io.IOException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RssReaderService {

  /** Read the latest news on Rss url */
  public Flux<RssItem> readFlux(String url) {
    try {
      return Flux.fromStream(
          new RssReader()
              .read(url) //
              .map(RssItem::new));
    } catch (IOException e) {
      String errorMessage = String.format("Error reading RSS by url - %s", url);
      throw new RssReaderException(errorMessage, e);
    }
  }
}
