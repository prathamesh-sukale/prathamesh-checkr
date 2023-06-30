package com.zemoso.checkr.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.MCandidate;
import com.zemoso.checkr.model.base.ApiResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "candidates"
})
public class GetAllCandidatesResp extends ApiResponse {

    @JsonProperty("candidates")
    private List<MCandidate> mCandidates;

    @JsonProperty("candidates")
    public List<MCandidate> getMCandidates() {
        return mCandidates;
    }

    @JsonProperty("candidates")
    public void setMCandidates(List<MCandidate> mCandidates) {
        this.mCandidates = mCandidates;
    }
}
