package com.zemoso.checkr.db.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "candidate")
public class TCandidate extends TBase implements Serializable {

    private String sEmail;
    private String sFirstName;
    private String sLastName;
    private LocalDate dtDob;
    private String sLocation;
    private int jReportStatus;
    private int jAdjudicationStatus;

    @Email
    @Size(max = 256)
    @Column(name = "email", nullable = false, length = 256)
    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    @NotEmpty
    @Size(max = 32)
    @Column(name = "first_name", nullable = false, length = 32)
    public String getSFirstName() {
        return sFirstName;
    }

    public void setSFirstName(String sFirstName) {
        this.sFirstName = sFirstName;
    }

    @NotEmpty
    @Size(max = 32)
    @Column(name = "last_name", nullable = false, length = 32)
    public String getSLastName() {
        return sLastName;
    }

    public void setSLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    @NotNull
    @Column(name = "dob", nullable = false,length = 29)
    public LocalDate getDtDob() {
        return dtDob;
    }

    public void setDtDob(LocalDate dtDob) {
        this.dtDob = dtDob;
    }

    @NotEmpty
    @Size(max = 32)
    @Column(name = "location", nullable = false, length = 32)
    public String getSLocation() {
        return sLocation;
    }

    public void setSLocation(String sLocation) {
        this.sLocation = sLocation;
    }

    @Digits(integer =10,fraction = 0)
    @NotNull
    @Column(name = "report_status",nullable = false,precision = 10)
    public int getJReportStatus() {
        return jReportStatus;
    }

    public void setJReportStatus(int jReportStatus) {
        this.jReportStatus = jReportStatus;
    }

    @Digits(integer =10,fraction = 0)
    @NotNull
    @Column(name = "adjudication",nullable = false,precision = 10)
    public int getJAdjudicationStatus() {
        return jAdjudicationStatus;
    }

    public void setJAdjudicationStatus(int jAdjudicationStatus) {
        this.jAdjudicationStatus = jAdjudicationStatus;
    }
}
