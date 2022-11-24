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

/**
 * Classe reponsavel pelas regras de negócio para Curso.
 */
@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class CursoService implements Serializable {

    private static final long serialVersionUID = 1L;

    //Injeta CursoRepository
    private final transient CursoRepository cursoRepository;

    /**
     * Metodo reponsavel por buscar curso por um determinado ID.
     */
    public Curso findById(Long id) {
        return this.cursoRepository.findById(id).orElse(null);
    }

    /**
     * Metodo reponsavel por buscar todos os dados.
     */
    public List<Curso> findAll() {
        return this.cursoRepository.findAll();
    }

    /**
     * Metodo reponsavel pela criação de curso.
     */
    public Curso create(Curso curso) {
        return this.cursoRepository.save(curso);
    }

    /**
     * Metodo reponsavel pela atualização de curso.
     */
    public Curso update(Curso entity, Long id) {
        Optional<Curso> entityFind = this.cursoRepository.findById(id);
        if (entityFind.isEmpty()) {
            return null;
        }
        entity.setId(id);
        Curso updatedEntity = this.cursoRepository.save(entity);
        return updatedEntity;
    }

    /**
     * Metodo reponsavel pela exclusao de curso.
     */
    public void delete(Curso curso) {
        this.cursoRepository.delete(curso);
    }

    /**
     * Metodo reponsavel pela exclusao de curso por um determinado ID.
     */
    public void delete(Long id) {
        this.cursoRepository.deleteById(id);
    }

    /**
     * Metodo reponsavel por buscar curso pelo nome.
     */
    public List<Curso> findByNome(String nome) {
        return this.cursoRepository.findByNomeLikeIgnoreCase(nome);
    }

    /**
     * Metodo reponsavel por buscar curso pelo tipo.
     */
    public List<Curso> findByTipo(TipoCursoEnum tipo) {
        return this.cursoRepository.findByTipo(tipo);
    }

    /**
     * Metodo reponsavel pela contabilização de quantos pessoas foram cadastrados.
     */
    public long count() {
        long result = this.cursoRepository.count();
        return result;
    }

}
