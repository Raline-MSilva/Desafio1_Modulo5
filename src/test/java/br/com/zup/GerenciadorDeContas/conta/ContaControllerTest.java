package br.com.zup.GerenciadorDeContas.conta;

import br.com.zup.GerenciadorDeContas.Conta;
import br.com.zup.GerenciadorDeContas.ContaController;
import br.com.zup.GerenciadorDeContas.ContaService;
import br.com.zup.GerenciadorDeContas.dtos.ContaEntradaDTO;
import br.com.zup.GerenciadorDeContas.dtos.ContaSaidaDTO;
import br.com.zup.GerenciadorDeContas.enums.Status;
import br.com.zup.GerenciadorDeContas.enums.Tipo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@WebMvcTest(ContaController.class)
public class ContaControllerTest {
    @MockBean
    private ContaService contaService;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    private ContaEntradaDTO contaEntradaDTO;
    private ContaSaidaDTO contaSaidaDTO;
    private Conta conta;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {

        contaEntradaDTO = new ContaEntradaDTO();
        contaEntradaDTO.setNome("saae");
        contaEntradaDTO.setValor(15);
        //contaEntradaDTO.setDataDeVencimento(LocalDate.now().plusDays(1));
        contaEntradaDTO.setTipo(Tipo.AGUA);

        conta = new Conta();
        conta.setId(1);
        conta.setNome("saae");
        conta.setValor(15);
        conta.setDataDevencimento(LocalDate.now().plusDays(1));
        conta.setTipo(Tipo.AGUA);
        conta.setStatus(Status.PAGO);

        contaSaidaDTO = new ContaSaidaDTO();
        contaSaidaDTO.setId(1);
        contaSaidaDTO.setNome("saae");
        contaSaidaDTO.setValor(15);
        contaSaidaDTO.setDataDevencimento(LocalDate.now().plusDays(1));
        contaSaidaDTO.setTipo(Tipo.AGUA);
        contaSaidaDTO.setStatus(Status.PAGO);


    }

    @Test
    public void testarCadastrarUmaConta() throws Exception {
        Mockito.when(modelMapper.map(Mockito.any(ContaEntradaDTO.class), Mockito.any())).thenReturn(conta);
        Mockito.when(contaService.salvar(conta)).thenReturn(conta);
        Mockito.when(modelMapper.map(conta, ContaSaidaDTO.class)).thenReturn(contaSaidaDTO);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(contaEntradaDTO);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.post("/contas")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

}
