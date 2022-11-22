package br.edu.unisinos.instituicaoabc.services;

import br.edu.unisinos.instituicaoabc.entities.Curso;
import br.edu.unisinos.instituicaoabc.entities.Matricula;
import br.edu.unisinos.instituicaoabc.entities.Pessoa;
import br.edu.unisinos.instituicaoabc.enums.TurnoEnum;
import br.edu.unisinos.instituicaoabc.repositories.MatriculaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MatriculaServiceTest {

    @Mock
    private MatriculaRepository matriculaRepository;

    @Mock
    private CursoService cursoService;

    @Mock
    private PessoaService pessoaService;

    @Mock
    private SqsService sqsService;

    private MatriculaService matriculaService;


    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        this.matriculaService = BDDMockito.spy(new MatriculaService(matriculaRepository, cursoService, pessoaService, sqsService));
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }


    @Test
    void testFindById() throws Exception {
        Long id = 10L;

        given(this.matriculaRepository.findById(id)).willReturn(Optional.of(new Matricula()));

        var result = this.matriculaService.findById(id);
        assertNotNull(result);
    }

    @Test
    void testFindAll() throws Exception {
        var result = this.matriculaService.findAll();
        assertNotNull(result);
    }

    @Test
    void testMatricular() throws Exception {
        Matricula matricula = new Matricula();
        matricula.setPessoa(new Pessoa());
        matricula.setCurso(new Curso());
        matricula.setTurno(TurnoEnum.NOITE);

        given(matriculaService.isValidMatricula(matricula)).willReturn(true);
        given(pessoaService.mergeOrCreate(matricula.getPessoa())).willReturn(matricula.getPessoa());
        given(cursoService.findById(matricula.getCurso().getId())).willReturn(matricula.getCurso());
        given(matriculaRepository.save(matricula)).willReturn(matricula);

        var result = this.matriculaService.matricular(matricula);
        assertNotNull(result);
    }

    @Test
    void testDelete() throws Exception {
        Matricula matricula = new Matricula();

        this.matriculaService.delete(matricula);
        assertNotNull(matricula);
    }

    @Test
    void testDeleteById() throws Exception {
        Long id = 10L;

        this.matriculaService.delete(id);
        assertNotNull(id);
    }


    @Test
    void testCount() throws Exception {
        given(this.matriculaRepository.count()).willReturn(10L);

        var result = this.matriculaService.count();
        assertNotNull(result);
        assertEquals(10L, result);
    }

}