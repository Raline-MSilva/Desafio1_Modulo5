package br.com.zup.GerenciadorDeContas;

import br.com.zup.GerenciadorDeContas.enums.Status;
import br.com.zup.GerenciadorDeContas.enums.Tipo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ContaRepository extends CrudRepository<Conta, Integer> {
    List<Conta> findAllByStatus(Status status);
    List<Conta> findAllByTipo(Tipo tipo);

    @Query(value = "SELECT * FROM contas WHERE VALOR BETWEEN :valor*0.5 AND :valor*1.2", nativeQuery = true)
    List<Conta> findAllByValor(double valor);

}
