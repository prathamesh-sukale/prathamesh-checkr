package com.zemoso.checkr.db.irepository;

import com.zemoso.checkr.db.domain.TPreAdverseAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPreAdverseActionRepository extends JpaRepository<TPreAdverseAction, Object> {

    List<TPreAdverseAction> findAllByJStatus(int status);
    TPreAdverseAction findByJIdAndJStatus(int jId, int jStatus);

}