package br.edu.unisinos.instituicaoabc.services;

import br.edu.unisinos.instituicaoabc.entities.Pessoa;
import br.edu.unisinos.instituicaoabc.repositories.PessoaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    private PessoaService pessoaService;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        this.pessoaService = BDDMockito.spy(new PessoaService(pessoaRepository));
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

        given(this.pessoaRepository.findById(id)).willReturn(Optional.of(new Pessoa()));

        var result = this.pessoaService.findById(id);
        assertNotNull(result);
    }

    @Test
    void testFindAll() throws Exception {
        var result = this.pessoaService.findAll();
        assertNotNull(result);
    }

    @Test
    void testCreate() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("0765456787");
        pessoa.setNome("Fulano de Tal");
        pessoa.setEmail("fulano@gmail.com");
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setTelefone("5132498030");
        pessoa.setEndereco("Rua onde o vento faz a curva");
        pessoa.setNumeroEndereco(1234L);
        pessoa.setBairro("Te arremanga e vem");
        pessoa.setCep(91920530L);
        pessoa.setCidade("Porto Alegre");
        pessoa.setEstado("RS");

        given(this.pessoaRepository.save(any(Pessoa.class))).willReturn(pessoa);

        var result = this.pessoaService.create(pessoa);
        assertNotNull(result);
        assertEquals(result.getCpf(), pessoa.getCpf());
        assertEquals(result.getNome(), pessoa.getNome());
        assertEquals(result.getEmail(), pessoa.getEmail());
        assertEquals(result.getDataNascimento(), pessoa.getDataNascimento());
        assertEquals(result.getTelefone(), pessoa.getTelefone());
        assertEquals(result.getEndereco(), pessoa.getEndereco());
        assertEquals(result.getNumeroEndereco(), pessoa.getNumeroEndereco());
        assertEquals(result.getBairro(), pessoa.getBairro());
        assertEquals(result.getCep(), pessoa.getCep());
        assertEquals(result.getCidade(), pessoa.getCidade());
        assertEquals(result.getEstado(), pessoa.getEstado());
    }

    @Test
    void testUpdate() throws Exception {
        Pessoa pessoa = new Pessoa();
        Long id = 10L;
        pessoa.setCpf("0765456787");
        pessoa.setNome("Fulano de Tal");
        pessoa.setEmail("fulano@gmail.com");
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setTelefone("5132498030");
        pessoa.setEndereco("Rua onde o vento faz a curva");
        pessoa.setNumeroEndereco(1234L);
        pessoa.setBairro("Te arremanga e vem");
        pessoa.setCep(91920530L);
        pessoa.setCidade("Porto Alegre");
        pessoa.setEstado("RS");

        given(this.pessoaRepository.findById(id)).willReturn(Optional.of(pessoa));

        given(this.pessoaRepository.save(any(Pessoa.class))).willReturn(pessoa);

        var result = this.pessoaService.update(pessoa, id);
        assertNotNull(result);
        assertEquals(result.getId(), pessoa.getId());
        assertEquals(result.getCpf(), pessoa.getCpf());
        assertEquals(result.getNome(), pessoa.getNome());
        assertEquals(result.getEmail(), pessoa.getEmail());
        assertEquals(result.getDataNascimento(), pessoa.getDataNascimento());
        assertEquals(result.getTelefone(), pessoa.getTelefone());
        assertEquals(result.getEndereco(), pessoa.getEndereco());
        assertEquals(result.getNumeroEndereco(), pessoa.getNumeroEndereco());
        assertEquals(result.getBairro(), pessoa.getBairro());
        assertEquals(result.getCep(), pessoa.getCep());
        assertEquals(result.getCidade(), pessoa.getCidade());
        assertEquals(result.getEstado(), pessoa.getEstado());
    }

    @Test
    void testDelete() throws Exception {
        Pessoa pessoa = new Pessoa();

        this.pessoaService.delete(pessoa);
        assertNotNull(pessoa);
    }

    @Test
    void testDeleteById() throws Exception {
        Long id = 10L;

        this.pessoaService.delete(id);
        assertNotNull(id);
    }

    @Test
    void testFindByCpf() throws Exception {
        String nome = "01345674023";

        given(this.pessoaRepository.findByCpf(nome)).willReturn(new Pessoa());

        var result = this.pessoaService.findByCpf(nome);
        assertNotNull(result);
    }

    @Test
    void testFindByNome() throws Exception {
        String nome = "Fulano de Tal";

        given(this.pessoaRepository.findByNomeLikeIgnoreCase(nome)).willReturn(new ArrayList<>());

        var result = this.pessoaService.findByNome(nome);
        assertNotNull(result);
    }

    @Test
    void testFindByEmail() throws Exception {
        String nome = "fulano@gmail.com";

        given(this.pessoaRepository.findByEmailLikeIgnoreCase(nome)).willReturn(new ArrayList<>());

        var result = this.pessoaService.findByEmail(nome);
        assertNotNull(result);
    }

    @Test
    void testCount() throws Exception {
        given(this.pessoaRepository.count()).willReturn(10L);

        var result = this.pessoaService.count();
        assertNotNull(result);
        assertEquals(10L, result);
    }

}