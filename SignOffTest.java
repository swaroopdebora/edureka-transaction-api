package com.demoaut.newtours.MavenDemo;

import org.testng.annotations.Test;
	public class SignOffTest  extends BaseTest{
		@Test
		public void singOff() throws InterruptedException
		{
			signoff.clickonSignOff();
		}
	}
