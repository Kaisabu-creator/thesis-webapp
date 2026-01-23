package com.awesome.thesis.logic.application.service.html;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

/**
 * Übersetzt Markdown String zu Html.
 */
@Service
public class HtmlService {

  /**
   * Methode, die Markdown zu Html übersetzt.
   *
   * @param markdown Die Eingabe
   * @return Html-String.
   */
  public String markdownToHtml(String markdown) {
    Parser parser = Parser.builder().build();
    Node document = parser.parse(markdown);
    String html = HtmlRenderer.builder().build().render(document);
    Document doc = Jsoup.parseBodyFragment(html);
    doc.select("script").remove();
    return doc.body().html();
  }
}
