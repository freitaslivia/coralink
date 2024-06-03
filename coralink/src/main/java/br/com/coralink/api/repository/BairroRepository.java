package br.com.coralink.api.repository;

import br.com.coralink.api.model.Bairro;
import br.com.coralink.api.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
}
