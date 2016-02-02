package testes;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import components.TopNavigation;
import pages.ComparePage;
import pages.HomePage;
import pages.SearchResultsPage;

public class MainPage {
	HomePage homepage;
	TopNavigation topnavigation;
	ComparePage comparepage;
	SearchResultsPage searchresultspage;
	

	@BeforeTest
	public void setUp() {
		 
		WebDriver driver = new FirefoxDriver();
		driver.get("http://demo.opencart.com/");
		driver.manage().window().maximize();
		homepage = PageFactory.initElements(driver, HomePage.class);
		topnavigation = PageFactory.initElements(driver, TopNavigation.class);
		searchresultspage = PageFactory.initElements(driver, SearchResultsPage.class);
		comparepage = PageFactory.initElements(driver, ComparePage.class);

	}

	@Test
	public void ChangeCurrencyFindIPodCompareRemoveComparePriceInCart() {
		Assert.assertEquals(homepage.PAGE_TITLE, homepage.isHomePage());

		String curr = "gbp";
		topnavigation.clickCurrencyDropDown();
		char newCurrencyToSet = topnavigation.setCurrency(curr);
		char currencyAfterSet = topnavigation.getCurrentCurrencySymbol();
		Assert.assertEquals(currencyAfterSet,
							newCurrencyToSet);

		homepage.inputIntoSearch("ipod");
		homepage.clickSearchButton();

		Assert.assertTrue(searchresultspage.isSearchResoultsPage().
				 contains(searchresultspage.PAGE_TITLE));
		searchresultspage.compareAllProducts();
		searchresultspage.goToComparePage();

		Assert.assertEquals(comparepage.isComparePage(), 
							comparepage.PAGE_TITLE);
		String searchingAvailabilityValue = "out of stock";
		comparepage.findAndRemoveProductWithAvailabilit(searchingAvailabilityValue);
		Assert.assertTrue(comparepage.putRandomProductToCartAndReturnPrice().
				 contains(comparepage.getPriceFromCart()));
	
	}
	 

}
