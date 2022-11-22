package br.edu.unisinos.instituicaoabc.controllers;

import br.edu.unisinos.instituicaoabc.services.PessoaService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {


    @Mock
    private PessoaService pessoaService;

    private PessoaController pessoaController;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        this.pessoaController = BDDMockito.spy(new PessoaController(pessoaService));
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

}