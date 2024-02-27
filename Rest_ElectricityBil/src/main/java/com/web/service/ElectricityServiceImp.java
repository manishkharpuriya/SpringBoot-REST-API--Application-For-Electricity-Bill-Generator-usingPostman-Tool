
package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entity.Electricity;
import com.web.repo.ElectricityRepo;
@Service
public class ElectricityServiceImp implements ElectricityService {
	
	@Autowired
	private ElectricityRepo electricityRepo;

	@Override
	public Electricity saveRecord(Electricity electricity) {
		
		int unts = 0;
		double total = 0;
		unts = electricity.getCurrent_reading()-electricity.getPrevious_reading();
		electricity.setUnits(unts);
		if (unts<300)
		{
			total = unts*1.75;
		}
		else if (unts>=300 && unts<500)
		{
			total = unts*3.75;
		}
		else if (unts>=500)
		{
			total = unts*7.35;
		}
		electricity.setTotalBill(total);
		Electricity e = electricityRepo.save(electricity);
	    return e;
	}

	@Override
	public Electricity updateRecord(Electricity electricity, int cid) {
		
		Electricity oldRecord = electricityRepo.findById(cid).get();
		oldRecord.setCname(electricity.getCname());
		oldRecord.setCurrent_reading(electricity.getCurrent_reading());
		oldRecord.setPrevious_reading(electricity.getPrevious_reading());
		
		int unts = 0;
		double tota = 0.0;
		unts = electricity.getCurrent_reading()-electricity.getPrevious_reading();
		electricity.setUnits(unts);
		if (unts<300)
		{
			tota = unts*1.75;
		}
		else if (unts>=300 && unts<500)
		{
			tota = unts*3.75;
		}
		else if (unts>=500)
		{
			tota = unts*7.35;
		}
		
		oldRecord.setUnits(unts);
		oldRecord.setTotalBill(tota);
		Electricity e = electricityRepo.save(oldRecord);
		return e;
	}

	@Override
	public void deleteRecord(int cid) {
		electricityRepo.deleteById(cid);

	}

	@Override
	public Electricity getOneRecord(int cid) {
		Electricity get = electricityRepo.findById(cid).get();
		return get;
	}

	@Override
	public List<Electricity> getAllRecord() {
		List<Electricity> getAll = electricityRepo.findAll();
		return getAll;
	}

}
