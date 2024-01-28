package com.dutn.be_do_an_vat.exception.advice;

import com.dutn.be_do_an_vat.exception.ErrorRespon;
import com.dutn.be_do_an_vat.exception.FileFormatEcception;
import com.dutn.be_do_an_vat.exception.RoleNotFounndException;
import com.dutn.be_do_an_vat.exception.SanPhamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerGlobalSnack {
    @org.springframework.web.bind.annotation.ExceptionHandler(RoleNotFounndException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity roleNotFound(RoleNotFounndException roleNotFounndException, WebRequest webRequest) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorRespon.builder()
                .message(List.of(roleNotFounndException.getMessage()))
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .description(webRequest.getDescription(false))
                .build()
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(FileFormatEcception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity FileFormat(FileFormatEcception fileFormatEcception, WebRequest webRequest) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorRespon.builder()
                .message(List.of(fileFormatEcception.getMessage()))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .description(webRequest.getDescription(false))
                .build()
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SanPhamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity FileFormat(SanPhamNotFoundException sanPhamNotFoundException, WebRequest webRequest) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorRespon.builder()
                .message(List.of(sanPhamNotFoundException.getMessage()))
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .description(webRequest.getDescription(false))
                .build()
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity IOEcception(IOException ioException, WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorRespon.builder()
                .message(List.of(ioException.getMessage()))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .description(webRequest.getDescription(false))
                .build()
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity RuntimeException(RuntimeException runtimeException, WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorRespon.builder()
                .message(List.of(runtimeException.getMessage()))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .description(webRequest.getDescription(false))
                .build()
        );
    }
}
