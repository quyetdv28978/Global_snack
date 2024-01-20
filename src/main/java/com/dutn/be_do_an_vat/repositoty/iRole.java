package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.Role;
import com.dutn.be_do_an_vat.entity.base_entity.E_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface iRole extends JpaRepository<Role, Long> {
    Role findByRole(E_Role roleName);
}
