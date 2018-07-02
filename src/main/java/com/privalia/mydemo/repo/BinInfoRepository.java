package com.privalia.mydemo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.privalia.mydemo.domain.Bin_info;


@Repository
public interface BinInfoRepository extends JpaRepository<Bin_info, Long>{

}
