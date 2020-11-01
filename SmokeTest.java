package com.demoaut.newtours.MavenDemo;

import org.testng.annotations.Test;

public class SmokeTest extends BaseTest{
	@Test
	public void basicflow() throws InterruptedException {
		
		
		login.applicationLogin("Debora", "Dear2021");
		SpecialOffer.specialofferview(); ;
		proddesc.savetocart();;
		signoff.clickonSignOff();;
	}

}
