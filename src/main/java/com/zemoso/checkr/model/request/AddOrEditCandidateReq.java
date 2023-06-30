package com.zemoso.checkr.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.MCandidate;
import com.zemoso.checkr.model.MUser;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "candidate",
        "user"
})
public class AddOrEditCandidateReq {

    @JsonProperty("candidate")
    private MCandidate mCandidate;

    @JsonProperty("user")
    private MUser mUser;

    @JsonProperty("candidate")
    public MCandidate getMCandidate() {
        return mCandidate;
    }

    @JsonProperty("candidate")
    public void setMCandidate(MCandidate mCandidate) {
        this.mCandidate = mCandidate;
    }

    @JsonProperty("user")
    public MUser getMUser() {
        return mUser;
    }

    @JsonProperty("user")
    public void setMUser(MUser mUser) {
        this.mUser = mUser;
    }
}
