package com.awesome.thesis.logic.application.service.files;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * Klasse zur Behandlung von eigenen Fehlern.
 */
@ControllerAdvice
public class ErrorHandler {

  /**
   * Methode zur Behandlung von MaxUploadSizeExceededException.
   *
   * @param maxUploadSizeExceededExceptionException Exception für zu große Dateien.
   * @param model                                   Model-Attribut für Fehlermeldung.
   * @return Gibt die upload.html zurück.
   */
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleException(
      MaxUploadSizeExceededException maxUploadSizeExceededExceptionException,
      Model model) {
    model.addAttribute("maxUploadError", "Maximale Dateigröße überschritten: max. 10MB");
    return "upload";
  }
}
