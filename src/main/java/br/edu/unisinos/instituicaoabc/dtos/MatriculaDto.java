package br.edu.unisinos.instituicaoabc.dtos;

import br.edu.unisinos.instituicaoabc.core.BaseDto;
import br.edu.unisinos.instituicaoabc.entities.Curso;
import br.edu.unisinos.instituicaoabc.entities.Matricula;
import br.edu.unisinos.instituicaoabc.entities.Pessoa;
import br.edu.unisinos.instituicaoabc.enums.TipoCursoEnum;
import br.edu.unisinos.instituicaoabc.enums.TurnoEnum;
import br.edu.unisinos.instituicaoabc.validations.OnCreate;
import br.edu.unisinos.instituicaoabc.validations.OnDelete;
import br.edu.unisinos.instituicaoabc.validations.OnUpdate;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DTO para Matricula.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MatriculaDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @NotNull(groups = {OnUpdate.class, OnDelete.class})
    private Long id;

    //Curso
    @NotNull(groups = {OnUpdate.class, OnDelete.class})
    private Long idCurso;

    private String nomeCurso;

    private TipoCursoEnum tipoCurso;

    //Pessoa
    @NotNull(groups = {OnUpdate.class, OnDelete.class})
    private Long idPessoa;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String cpf;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String nome;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private LocalDate dataNascimento;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String telefone;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String endereco;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Long numeroEndereco;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String bairro;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Long cep;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String cidade;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String estado;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private TurnoEnum turno;

    public static Matricula to(MatriculaDto dto) {
        Matricula entity = new Matricula();
        entity.setId(dto.getId());
        entity.setCurso(new Curso());
        entity.getCurso().setId(dto.getIdCurso());
        entity.getCurso().setNome(dto.getNomeCurso());
        entity.getCurso().setTipo(dto.getTipoCurso());
        entity.setPessoa(new Pessoa());
        entity.getPessoa().setId(dto.getIdPessoa());
        entity.getPessoa().setCpf(dto.getCpf());
        entity.getPessoa().setNome(dto.getNome());
        entity.getPessoa().setEmail(dto.getEmail());
        entity.getPessoa().setDataNascimento(dto.getDataNascimento());
        entity.getPessoa().setTelefone(dto.getTelefone());
        entity.getPessoa().setEndereco(dto.getEndereco());
        entity.getPessoa().setNumeroEndereco(dto.getNumeroEndereco());
        entity.getPessoa().setBairro(dto.getBairro());
        entity.getPessoa().setCep(dto.getCep());
        entity.getPessoa().setCidade(dto.getCidade());
        entity.getPessoa().setEstado(dto.getEstado());
        entity.setTurno(dto.getTurno());
        return entity;
    }

    public static List<Matricula> to(List<MatriculaDto> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Matricula> entities = new ArrayList<>();
        for (MatriculaDto dto : dtos) {
            entities.add(to(dto));
        }
        return entities;
    }

    public static MatriculaDto from(Matricula entity) {
        MatriculaDto dto = new MatriculaDto();
        dto.setId(entity.getId());
        if (entity.getCurso() != null) {
            dto.setIdCurso(entity.getCurso().getId());
            dto.setNomeCurso(entity.getCurso().getNome());
            dto.setTipoCurso(entity.getCurso().getTipo());
        }
        if (entity.getPessoa() != null) {
            dto.setIdPessoa(entity.getPessoa().getId());
            dto.setCpf(entity.getPessoa().getCpf());
            dto.setNome(entity.getPessoa().getNome());
            dto.setEmail(entity.getPessoa().getEmail());
            dto.setDataNascimento(entity.getPessoa().getDataNascimento());
            dto.setTelefone(entity.getPessoa().getTelefone());
            dto.setEndereco(entity.getPessoa().getEndereco());
            dto.setNumeroEndereco(entity.getPessoa().getNumeroEndereco());
            dto.setBairro(entity.getPessoa().getBairro());
            dto.setCep(entity.getPessoa().getCep());
            dto.setCidade(entity.getPessoa().getCidade());
            dto.setEstado(entity.getPessoa().getEstado());
        }
        dto.setTurno(entity.getTurno());

        return dto;
    }

    public static List<MatriculaDto> from(List<Matricula> entities) {
        if (entities == null) {
            return null;
        }
        List<MatriculaDto> dtos = new ArrayList<>();
        for (Matricula entity : entities) {
            dtos.add(from(entity));
        }
        return dtos;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}