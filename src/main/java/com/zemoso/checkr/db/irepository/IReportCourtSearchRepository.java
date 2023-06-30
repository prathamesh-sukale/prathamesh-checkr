package com.zemoso.checkr.db.irepository;

import com.zemoso.checkr.db.domain.TReportCourtSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReportCourtSearchRepository extends JpaRepository<TReportCourtSearch, Object> {

    List<TReportCourtSearch> findAllByJStatus(int status);
    TReportCourtSearch findByJIdAndJStatus(int jId, int jStatus);

}