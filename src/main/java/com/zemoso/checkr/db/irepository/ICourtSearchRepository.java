package com.zemoso.checkr.db.irepository;

import com.zemoso.checkr.db.domain.TCourtSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourtSearchRepository extends JpaRepository<TCourtSearch, Object> {

    List<TCourtSearch> findAllByJStatus(int status);
    TCourtSearch findByJIdAndJStatus(int jId, int jStatus);

}