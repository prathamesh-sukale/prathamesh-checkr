package com.zemoso.checkr.db.repository;

import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.db.contract.ICharge;
import com.zemoso.checkr.db.domain.TCharge;
import com.zemoso.checkr.db.irepository.IChargeRepository;
import com.zemoso.checkr.db.wrapper.OCharge;
import com.zemoso.checkr.dto.CCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChargeRepository extends ApplicationRepository {

    public ChargeRepository(){
        super(ChargeRepository.class);
    }

    @Autowired
    private IChargeRepository iChargeRepository;

    public List<ICharge> getAll(){
        return iChargeRepository.findAllByJStatus(ERowStatus.ACTIVE.getJValue()).stream().map(OCharge::create).collect(Collectors.toList());
    }

    public ICharge get(int jId){
        return OCharge.create(iChargeRepository.findByJIdAndJStatus(jId, ERowStatus.ACTIVE.getJValue()));
    }

    public ICharge create(CCharge cCharge){

        try {
            TCharge tCharge = new TCharge();

            tCharge.setSName(cCharge.getSName());

            tCharge.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tCharge.setJStatus(ERowStatus.ACTIVE.getJValue());

            tCharge = iChargeRepository.save(tCharge);

            return OCharge.create(tCharge);
        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

    public ICharge update(ICharge iCharge, CCharge cCharge){

        try {
            TCharge tCharge = iChargeRepository.findById(iCharge.getJId()).orElse(null);

            if(tCharge==null){
                return  null;
            }

            tCharge.setSName(cCharge.getSName());

            tCharge.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tCharge.setJStatus(cCharge.getJStatus());

            tCharge = iChargeRepository.save(tCharge);

            return OCharge.create(tCharge);

        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

}
