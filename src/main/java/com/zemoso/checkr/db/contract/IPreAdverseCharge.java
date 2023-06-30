package com.zemoso.checkr.db.contract;

public interface IPreAdverseCharge extends IBase{

    ICharge getICharge();
    int getJChargeStatus();
}
