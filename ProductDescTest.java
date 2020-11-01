package com.demoaut.newtours.MavenDemo;

import org.testng.annotations.Test;

public class ProductDescTest extends BaseTest{
	@Test
	public void addtocart() {
		proddesc.savetocart();
	}
}
