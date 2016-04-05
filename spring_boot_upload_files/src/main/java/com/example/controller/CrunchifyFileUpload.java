package com.example.controller;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
 
public class CrunchifyFileUpload {
 
    private List<MultipartFile> crunchifyFiles;
    private List<String> revert;
    private List<String> xandy;
    private List<Integer> pass;
    private List<Float> divideByR;
    
	public List<Integer> getPass() {
		return pass;
	}

	public void setPass(List<Integer> pass) {
		this.pass = pass;
	}

	public List<String> getXandy() {
		return xandy;
	}

	public void setXandy(List<String> xandy) {
		this.xandy = xandy;
	}

	public List<String> getRevert() {
		return revert;
	}

	public void setRevert(List<String> revert) {
		this.revert = revert;
	}

	public List<MultipartFile> getFiles() {
        return crunchifyFiles;
    }
 
    public void setFiles(List<MultipartFile> files) {
        this.crunchifyFiles = files;
    }

	public List<Float> getDivideByR() {
		return divideByR;
	}

	public void setDivideByR(List<Float> divideByR) {
		this.divideByR = divideByR;
	}
    
}