package br.com.coralink.api.repository;

import br.com.coralink.api.model.Bairro;
import br.com.coralink.api.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Estado findBySigla(String sigla);
}
