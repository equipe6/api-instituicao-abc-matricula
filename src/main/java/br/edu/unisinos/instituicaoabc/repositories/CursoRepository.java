package br.edu.unisinos.instituicaoabc.repositories;

import br.edu.unisinos.instituicaoabc.entities.Curso;
import br.edu.unisinos.instituicaoabc.enums.TipoCursoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe repository reponsavel pela persistencia da entidade Curso.
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByNomeLikeIgnoreCase(String nome);

    List<Curso> findByTipo(TipoCursoEnum tipo);

}
