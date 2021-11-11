package br.com.zup.GerenciadorDeContas.excecoes;

public class StatusInvalidoException extends RuntimeException{
    public StatusInvalidoException(String message) {
        super(message);
    }
}
