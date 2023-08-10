package UI.step;

import org.testng.Assert;
import org.testng.annotations.Test;

import UI.BaseClass;

public class TestNgGrouping extends BaseClass{

//	@Test(groups = {"smoke"})
//	public void teatSmoke() {
//		System.out.println("Running Smoke Group");
//	}
	
	@Test(groups = {"sanity"})
	public void teatSanity() {
		System.out.println("Running sanity Group");
		driver.get("https://www.youtube.com/");
		Assert.assertTrue(false);
	}
//	
//	@Test(groups = {"regression"})
//	public void teatRegression() {
//		System.out.println("Running regression Group");
//	}
//	
//	@Test(enabled  = true)
//	public void teatInclude() {
//		System.out.println("enabled");
//	}
//	@Test(enabled  = false)
//	public void teatDisabled() {
//		System.out.println("disabled");
//	}
//	
//	@Test
//	public void dependentMethodTest() {
//		System.out.println("dependentMethodTest");
//	}
//	@Test(dependsOnMethods = "dependentMethodTest")
//	public void teatDependsOnMethod() {
//		System.out.println("testing depends on");
//	}
//	
//	@Test(groups= {"abcd"})
//	public void dependsGroupTest() {
//		System.out.println("dependentGroupTest");
//	}
//	@Test(dependsOnGroups = "abcd")
//	public void teatDependsOnGroup() {
//		System.out.println("testing depends groups pass");
//	}
}
