package br.com.zup.GerenciadorDeContas;

import br.com.zup.GerenciadorDeContas.enums.Status;
import br.com.zup.GerenciadorDeContas.enums.Tipo;
import br.com.zup.GerenciadorDeContas.excecoes.IdNaoExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<Conta> listarContasCadastradas(Status status){
        if (status != null){
            return contaRepository.findAllByStatus(status);
        }
        List<Conta> contas = (List<Conta>)contaRepository.findAll();
        return contas;
    }

    public Conta buscarId (int id){
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isEmpty()){
            throw new IdNaoExisteException("Id não encontrado");
        }

        return conta.get();
    }

    public Conta atualizarStatusDoPagamento(int id){
        Conta conta = buscarId(id);
        conta.setStatus(Status.PAGO);
        conta.setDataDePagamento(LocalDateTime.now());
        contaRepository.save(conta);

        return conta;
    }
}
