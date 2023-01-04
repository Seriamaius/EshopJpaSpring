package eshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	@Query("select c from Client c left join fetch c.commandes where c.id=:id")
	Optional<Client> findByIdFetchCommandes(@Param("id") Long id);
}
