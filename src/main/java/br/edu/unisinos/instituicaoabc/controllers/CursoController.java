package br.edu.unisinos.instituicaoabc.controllers;

import br.edu.unisinos.instituicaoabc.dtos.CursoDto;
import br.edu.unisinos.instituicaoabc.entities.Curso;
import br.edu.unisinos.instituicaoabc.services.CursoService;
import br.edu.unisinos.instituicaoabc.validations.OnCreate;
import br.edu.unisinos.instituicaoabc.validations.OnDelete;
import br.edu.unisinos.instituicaoabc.validations.OnUpdate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Controller para Curso responsável pelas requests/responses via  Http REST com JSON.
 */
@Log4j2
@RestController
@RequestMapping("/")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class CursoController implements Serializable {

    private static final long serialVersionUID = 1L;

    //Injeta CursoService
    private final CursoService cursoService;

    /**
     * Metodo reponsavel por buscar todos os dados.
     */
    @GetMapping("/cursos")
    public ResponseEntity<List<CursoDto>> findAll() {
        log.info("findAll");
        List<Curso> result = this.cursoService.findAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CursoDto.from(result));
    }

    /**
     * Metodo reponsavel por buscar curso por um determinado ID.
     */
    @GetMapping("/curso/{id}")
    @ResponseBody
    public ResponseEntity<CursoDto> findById(@PathVariable @NotNull Long id) {
        log.info("findById ID:" + id);
        Curso entity = this.cursoService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CursoDto.from(entity));
    }

    /**
     * Metodo reponsavel pela criação de curso.
     */
    @PostMapping("/curso")
    @ResponseBody
    @Validated(OnCreate.class)
    public ResponseEntity<CursoDto> create(@RequestBody @Valid CursoDto dto) {
        log.info("create Curso:" + dto);
        Curso savedEntity = this.cursoService.create(CursoDto.to(dto));
        return ResponseEntity.ok(CursoDto.from(savedEntity));
    }

    /**
     * Metodo reponsavel pela atualização de curso.
     */
    @PutMapping("/curso/{id}")
    @ResponseBody
    @Validated(OnUpdate.class)
    public ResponseEntity<CursoDto> update(@RequestBody @Valid CursoDto dto, @PathVariable @NotNull Long id) {
        log.info("update Curso:" + dto + " - " + id);
        Curso updatedEntity = this.cursoService.update(CursoDto.to(dto), id);
        if (updatedEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CursoDto.from(updatedEntity));
    }

    /**
     * Metodo reponsavel pela exclusao de curso por um determinado ID.
     */
    @DeleteMapping("/curso/{id}")
    @Validated(OnDelete.class)
    public void delete(@PathVariable @NotNull Long id) {
        log.info("delete ID:" + id);
        this.cursoService.delete(id);
    }

    /**
     * Metodo reponsavel pela contabilização de quantos cursos foram cadastrados.
     */
    @GetMapping("/curso/count")
    public Long count() {
        return this.cursoService.count();
    }

}
