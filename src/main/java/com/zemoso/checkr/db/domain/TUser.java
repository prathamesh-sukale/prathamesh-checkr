package com.zemoso.checkr.db.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class TUser extends TBase implements Serializable {

    private String sEmail;
    private String sPassword;
    private int jRole;

    //Many-to-Many
    private List<TCandidate> tCandidates = new ArrayList<>();

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
    @Size(max = 512)
    @Column(name = "password", nullable = false, length = 512)
    public String getSPassword() {
        return sPassword;
    }

    public void setSPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    @Digits(integer =10,fraction = 0)
    @NotNull
    @Column(name = "role",nullable = false,precision = 10)
    public int getJRole() {
        return jRole;
    }

    public void setJRole(int jRole) {
        this.jRole = jRole;
    }

    @NotNull
    @JoinTable(name = "user_candidate_mapper",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id"))
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = TCandidate.class)
    @ElementCollection(targetClass=TCandidate.class)
    public List<TCandidate> getTCandidates() {
        return tCandidates;
    }

    public void setTCandidates(List<TCandidate> tCandidates) {
        this.tCandidates = tCandidates;
    }
}
