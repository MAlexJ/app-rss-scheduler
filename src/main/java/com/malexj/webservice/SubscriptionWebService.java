package com.malexj.webservice;

import com.malexj.model.base.type.SubscriptionType;
import com.malexj.model.response.SubscriptionResponse;
import java.net.URI;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionWebService {

  private final WebClient webClient;

  @Value("${app-subscription-service.endpoint}")
  private String endpoint;

  @Value("${app-subscription-service.url}")
  private String hostname;

  public Flux<SubscriptionResponse> findAllActiveRssSubscriptions() {
    var pathParams = Map.of("type", SubscriptionType.RSS.name(), "active", "true");
    var uri = buildSubscriptionServiceUri(pathParams);
    log.info("HTTP Subscriptions request, url - {}", uri);
    return webClient
        .get()
        .uri(uri)
        .retrieve()
        .bodyToFlux(SubscriptionResponse.class)
        .doOnNext(response -> log.info("HTTP Subscriptions response - {}", response));
  }

  /** GET /v1/subscriptions?type=rss&active=true */
  private URI buildSubscriptionServiceUri(Map<String, String> queryParams) {
    var uriBuilder = UriComponentsBuilder.fromUriString(hostname);
    if (!CollectionUtils.isEmpty(queryParams)) {
      queryParams.forEach(uriBuilder::queryParam);
      uriBuilder.pathSegment();
    }
    return uriBuilder.path(endpoint).build().toUri();
  }
}
