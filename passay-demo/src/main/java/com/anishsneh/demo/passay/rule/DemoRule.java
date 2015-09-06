package com.anishsneh.demo.passay.rule;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.passay.PasswordData;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.RuleResultDetail;

/**
 * The Class DemoRule.
 * 
 * @author Anish Sneh
 */
public class DemoRule implements Rule{
	
	/** The Constant ERROR_CODE. */
	public static final String ERROR_CODE = "CONTAINS_RESTRICTED_STRINGS";
	
	/** The restrictions. */
	private List<String> restrictions;

	/* (non-Javadoc)
	 * @see org.passay.Rule#validate(org.passay.PasswordData)
	 */
	@Override
	public RuleResult validate(final PasswordData passwordData) {
		final String password = passwordData.getPassword();
		final RuleResult ruleResult = new RuleResult();
		for(final String tmpRestriction : restrictions){
			final boolean invalid = password.toLowerCase().contains(tmpRestriction);
			if(invalid){
				ruleResult.getDetails().add(new RuleResultDetail(ERROR_CODE, createRuleResultDetailParameters()));
				ruleResult.setValid(false);
				return ruleResult;
			}
		}		
		ruleResult.setValid(true);
		return ruleResult;
	}
	
	/**
	 * Adds the restriction.
	 *
	 * @param restriction the restriction
	 */
	public void addRestriction(final String restriction){
		Objects.requireNonNull(restriction);
		if(null == restrictions){
			restrictions = new ArrayList<String>();
		}
		restrictions.add(restriction.toLowerCase());
	}
	
	/**
	 * Creates the rule result detail parameters.
	 *
	 * @return the map
	 */
	protected Map<String, Object> createRuleResultDetailParameters()
	  {
	    final Map<String, Object> m = new LinkedHashMap<>();
	    m.put("restrictions", restrictions.toString());	    
	    return m;
	  }
}
