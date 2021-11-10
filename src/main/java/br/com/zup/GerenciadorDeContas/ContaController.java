package br.com.zup.GerenciadorDeContas;

import br.com.zup.GerenciadorDeContas.dtos.ContaEntradaDTO;
import br.com.zup.GerenciadorDeContas.dtos.ContaSaidaDTO;
import br.com.zup.GerenciadorDeContas.dtos.ResumoContaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public ContaSaidaDTO cadastrar(@RequestBody ContaEntradaDTO contaEntradaDTO){
        Conta conta1 = modelMapper.map(contaEntradaDTO, Conta.class);
        return modelMapper.map(contaService.salvar(conta1), ContaSaidaDTO.class);
    }

    @GetMapping
    public List<ResumoContaDTO> mostrarContasCadastradas (){
        List<ResumoContaDTO> resumoDTO = new ArrayList<>();
        for (Conta contas : contaService.listarContasCadastradas()){
            ResumoContaDTO resumoContaDTO = modelMapper.map(contas, ResumoContaDTO.class);
            resumoDTO.add(resumoContaDTO);
        }
        return resumoDTO;
    }
}
