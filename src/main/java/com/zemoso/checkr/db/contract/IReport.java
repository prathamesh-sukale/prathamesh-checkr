package com.zemoso.checkr.db.contract;

import java.util.List;

public interface IReport extends IBase{

    ICandidate getICandidate();
    List<IPreAdverseAction> getIPreAdverseActions();
    List<IReportCourtSearch> getIReportCourtSearches();
}
