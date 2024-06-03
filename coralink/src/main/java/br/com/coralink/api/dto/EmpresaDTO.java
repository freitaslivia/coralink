package br.com.coralink.api.dto;

import br.com.coralink.api.model.Empresa;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

public record EmpresaDTO(

        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        String nomeExibicao,

        @NotBlank(message = "O campo não pode estar vazio")
        @CNPJ
        String cnpj,

        @NotBlank(message = "O campo não pode estar vazio")
        @Email(message = "Formato de e-mail inválido")
        @Pattern(regexp = "^[a-z]+@[a-z]+\\.[a-z]+$", message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "O campo não pode estar vazio")
        @Pattern(regexp = "^[0-9]{13}$", message = "Deve ter somente números e extamente 13 digitos")
        String telefone,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 20, message = "O campo pode ter no máximo 20 caracteres")
        String tpEmpresa
) {

        public EmpresaDTO(Empresa empresa) {
                this(empresa.getId(), empresa.getNome(), empresa.getNomeExibicao(),empresa.getCnpj(), empresa.getEmail(), empresa.getTelefone(), empresa.getTpEmpresa());
        }
}