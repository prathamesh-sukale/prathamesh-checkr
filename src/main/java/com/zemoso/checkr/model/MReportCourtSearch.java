package com.zemoso.checkr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.base.MBase;

import javax.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "courtSearch",
        "jCourtSearchStatus"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MReportCourtSearch extends MBase {

    @JsonProperty("courtSearch")
    @Valid
    private MCourtSearch mCourtSearch;
    @JsonProperty("jCourtSearchStatus")
    private int jCourtSearchStatus;

    @JsonProperty("courtSearch")
    public MCourtSearch getMCourtSearch() {
        return mCourtSearch;
    }

    @JsonProperty("courtSearch")
    public void setMCourtSearch(MCourtSearch mCourtSearch) {
        this.mCourtSearch = mCourtSearch;
    }

    @JsonProperty("jCourtSearchStatus")
    public int getJCourtSearchStatus() {
        return jCourtSearchStatus;
    }

    @JsonProperty("jCourtSearchStatus")
    public void setJCourtSearchStatus(int jCourtSearchStatus) {
        this.jCourtSearchStatus = jCourtSearchStatus;
    }

}