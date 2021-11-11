package br.com.zup.GerenciadorDeContas.excecoes;

public class IdNaoExiste extends RuntimeException{
    public IdNaoExiste(String message) {
        super(message);
    }
}
