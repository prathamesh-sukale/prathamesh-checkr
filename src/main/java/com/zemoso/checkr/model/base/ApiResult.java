package com.zemoso.checkr.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "jCode",
        "sMessage"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResult {

    public ApiResult(){}

    public ApiResult(int jCode,String sMessage){
        this.jCode = jCode;
        this.sMessage = sMessage;
    }

    @JsonProperty("jCode")
    private int jCode;
    @JsonProperty("sMessage")
    private String sMessage;

    @JsonProperty("jCode")
    public int getJCode() {
        return jCode;
    }

    @JsonProperty("jCode")
    public void setJCode(int jCode) {
        this.jCode = jCode;
    }

    @JsonProperty("sMessage")
    public String getSMessage() {
        return sMessage;
    }

    @JsonProperty("sMessage")
    public void setSMessage(String sMessage) {
        this.sMessage = sMessage;
    }

    public static ApiResult ok(){
        return  new ApiResult(0,"success");
    }

    public static ApiResult failed(String sMessage){
        return  new ApiResult(1,sMessage);
    }

}