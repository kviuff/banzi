package com.bootdo.monitoring.service.impl;

import com.bootdo.lamp.LampImpl;
import com.bootdo.lamp.vo.LampVo;
import com.bootdo.monitoring.domain.LightCtrVo;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.LightService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.monitoring.dao.LampDao;
import com.bootdo.monitoring.domain.LampDO;


@Service
public class LightServiceImpl implements LightService {
	@Autowired
	private LampDao lampDao;
	
	@Override
	public LampDO get(String returnId){
		return lampDao.get(returnId);
	}
	
	@Override
	public List<LampDO> list(Map<String, Object> map){
		return lampDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lampDao.count(map);
	}
	
	@Override
	public int save(LampDO lamp){
		return lampDao.save(lamp);
	}
	
	@Override
	public int update(LampDO lamp){
		return lampDao.update(lamp);
	}
	
	@Override
	public int remove(LampDO lamp){
		return lampDao.remove(lamp);
	}
	
	@Override
	public int batchRemove(String[] returnIds){
		return lampDao.batchRemove(returnIds);
	}

	@Override
	public List<LampDO> allList(Map<String, Object> map) {
		return lampDao.allList(map);
	}

	/**
	 * 获取灯列表 对灯表进行更新
	 * @param
	 * @return
     */
	@Override
	public String getLight(MonitoringDO monitoringDO1){

		Map<String, Object> map = new HashMap<>();
		map.put("controller",monitoringDO1.getContollerAddr());
		map.put("userId",monitoringDO1.getUserId());
		map.put("address",monitoringDO1.getReturnid());
		//获取到控制器下库里面所有的灯
		List<LampDO> lampDOList = allList(map);

		//用户登录
		Map params1 = new HashMap();
		params1.put("userName", monitoringDO1.getLanerUserName());
		params1.put("userPwd", monitoringDO1.getLanerPassword());
		String result = LampImpl.loginToLamp(params1); // 登录
		JSONObject jsonObject = JSONObject.fromObject(result);
		String resultCode = jsonObject.get("code").toString(); // 获取注册返回值
		if ("0".equals(resultCode)) {
			//获取tokenId
			String tokenId = jsonObject.getString("tokenId");

			Map params3 = new HashMap();
			params3.put("tokenId", tokenId);
			params3.put("toAddr", monitoringDO1.getContollerAddr());
			String resultList = LampImpl.getRegionListUrl(params3);
			List<LightCtrVo> LightCtrVoList = new ArrayList<>();
			jsonObject = JSONObject.fromObject(resultList);
			String dataStr = jsonObject.get("data").toString();
			JSONArray array = JSONArray.fromObject(dataStr);
			Iterator<JSONArray> itr = array.iterator();
			List<LampDO> lampList= new ArrayList<>();
			while (itr.hasNext()) {
				//创建控制器区域列表
				JSONObject temp = JSONObject.fromObject(itr.next());
				String address = String.valueOf(temp.get("id"));
				String regionName = String.valueOf(temp.get("name"));
				if(address.equals(monitoringDO1.getReturnid())){
					JSONArray ChannelList = temp.getJSONArray("ChannelList");
					List<LampDO> lampVoList = new ArrayList<>();
					Iterator<JSONArray> Channel = ChannelList.iterator();
					while (Channel.hasNext()) {
						//获取区域下的灯
						LampDO lampDO = new LampDO();
						JSONObject channel = JSONObject.fromObject(Channel.next());

						lampDO.setName(String.valueOf(channel.get("name")));
						lampDO.setReturnId(String.valueOf(channel.get("refid")));
						lampDO.setController(monitoringDO1.getContollerAddr());
						lampDO.setUserId(String.valueOf(monitoringDO1.getUserId()));
						lampDO.setBelongRegion(regionName);
						lampDO.setAddress(address);//区id
						if (findLamp(lampDOList,lampDO.getReturnId())) {
							lampDao.update(lampDO);
						} else {
							lampDao.save(lampDO);
						}
						lampList.add(lampDO);
					}
				}

			}



			//遍历库里此控制器下所有的灯
			for(LampDO lampDO : lampDOList){
				if (!findLamp(lampList,lampDO.getReturnId())) {
					lampDao.remove(lampDO);
				}
			}


		} else {
			System.out.println(jsonObject.get("message").toString());
		}
		return monitoringDO1.getContollerAddr();
	}

	//判断返回id是否在集合中存在
	public boolean findLamp(List<LampDO> list, String id) {
		for(LampDO u : list) {
			if(id.equals(u.getReturnId())) {
				return true;
			}
		}
		return false;
	}

	//判断返回id是否在集合中存在
	public boolean findLocalLamp(List<LampVo> list, String id) {
		for(LampVo u : list) {
			if(id.equals(u.getId())) {
				return true;
			}
		}
		return false;
	}

}
