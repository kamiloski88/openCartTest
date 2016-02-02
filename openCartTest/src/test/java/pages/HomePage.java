package pages;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage{
	
	public String PAGE_TITLE = "Your Store";

	@FindBy(className = "input-lg")
	WebElement inputSearch;	
	
	@FindBy(className = "btn-lg")
	WebElement searchButton;		
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String isHomePage(){
		return driver.getTitle();	
	}
	
	public void inputIntoSearch(String searchingProductName ){
		inputSearch.clear();
		inputSearch.sendKeys(searchingProductName);
	}
	
	public  SearchResultsPage clickSearchButton(){
		searchButton.click();
		return PageFactory.initElements(driver, SearchResultsPage.class);
	}

}
