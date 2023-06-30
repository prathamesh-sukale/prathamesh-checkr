package com.zemoso.checkr.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.MUser;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "user"
})
public class AddOrEditUserReq {

    @JsonProperty("user")
    private MUser mUser;

    @JsonProperty("user")
    public MUser getMUser() {
        return mUser;
    }

    @JsonProperty("user")
    public void setMUser(MUser mUser) {
        this.mUser = mUser;
    }
}
