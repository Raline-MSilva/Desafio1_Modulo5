package br.com.zup.GerenciadorDeContas.excecoes;

public class TipoEnumInvalidoException extends RuntimeException{
    public TipoEnumInvalidoException(String message) {
        super(message);
    }
}
