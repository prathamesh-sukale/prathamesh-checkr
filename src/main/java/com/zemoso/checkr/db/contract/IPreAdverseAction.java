package com.zemoso.checkr.db.contract;

import java.time.LocalDateTime;
import java.util.List;

public interface IPreAdverseAction extends IBase{

    LocalDateTime getDtAction();
    int getJDays();
     List<IPreAdverseCharge> getIPreAdverseCharges();
}
