package com.awesome.thesis.logic.application.service.files;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.awesome.thesis.controller.advice.ErrorHandler;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

class ErrorHandlerTest {

  @Test
  void handleExceptionWirft() throws Exception {
    ErrorHandler errorHandler = new ErrorHandler();
    MaxUploadSizeExceededException m = new MaxUploadSizeExceededException(10000000);
    Model model = new ConcurrentModel();

    String view = errorHandler.handleException(m, model);

    assertEquals("upload", view);
    assertEquals("Maximale Dateigröße überschritten: max. 10MB",
        model.getAttribute("maxUploadError"));
  }
}