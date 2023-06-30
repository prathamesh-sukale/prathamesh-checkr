package com.zemoso.checkr.db.datastore;

import com.zemoso.checkr.db.repository.*;

public interface IDataStore {

    UserRepository getUserRepository();
    CandidateRepository getCandidateRepository();
    ChargeRepository getChargeRepository();
    CourtSearchRepository getCourtSearchRepository();
    PreAdverseChargeRepository getPreAdverseChargeRepository();
    PreAdverseActionRepository getPreAdverseActionRepository();
    ReportRepository getReportRepository();
    ReportCourtSearchRepository getReportCourtSearchRepository();
}
