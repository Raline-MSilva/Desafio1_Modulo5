package br.com.zup.GerenciadorDeContas.excecoes;

public class IdNaoExisteException extends RuntimeException{
    public IdNaoExisteException(String message) {
        super(message);
    }
}
