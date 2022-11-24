package br.edu.unisinos.instituicaoabc.entities;

import br.edu.unisinos.instituicaoabc.core.BaseEntity;
import br.edu.unisinos.instituicaoabc.enums.TurnoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Classe que representa a modelo de dados para matricula persistente em banco.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "matricula", schema = "public")
public class Matricula extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "matricula_entity_seq", sequenceName = "matricula_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matricula_entity_seq")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false)
    private Curso curso;

    @Enumerated(EnumType.STRING)
    @Column(name = "turno", nullable = false)
    private TurnoEnum turno;

    @Override
    public String toString() {
        return super.toString();
    }
}
