package com.example.controller;

public class Reading {
	
	float time;
	float zero;
	float data_r;
	float data_theta;
	float new_x;
	float data_r_before_subtracting_from_avg;
	
	public Reading(float time, float zero, float data_r, float data_theta) {
		this.time = time;
		this.zero = zero;
		this.data_r = data_r;
		this.data_theta = data_theta;
	}
	
	public float getNew_x() {
		return new_x;
	}

	public void setNew_x(float new_x) {
		this.new_x = new_x;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public float getZero() {
		return zero;
	}
	public void setZero(float zero) {
		this.zero = zero;
	}
	public float getData_r() {
		return data_r;
	}
	public void setData_r(float data_r) {
		this.data_r = data_r;
	}
	public float getData_theta() {
		return data_theta;
	}
	public void setData_theta(float data_theta) {
		this.data_theta = data_theta;
	}
	
	public float getData_r_before_subtracting_from_avg() {
		return data_r_before_subtracting_from_avg;
	}

	public void setData_r_before_subtracting_from_avg(float data_r_before_subtracting_from_avg) {
		this.data_r_before_subtracting_from_avg = data_r_before_subtracting_from_avg;
	}

	@Override
	public String toString() {
		return  time+"\t"+zero+"\t" + data_r + "\t" + data_theta+"\t"+new_x+"\t"+data_r_before_subtracting_from_avg;
	}
	
	
	

}
