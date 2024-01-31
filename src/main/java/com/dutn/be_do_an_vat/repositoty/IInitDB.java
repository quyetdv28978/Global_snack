package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.intiDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInitDB extends JpaRepository<intiDB, Long> {
}
