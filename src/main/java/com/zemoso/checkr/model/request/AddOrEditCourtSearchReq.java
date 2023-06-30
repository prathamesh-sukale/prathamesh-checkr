package com.zemoso.checkr.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.MCourtSearch;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "courtSearch"
})
public class AddOrEditCourtSearchReq {

    @JsonProperty("courtSearch")
    private MCourtSearch mCourtSearch;

    @JsonProperty("courtSearch")
    public MCourtSearch getMCourtSearch() {
        return mCourtSearch;
    }

    @JsonProperty("courtSearch")
    public void setMCourtSearch(MCourtSearch mCourtSearch) {
        this.mCourtSearch = mCourtSearch;
    }
}
