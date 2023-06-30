package com.zemoso.checkr.model;

import java.util.List;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.base.MBase;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "candidate",
        "preAdverseActions",
        "reportCourtSearches"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MReport extends MBase {

    @JsonProperty("candidate")
    @Valid
    private MCandidate mCandidate;
    @JsonProperty("preAdverseActions")
    @Valid
    private List<MPreAdverseAction> mPreAdverseActions;
    @JsonProperty("reportCourtSearches")
    @Valid
    private List<MReportCourtSearch> mReportCourtSearches;

    @JsonProperty("candidate")
    public MCandidate getMCandidate() {
        return mCandidate;
    }

    @JsonProperty("candidate")
    public void setMCandidate(MCandidate mCandidate) {
        this.mCandidate = mCandidate;
    }

    @JsonProperty("preAdverseActions")
    public List<MPreAdverseAction> getMPreAdverseActions() {
        return mPreAdverseActions;
    }

    @JsonProperty("preAdverseActions")
    public void setMPreAdverseActions(List<MPreAdverseAction> mPreAdverseActions) {
        this.mPreAdverseActions = mPreAdverseActions;
    }

    @JsonProperty("reportCourtSearches")
    public List<MReportCourtSearch> getMReportCourtSearches() {
        return mReportCourtSearches;
    }

    @JsonProperty("reportCourtSearches")
    public void setMReportCourtSearches(List<MReportCourtSearch> mReportCourtSearches) {
        this.mReportCourtSearches = mReportCourtSearches;
    }

}