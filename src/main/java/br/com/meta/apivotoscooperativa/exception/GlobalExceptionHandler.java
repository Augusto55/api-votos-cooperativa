package br.com.meta.apivotoscooperativa.exception;

import br.com.meta.apivotoscooperativa.controller.PautaController;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(PautaController.class);

    @ExceptionHandler(PautaBadRequestException.class)
    public ResponseEntity<String> handlePautaBadRequestException(PautaBadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String errorMessage = "Payload enviado invalido, verifique a sintaxe ou campos.";
        if (e.getCause() instanceof InvalidFormatException) {
            errorMessage = "Campo resultadoSessao inserido com valor invalido.";
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        logger.error("Unexpected error: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<String> handleInternalServerError(HttpServerErrorException.InternalServerError e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.NotImplemented.class)
    public ResponseEntity<String> handleNotImplementedError(HttpServerErrorException.NotImplemented e) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(e.getMessage());
    }

    @ExceptionHandler(PautaNotFoundException.class)
    public ResponseEntity<String> handlePautaNotFoundException(PautaNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
