package com.malexj.model.request;

import java.util.Objects;

public record RssRequest(String url) {

  public RssRequest {
    Objects.requireNonNull(url);
  }
}
