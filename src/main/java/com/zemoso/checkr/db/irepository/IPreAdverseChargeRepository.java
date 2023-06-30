package com.zemoso.checkr.db.irepository;

import com.zemoso.checkr.db.domain.TPreAdverseCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPreAdverseChargeRepository extends JpaRepository<TPreAdverseCharge, Object> {

    List<TPreAdverseCharge> findAllByJStatus(int status);
    TPreAdverseCharge findByJIdAndJStatus(int jId, int jStatus);

}