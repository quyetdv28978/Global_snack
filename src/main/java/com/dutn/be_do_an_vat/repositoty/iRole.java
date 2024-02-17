package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRole extends JpaRepository<Role, Long> {
    Role findByRole(String roleName);
}
