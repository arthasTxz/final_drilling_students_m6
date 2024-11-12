package com.edutecno.backend.repository;


import com.edutecno.backend.model.AuthorityEnum;
import com.edutecno.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(AuthorityEnum name);
}
