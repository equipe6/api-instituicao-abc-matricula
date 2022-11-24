package br.edu.unisinos.instituicaoabc.repositories;

import br.edu.unisinos.instituicaoabc.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe repository reponsavel pela persistencia da entidade Pessoa.
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findByCpf(String cpf);

    List<Pessoa> findByNomeLikeIgnoreCase(String nome);

    List<Pessoa> findByEmailLikeIgnoreCase(String nome);

}
