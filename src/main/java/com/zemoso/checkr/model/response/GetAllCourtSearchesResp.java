package com.zemoso.checkr.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.MCourtSearch;
import com.zemoso.checkr.model.base.ApiResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "courtSearches"
})
public class GetAllCourtSearchesResp extends ApiResponse {

    @JsonProperty("courtSearches")
    private List<MCourtSearch> mCourtSearches;

    @JsonProperty("courtSearches")
    public List<MCourtSearch> getMCourtSearches() {
        return mCourtSearches;
    }

    @JsonProperty("courtSearches")
    public void setMCourtSearches(List<MCourtSearch> mCourtSearches) {
        this.mCourtSearches = mCourtSearches;
    }
}
