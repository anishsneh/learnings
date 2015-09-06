package com.anishsneh.demo.passay.processor;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.passay.CharacterCharacteristicsRule;
import org.passay.CharacterRule;
import org.passay.DictionaryRule;
import org.passay.EnglishCharacterData;
import org.passay.EnglishSequenceData;
import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RepeatCharacterRegexRule;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.RuleResultDetail;
import org.passay.WhitespaceRule;
import org.passay.dictionary.WordListDictionary;
import org.passay.dictionary.WordLists;
import org.passay.dictionary.sort.ArraysSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.passay.rule.DemoRule;

/**
 * The Class PasswordProcessor.
 * 
 * @author Anish Sneh
 */
public class PasswordProcessor {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(PasswordProcessor.class);

	/**
	 * Gets the active rule.
	 *
	 * @return the active rules
	 * @throws Exception the exception
	 */
	public List<Rule> getActiveRules() throws Exception {

		final List<Rule> activeRuleList = new ArrayList<Rule>();

		// Positive match i.e. should have
		final CharacterCharacteristicsRule charRule = new CharacterCharacteristicsRule();
		final List<CharacterRule> characterRules = charRule.getRules();
		characterRules.add(new CharacterRule(EnglishCharacterData.Digit, 1));
		characterRules.add(new CharacterRule(EnglishCharacterData.Alphabetical, 1));
		characterRules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
		characterRules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
		characterRules.add(new CharacterRule(EnglishCharacterData.Special, 1));
		charRule.setNumberOfCharacteristics(5);
		LengthRule lengthRule = new LengthRule(8, 20);

		// Negative match i.e. should not have
		final WhitespaceRule whitespaceRule = new WhitespaceRule();
		final IllegalSequenceRule illegalAlphabeticalSequenceRule = new IllegalSequenceRule(EnglishSequenceData.Alphabetical);
		final IllegalSequenceRule illegalNumericalSequenceRule = new IllegalSequenceRule(EnglishSequenceData.Numerical);
		final IllegalSequenceRule illegalUSQwertySequenceRule = new IllegalSequenceRule(EnglishSequenceData.USQwerty);
		final RepeatCharacterRegexRule repeatCharacterRule = new RepeatCharacterRegexRule(4);
		final DictionaryRule dictionaryRule = new DictionaryRule(new WordListDictionary(WordLists.createFromReader(new FileReader[] { new FileReader(PasswordProcessor.class.getResource("/dictionary.txt").toURI().getPath()) }, true, new ArraysSort())));
		
		// Custom negative rule
		final DemoRule demoRule = new DemoRule();
		demoRule.addRestriction("Anish");
		demoRule.addRestriction("Sneh");

		// Add all rules to active list
		activeRuleList.add(charRule);
		activeRuleList.add(lengthRule);
		activeRuleList.add(whitespaceRule);
		activeRuleList.add(illegalAlphabeticalSequenceRule);
		activeRuleList.add(illegalNumericalSequenceRule);
		activeRuleList.add(illegalUSQwertySequenceRule);
		activeRuleList.add(repeatCharacterRule);
		activeRuleList.add(dictionaryRule);
		activeRuleList.add(demoRule);

		return activeRuleList;
	}

	/**
	 * Validate password.
	 *
	 * @param password the password
	 * @return the rule result
	 * @throws Exception the exception
	 */
	public RuleResult validatePassword(final String password) throws Exception {
		final PasswordValidator validator = new PasswordValidator(getActiveRules());
		final RuleResult ruleResult = validator.validate(new PasswordData(password));
		return ruleResult;
	}

	/**
	 * Process password.
	 *
	 * @param password the password
	 */
	public void processPassword(final String password) {
		RuleResult ruleResult;
		try {
			ruleResult = validatePassword(password);
			if (!ruleResult.isValid()) {
				for (final RuleResultDetail rrDetails : ruleResult.getDetails()) {
					logger.info(rrDetails.toString());
				}
			} else {
				logger.info("SUCCESS: Valid password chosen");
			}
		} catch (final Exception ex) {
			logger.error("Failed to validate password", ex);
		}

	}
}
