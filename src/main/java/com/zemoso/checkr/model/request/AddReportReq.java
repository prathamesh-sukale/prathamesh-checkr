package com.zemoso.checkr.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.MReport;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "report"
})
public class AddReportReq {

    @JsonProperty("report")
    private MReport mReport;

    @JsonProperty("report")
    public MReport getMReport() {
        return mReport;
    }

    @JsonProperty("report")
    public void setMReport(MReport mReport) {
        this.mReport = mReport;
    }
}
