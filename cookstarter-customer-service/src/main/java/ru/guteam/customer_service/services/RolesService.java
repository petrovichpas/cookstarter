package ru.guteam.customer_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guteam.customer_service.entities.Role;
import ru.guteam.customer_service.repositories.RolesRepository;


@Service
public class RolesService {
    private RolesRepository rolesRepository;

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Role findByName(String name) {
        return rolesRepository.findOneByName(name);
    }
}
