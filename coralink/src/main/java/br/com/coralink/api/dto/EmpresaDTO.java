package br.com.coralink.api.dto;

import br.com.coralink.api.model.Empresa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

public record EmpresaDTO(
        @Schema(hidden = true)
        Long id,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        @Schema(description = "Nome da empresa", example = "SOS Mata Atlântica")
        String nome,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 100, message = "O campo pode ter no máximo 100 caracteres")
        @Schema(description = "Nome de Exibição empresa", example = "SOS Mata")
        String nomeExibicao,

        @NotBlank(message = "O campo não pode estar vazio")
        @CNPJ
        @Schema(description = "CNPJ da empresa", example = "21291618000128")
        String cnpj,

        @NotBlank(message = "O campo não pode estar vazio")
        @Email(message = "Formato de e-mail inválido")
        @Schema(description = "Email da empresa", example = "info@sosma.org.br")
        String email,

        @NotBlank(message = "O campo não pode estar vazio")
        @Pattern(regexp = "^[0-9]{13}$", message = "Deve ter somente números e extamente 13 digitos")
        @Schema(description = "Telefone da empresa", example = "5511922448997")
        String telefone,

        @NotBlank(message = "O campo não pode estar vazio")
        @Size(max = 20, message = "O campo pode ter no máximo 20 caracteres")
        @Schema(description = "Tipo de empresa", example = "ONG")
        String tpEmpresa
) {

        public EmpresaDTO(Empresa empresa) {
                this(empresa.getId(), empresa.getNome(), empresa.getNomeExibicao(),empresa.getCnpj(), empresa.getEmail(), empresa.getTelefone(), empresa.getTpEmpresa());
        }
}