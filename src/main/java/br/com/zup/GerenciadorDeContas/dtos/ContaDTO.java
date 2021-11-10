package br.com.zup.GerenciadorDeContas.dtos;

import br.com.zup.GerenciadorDeContas.enums.Status;
import br.com.zup.GerenciadorDeContas.enums.Tipo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContaDTO {
    private int id;
    private Tipo tipo;
    private String nome;
    private double valor;
    private LocalDate dataDevencimento;
    private LocalDateTime dataDePagamento;
    private Status status;

    public ContaDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
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

    public LocalDate getDataDevencimento() {
        return dataDevencimento;
    }

    public void setDataDevencimento(LocalDate dataDevencimento) {
        this.dataDevencimento = dataDevencimento;
    }

    public LocalDateTime getDataDePagamento() {
        return dataDePagamento;
    }

    public void setDataDePagamento(LocalDateTime dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
