package com.zemoso.checkr.controller;

import com.zemoso.checkr.core.iservice.IChargeService;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.request.AddOrEditChargeReq;
import com.zemoso.checkr.model.response.GetAllChargesResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/charges")
public class ChargeController {

    @Autowired
    IChargeService iChargeService;

    @GetMapping(value = "",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllChargesResp> getAll(){
        return  ResponseEntity.ok(iChargeService.getAll());
    }

    @GetMapping(value = "/{jId}",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllChargesResp> get(@PathVariable int jId){
        return  ResponseEntity.ok(iChargeService.get(jId));
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> add(@RequestBody AddOrEditChargeReq req){
        return ResponseEntity.ok(iChargeService.add(req));
    }

    @PatchMapping(value = "/{jId}",  consumes = APPLICATION_JSON_VALUE,  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> edit(@PathVariable int jId,@RequestBody AddOrEditChargeReq req){
        return  ResponseEntity.ok(iChargeService.edit(jId,req));
    }

    @DeleteMapping(value = "/{jId}",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@PathVariable int jId){
        return  ResponseEntity.ok(iChargeService.delete(jId));
    }
}
