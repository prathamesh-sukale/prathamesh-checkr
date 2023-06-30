package com.zemoso.checkr.db.irepository;

import com.zemoso.checkr.db.domain.TUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<TUser, Object> {

    List<TUser> findAllByJStatus(int status);
    TUser findByJIdAndJStatus(int jId, int jStatus);

}