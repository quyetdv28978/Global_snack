package com.example.demo.reponsitory;

import com.example.demo.entity.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhaCungCapReponsitory extends JpaRepository<NhaCungCap, Long> {
    NhaCungCap findByTenNhaCungCap(String name);
}
