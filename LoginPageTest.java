package com.demoaut.newtours.MavenDemo;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.Test;

import utils.ReadExcel;

public class LoginPageTest extends BaseTest{
	@Test
	public void validateLoginTest() throws IOException, InterruptedException {
		
		String[][] data = ReadExcel.getData("TestData.xlsx", "Sheet1");
			for(int i=1; i<data.length; i++) {
			String username = data[i][1];
			String password = data[i][2];
			login.applicationLogin(username, password);
		}
	}	
}

