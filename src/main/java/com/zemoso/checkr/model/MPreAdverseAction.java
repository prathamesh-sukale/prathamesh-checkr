package com.zemoso.checkr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.base.MBase;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dtAction",
        "jDays",
        "preAdverseCharges"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MPreAdverseAction extends MBase {

    @JsonProperty("dtAction")
    private LocalDateTime dtAction;
    @JsonProperty("jDays")
    private int jDays;
    @JsonProperty("preAdverseCharges")
    @Valid
    private List<MPreAdverseCharge> mPreAdverseCharges;

    @JsonProperty("dtAction")
    public LocalDateTime getDtAction() {
        return dtAction;
    }

    @JsonProperty("dtAction")
    public void setDtAction(LocalDateTime dtAction) {
        this.dtAction = dtAction;
    }

    @JsonProperty("jDays")
    public int getJDays() {
        return jDays;
    }

    @JsonProperty("jDays")
    public void setJDays(int jDays) {
        this.jDays = jDays;
    }

    @JsonProperty("preAdverseCharges")
    public List<MPreAdverseCharge> getMPreAdverseCharges() {
        return mPreAdverseCharges;
    }

    @JsonProperty("preAdverseCharges")
    public void setMPreAdverseCharges(List<MPreAdverseCharge> mPreAdverseCharges) {
        this.mPreAdverseCharges = mPreAdverseCharges;
    }

}