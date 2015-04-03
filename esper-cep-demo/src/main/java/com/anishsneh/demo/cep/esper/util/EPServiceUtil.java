package com.anishsneh.demo.cep.esper.util;

import com.anishsneh.demo.cep.esper.event.AlarmEvent;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

/**
 * The Class EPServiceUtil.
 * 
 * @author Anish Sneh
 */
public class EPServiceUtil {
	
	/**
	 * Gets the ep service provider.
	 *
	 * @return the ep service provider
	 */
	public static EPServiceProvider getEpServiceProvider(){
		final Configuration epConfig = new Configuration();
		epConfig.addEventTypeAutoName(AlarmEvent.class.getPackage().getName());
		final EPServiceProvider epServiceProvider = EPServiceProviderManager.getDefaultProvider(epConfig);
		return epServiceProvider;
	}
}
