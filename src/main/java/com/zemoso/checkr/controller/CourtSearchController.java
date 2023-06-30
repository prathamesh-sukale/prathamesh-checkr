package com.zemoso.checkr.controller;

import com.zemoso.checkr.core.iservice.ICourtSearchService;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.request.AddOrEditCourtSearchReq;
import com.zemoso.checkr.model.response.GetAllCourtSearchesResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/court-searches")
public class CourtSearchController {

    @Autowired
    ICourtSearchService iCourtSearchService;

    @GetMapping(value = "",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllCourtSearchesResp> getAll(){
        return  ResponseEntity.ok(iCourtSearchService.getAll());
    }

    @GetMapping(value = "/{jId}",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllCourtSearchesResp> get(@PathVariable int jId){
        return  ResponseEntity.ok(iCourtSearchService.get(jId));
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> add(@RequestBody AddOrEditCourtSearchReq req){
        return ResponseEntity.ok(iCourtSearchService.add(req));
    }

    @PatchMapping(value = "/{jId}",  consumes = APPLICATION_JSON_VALUE,  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> edit(@PathVariable int jId,@RequestBody AddOrEditCourtSearchReq req){
        return  ResponseEntity.ok(iCourtSearchService.edit(jId,req));
    }

    @DeleteMapping(value = "/{jId}",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@PathVariable int jId){
        return  ResponseEntity.ok(iCourtSearchService.delete(jId));
    }
}
