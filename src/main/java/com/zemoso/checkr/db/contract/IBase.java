package com.zemoso.checkr.db.contract;

import java.time.LocalDateTime;

public interface IBase {

    int getJId();
    LocalDateTime getDtCreate();
    int getJStatus();
}
