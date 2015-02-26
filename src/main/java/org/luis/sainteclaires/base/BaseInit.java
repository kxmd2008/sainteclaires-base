package org.luis.sainteclaires.base;

import javax.annotation.PostConstruct;

import org.luis.basic.domain.FilterAttributes;
import org.luis.sainteclaires.base.bean.Config;
import org.luis.sainteclaires.base.bean.service.ServiceFactory;
import org.luis.sainteclaires.base.util.BaseUtil;
import org.springframework.stereotype.Component;

@Component
public class BaseInit {

	@PostConstruct
	public void init() {
		FilterAttributes fa = FilterAttributes.blank().add("key", "quarter")
				.add("type", "QUARTER");
		Config config = ServiceFactory.getConfigService().findOneByFilter(fa);
		if(config == null){
			config = new Config();
			config.setKey("quarter");
			config.setType("QUARTER");
			config.setValue("1");
			ServiceFactory.getConfigService().save(config);
		}
		BaseUtil.setCurrQuarter(config);
	}
}
