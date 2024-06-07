package br.com.coralink.api.repository;

import br.com.coralink.api.model.Bairro;
import br.com.coralink.api.model.Cidade;
import br.com.coralink.api.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BairroRepository extends JpaRepository<Bairro, Long> {


    @Query(value = "SELECT c.*"
            + " FROM CORALINK_BAIRRO c"
            + " WHERE c.pk_id_cidade=?2 "
            + " AND LOWER(c.nm_bairro)=?1 AND rownum = 1"
            + " ORDER BY c.pk_id_bairro"
            , nativeQuery = true)
    Bairro findByNomeAndIdCidade(String nome, Long idCidade);
}
