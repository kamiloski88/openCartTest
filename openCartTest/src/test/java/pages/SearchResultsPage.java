package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends BasePage {

	public String PAGE_TITLE = "Search - ";

	@FindBy(xpath = "//*[@data-original-title=\"Compare this Product\"]")
	List<WebElement> compareButtons;

	@FindBy(partialLinkText = "Product Compare")
	WebElement urlComparePage;

	public SearchResultsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String isSearchResoultsPage() {
		return driver.getTitle();
	}

	public void compareAllProducts() {
		for (WebElement compareButton : compareButtons) {
			compareButton.click();
		}
	}

	public ComparePage goToComparePage() {
		urlComparePage.click();
		return PageFactory.initElements(driver, ComparePage.class);
	}
}
