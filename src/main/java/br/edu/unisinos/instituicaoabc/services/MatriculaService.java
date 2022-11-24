package br.edu.unisinos.instituicaoabc.services;

import br.edu.unisinos.instituicaoabc.dtos.MatriculaDto;
import br.edu.unisinos.instituicaoabc.entities.Curso;
import br.edu.unisinos.instituicaoabc.entities.Matricula;
import br.edu.unisinos.instituicaoabc.entities.Pessoa;
import br.edu.unisinos.instituicaoabc.repositories.MatriculaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Classe reponsavel pelas regras de negócio para Matrícula.
 */
@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MatriculaService implements Serializable {

    private static final long serialVersionUID = 1L;

    //Injeta MatriculaRepository
    private final transient MatriculaRepository matriculaRepository;

    //Injeta CursoService
    private final transient CursoService cursoService;

    //Injeta PessoaService
    private final transient PessoaService pessoaService;

    //Injeta SqsService
    private final transient SqsService sqsService;

    /**
     * Metodo reponsavel pela criação de matrícula.
     */
    public Matricula matricular(Matricula matricula) {

        //Verifica se matrícula está valida para cadastro.
        if (this.isValidMatricula(matricula)) {
            //Procura pessoa se já existe, senão cria.
            Pessoa pessoa = this.pessoaService.mergeOrCreate(matricula.getPessoa());
            matricula.setPessoa(pessoa);

            //Busca curso por ID para preencher objeto.
            Curso curso = cursoService.findById(matricula.getCurso().getId());
            matricula.setCurso(curso);

            //Salva matrícula no banco.
            matricula = matriculaRepository.save(matricula);

            //Envia para fila de mensageria o objeto matrícula em formato JSON DTO.
            this.sqsService.sendMessage(MatriculaDto.from(matricula));
        }
        return matricula;
    }

    /**
     * Metodo reponsavel por buscar matricula por um determinado ID.
     */
    public Matricula findById(Long id) {
        return this.matriculaRepository.findById(id).orElse(null);
    }

    /**
     * Metodo reponsavel por buscar todos os dados.
     */
    public List<Matricula> findAll() {
        return this.matriculaRepository.findAll();
    }

    /**
     * Metodo reponsavel pela atualização de matrícula.
     */
    public Matricula update(Matricula entity, Long id) {
        Optional<Matricula> entityFind = this.matriculaRepository.findById(id);
        if (entityFind.isEmpty()) {
            return null;
        }
        entity.setId(id);
        Matricula updatedEntity = this.matriculaRepository.save(entity);
        return updatedEntity;
    }

    /**
     * Metodo reponsavel pela exclusao de matricula.
     */
    public void delete(Matricula matricula) {
        this.matriculaRepository.delete(matricula);
    }

    /**
     * Metodo reponsavel pela exclusao de matrícula por um determinado ID.
     */
    public void delete(Long id) {
        this.matriculaRepository.deleteById(id);
    }

    /**
     * Metodo reponsavel pela contabilização de quantos matrículas foram cadastrados.
     */
    public long count() {
        long result = this.matriculaRepository.count();
        return result;
    }

    /**
     * Metodo responsavel para validar se matricula esta valida antes de salvar.
     */
    boolean isValidMatricula(Matricula matricula) {
        var result = matricula != null;
        result = result && matricula.getPessoa() != null;
        result = result && matricula.getPessoa().getCpf() != null;
        result = result && matricula.getCurso() != null;
        return result && matricula.getCurso().getId() != null;
    }

}
