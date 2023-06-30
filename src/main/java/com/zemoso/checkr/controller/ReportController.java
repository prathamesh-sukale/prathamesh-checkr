package com.zemoso.checkr.controller;

import com.zemoso.checkr.core.iservice.IReportService;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.request.AddReportReq;
import com.zemoso.checkr.model.response.GetReportResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    IReportService iReportService;

    @GetMapping(value = "/candidate/{jId}",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetReportResp> get(@PathVariable int jId){
        return  ResponseEntity.ok(iReportService.get(jId));
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> add(@RequestBody AddReportReq req){
        return ResponseEntity.ok(iReportService.add(req));
    }

    @PostMapping(value = "/pre-adverse-action", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> addPreAdverseAction(@RequestBody AddReportReq req){
        return ResponseEntity.ok(iReportService.addPreAdverseAction(req));
    }

    @PatchMapping(value = "/candidate/{jId}/engage", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> engage(@PathVariable int jId){
        return  ResponseEntity.ok(iReportService.engage(jId));
    }

    @DeleteMapping(value = "/{jId}",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@PathVariable int jId){
        return  ResponseEntity.ok(iReportService.delete(jId));
    }
}
