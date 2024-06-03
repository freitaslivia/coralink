package br.com.coralink.api.repository;

import br.com.coralink.api.model.Empresa;
import br.com.coralink.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
