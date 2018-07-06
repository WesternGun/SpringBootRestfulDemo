package com.privalia.mydemo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.privalia.mydemo.model.BinInfo;


@Repository
public interface BinInfoRepository extends JpaRepository<BinInfo, Long>{
    BinInfo findByBin(String bin_num);
    List<BinInfo> findAllByBin(String bin_num);
}
