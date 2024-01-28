package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.Role_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDetail extends JpaRepository<Role_Detail, Long> {
}
