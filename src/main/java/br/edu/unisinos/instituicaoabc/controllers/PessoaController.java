package br.edu.unisinos.instituicaoabc.controllers;

import br.edu.unisinos.instituicaoabc.dtos.PessoaDto;
import br.edu.unisinos.instituicaoabc.entities.Pessoa;
import br.edu.unisinos.instituicaoabc.services.PessoaService;
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
 * Controller para Pessoa responsável pelas requests/responses via  Http REST com JSON.
 */
@Log4j2
@RestController
@RequestMapping("/")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PessoaController implements Serializable {

    private static final long serialVersionUID = 1L;

    //Injeta PessoaService
    private final PessoaService pessoaService;

    /**
     * Metodo reponsavel por buscar todos os dados.
     */
    @GetMapping("/pessoas")
    public ResponseEntity<List<PessoaDto>> findAll() {
        log.info("findAll");
        List<Pessoa> result = this.pessoaService.findAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(PessoaDto.from(result));
    }

    /**
     * Metodo reponsavel por buscar pessoa por um determinado ID.
     */
    @GetMapping("/pessoa/{id}")
    @ResponseBody
    public ResponseEntity<PessoaDto> findById(@PathVariable @NotNull Long id) {
        log.info("findById ID:" + id);
        Pessoa entity = this.pessoaService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PessoaDto.from(entity));
    }

    /**
     * Metodo reponsavel pela criação de pessoa.
     */
    @PostMapping("/pessoa")
    @ResponseBody
    @Validated(OnCreate.class)
    public ResponseEntity<PessoaDto> create(@RequestBody @Valid PessoaDto dto) {
        log.info("create Pessoa:" + dto);
        Pessoa savedEntity = this.pessoaService.create(PessoaDto.to(dto));
        return ResponseEntity.ok(PessoaDto.from(savedEntity));
    }

    /**
     * Metodo reponsavel pela atualização de pessoa.
     */
    @PutMapping("/pessoa/{id}")
    @ResponseBody
    @Validated(OnUpdate.class)
    public ResponseEntity<PessoaDto> update(@RequestBody @Valid PessoaDto dto, @PathVariable @NotNull Long id) {
        log.info("update Pessoa:" + dto + " - " + id);
        Pessoa updatedEntity = this.pessoaService.update(PessoaDto.to(dto), id);
        if (updatedEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PessoaDto.from(updatedEntity));
    }

    /**
     * Metodo reponsavel pela exclusao de pessoa por um determinado ID.
     */
    @DeleteMapping("/pessoa/{id}")
    @Validated(OnDelete.class)
    public void delete(@PathVariable @NotNull Long id) {
        log.info("delete ID:" + id);
        this.pessoaService.delete(id);
    }

    /**
     * Metodo reponsavel pela contabilização de quantos pessoas foram cadastrados.
     */
    @GetMapping("/pessoa/count")
    public Long count() {
        return this.pessoaService.count();
    }

}
