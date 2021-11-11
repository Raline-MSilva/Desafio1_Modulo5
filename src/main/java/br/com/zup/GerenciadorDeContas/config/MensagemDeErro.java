package br.com.zup.GerenciadorDeContas.config;

public class MensagemDeErro {
    public String mensagem;

    public MensagemDeErro(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
