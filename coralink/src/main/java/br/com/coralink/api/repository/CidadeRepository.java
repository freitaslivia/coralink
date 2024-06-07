package br.com.coralink.api.repository;

import br.com.coralink.api.model.Bairro;
import br.com.coralink.api.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query(value = "SELECT c.*"
            + " FROM CORALINK_CIDADE c"
            + " WHERE c.pk_id_estado=?2 "
            + " AND LOWER(c.nm_cidade)=?1 AND rownum = 1"
            + " ORDER BY c.pk_id_cidade"
            , nativeQuery = true)
    Cidade findByNomeAndIdEstado(String nome, Long idEstado);

    /*
    @Query("select u from User u where u.firstname = :#{#customer.firstname}")
    List<User> findUsersByCustomersFirstname(@Param("customer") Customer customer);
     */
    /*
        @Query(value = "SELECT new Map(c.pk_id_cidade, c.nm_cidade, c.nr_ddd, c.pk_id_estado)"
            + " from CORALINK_CIDADE c"
            + " where c.pk_id_estado=?1 "
            + " and c.nm_cidade=?2"
            , nativeQuery = true)
    Cidade findByNomeAndIdEstado(String nome, Long idEstado);
     */
}
