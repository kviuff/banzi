package com.bootdo.monitoring.service.impl;

import com.bootdo.monitoring.vo.EchartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.monitoring.dao.HistoryDao;
import com.bootdo.monitoring.domain.HistoryDO;
import com.bootdo.monitoring.service.HistoryService;



@Service
public class HistoryServiceImpl implements HistoryService {
	@Autowired
	private HistoryDao historyDao;
	
	@Override
	public HistoryDO get(Long id){
		return historyDao.get(id);
	}
	
	@Override
	public List<HistoryDO> list(Map<String, Object> map){
		return historyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return historyDao.count(map);
	}
	
	@Override
	public int save(HistoryDO history){
		return historyDao.save(history);
	}
	
	@Override
	public int update(HistoryDO history){
		return historyDao.update(history);
	}
	
	@Override
	public int remove(Long id){
		return historyDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return historyDao.batchRemove(ids);
	}

	@Override
	public List<EchartVo> getGroupXf(EchartVo vo) {
		List<EchartVo> list1 = historyDao.getGroupXf(vo);
		if(list1.size()==0){
			return list1;
		}
		List<Integer> lists = new ArrayList<>();
		//把存在的时间放到集合内
		for(EchartVo e : list1){
			lists.add(e.getHour());
		}
		//从0进行循环到当前最大时间 因为最大时间已经存在 所以循环到最大值的前一位即可
		for(int i = 0; i< Collections.max(lists); i++){
			//如果存在则跳过当前循环 如果不存在 则插入一个
			if(lists.contains(i)){
				continue;
			}else{
				EchartVo echartVo = new EchartVo();
				echartVo.setHour(i);
				echartVo.setPm("0");
				list1.add(echartVo);
			}
		}
		Collections.sort(list1, new Comparator<EchartVo>(){
			/*
             * int compare(Student o1, Student o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
			public int compare(EchartVo o1, EchartVo o2) {

				//按照学生的年龄进行升序排列
				if(o1.getHour() > o2.getHour()){
					return 1;
				}
				if(o1.getHour() == o2.getHour()){
					return 0;
				}
				return -1;
			}
		});
		return list1;
	}

	@Override
	public List<EchartVo> getGroupYs(EchartVo vo) {
		List<EchartVo> list1 = historyDao.getGroupYs(vo);
		if(list1.size()==0){
			return list1;
		}
		List<Integer> lists = new ArrayList<>();
		//把存在的时间放到集合内
		for(EchartVo e : list1){
			lists.add(e.getHour());
		}
		//从0进行循环到当前最大时间 因为最大时间已经存在 所以循环到最大值的前一位即可
		for(int i = 0; i< Collections.max(lists); i++){
			//如果存在则跳过当前循环 如果不存在 则插入一个
			if(lists.contains(i)){
				continue;
			}else{
				EchartVo echartVo = new EchartVo();
				echartVo.setHour(i);
				echartVo.setRaw("0");
				echartVo.setPurification("0");
				list1.add(echartVo);
			}
		}
		Collections.sort(list1, new Comparator<EchartVo>(){
			/*
             * int compare(Student o1, Student o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
			public int compare(EchartVo e1, EchartVo e2) {

				//按照学生的年龄进行升序排列
				if(e1.getHour() > e2.getHour()){
					return 1;
				}
				if(e1.getHour() == e2.getHour()){
					return 0;
				}
				return -1;
			}
		});
		return list1;

	}


}
