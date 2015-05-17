package com.anishsneh.demo.hbase.phoenix.vo;

import java.io.Serializable;

/**
 * The Class UserVo.
 */
public class UserVo implements Serializable{
		
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private String id;
	
	/** The age. */
	private int age;
	
	/** The gender. */
	private String gender;
	
	/** The birthplace. */
	private String birthplace;
	
	/**
	 * Instantiates a new user vo.
	 *
	 * @param id the id
	 * @param age the age
	 * @param gender the gender
	 * @param birthplace the birthplace
	 */
	public UserVo(final String id, final int age, final String gender, final String birthplace){
		this.id = id;
		this.age = age;;
		this.gender = gender;
		this.birthplace = birthplace;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final String id) {
		this.id = id;
	}
	
	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(final int age) {
		this.age = age;
	}
	
	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(final String gender) {
		this.gender = gender;
	}
	
	/**
	 * Gets the birthplace.
	 *
	 * @return the birthplace
	 */
	public String getBirthplace() {
		return birthplace;
	}
	
	/**
	 * Sets the birthplace.
	 *
	 * @param birthplace the new birthplace
	 */
	public void setBirthplace(final String birthplace) {
		this.birthplace = birthplace;
	}		
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {	
		return "[id=" + id + ";age=" + age + ";gender=" + gender + ";birthplace=" + birthplace + "]";
	}
}
