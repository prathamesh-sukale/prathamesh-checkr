package com.zemoso.checkr.model.base;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "jId",
        "dtCreate",
        "jStatus"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MBase implements Serializable {

    @JsonProperty("jId")
    private int jId;
    @JsonProperty("dtCreate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtCreate;
    @JsonProperty("jStatus")
    private int jStatus;

    @JsonProperty("jId")
    public int getJId() {
        return jId;
    }

    @JsonProperty("jId")
    public void setJId(int jId) {
        this.jId = jId;
    }

    @JsonProperty("dtCreate")
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    @JsonProperty("dtCreate")
    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    @JsonProperty("jStatus")
    public int getJStatus() {
        return jStatus;
    }

    @JsonProperty("jStatus")
    public void setJStatus(int jStatus) {
        this.jStatus = jStatus;
    }

}