package br.com.zup.GerenciadorDeContas;

import br.com.zup.GerenciadorDeContas.dtos.AtualizarStatusDTO;
import br.com.zup.GerenciadorDeContas.dtos.ContaEntradaDTO;
import br.com.zup.GerenciadorDeContas.dtos.ContaSaidaDTO;
import br.com.zup.GerenciadorDeContas.dtos.ResumoContaDTO;
import br.com.zup.GerenciadorDeContas.enums.Status;
import br.com.zup.GerenciadorDeContas.enums.Tipo;
import br.com.zup.GerenciadorDeContas.excecoes.StatusInvalidoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@Api(value = "Gerenciador de contas")
@CrossOrigin(origins = "*")
public class ContaController {
    @Autowired
    private ContaService contaService;
    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Metodo que cadastrar contas")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaSaidaDTO cadastrar(@RequestBody @Valid ContaEntradaDTO contaEntradaDTO){
        Conta conta1 = modelMapper.map(contaEntradaDTO, Conta.class);
        return modelMapper.map(contaService.salvar(conta1), ContaSaidaDTO.class);
    }

    @ApiOperation(value = "Metodo que que permite filtrar as contas por atributo especifico e exibir todas as contas")
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

    @ApiOperation(value = "Metodo que atualiza o status de pagamento da conta")
    @PutMapping("/{id}")
    public ContaSaidaDTO atualizar (@PathVariable int id, @RequestBody AtualizarStatusDTO statusDTO){
        if (statusDTO.getStatus() == Status.PAGO) {
            return modelMapper.map(contaService.atualizarStatusDoPagamento(id), ContaSaidaDTO.class);
        }
        throw new StatusInvalidoException("Status inválido");
    }

    @ApiOperation(value = "Metodo que exibe uma conta especifica atraves do id")
    @GetMapping("/{id}")
    public ContaSaidaDTO exibirContaEspecifico(@PathVariable int id){
        Conta conta = contaService.buscarId(id);
        return modelMapper.map(conta, ContaSaidaDTO.class);
    }

    @ApiOperation(value = "Método que permite deletar uma conta a partir de um id específico")
    @DeleteMapping("{id}")
    public void deletarConta (@PathVariable int id){
        contaService.deletarConta(id);
    }
}
