package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MRegionMapper;
import com.fast.base.data.entity.MRegion;
import com.fast.base.data.entity.MRegionExample;
import com.fast.service.IRegionService;
import com.fast.system.log.FastLog;

/**
 * 省市县
 * @author J
 *
 */
@Service
public class RegionServiceImpl implements IRegionService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MRegionMapper mRegionMapper;

	@Override
	public Result region() {
		Result result = new Result();

		try {
			MRegionExample mRegionExample = new MRegionExample();
			mRegionExample.createCriteria().andTypeNotEqualTo(Byte.valueOf("1"));
			mRegionExample.setOrderByClause("type asc");
			List<MRegion> list = mRegionMapper.selectByExample(mRegionExample);
			if (list != null && list.size() > 0) {
				List<MRegion> province = new ArrayList<>();
				List<MRegion> city = new ArrayList<>();
				List<MRegion> county = new ArrayList<>();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getType().intValue() == 2) {
						province.add(list.get(i));
					} else if (list.get(i).getType().intValue() == 3) {
						city.add(list.get(i));
					} else {
						county.add(list.get(i));
					}
				}
				
				List<HashMap<String, Object>> region = categoriseRegion(province, city, county);
				result.setErrcode(Integer.valueOf("0"));
				result.setData(region);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用RegionServiceImpl.region报错：", e);
		}

		return result;
	}
	
	public List<HashMap<String, Object>> categoriseRegion(List<MRegion> province, List<MRegion> city, List<MRegion> county) {
		List<HashMap<String, Object>> provinceList = new ArrayList<>();
		for (int i = 0; i < province.size(); i++) {
			HashMap<String, Object> provinceMap = new HashMap<>();
			provinceMap.put("id", province.get(i).getId());
			provinceMap.put("name", province.get(i).getName());			
			List<HashMap<String, Object>> cityList = new ArrayList<>();
			for (int j = 0; j < city.size(); j++) {
				if (province.get(i).getId().intValue() == city.get(j).getParentid().intValue()) {
					HashMap<String, Object> cityMap = new HashMap<>();
					cityMap.put("id", city.get(j).getId());
					cityMap.put("name", city.get(j).getName());					
					List<HashMap<String, Object>> countyList = new ArrayList<>();
					for (int k = 0; k < county.size(); k++) {
						if (city.get(j).getId().intValue() == county.get(k).getParentid().intValue()) {
							HashMap<String, Object> countyMap = new HashMap<>();
							countyMap.put("id", county.get(k).getId());
							countyMap.put("name", county.get(k).getName());
							countyList.add(countyMap);
						}
					}					
					cityMap.put("regions", countyList);
					cityList.add(cityMap);
				}
			}			
			provinceMap.put("regions", cityList);
			provinceList.add(provinceMap);
		}
		return provinceList;
	}

}
