package com.zemoso.checkr.model.base;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "apiResult"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    @JsonProperty("apiResult")
    @Valid
    private ApiResult apiResult;

    @JsonProperty("apiResult")
    public ApiResult getApiResult() {
        return apiResult;
    }

    @JsonProperty("apiResult")
    public void setApiResult(ApiResult apiResult) {
        this.apiResult = apiResult;
    }

}
