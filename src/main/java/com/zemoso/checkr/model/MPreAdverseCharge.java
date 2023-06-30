package com.zemoso.checkr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.base.MBase;

import javax.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "charge",
        "jChargeStatus"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MPreAdverseCharge extends MBase {

    @JsonProperty("charge")
    @Valid
    private MCharge mCharge;
    @JsonProperty("jChargeStatus")
    private int jChargeStatus;

    @JsonProperty("charge")
    public MCharge getMCharge() {
        return mCharge;
    }

    @JsonProperty("charge")
    public void setMCharge(MCharge mCharge) {
        this.mCharge = mCharge;
    }

    @JsonProperty("jChargeStatus")
    public int getJChargeStatus() {
        return jChargeStatus;
    }

    @JsonProperty("jChargeStatus")
    public void setJChargeStatus(int jChargeStatus) {
        this.jChargeStatus = jChargeStatus;
    }

}