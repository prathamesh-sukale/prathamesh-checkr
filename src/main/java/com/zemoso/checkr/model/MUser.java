package com.zemoso.checkr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.base.MBase;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sEmail",
        "sPassword",
        "jRole"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MUser extends MBase {
    @JsonProperty("sEmail")
    private String sEmail;
    @JsonProperty("sPassword")
    private String sPassword;
    @JsonProperty("jRole")
    private int jRole;

    @JsonProperty("sEmail")
    public String getSEmail() {
        return sEmail;
    }

    @JsonProperty("sEmail")
    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    @JsonProperty("sPassword")
    public String getSPassword() {
        return sPassword;
    }

    @JsonProperty("sPassword")
    public void setSPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    @JsonProperty("jRole")
    public int getJRole() {
        return jRole;
    }

    @JsonProperty("jRole")
    public void setJRole(int jRole) {
        this.jRole = jRole;
    }

}