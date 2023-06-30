package com.zemoso.checkr.db.contract;

import java.util.List;

public interface IUser extends IBase{
    String getSEmail();
    String getSPassword();
    int getJRole();
    List<ICandidate> getICandidates();
}
