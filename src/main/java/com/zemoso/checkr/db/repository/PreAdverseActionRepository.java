package com.zemoso.checkr.db.repository;

import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.db.contract.IPreAdverseAction;
import com.zemoso.checkr.db.contract.IReport;
import com.zemoso.checkr.db.domain.TPreAdverseAction;
import com.zemoso.checkr.db.domain.TReport;
import com.zemoso.checkr.db.irepository.IPreAdverseActionRepository;
import com.zemoso.checkr.db.irepository.IReportRepository;
import com.zemoso.checkr.db.wrapper.OPreAdverseAction;
import com.zemoso.checkr.dto.CPreAdverseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PreAdverseActionRepository extends ApplicationRepository {

    public PreAdverseActionRepository(){
        super(PreAdverseActionRepository.class);
    }

    @Autowired
    private IPreAdverseActionRepository iPreAdverseActionRepository;

    @Autowired
    private IReportRepository iReportRepository;

    public List<IPreAdverseAction> getAll(){
        return iPreAdverseActionRepository.findAllByJStatus(ERowStatus.ACTIVE.getJValue()).stream().map(OPreAdverseAction::create).collect(Collectors.toList());
    }

    public IPreAdverseAction get(int jId){
        return OPreAdverseAction.create(iPreAdverseActionRepository.findByJIdAndJStatus(jId, ERowStatus.ACTIVE.getJValue()));
    }

    public IPreAdverseAction create(IReport iReport, CPreAdverseAction cPreAdverseAction){

        try {
            TReport tReport = iReportRepository.findById(iReport.getJId()).orElse(null);
            if(tReport==null){
                return null;
            }

            TPreAdverseAction tPreAdverseAction = new TPreAdverseAction();

            tPreAdverseAction.setDtAction(cPreAdverseAction.getDtAction());
            tPreAdverseAction.setJDays(cPreAdverseAction.getJDays());

            tPreAdverseAction.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tPreAdverseAction.setJStatus(ERowStatus.ACTIVE.getJValue());

            tPreAdverseAction = iPreAdverseActionRepository.save(tPreAdverseAction);

            return OPreAdverseAction.create(tPreAdverseAction);
        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

    public IPreAdverseAction update(IPreAdverseAction iPreAdverseAction, CPreAdverseAction cPreAdverseAction){

        try {
            TPreAdverseAction tPreAdverseAction = iPreAdverseActionRepository.findById(iPreAdverseAction.getJId()).orElse(null);

            if(tPreAdverseAction==null){
                return  null;
            }

            tPreAdverseAction.setDtAction(cPreAdverseAction.getDtAction());
            tPreAdverseAction.setJDays(cPreAdverseAction.getJDays());

            tPreAdverseAction.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tPreAdverseAction.setJStatus(cPreAdverseAction.getJStatus());

            tPreAdverseAction = iPreAdverseActionRepository.save(tPreAdverseAction);

            return OPreAdverseAction.create(tPreAdverseAction);

        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

}
