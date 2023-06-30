package com.zemoso.checkr.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.MReport;
import com.zemoso.checkr.model.base.ApiResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "report"
})
public class GetReportResp extends ApiResponse {

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
