package br.com.coralink.api.repository;

import br.com.coralink.api.model.Cidade;
import br.com.coralink.api.model.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {
}
