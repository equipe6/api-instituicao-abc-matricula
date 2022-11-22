package br.edu.unisinos.instituicaoabc.services;

import br.edu.unisinos.instituicaoabc.entities.Curso;
import br.edu.unisinos.instituicaoabc.enums.TipoCursoEnum;
import br.edu.unisinos.instituicaoabc.repositories.CursoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    private CursoService cursoService;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        this.cursoService = BDDMockito.spy(new CursoService(cursoRepository));
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

        given(this.cursoRepository.findById(id)).willReturn(Optional.of(new Curso()));

        var result = this.cursoService.findById(id);
        assertNotNull(result);
    }

    @Test
    void testFindAll() throws Exception {
        var result = this.cursoService.findAll();
        assertNotNull(result);
    }

    @Test
    void testCreate() throws Exception {
        Curso curso = new Curso();
        curso.setNome("Medicina");
        curso.setTipo(TipoCursoEnum.GRADUACAO);

        given(this.cursoRepository.save(any(Curso.class))).willReturn(curso);

        var result = this.cursoService.create(curso);
        assertNotNull(result);
        assertEquals(result.getNome(), curso.getNome());
        assertEquals(result.getTipo(), curso.getTipo());
    }

    @Test
    void testUpdate() throws Exception {
        Curso curso = new Curso();
        Long id = 10L;
        curso.setNome("Odontologia");
        curso.setTipo(TipoCursoEnum.GRADUACAO);
        given(this.cursoRepository.findById(id)).willReturn(Optional.of(curso));

        given(this.cursoRepository.save(any(Curso.class))).willReturn(curso);

        var result = this.cursoService.update(curso, id);
        assertNotNull(result);
    }

    @Test
    void testDelete() throws Exception {
        Curso curso = new Curso();

        this.cursoService.delete(curso);
        assertNotNull(curso);
    }

    @Test
    void testDeleteById() throws Exception {
        Long id = 10L;

        this.cursoService.delete(id);
        assertNotNull(id);
    }

    @Test
    void testFindByNome() throws Exception {
        String nome = "Fulano de Tal";

        given(this.cursoRepository.findByNomeLikeIgnoreCase(nome)).willReturn(new ArrayList<>());

        var result = this.cursoService.findByNome(nome);
        assertNotNull(result);
    }

    @Test
    void testFindByTipo() throws Exception {
        TipoCursoEnum tipoCurso = TipoCursoEnum.GRADUACAO;

        given(this.cursoRepository.findByTipo(tipoCurso)).willReturn(new ArrayList<>());

        var result = this.cursoService.findByTipo(tipoCurso);
        assertNotNull(result);
    }

    @Test
    void testCount() throws Exception {
        given(this.cursoRepository.count()).willReturn(10L);

        var result = this.cursoService.count();
        assertNotNull(result);
        assertEquals(10L, result);
    }

}