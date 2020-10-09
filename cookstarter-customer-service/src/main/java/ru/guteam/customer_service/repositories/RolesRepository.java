package ru.guteam.customer_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.guteam.customer_service.entities.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
	Role findOneByName(String name);
}
