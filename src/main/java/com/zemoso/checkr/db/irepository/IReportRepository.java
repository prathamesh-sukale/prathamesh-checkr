package com.zemoso.checkr.db.irepository;

import com.zemoso.checkr.db.domain.TCandidate;
import com.zemoso.checkr.db.domain.TReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReportRepository extends JpaRepository<TReport, Object> {

    List<TReport> findAllByJStatus(int status);
    TReport findByJIdAndJStatus(int jId, int jStatus);
    TReport findByTCandidateAndJStatus(TCandidate tCandidate, int jStatus);

}