package com.zemoso.checkr.db.repository;

import com.zemoso.checkr.common.enums.EAdjudicationStatus;
import com.zemoso.checkr.common.enums.ECourtSearchStatus;
import com.zemoso.checkr.common.enums.EReportStatus;
import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.db.contract.IReport;
import com.zemoso.checkr.db.domain.*;
import com.zemoso.checkr.db.irepository.*;
import com.zemoso.checkr.db.wrapper.OReport;
import com.zemoso.checkr.dto.CCandidate;
import com.zemoso.checkr.dto.CPreAdverseAction;
import com.zemoso.checkr.dto.CPreAdverseCharge;
import com.zemoso.checkr.dto.CReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReportRepository extends ApplicationRepository {

    public ReportRepository(){
        super(ReportRepository.class);
    }

    @Autowired
    private IReportRepository iReportRepository;

    @Autowired
    private ICandidateRepository iCandidateRepository;

    @Autowired
    private IChargeRepository iChargeRepository;

    @Autowired
    private ICourtSearchRepository iCourtSearchRepository;

    public List<IReport> getAll(){
        return iReportRepository.findAllByJStatus(ERowStatus.ACTIVE.getJValue()).stream().map(OReport::create).collect(Collectors.toList());
    }

    public IReport get(int jId){
        return OReport.create(iReportRepository.findByJIdAndJStatus(jId, ERowStatus.ACTIVE.getJValue()));
    }

    public IReport getByCandidate(int jCandidateId){

        TCandidate tCandidate = iCandidateRepository.findById(jCandidateId).orElse(null);
        if(tCandidate==null){
            return null;
        }
        return OReport.create(iReportRepository.findByTCandidateAndJStatus(tCandidate, ERowStatus.ACTIVE.getJValue()));
    }

    public IReport create(CCandidate cCandidate, List<CPreAdverseAction> cPreAdverseActions, List<CPreAdverseCharge> cPreAdverseCharges){

        try {

            TCandidate tCandidate = iCandidateRepository.findById(cCandidate.getJId()).orElse(null);
            if(tCandidate==null){
                return null;
            }

            TReport tReport = new TReport();

            tCandidate.setJReportStatus(cCandidate.getJReportStatus());
            tCandidate.setJAdjudicationStatus(cCandidate.getJAdjudicationStatus());

            tReport.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tReport.setJStatus(ERowStatus.ACTIVE.getJValue());
            tReport.setTPreAdverseActions(new ArrayList<>());

            if(!cPreAdverseActions.isEmpty() && !cPreAdverseCharges.isEmpty()){
                for(CPreAdverseAction cPreAdverseAction : cPreAdverseActions){
                    TPreAdverseAction tPreAdverseAction = new TPreAdverseAction();

                    tPreAdverseAction.setDtAction(cPreAdverseAction.getDtAction());
                    tPreAdverseAction.setJDays(cPreAdverseAction.getJDays());
                    tPreAdverseAction.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
                    tPreAdverseAction.setJStatus(ERowStatus.ACTIVE.getJValue());
                    tPreAdverseAction.setTPreAdverseCharges(new ArrayList<>());

                    for(CPreAdverseCharge cPreAdverseCharge : cPreAdverseCharges){
                        TPreAdverseCharge tPreAdverseCharge = new TPreAdverseCharge();

                        TCharge tCharge = iChargeRepository.findById(cPreAdverseCharge.getICharge().getJId()).orElse(null);

                        tPreAdverseCharge.setTCharge(tCharge);
                        tPreAdverseCharge.setJChargeStatus(ERowStatus.ACTIVE.getJValue());
                        tPreAdverseCharge.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
                        tPreAdverseCharge.setJStatus(ERowStatus.ACTIVE.getJValue());

                        tPreAdverseAction.getTPreAdverseCharges().add(tPreAdverseCharge);
                    }

                    tReport.getTPreAdverseActions().add(tPreAdverseAction);
                }
                tCandidate.setJAdjudicationStatus(EAdjudicationStatus.ADVERSE_ACTION.getJValue());
            }

            List<TReportCourtSearch> tReportCourtSearches = getSampleCourtSearches();
            if(!tReportCourtSearches.isEmpty()){
                tReport.setTReportCourtSearches(tReportCourtSearches);

                if(tReportCourtSearches.stream().anyMatch(obj-> obj.getJCourtSearchStatus()==ECourtSearchStatus.CONSIDER.getJValue())){
                    tCandidate.setJReportStatus(EReportStatus.CONSIDER.getJValue());
                }else{
                    tCandidate.setJReportStatus(EReportStatus.CLEAR.getJValue());
                }
            }

            tReport.setTCandidate(tCandidate);

            tReport = iReportRepository.save(tReport);

            return OReport.create(tReport);
        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

    public IReport addPreAdverseActions(IReport iReport,CCandidate cCandidate, List<CPreAdverseAction> cPreAdverseActions, List<CPreAdverseCharge> cPreAdverseCharges){

        try {

            TReport tReport = iReportRepository.findById(iReport.getJId()).orElse(null);
            if(tReport == null){
                return null;
            }

            TCandidate tCandidate = iCandidateRepository.findById(cCandidate.getJId()).orElse(null);
            if(tCandidate==null){
                return null;
            }

            if(!cPreAdverseActions.isEmpty() && !cPreAdverseCharges.isEmpty()){
                for(CPreAdverseAction cPreAdverseAction : cPreAdverseActions){
                    TPreAdverseAction tPreAdverseAction = new TPreAdverseAction();

                    tPreAdverseAction.setDtAction(cPreAdverseAction.getDtAction());
                    tPreAdverseAction.setJDays(cPreAdverseAction.getJDays());
                    tPreAdverseAction.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
                    tPreAdverseAction.setJStatus(ERowStatus.ACTIVE.getJValue());
                    tPreAdverseAction.setTPreAdverseCharges(new ArrayList<>());

                    for(CPreAdverseCharge cPreAdverseCharge : cPreAdverseCharges){
                        TPreAdverseCharge tPreAdverseCharge = new TPreAdverseCharge();

                        TCharge tCharge = iChargeRepository.findById(cPreAdverseCharge.getICharge().getJId()).orElse(null);

                        tPreAdverseCharge.setTCharge(tCharge);
                        tPreAdverseCharge.setJChargeStatus(ERowStatus.ACTIVE.getJValue());
                        tPreAdverseCharge.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
                        tPreAdverseCharge.setJStatus(ERowStatus.ACTIVE.getJValue());

                        tPreAdverseAction.getTPreAdverseCharges().add(tPreAdverseCharge);
                    }

                    tReport.getTPreAdverseActions().add(tPreAdverseAction);
                }
                tCandidate.setJAdjudicationStatus(EAdjudicationStatus.ADVERSE_ACTION.getJValue());
            }

            List<TReportCourtSearch> tReportCourtSearches = getSampleCourtSearches();
            if(!tReportCourtSearches.isEmpty()){
                tReport.setTReportCourtSearches(tReportCourtSearches);

                if(tReportCourtSearches.stream().anyMatch(obj-> obj.getJCourtSearchStatus()==ECourtSearchStatus.CONSIDER.getJValue())){
                    tCandidate.setJReportStatus(EReportStatus.CONSIDER.getJValue());
                }else{
                    tCandidate.setJReportStatus(EReportStatus.CLEAR.getJValue());
                }
            }

            tReport.setTCandidate(tCandidate);

            tReport = iReportRepository.save(tReport);

            return OReport.create(tReport);
        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

    public IReport update(IReport iReport, CReport cReport, boolean isRemoveAction){

        try {
            TReport tReport = iReportRepository.findById(iReport.getJId()).orElse(null);

            if(tReport==null){
                return  null;
            }

            tReport.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tReport.setJStatus(cReport.getJStatus());

            if(isRemoveAction){
                tReport.setTPreAdverseActions(new ArrayList<>());
            }

            tReport = iReportRepository.save(tReport);

            return OReport.create(tReport);

        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

    private  List<TReportCourtSearch> getSampleCourtSearches(){
        List<TReportCourtSearch> searches = new ArrayList<>();

        //1
        TCourtSearch tCourtSearch1 = iCourtSearchRepository.findById(1).orElse(null);
        TReportCourtSearch tReportCourtSearch1 = new TReportCourtSearch();
        tReportCourtSearch1.setTCourtSearch(tCourtSearch1);
        tReportCourtSearch1.setJCourtSearchStatus(ECourtSearchStatus.CLEAR.getJValue());
        tReportCourtSearch1.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
        tReportCourtSearch1.setJStatus(ERowStatus.ACTIVE.getJValue());
        searches.add(tReportCourtSearch1);

        //2
        TCourtSearch tCourtSearch2 = iCourtSearchRepository.findById(2).orElse(null);
        TReportCourtSearch tReportCourtSearch2 = new TReportCourtSearch();
        tReportCourtSearch2.setTCourtSearch(tCourtSearch2);
        tReportCourtSearch2.setJCourtSearchStatus(ECourtSearchStatus.CONSIDER.getJValue());
        tReportCourtSearch2.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
        tReportCourtSearch2.setJStatus(ERowStatus.ACTIVE.getJValue());
        searches.add(tReportCourtSearch2);

        //3
        TCourtSearch tCourtSearch3 = iCourtSearchRepository.findById(3).orElse(null);
        TReportCourtSearch tReportCourtSearch3 = new TReportCourtSearch();
        tReportCourtSearch3.setTCourtSearch(tCourtSearch3);
        tReportCourtSearch3.setJCourtSearchStatus(ECourtSearchStatus.CLEAR.getJValue());
        tReportCourtSearch3.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
        tReportCourtSearch3.setJStatus(ERowStatus.ACTIVE.getJValue());
        searches.add(tReportCourtSearch3);

        return searches;

    }

}
