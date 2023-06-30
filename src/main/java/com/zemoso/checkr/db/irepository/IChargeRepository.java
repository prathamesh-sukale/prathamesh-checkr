package com.zemoso.checkr.db.irepository;

import com.zemoso.checkr.db.domain.TCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChargeRepository extends JpaRepository<TCharge, Object> {

    List<TCharge> findAllByJStatus(int jStatus);

    TCharge findByJIdAndJStatus(int jId,int jStatus);

}