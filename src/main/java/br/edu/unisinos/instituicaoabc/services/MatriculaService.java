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

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MatriculaService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final transient MatriculaRepository matriculaRepository;

    private final transient CursoService cursoService;

    private final transient PessoaService pessoaService;

    private final transient SqsService sqsService;

    boolean isValidMatricula(Matricula matricula) {
        var result = matricula != null;
        result = result && matricula.getPessoa() != null;
        result = result && matricula.getPessoa().getCpf() != null;
        result = result && matricula.getCurso() != null;
        return result && matricula.getCurso().getId() != null;
    }

    public Matricula matricular(Matricula matricula) {
        if (this.isValidMatricula(matricula)) {
            Pessoa pessoa = this.pessoaService.mergeOrCreate(matricula.getPessoa());
            matricula.setPessoa(pessoa);

            Curso curso = cursoService.findById(matricula.getCurso().getId());
            matricula.setCurso(curso);

            matricula = this.save(matricula);

            this.sqsService.sendMessage(MatriculaDto.from(matricula));
        }
        return matricula;
    }

    public Matricula findById(Long id) {
        return this.matriculaRepository.findById(id).orElse(null);
    }

    public List<Matricula> findAll() {
        return this.matriculaRepository.findAll();
    }

    public Matricula save(Matricula matricula) {
        return this.matriculaRepository.save(matricula);
    }

    public void delete(Matricula matricula) {
        this.matriculaRepository.delete(matricula);
    }

    public void delete(Long id) {
        this.matriculaRepository.deleteById(id);
    }

    public long count() {
        long result = this.matriculaRepository.count();
        return result;
    }

}
