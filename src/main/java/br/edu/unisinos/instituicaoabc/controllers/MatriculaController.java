package br.edu.unisinos.instituicaoabc.controllers;


import br.edu.unisinos.instituicaoabc.dtos.MatriculaDto;
import br.edu.unisinos.instituicaoabc.dtos.MatricularDto;
import br.edu.unisinos.instituicaoabc.entities.Matricula;
import br.edu.unisinos.instituicaoabc.services.MatriculaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Controller para Matrícula responsável pelas requests/responses via  Http REST com JSON.
 */
@Log4j2
@RestController
@RequestMapping("/")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MatriculaController implements Serializable {

    private static final long serialVersionUID = 1L;

    //Injeta MatriculaService
    private final MatriculaService matriculaService;

    /**
     * Metodo reponsavel por buscar todos os dados.
     */
    @GetMapping("/matriculas")
    public ResponseEntity<List<MatriculaDto>> findAll() {
        log.info("findAll");
        List<Matricula> result = this.matriculaService.findAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(MatriculaDto.from(result));
    }

    /**
     * Metodo reponsavel por buscar matricula por um determinado ID.
     */
    @GetMapping("/matricula/{id}")
    @ResponseBody
    public ResponseEntity<MatriculaDto> findById(@PathVariable @NotNull Long id) {
        log.info("findById ID:" + id);
        Matricula entity = this.matriculaService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MatriculaDto.from(entity));
    }

    /**
     * Metodo reponsavel pela criação de matrícula.
     */
    @PostMapping(value = "matricular")
    @ResponseBody
    public ResponseEntity<MatriculaDto> matricular(@RequestBody MatricularDto dto) {
        log.info("matricular Matricula:" + dto);
        Matricula matricula = this.matriculaService.matricular(MatricularDto.to(dto));
        return ResponseEntity.ok(MatriculaDto.from(matricula));
    }

}
