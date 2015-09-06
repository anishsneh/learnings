package com.anishsneh.demo.infinispan;

import java.io.Serializable;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Store;

/**
 * The Class Person.
 * 
 * @author Anish Sneh
 */
@Indexed(index = "personIndex")
public class Person implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The name. */
	@Field(store = Store.YES, analyze = Analyze.NO)
	private String name;
	
	/** The country. */
	@Field(store = Store.YES, analyze = Analyze.NO, indexNullAs = Field.DEFAULT_NULL_TOKEN)
	private String country;
	
	/** The age. */
	@Field(store = Store.YES, analyze = Analyze.NO, indexNullAs = Field.DEFAULT_NULL_TOKEN)
	@NumericField
	private Integer age;
	
	/** The timestamp. */
	@Field(store = Store.YES, analyze = Analyze.NO, indexNullAs = Field.DEFAULT_NULL_TOKEN)
	@NumericField
	private Long timestamp;
	
	/**
	 * Instantiates a new person.
	 */
	public Person(){		
	}
	
	/**
	 * Instantiates a new person.
	 *
	 * @param name the name
	 * @param age the age
	 * @param country the country
	 * @param timestamp the timestamp
	 */
	public Person(final String name, final Integer age, final String country, final Long timestamp){
		this.name = name;
		this.age = age;
		this.country = country;
		this.timestamp = timestamp;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	
	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(final Integer age) {
		this.age = age;
	}
	
	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(final Long timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(final String country) {
		this.country = country;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return "[name=" + name +"; age=" + age + "; country=" + country +";timestamp=" + timestamp + "]";		
	}
}
