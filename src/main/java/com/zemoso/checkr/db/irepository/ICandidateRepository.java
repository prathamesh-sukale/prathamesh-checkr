package com.zemoso.checkr.db.irepository;

import com.zemoso.checkr.db.domain.TCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICandidateRepository extends JpaRepository<TCandidate, Object> {

    List<TCandidate> findAllByJStatus(int status);
    TCandidate findByJIdAndJStatus(int jId,int jStatus);
}