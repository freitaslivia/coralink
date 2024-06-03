package br.com.coralink.api.exception;

public class ErroNegocioException extends RuntimeException{

    public ErroNegocioException(String mensagem) {
        super(mensagem);
    }
}