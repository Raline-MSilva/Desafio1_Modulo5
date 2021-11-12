package br.com.zup.GerenciadorDeContas;

import br.com.zup.GerenciadorDeContas.enums.Status;
import br.com.zup.GerenciadorDeContas.enums.Tipo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ContaRepository extends CrudRepository<Conta, Integer> {
    List<Conta> findAllByStatus(Status status);
    List<Conta> findAllByTipo(Tipo tipo);

}
