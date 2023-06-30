package com.zemoso.checkr.db.contract;

import java.time.LocalDate;

public interface ICandidate extends IBase{

    String getSEmail();
    String getSFirstName();
    String getSLastName();
    LocalDate getDtDob();
    String getSLocation();
    int getJReportStatus();
    int getJAdjudicationStatus();
}
