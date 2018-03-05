package com.nsu.huangyong.dao;

import com.nsu.huangyong.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SellerDao extends JpaRepository<Seller,Long>, JpaSpecificationExecutor<Seller>{
}
