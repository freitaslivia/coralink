package br.com.coralink.api.dto;

import br.com.coralink.api.model.Empresa;
import org.springframework.hateoas.Link;

public record EmpresaResponseDTO(

        Long id,
        String nome,
        String nomeExibicao,
        String email,
        String tpEmpresa,
        String telefone,
        Link link
) {
    public EmpresaResponseDTO(Empresa empresa) {
        this(empresa.getId(), empresa.getNome(), empresa.getNomeExibicao(),empresa.getEmail(), empresa.getTpEmpresa(), empresa.getTelefone(), null);
    }
}
