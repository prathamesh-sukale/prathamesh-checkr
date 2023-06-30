package com.zemoso.checkr.db.repository;

import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.db.contract.IUser;
import com.zemoso.checkr.db.domain.TCandidate;
import com.zemoso.checkr.db.domain.TUser;
import com.zemoso.checkr.db.irepository.ICandidateRepository;
import com.zemoso.checkr.db.irepository.IUserRepository;
import com.zemoso.checkr.db.wrapper.OUser;
import com.zemoso.checkr.dto.CCandidate;
import com.zemoso.checkr.dto.CUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRepository extends ApplicationRepository {

    public  UserRepository(){
        super(UserRepository.class);
    }

    @Autowired
    private IUserRepository iUserRepository;


    @Autowired
    private ICandidateRepository iCandidateRepository;

    public List<IUser> getAll(){
        return iUserRepository.findAllByJStatus(ERowStatus.ACTIVE.getJValue()).stream().map(OUser::create).collect(Collectors.toList());
    }

    public IUser get(int jId){
        return OUser.create(iUserRepository.findByJIdAndJStatus(jId, ERowStatus.ACTIVE.getJValue()));
    }

    public IUser create(CUser cUser){

        try {
            TUser tUser = new TUser();

            tUser.setSEmail(cUser.getSEmail());
            tUser.setSPassword(cUser.getSPassword());
            tUser.setJRole(cUser.getJRole());

            tUser.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tUser.setJStatus(ERowStatus.ACTIVE.getJValue());

            tUser = iUserRepository.save(tUser);

            return OUser.create(tUser);
        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

    public IUser update(IUser iUser, CUser cUser,CCandidate cCandidate){

        try {
            TUser tUser = iUserRepository.findById(iUser.getJId()).orElse(null);

            if(tUser==null){
                return  null;
            }

            tUser.setSPassword(cUser.getSPassword());

            tUser.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tUser.setJStatus(cUser.getJStatus());

            if(cCandidate!=null ){

                TCandidate tCandidate = new TCandidate();
                tCandidate.setSEmail(cCandidate.getSEmail());
                tCandidate.setSFirstName(cCandidate.getSFirstName());
                tCandidate.setSLastName(cCandidate.getSLastName());
                tCandidate.setSLocation(cCandidate.getSLocation());
                tCandidate.setDtDob(cCandidate.getDtDob());

                tCandidate.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
                tCandidate.setJStatus(ERowStatus.ACTIVE.getJValue());

                tCandidate = iCandidateRepository.save(tCandidate);

                tUser.getTCandidates().add(tCandidate);
            }

            tUser = iUserRepository.save(tUser);

            return OUser.create(tUser);

        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

}
