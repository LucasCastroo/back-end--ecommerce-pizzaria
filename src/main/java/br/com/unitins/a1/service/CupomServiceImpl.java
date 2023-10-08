package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.CupomDTO;
import br.com.unitins.a1.dto.CupomResponseDTO;
import br.com.unitins.a1.model.Cupom;
import br.com.unitins.a1.repository.CupomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class CupomServiceImpl implements CupomService{
    @Inject
    CupomRepository repository;

    @Override
    @Transactional
    public CupomResponseDTO create(@Valid CupomDTO dto) {
        Cupom cupom = dto.valueOf();
        repository.persist(cupom);
        return CupomResponseDTO.from(cupom);
    }

    @Transactional
    @Override
    public CupomResponseDTO update(CupomDTO dto, Long id) {
        Cupom cupom = repository.findById(id);
        if (dto.codigo() != null) cupom.setCodigo(dto.codigo());
        if (dto.desconto() != null) cupom.setDesconto(dto.desconto());
        repository.persist(cupom);
        return CupomResponseDTO.from(cupom);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CupomResponseDTO findById(Long id) {
        return CupomResponseDTO.from(repository.findById(id));
    }

    @Override
    public CupomResponseDTO findByCodigo(String codigo) {
        return CupomResponseDTO.from(repository.findByCodigo(codigo));
    }
}