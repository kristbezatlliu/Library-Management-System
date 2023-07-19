//should have a private name, day, month, year and age variables.

import java.io.Serializable;

public abstract class Person implements Addable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String name;
	private int day;
	private int month;
	private int year;
	private int age;
	
	Person(String name, int day, int month, int year){
		this.name = name;
		this.day = day;
		this.month = month;
		this.year = year;
		
		this.age = 2023 - year;
	}
	
	Person(){
		this.name = "none";
		this.day = 1;
		this.month = 1;
		this.year = 2023 - 18;
		this.age = 18;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
}
