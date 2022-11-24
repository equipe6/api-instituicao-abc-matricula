package br.edu.unisinos.instituicaoabc.dtos;

import br.edu.unisinos.instituicaoabc.core.BaseDto;
import br.edu.unisinos.instituicaoabc.entities.Curso;
import br.edu.unisinos.instituicaoabc.enums.TipoCursoEnum;
import br.edu.unisinos.instituicaoabc.validations.OnCreate;
import br.edu.unisinos.instituicaoabc.validations.OnDelete;
import br.edu.unisinos.instituicaoabc.validations.OnUpdate;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DTO para Curso.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CursoDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @NotNull(groups = {OnUpdate.class, OnDelete.class})
    private Long id;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String nome;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private TipoCursoEnum tipo;

    public static Curso to(CursoDto dto) {
        Curso entity = new Curso();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setTipo(dto.getTipo());
        return entity;
    }

    public static List<Curso> to(List<CursoDto> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Curso> entities = new ArrayList<>();
        for (CursoDto dto : dtos) {
            entities.add(to(dto));
        }
        return entities;
    }

    public static CursoDto from(Curso entity) {
        CursoDto dto = new CursoDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setTipo(entity.getTipo());
        return dto;
    }

    public static List<CursoDto> from(List<Curso> entities) {
        if (entities == null) {
            return null;
        }
        List<CursoDto> dtos = new ArrayList<>();
        for (Curso entity : entities) {
            dtos.add(from(entity));
        }
        return dtos;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}