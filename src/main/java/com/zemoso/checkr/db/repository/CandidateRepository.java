package com.zemoso.checkr.db.repository;

import com.zemoso.checkr.common.enums.EAdjudicationStatus;
import com.zemoso.checkr.common.enums.EReportStatus;
import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.db.contract.ICandidate;
import com.zemoso.checkr.db.domain.TCandidate;
import com.zemoso.checkr.db.irepository.ICandidateRepository;
import com.zemoso.checkr.db.wrapper.OCandidate;
import com.zemoso.checkr.dto.CCandidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateRepository extends ApplicationRepository {

    public CandidateRepository(){
        super(CandidateRepository.class);
    }

    @Autowired
    private ICandidateRepository iCandidateRepository;

    public List<ICandidate> getAll(){
        return iCandidateRepository.findAllByJStatus(ERowStatus.ACTIVE.getJValue()).stream().map(OCandidate::create).collect(Collectors.toList());
    }

    public ICandidate get(int jId){
        return OCandidate.create(iCandidateRepository.findByJIdAndJStatus(jId, ERowStatus.ACTIVE.getJValue()));
    }

    public ICandidate create(CCandidate cCandidate){

        try {
            TCandidate tCandidate = new TCandidate();

            tCandidate.setSEmail(cCandidate.getSEmail());
            tCandidate.setSFirstName(cCandidate.getSFirstName());
            tCandidate.setSLastName(cCandidate.getSLastName());
            tCandidate.setSLocation(cCandidate.getSLocation());
            tCandidate.setDtDob(cCandidate.getDtDob());
            tCandidate.setJReportStatus(EReportStatus.NO_ACTION.getJValue());
            tCandidate.setJAdjudicationStatus(EAdjudicationStatus.NO_ACTION.getJValue());

            tCandidate.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tCandidate.setJStatus(ERowStatus.ACTIVE.getJValue());

            tCandidate = iCandidateRepository.save(tCandidate);

            return OCandidate.create(tCandidate);
        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

    public ICandidate update(ICandidate iCandidate, CCandidate cCandidate){

        try {
            TCandidate tCandidate = iCandidateRepository.findById(iCandidate.getJId()).orElse(null);

            if(tCandidate==null){
                return  null;
            }

            tCandidate.setSFirstName(cCandidate.getSFirstName());
            tCandidate.setSLastName(cCandidate.getSLastName());
            tCandidate.setSLocation(cCandidate.getSLocation());
            tCandidate.setDtDob(cCandidate.getDtDob());

            tCandidate.setJReportStatus(cCandidate.getJReportStatus());
            tCandidate.setJAdjudicationStatus(cCandidate.getJAdjudicationStatus());

            tCandidate.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tCandidate.setJStatus(cCandidate.getJStatus());

            tCandidate = iCandidateRepository.save(tCandidate);

            return OCandidate.create(tCandidate);

        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

}
