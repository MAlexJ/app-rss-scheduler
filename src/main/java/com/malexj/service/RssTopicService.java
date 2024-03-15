package com.malexj.service;

import com.malexj.repository.RssTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RssTopicService {

  private final RssTopicRepository rssTopicRepository;
}
