package com.zemoso.checkr.db.repository;

import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.db.contract.ICourtSearch;
import com.zemoso.checkr.db.domain.TCourtSearch;
import com.zemoso.checkr.db.irepository.ICourtSearchRepository;
import com.zemoso.checkr.db.wrapper.OCourtSearch;
import com.zemoso.checkr.dto.CCourtSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourtSearchRepository extends ApplicationRepository {

    public CourtSearchRepository(){
        super(CourtSearchRepository.class);
    }

    @Autowired
    private ICourtSearchRepository iCourtSearchRepository;

    public List<ICourtSearch> getAll(){
        return iCourtSearchRepository.findAllByJStatus(ERowStatus.ACTIVE.getJValue())
                .stream().map(OCourtSearch::create)
                .collect(Collectors.toList());
    }

    public ICourtSearch get(int jId){
        return OCourtSearch.create(iCourtSearchRepository.findByJIdAndJStatus(jId, ERowStatus.ACTIVE.getJValue()));
    }

    public ICourtSearch create(CCourtSearch cCourtSearch){

        try {
            TCourtSearch tCourtSearch = new TCourtSearch();

            tCourtSearch.setSName(cCourtSearch.getSName());

            tCourtSearch.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tCourtSearch.setJStatus(ERowStatus.ACTIVE.getJValue());

            tCourtSearch = iCourtSearchRepository.save(tCourtSearch);

            return OCourtSearch.create(tCourtSearch);
        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

    public ICourtSearch update(ICourtSearch iCourtSearch, CCourtSearch cCourtSearch){

        try {
            TCourtSearch tCourtSearch = iCourtSearchRepository.findById(iCourtSearch.getJId()).orElse(null);

            if(tCourtSearch==null){
                return  null;
            }

            tCourtSearch.setSName(cCourtSearch.getSName());

            tCourtSearch.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tCourtSearch.setJStatus(cCourtSearch.getJStatus());

            tCourtSearch = iCourtSearchRepository.save(tCourtSearch);

            return OCourtSearch.create(tCourtSearch);

        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

}
