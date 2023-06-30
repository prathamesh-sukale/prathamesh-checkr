package com.zemoso.checkr.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.MCharge;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "charge"
})
public class AddOrEditChargeReq {

    @JsonProperty("charge")
    private MCharge mCharge;

    @JsonProperty("charge")
    public MCharge getMCharge() {
        return mCharge;
    }

    @JsonProperty("charge")
    public void setMCharge(MCharge mCharge) {
        this.mCharge = mCharge;
    }
}
