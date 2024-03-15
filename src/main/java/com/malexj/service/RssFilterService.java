package com.malexj.service;

import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class RssFilterService {

  private static final String EMPTY_STRING = " ";

  /** find the occurrence of word within text ignoring case */
  public boolean findOccurrenceWord(String text, String word) {
    if (text == null || word == null) {
      return false;
    }
    String excludePunctuationMarksWormText = text.replaceAll("[\\p{P}\\s]", EMPTY_STRING);
    String[] wordList = excludePunctuationMarksWormText.split(EMPTY_STRING);
    return Arrays.stream(wordList) //
        .filter(s -> !s.isBlank())
        .anyMatch(w -> w.equalsIgnoreCase(word));
  }

  /** find the occurrence of specific phrase within a text */
  public boolean findOccurrencePhrase(String text, String phrase) {
    return text.indexOf(phrase) >= 1;
  }
}
