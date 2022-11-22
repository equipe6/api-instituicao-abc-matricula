package br.edu.unisinos.instituicaoabc.services;

import br.edu.unisinos.instituicaoabc.entities.Pessoa;
import br.edu.unisinos.instituicaoabc.repositories.PessoaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PessoaService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final transient PessoaRepository pessoaRepository;

    public Pessoa findById(Long id) {
        return this.pessoaRepository.findById(id).orElse(null);
    }

    public List<Pessoa> findAll() {
        return this.pessoaRepository.findAll();
    }

    public Pessoa create(Pessoa pessoa) {
        return this.pessoaRepository.save(pessoa);
    }

    public Pessoa update(Pessoa entity, Long id) {
        Optional<Pessoa> entityFind = this.pessoaRepository.findById(id);
        if (entityFind.isEmpty()) {
            return null;
        }
        entity.setId(id);
        Pessoa updatedEntity = this.pessoaRepository.save(entity);
        return updatedEntity;
    }

    public Pessoa mergeOrCreate(Pessoa pessoa) {
        Pessoa pessoaFind = this.findByCpf(pessoa.getCpf());
        if (pessoaFind != null) {
            pessoaFind.setCpf(StringUtils.isNotBlank(pessoa.getCpf()) ? pessoa.getCpf() : pessoaFind.getCpf());
            pessoaFind.setNome(StringUtils.isNotBlank(pessoa.getNome()) ? pessoa.getNome() : pessoaFind.getNome());
            pessoaFind.setEmail(StringUtils.isNotBlank(pessoa.getEmail()) ? pessoa.getEmail() : pessoaFind.getEmail());
            pessoaFind.setDataNascimento(pessoa.getDataNascimento() != null ? pessoa.getDataNascimento() : pessoaFind.getDataNascimento());
            pessoaFind.setTelefone(StringUtils.isNotBlank(pessoa.getTelefone()) ? pessoa.getTelefone() : pessoaFind.getTelefone());
            pessoaFind.setEndereco(StringUtils.isNotBlank(pessoa.getEndereco()) ? pessoa.getEndereco() : pessoaFind.getEndereco());
            pessoaFind.setNumeroEndereco(pessoa.getNumeroEndereco() != null ? pessoa.getNumeroEndereco() : pessoaFind.getNumeroEndereco());
            pessoaFind.setBairro(StringUtils.isNotBlank(pessoa.getBairro()) ? pessoa.getBairro() : pessoaFind.getBairro());
            pessoaFind.setCep(pessoa.getCep() != null ? pessoa.getCep() : pessoaFind.getCep());
            pessoaFind.setCidade(StringUtils.isNotBlank(pessoa.getCidade()) ? pessoa.getCidade() : pessoaFind.getCidade());
            pessoaFind.setEstado(StringUtils.isNotBlank(pessoa.getEstado()) ? pessoa.getEstado() : pessoaFind.getEstado());
        }
        return this.pessoaRepository.save(pessoa);
    }

    public void delete(Pessoa pessoa) {
        this.pessoaRepository.delete(pessoa);
    }

    public void delete(Long id) {
        this.pessoaRepository.deleteById(id);
    }

    public Pessoa findByCpf(String cpf) {
        return this.pessoaRepository.findByCpf(cpf);
    }

    public List<Pessoa> findByNome(String nome) {
        return this.pessoaRepository.findByNomeLikeIgnoreCase(nome);
    }

    public List<Pessoa> findByEmail(String email) {
        return this.pessoaRepository.findByEmailLikeIgnoreCase(email);
    }

    public long count() {
        long result = this.pessoaRepository.count();
        return result;
    }

}
