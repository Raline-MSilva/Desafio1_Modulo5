package br.com.zup.GerenciadorDeContas.excecoes;

public class TipoEnumInvalido extends RuntimeException{
    public TipoEnumInvalido(String message) {
        super(message);
    }
}
