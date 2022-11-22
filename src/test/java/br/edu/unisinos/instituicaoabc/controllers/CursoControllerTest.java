package br.edu.unisinos.instituicaoabc.controllers;

import br.edu.unisinos.instituicaoabc.services.CursoService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CursoControllerTest {

    @Mock
    private CursoService cursoService;

    private CursoController cursoController;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        this.cursoController = BDDMockito.spy(new CursoController(cursoService));
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }


}