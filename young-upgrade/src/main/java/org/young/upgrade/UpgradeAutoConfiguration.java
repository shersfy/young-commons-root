package org.young.upgrade;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;

@Order(PriorityOrdered.HIGHEST_PRECEDENCE)
@Configuration
@EnableConfigurationProperties(UpgradeProperties.class)
@ConditionalOnProperty(prefix=UpgradeProperties.PREFIX, value="enabled", havingValue="true")
public class UpgradeAutoConfiguration implements ApplicationListener<ContextRefreshedEvent>{
	
	private boolean upgraded;
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UpgradeProperties upgradeProperties;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (upgraded) {
			return;
		}
		RunUpgrade upgrade = new RunUpgrade(upgradeProperties);
		upgrade.upgradeDb(dataSource);
		upgraded = true;
	}

}
