package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.example.TestUtils.AndroidBaseTest;
import org.example.pageObjects.CartPage;
import org.example.pageObjects.ProductCatalogue;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class eCommerce_tc_4_Hybrid extends AndroidBaseTest {
	
	

		
		@Test(dataProvider="getData", groups= {"Smoke"})
		public void FillForm(HashMap<String,String> input) throws InterruptedException
		{	
			formPage.setNameField(input.get("name"));
			formPage.setGender(input.get("gender"));
			formPage.setCountrySelection(input.get("country"));
			ProductCatalogue productCatalogue = formPage.submitForm();
			productCatalogue.addItemToCartByIndex(0);
			productCatalogue.addItemToCartByIndex(0);
			CartPage cartPage = productCatalogue.goToCartPage();
			
			//Thread.sleep(2000);
//		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
	//wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text" , "Cart"));
			double totalSum = cartPage.getProductsSum();
			double displayFormattedSum = cartPage.getTotalAmountDisplayed();
			AssertJUnit.assertEquals(totalSum, displayFormattedSum);
			cartPage.acceptTermsConditions();
			cartPage.submitOrder();
		
		//		
			
		}
		
		@BeforeMethod(alwaysRun=true)
		public void preSetup()
		{
		formPage.setActivity();
					
		}
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
			List<HashMap<String, String>>	data =getJsonData(System.getProperty("user.dir")+"//src//test//java//org//example//testData//eCommerce.json");
			return new Object[][] { {data.get(0)},{data.get(1)}  };
		}
		
		

		
	}
