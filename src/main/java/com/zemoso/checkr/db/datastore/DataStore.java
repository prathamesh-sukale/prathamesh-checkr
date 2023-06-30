package com.zemoso.checkr.db.datastore;

import com.zemoso.checkr.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class DataStore implements IDataStore{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserRepository getUserRepository(){
        if(userRepository==null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    @Autowired
    private CandidateRepository candidateRepository;
    @Override
    public CandidateRepository getCandidateRepository(){
        if(candidateRepository==null){
            candidateRepository = new CandidateRepository();
        }
        return candidateRepository;
    }

    @Autowired
    private ChargeRepository chargeRepository;
    @Override
    public ChargeRepository getChargeRepository(){
        if(chargeRepository==null){
            chargeRepository = new ChargeRepository();
        }
        return chargeRepository;
    }

    @Autowired
    private CourtSearchRepository courtSearchRepository;
    @Override
    public CourtSearchRepository getCourtSearchRepository(){
        if(courtSearchRepository==null){
            courtSearchRepository = new CourtSearchRepository();
        }
        return courtSearchRepository;
    }

    @Autowired
    private PreAdverseChargeRepository preAdverseChargeRepository;
    @Override
    public PreAdverseChargeRepository getPreAdverseChargeRepository(){
        if(preAdverseChargeRepository ==null){
            preAdverseChargeRepository = new PreAdverseChargeRepository();
        }
        return preAdverseChargeRepository;
    }

    @Autowired
    private PreAdverseActionRepository preAdverseActionRepository;
    @Override
    public PreAdverseActionRepository getPreAdverseActionRepository(){
        if(preAdverseActionRepository==null){
            preAdverseActionRepository = new PreAdverseActionRepository();
        }
        return preAdverseActionRepository;
    }

    @Autowired
    private ReportRepository reportRepository;
    @Override
    public ReportRepository getReportRepository(){
        if(reportRepository==null){
            reportRepository = new ReportRepository();
        }
        return reportRepository;
    }

    @Autowired
    private ReportCourtSearchRepository reportCourtSearchRepository;
    @Override
    public ReportCourtSearchRepository getReportCourtSearchRepository(){
        if(reportCourtSearchRepository==null){
            reportCourtSearchRepository = new ReportCourtSearchRepository();
        }
        return reportCourtSearchRepository;
    }
}
