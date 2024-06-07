package br.com.coralink.api.repository;

import br.com.coralink.api.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    boolean existsByCnpj(String cnpj);

    boolean existsByEmail(String email);
}
