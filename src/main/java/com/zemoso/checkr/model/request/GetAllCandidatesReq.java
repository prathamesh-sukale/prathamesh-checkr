package com.zemoso.checkr.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.MUser;

import javax.validation.Valid;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "user",
    "jStatuses",
    "jAdjudications",
    "sSearch"
})
public class GetAllCandidatesReq{

    @JsonProperty("user")
    @Valid
    private MUser mUser;
    @JsonProperty("jStatuses")
    @Valid
    private List<Integer> jStatuses;
    @JsonProperty("jAdjudications")
    @Valid
    private List<Integer> jAdjudications;
    @JsonProperty("sSearch")
    private String sSearch;

    @JsonProperty("user")
    public MUser getMUser() {
        return mUser;
    }

    @JsonProperty("user")
    public void setMUser(MUser mUser) {
        this.mUser = mUser;
    }

    @JsonProperty("jStatuses")
    public List<Integer> getJStatuses() {
        return jStatuses;
    }

    @JsonProperty("jStatuses")
    public void setJStatuses(List<Integer> jStatuses) {
        this.jStatuses = jStatuses;
    }

    @JsonProperty("jAdjudications")
    public List<Integer> getJAdjudications() {
        return jAdjudications;
    }

    @JsonProperty("jAdjudications")
    public void setJAdjudications(List<Integer> jAdjudications) {
        this.jAdjudications = jAdjudications;
    }

    @JsonProperty("sSearch")
    public String getSSearch() {
        return sSearch;
    }

    @JsonProperty("sSearch")
    public void setSSearch(String sSearch) {
        this.sSearch = sSearch;
    }

}
