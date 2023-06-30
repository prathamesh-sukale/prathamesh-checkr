package com.zemoso.checkr.db.repository;

import com.zemoso.checkr.common.enums.EChargeStatus;
import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.db.contract.IPreAdverseCharge;
import com.zemoso.checkr.db.domain.TCharge;
import com.zemoso.checkr.db.domain.TPreAdverseCharge;
import com.zemoso.checkr.db.irepository.IChargeRepository;
import com.zemoso.checkr.db.irepository.IPreAdverseChargeRepository;
import com.zemoso.checkr.db.wrapper.OPreAdverseCharge;
import com.zemoso.checkr.dto.CPreAdverseCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PreAdverseChargeRepository extends ApplicationRepository {

    public PreAdverseChargeRepository(){
        super(PreAdverseChargeRepository.class);
    }

    @Autowired
    private IPreAdverseChargeRepository iPreAdverseChargeRepository;

    @Autowired
    IChargeRepository iChargeRepository;

    public List<IPreAdverseCharge> getAll(){
        return iPreAdverseChargeRepository.findAllByJStatus(ERowStatus.ACTIVE.getJValue()).stream().map(OPreAdverseCharge::create).collect(Collectors.toList());
    }

    public IPreAdverseCharge get(int jId){
        return OPreAdverseCharge.create(iPreAdverseChargeRepository.findByJIdAndJStatus(jId, ERowStatus.ACTIVE.getJValue()));
    }

    public IPreAdverseCharge create(CPreAdverseCharge cPreAdverseCharge){

        try {
            TCharge tCharge = iChargeRepository.findById(cPreAdverseCharge.getICharge().getJId()).orElse(null);

            TPreAdverseCharge tPreAdverseCharge = new TPreAdverseCharge();

            tPreAdverseCharge.setJChargeStatus(EChargeStatus.ACTIVE.getJValue());
            tPreAdverseCharge.setTCharge(tCharge);

            tPreAdverseCharge.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tPreAdverseCharge.setJStatus(ERowStatus.ACTIVE.getJValue());

            tPreAdverseCharge = iPreAdverseChargeRepository.save(tPreAdverseCharge);

            return OPreAdverseCharge.create(tPreAdverseCharge);
        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

    public IPreAdverseCharge update(IPreAdverseCharge iPreAdverseCharge, CPreAdverseCharge cPreAdverseCharge){

        try {
            TPreAdverseCharge tPreAdverseCharge = iPreAdverseChargeRepository.findById(iPreAdverseCharge.getJId()).orElse(null);

            if(tPreAdverseCharge==null){
                return  null;
            }

            tPreAdverseCharge.setJChargeStatus(cPreAdverseCharge.getJChargeStatus());

            tPreAdverseCharge.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
            tPreAdverseCharge.setJStatus(cPreAdverseCharge.getJStatus());

            tPreAdverseCharge = iPreAdverseChargeRepository.save(tPreAdverseCharge);

            return OPreAdverseCharge.create(tPreAdverseCharge);

        }catch (Exception e){
            logger.error(e.toString());
        }

        return null;
    }

}
