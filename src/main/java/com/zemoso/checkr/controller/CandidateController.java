package com.zemoso.checkr.controller;

import com.zemoso.checkr.core.iservice.ICandidateService;
import com.zemoso.checkr.model.MUser;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.request.AddOrEditCandidateReq;
import com.zemoso.checkr.model.request.GetAllCandidatesReq;
import com.zemoso.checkr.model.response.GetAllCandidatesResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    ICandidateService iCandidateService;

    @GetMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllCandidatesResp> getAll(@RequestParam(name = "userId") Integer jUserId,
                                                       @RequestParam(name = "search", required = false) String sSearch,
                                                       @RequestParam(name = "statuses", required = false) List<Integer> jStatuses,
                                                       @RequestParam(name = "adjudications", required = false) List<Integer> jAdjudications){
        GetAllCandidatesReq req = new GetAllCandidatesReq();
        req.setMUser(new MUser());
        req.getMUser().setJId(jUserId);
        req.setSSearch(sSearch);
        req.setJStatuses(jStatuses);
        req.setJAdjudications(jAdjudications);

        return  ResponseEntity.ok(iCandidateService.getAll(req));
    }

    @GetMapping(value = "/{jId}",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllCandidatesResp> get(@PathVariable int jId){
        return  ResponseEntity.ok(iCandidateService.get(jId));
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> add(@RequestBody AddOrEditCandidateReq req){
        return ResponseEntity.ok(iCandidateService.add(req));
    }

    @PatchMapping(value = "/{jId}",  consumes = APPLICATION_JSON_VALUE,  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> edit(@PathVariable int jId,@RequestBody AddOrEditCandidateReq req){
        return  ResponseEntity.ok(iCandidateService.edit(jId,req));
    }

    @DeleteMapping(value = "/{jId}",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@PathVariable int jId){
        return  ResponseEntity.ok(iCandidateService.delete(jId));
    }
}
