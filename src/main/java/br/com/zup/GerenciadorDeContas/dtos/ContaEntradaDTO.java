package br.com.zup.GerenciadorDeContas.dtos;

import br.com.zup.GerenciadorDeContas.enums.Tipo;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ContaEntradaDTO {

    @Size(min = 2, max = 20, message = "Favor digitar um nome entre 2 a 20 caracteres")
    private String nome;
    @DecimalMin(value = "0.01", message = "favor colocar um valor maior que 0")
    private double valor;

    private Tipo tipo;

    private LocalDate dataDeVencimento;

    public ContaEntradaDTO() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }
}
