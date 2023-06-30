package com.zemoso.checkr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.base.MBase;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sName"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MCourtSearch extends MBase {
    @JsonProperty("sName")
    private String sName;

    @JsonProperty("sName")
    public String getSName() {
        return sName;
    }

    @JsonProperty("sName")
    public void setSName(String sName) {
        this.sName = sName;
    }

}