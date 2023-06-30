package com.zemoso.checkr.db.repository;

import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.db.contract.IReportCourtSearch;
import com.zemoso.checkr.db.domain.TReportCourtSearch;
import com.zemoso.checkr.db.irepository.IReportCourtSearchRepository;
import com.zemoso.checkr.db.wrapper.OReportCourtSearch;
import com.zemoso.checkr.dto.CReportCourtSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReportCourtSearchRepository extends ApplicationRepository {

    public ReportCourtSearchRepository(){
        super(ReportCourtSearchRepository.class);
    }

    @Autowired
    private IReportCourtSearchRepository iReportCourtSearchRepository;

    public List<IReportCourtSearch> getAll(){
        return iReportCourtSearchRepository.findAllByJStatus(ERowStatus.ACTIVE.getJValue()).stream().map(OReportCourtSearch::create).collect(Collectors.toList());
    }

    public IReportCourtSearch get(int jId){
        return OReportCourtSearch.create(iReportCourtSearchRepository.findByJIdAndJStatus(jId, ERowStatus.ACTIVE.getJValue()));
    }

    public IReportCourtSearch create(CReportCourtSearch cReportCourtSearch){

        try {
            TReportCourtSearch tReportCourtSearch = new TReportCourtSearch();

            tReportCourtSearch.setJCourtSearchStatus(cReportCourtSearch.getJCourtSearchStatus());

            tReportCourtSearch.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tReportCourtSearch.setJStatus(ERowStatus.ACTIVE.getJValue());

            tReportCourtSearch = iReportCourtSearchRepository.save(tReportCourtSearch);

            return OReportCourtSearch.create(tReportCourtSearch);
        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

    public IReportCourtSearch update(IReportCourtSearch iReportCourtSearch, CReportCourtSearch cReportCourtSearch){

        try {
            TReportCourtSearch tReportCourtSearch = iReportCourtSearchRepository.findById(iReportCourtSearch.getJId()).orElse(null);

            if(tReportCourtSearch==null){
                return  null;
            }

            tReportCourtSearch.setJCourtSearchStatus(cReportCourtSearch.getJCourtSearchStatus());

            tReportCourtSearch.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tReportCourtSearch.setJStatus(cReportCourtSearch.getJStatus());

            tReportCourtSearch = iReportCourtSearchRepository.save(tReportCourtSearch);

            return OReportCourtSearch.create(tReportCourtSearch);

        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

}
