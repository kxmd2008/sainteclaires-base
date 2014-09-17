package org.luis.sainteclaires.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.luis.basic.domain.FilterAttributes;
import org.luis.sainteclaires.base.bean.Config;
import org.luis.sainteclaires.base.bean.service.ServiceFactory;

/**
 * 序列号生成器
 * 
 * @author Guoliang.Li
 * @date 2014-9-11
 * @since 2.0.8.0
 */
public class SequenceProvider {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 每天都从1开始成序列号
	 * @param type
	 * @return
	 */
	public String genericSequence(String type) {
		return genericSequence(type, false);
	}

	/**
	 * 生成序列号
	 * 
	 * @param key
	 *            序列号类型
	 * @param ignoreDate
	 *            是否忽略日期，即是否每天都从1开始成序列号
	 * @return
	 */
	public synchronized String genericSequence(String key, boolean ignoreDate) {
		FilterAttributes fa = FilterAttributes.blank().add("key", key);
		List<Config> list = ServiceFactory.getConfigService().findByAttributes(fa);
		Config config = null;
		if(list.size() > 0){
			config = list.get(0);
			String sequence = (Long.parseLong(config.getValue()) + 1) + "";
			config.setValue(sequence);
		} else {
			config = createNewConfig(key);
		}
		if (!ignoreDate) {
			String date = sdf.format(new Date());
			String currDate = config.getNote();
			// 判断是否新的一天
			if (!date.equals(currDate)) {
				config.setNote(date);
				config.setValue("1");
			}
		} 
		ServiceFactory.getConfigService().update(config);
		return config.getValue();
		
	}

	private Config createNewConfig(String key) {
		Config config = new Config();
		config.setDescription("序列号");
		config.setValue("1");
		config.setKey(key);
		config.setNote(sdf.format(new Date()));
		return config;
	}
	
	private static final SequenceProvider instance = new SequenceProvider();
	
	public static SequenceProvider getInstance(){
		return instance;
	}
	
	private SequenceProvider(){
		
	}

}
