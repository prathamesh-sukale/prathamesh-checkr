package com.zemoso.checkr.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zemoso.checkr.model.MUser;
import com.zemoso.checkr.model.base.ApiResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "users"
})
public class GetAllUsersResp extends ApiResponse {

    @JsonProperty("users")
    private List<MUser> mUsers;

    @JsonProperty("users")
    public List<MUser> getMUsers() {
        return mUsers;
    }

    @JsonProperty("users")
    public void setMUsers(List<MUser> mUsers) {
        this.mUsers = mUsers;
    }
}
