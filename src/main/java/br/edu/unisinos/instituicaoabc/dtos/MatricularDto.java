package br.edu.unisinos.instituicaoabc.dtos;

import br.edu.unisinos.instituicaoabc.core.BaseDto;
import br.edu.unisinos.instituicaoabc.entities.Curso;
import br.edu.unisinos.instituicaoabc.entities.Matricula;
import br.edu.unisinos.instituicaoabc.entities.Pessoa;
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

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MatricularDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    //Pessoa
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

    //Curso
    @NotNull(groups = {OnUpdate.class, OnDelete.class})
    private Long idCurso;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private TurnoEnum turno;

    public static Matricula to(MatricularDto dto) {
        Matricula entity = new Matricula();
        entity.setPessoa(new Pessoa());
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
        entity.setCurso(new Curso());
        entity.getCurso().setId(dto.getIdCurso());
        entity.setTurno(dto.getTurno());
        return entity;
    }

    public static List<Matricula> to(List<MatricularDto> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Matricula> entities = new ArrayList<>();
        for (MatricularDto dto : dtos) {
            entities.add(to(dto));
        }
        return entities;
    }

    public static MatricularDto from(Matricula entity) {
        MatricularDto dto = new MatricularDto();
        if (entity.getPessoa() != null) {
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
        if (entity.getCurso() != null) {
            dto.setIdCurso(entity.getCurso().getId());
        }
        dto.setTurno(entity.getTurno());
        return dto;
    }

    public static List<MatricularDto> from(List<Matricula> entities) {
        if (entities == null) {
            return null;
        }
        List<MatricularDto> dtos = new ArrayList<>();
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