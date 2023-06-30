package com.zemoso.checkr.db.datastore;

import com.zemoso.checkr.db.repository.*;

public interface IDataStore {

    public UserRepository getUserRepository();
    public CandidateRepository getCandidateRepository();
    public ChargeRepository getChargeRepository();
    public CourtSearchRepository getCourtSearchRepository();
    public PreAdverseChargeRepository getPreAdverseChargeRepository();
    public PreAdverseActionRepository getPreAdverseActionRepository();
    public ReportRepository getReportRepository();
    public ReportCourtSearchRepository getReportCourtSearchRepository();
}
