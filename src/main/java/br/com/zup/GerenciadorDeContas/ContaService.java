package br.com.zup.GerenciadorDeContas;

import br.com.zup.GerenciadorDeContas.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta salvar(Conta conta) {
        conta.setDataDePagamento(null);
        LocalDate dataAtual = LocalDate.now();
        if (conta.getDataDevencimento().isBefore(dataAtual)){
            conta.setStatus(Status.VENCIDA);
        }else {
            conta.setStatus(Status.AGUARDANDO);
        }
        return contaRepository.save(conta);
    }
}
