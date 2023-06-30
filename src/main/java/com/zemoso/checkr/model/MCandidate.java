package com.zemoso.checkr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.base.MBase;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sFirstName",
        "sLastName",
        "sEmail",
        "dtDob",
        "sLocation",
        "jReportStatus",
        "jAdjudicationStatus"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MCandidate extends MBase {

    @JsonProperty("sFirstName")
    private String sFirstName;
    @JsonProperty("sLastName")
    private String sLastName;
    @JsonProperty("sEmail")
    private String sEmail;
    @JsonProperty("dtDob")
    private LocalDate dtDob;
    @JsonProperty("sLocation")
    private String sLocation;
    @JsonProperty("jReportStatus")
    private int jReportStatus;
    @JsonProperty("jAdjudicationStatus")
    private int jAdjudicationStatus;

    @JsonProperty("sFirstName")
    public String getSFirstName() {
        return sFirstName;
    }

    @JsonProperty("sFirstName")
    public void setSFirstName(String sFirstName) {
        this.sFirstName = sFirstName;
    }

    @JsonProperty("sLastName")
    public String getSLastName() {
        return sLastName;
    }

    @JsonProperty("sLastName")
    public void setSLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    @JsonProperty("sEmail")
    public String getSEmail() {
        return sEmail;
    }

    @JsonProperty("sEmail")
    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    @JsonProperty("dtDob")
    public LocalDate getDtDob() {
        return dtDob;
    }

    @JsonProperty("dtDob")
    public void setDtDob(LocalDate dtDob) {
        this.dtDob = dtDob;
    }

    @JsonProperty("sLocation")
    public String getSLocation() {
        return sLocation;
    }

    @JsonProperty("sLocation")
    public void setSLocation(String sLocation) {
        this.sLocation = sLocation;
    }

    @JsonProperty("jReportStatus")
    public int getJReportStatus() {
        return jReportStatus;
    }

    @JsonProperty("jReportStatus")
    public void setJReportStatus(int jReportStatus) {
        this.jReportStatus = jReportStatus;
    }

    @JsonProperty("jAdjudicationStatus")
    public int getJAdjudicationStatus() {
        return jAdjudicationStatus;
    }

    @JsonProperty("jAdjudicationStatus")
    public void setJAdjudicationStatus(int jAdjudicationStatus) {
        this.jAdjudicationStatus = jAdjudicationStatus;
    }

}
