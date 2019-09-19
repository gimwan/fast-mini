package com.fast.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MVipcartMapper;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MViptype;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipService;
import com.fast.service.IVipcartService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

/**
 * 购物袋
 * @author J
 *
 */
@Service
public class VipcartServiceImpl implements IVipcartService, Serializable {
	
private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVipcartMapper vipcartMapper;
	
	@Autowired
	IVipService iVipService;
	
	@Autowired
	MViptypeMapper viptypeMapper;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	IMiniProgramService iMiniProgramService;

	@Override
	public Result queryVipcart(String openid, String appid, Integer kind) {
		Result result = new Result();

		try {
			if (Common.isEmpty(openid)) {
				result.setMessage("openid无效");
				return result;
			}
			Integer publicplatformid = 0;
			Result re = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (Common.isActive(re)) {
				MMiniprogram miniprogram = (MMiniprogram) re.getData();
				publicplatformid = miniprogram.getPublicplatformid();
			}	
			Result r = iVipService.queryVipByOpenid(appid, openid);
			if (Common.isActive(r)) {
				Integer vipid = Integer.valueOf(r.getId().toString());
				
				// 会员折扣
				BigDecimal discount = BigDecimal.ONE;
				HashMap<String, Object> map = (HashMap<String, Object>) r.getData();
				MViptype viptype = viptypeMapper.selectByPrimaryKey(Integer.valueOf(map.get("typeid").toString()));
				if (viptype != null && viptype.getId() != null) {
					discount = viptype.getDiscount() == null ? BigDecimal.ONE : viptype.getDiscount();
				}				
				
				String sql = "select a.id,a.goodsid,a.colorid,a.sizeid,a.patternid,a.quantity as qty,isnull(f.quantity,0) as stockqty,isnull(f.barcode,'') as barcode,"
						+ "e.code as goodsno,e.name as goodsname,e.photourl as imageurl,e.showcolor,e.showsize,e.showpattern,"
						+ "e.onsale as atlist,e.shiptype,isnull(e.exchangepoint,0) as point,e.price as unitprice,a.quantity*isnull(e.price,0) as amt,e.kind,"
						+ "b.name as color,c.name as pattern,d.name as size "
						+ "from m_vipcart a "
						+ "inner join m_goods e on a.goodsid=e.id "
						+ "left join m_color b on a.colorid=b.id "
						+ "left join m_pattern c on a.patternid=c.id "
						+ "left join m_size d on a.sizeid=d.id "
						+ "left join m_goodssku f on a.goodsid=f.goodsid and a.colorid=f.colorid and a.sizeid=f.sizeid and a.patternid=f.patternid "
						+ "where a.vipid="+vipid + " and e.onsale=1 and e.onlyshow<>1 and a.type=" + kind + " and a.publicplatformid=" + publicplatformid;
				List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
				
				// 会员折扣
				for (int i = 0; i < list.size(); i++) {
					Integer quantity = list.get(i).get("quantity") == null ? 0 : Integer.valueOf(list.get(i).get("quantity").toString().trim());
					BigDecimal unitprice = list.get(i).get("unitprice") == null ? BigDecimal.ZERO : new BigDecimal(list.get(i).get("unitprice").toString().trim());
					BigDecimal newPrice = unitprice.multiply(discount).setScale(2, BigDecimal.ROUND_HALF_UP);
					BigDecimal newAmt = newPrice.multiply(new BigDecimal(quantity)).setScale(2, BigDecimal.ROUND_HALF_UP);
					list.get(i).put("unitprice", newPrice);
					list.get(i).put("amt", newAmt);
				}
				result.setData(list);
				result.setErrcode(Integer.valueOf(0));
				result.setId(vipid);
			} else {
				result.setMessage("会员无效");
				return result;
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipcartServiceImpl.queryVipcart报错：", e);
		}

		return result;
	}

}
