package br.com.coralink.api.repository;

import br.com.coralink.api.model.Bairro;
import br.com.coralink.api.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
