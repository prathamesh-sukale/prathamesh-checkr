package com.zemoso.checkr.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.MCharge;
import com.zemoso.checkr.model.base.ApiResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "charges"
})
public class GetAllChargesResp extends ApiResponse {

    @JsonProperty("charges")
    private List<MCharge> mCharges;

    @JsonProperty("charges")
    public List<MCharge> getMCharges() {
        return mCharges;
    }

    @JsonProperty("charges")
    public void setMCharges(List<MCharge> mCharges) {
        this.mCharges = mCharges;
    }
}
