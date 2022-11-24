package br.edu.unisinos.instituicaoabc.repositories;

import br.edu.unisinos.instituicaoabc.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe repository reponsavel pela persistencia da entidade Matricula.
 */
@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {


}
