package br.edu.unisinos.instituicaoabc.services;

import br.edu.unisinos.instituicaoabc.entities.Curso;
import br.edu.unisinos.instituicaoabc.enums.TipoCursoEnum;
import br.edu.unisinos.instituicaoabc.repositories.CursoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class CursoService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final transient CursoRepository cursoRepository;

    public Curso findById(Long id) {
        return this.cursoRepository.findById(id).orElse(null);
    }

    public List<Curso> findAll() {
        return this.cursoRepository.findAll();
    }

    public Curso create(Curso curso) {
        return this.cursoRepository.save(curso);
    }

    public Curso update(Curso entity, Long id) {
        Optional<Curso> entityFind = this.cursoRepository.findById(id);
        if (entityFind.isEmpty()) {
            return null;
        }
        entity.setId(id);
        Curso updatedEntity = this.cursoRepository.save(entity);
        return updatedEntity;
    }

    public void delete(Curso curso) {
        this.cursoRepository.delete(curso);
    }

    public void delete(Long id) {
        this.cursoRepository.deleteById(id);
    }

    public List<Curso> findByNome(String nome) {
        return this.cursoRepository.findByNomeLikeIgnoreCase(nome);
    }

    public List<Curso> findByTipo(TipoCursoEnum tipo) {
        return this.cursoRepository.findByTipo(tipo);
    }

    public long count() {
        long result = this.cursoRepository.count();
        return result;
    }

}
