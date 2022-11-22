package br.edu.unisinos.instituicaoabc.dtos;

import br.edu.unisinos.instituicaoabc.core.BaseDto;
import br.edu.unisinos.instituicaoabc.entities.Pessoa;
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
public class PessoaDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @NotNull(groups = {OnUpdate.class, OnDelete.class})
    private Long id;

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


    public static Pessoa to(PessoaDto dto) {
        Pessoa entity = new Pessoa();
        entity.setId(dto.getId());
        entity.setCpf(dto.getCpf());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setTelefone(dto.getTelefone());
        entity.setEndereco(dto.getEndereco());
        entity.setNumeroEndereco(dto.getNumeroEndereco());
        entity.setBairro(dto.getBairro());
        entity.setCep(dto.getCep());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        return entity;
    }

    public static List<Pessoa> to(List<PessoaDto> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Pessoa> entities = new ArrayList<>();
        for (PessoaDto dto : dtos) {
            entities.add(to(dto));
        }
        return entities;
    }

    public static PessoaDto from(Pessoa entity) {
        PessoaDto dto = new PessoaDto();
        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setTelefone(entity.getTelefone());
        dto.setEndereco(entity.getEndereco());
        dto.setNumeroEndereco(entity.getNumeroEndereco());
        dto.setBairro(entity.getBairro());
        dto.setCep(entity.getCep());
        dto.setCidade(entity.getCidade());
        dto.setEstado(entity.getEstado());
        return dto;
    }

    public static List<PessoaDto> from(List<Pessoa> entities) {
        if (entities == null) {
            return null;
        }
        List<PessoaDto> dtos = new ArrayList<>();
        for (Pessoa entity : entities) {
            dtos.add(from(entity));
        }
        return dtos;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}