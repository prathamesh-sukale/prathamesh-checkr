package com.zemoso.checkr.controller;

import com.zemoso.checkr.core.iservice.IUserService;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.request.AddOrEditUserReq;
import com.zemoso.checkr.model.response.GetAllUsersResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    IUserService iUserService;

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllUsersResp> getAll(){
        return  ResponseEntity.ok(iUserService.getAll());
    }

    @GetMapping(value = "/{jId}",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllUsersResp> get(@PathVariable int jId){
        return  ResponseEntity.ok(iUserService.get(jId));
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> add(@RequestBody AddOrEditUserReq req){
        return ResponseEntity.ok(iUserService.add(req));
    }

    @PatchMapping(value = "/{jId}",  consumes = APPLICATION_JSON_VALUE,  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> edit(@PathVariable int jId,@RequestBody AddOrEditUserReq req){
        return  ResponseEntity.ok(iUserService.edit(jId,req));
    }

    @DeleteMapping(value = "/{jId}",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@PathVariable int jId){
        return  ResponseEntity.ok(iUserService.delete(jId));
    }
}
