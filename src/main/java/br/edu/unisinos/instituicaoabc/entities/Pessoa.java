package br.edu.unisinos.instituicaoabc.entities;

import br.edu.unisinos.instituicaoabc.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "pessoa", schema = "public")
public class Pessoa extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "pessoa_entity_seq", sequenceName = "pessoa_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_entity_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "numero_endereco", nullable = false)
    private Long numeroEndereco;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cep", nullable = false)
    private Long cep;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Override
    public String toString() {
        return super.toString();
    }
}