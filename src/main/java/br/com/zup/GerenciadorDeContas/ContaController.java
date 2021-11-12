package br.com.zup.GerenciadorDeContas;

import br.com.zup.GerenciadorDeContas.dtos.AtualizarStatusDTO;
import br.com.zup.GerenciadorDeContas.dtos.ContaEntradaDTO;
import br.com.zup.GerenciadorDeContas.dtos.ContaSaidaDTO;
import br.com.zup.GerenciadorDeContas.dtos.ResumoContaDTO;
import br.com.zup.GerenciadorDeContas.enums.Status;
import br.com.zup.GerenciadorDeContas.enums.Tipo;
import br.com.zup.GerenciadorDeContas.excecoes.StatusInvalidoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/contas")
public class ContaController {
    @Autowired
    private ContaService contaService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaSaidaDTO cadastrar(@RequestBody @Valid ContaEntradaDTO contaEntradaDTO){
        Conta conta1 = modelMapper.map(contaEntradaDTO, Conta.class);
        return modelMapper.map(contaService.salvar(conta1), ContaSaidaDTO.class);
    }

    @GetMapping
    public List<ResumoContaDTO> mostrarContasCadastradas (@RequestParam (required = false) Status status,
                                                          @RequestParam (required = false) Tipo tipo,
                                                          @RequestParam (required = false) Double valor){
        List<ResumoContaDTO> resumoDTO = new ArrayList<>();
        for (Conta contas : contaService.listarContasCadastradas(status, tipo, valor)){
            ResumoContaDTO resumoContaDTO = modelMapper.map(contas, ResumoContaDTO.class);
            resumoDTO.add(resumoContaDTO);
        }
        return resumoDTO;
    }

    @PutMapping("/{id}")
    public ContaSaidaDTO atualizar (@PathVariable int id, @RequestBody AtualizarStatusDTO statusDTO){
        if (statusDTO.getStatus() == Status.PAGO) {
            return modelMapper.map(contaService.atualizarStatusDoPagamento(id), ContaSaidaDTO.class);
        }
        throw new StatusInvalidoException("Status inválido");
    }

    @GetMapping("/{id}")
    public ContaSaidaDTO exibirContaEspecifico(@PathVariable int id){
        Conta conta = contaService.buscarId(id);
        return modelMapper.map(conta, ContaSaidaDTO.class);
    }

    @DeleteMapping("{id}")
    public void deletarConta (@PathVariable int id){
        contaService.deletarConta(id);
    }
}
