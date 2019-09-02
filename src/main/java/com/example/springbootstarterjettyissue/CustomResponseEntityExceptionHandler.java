package com.example.springbootstarterjettyissue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Value("${spring.servlet.multipart.max-file-size}")
    private Long maxAllowedSize;

    @ResponseBody
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxUploadSizeExceededException(final MaxUploadSizeExceededException exception) {
        final String message = "Attempt to upload file with the size exceeded max allowed value = " + maxAllowedSize + " byte(s).";

        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(message);
    }
}
