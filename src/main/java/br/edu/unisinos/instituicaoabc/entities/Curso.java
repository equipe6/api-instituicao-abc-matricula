package br.edu.unisinos.instituicaoabc.entities;

import br.edu.unisinos.instituicaoabc.core.BaseEntity;
import br.edu.unisinos.instituicaoabc.enums.TipoCursoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Classe que representa a modelo de dados para curso persistente em banco.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "curso", schema = "public")
public class Curso extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "curso_entity_seq", sequenceName = "curso_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curso_entity_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoCursoEnum tipo;

    @Override
    public String toString() {
        return super.toString();
    }
}