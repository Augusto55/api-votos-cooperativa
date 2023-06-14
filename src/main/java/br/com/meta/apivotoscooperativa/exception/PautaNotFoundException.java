package br.com.meta.apivotoscooperativa.exception;

public class PautaNotFoundException extends RuntimeException {
    public PautaNotFoundException(String message) {
        super(message);
    }
}
