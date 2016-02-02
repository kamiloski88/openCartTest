package pages;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ComparePage extends BasePage {

	public String PAGE_TITLE = "Product Comparison";
	
	WebDriverWait wait = new WebDriverWait(driver, 10);

	@FindBy(xpath = "//td[contains(text(), \"Availability\")]/following-sibling::*")
	List<WebElement> availabilityStatus;

	@FindBy(xpath = "//input[@value = \"Add to Cart\"]")
	List<WebElement> addToCartButtons;

	@FindBy(id = "cart-total")
	WebElement priceFromCart;

	public ComparePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String isComparePage() {
		return driver.getTitle();
	}

	public void findAndRemoveProductWithAvailabilit(String searchingAvailabilityValue) {
		int availabilityPosition = 0;
		int firstColAvailabilitTab = 2;
		int numberOfAvailabilityStatus = availabilityStatus.size();
		for (int i = numberOfAvailabilityStatus-1; i >=0; i--) {
			String availabilityValues = availabilityStatus.get(i).getText();
			if (availabilityValues.equalsIgnoreCase(searchingAvailabilityValue)) {
				availabilityPosition = i + firstColAvailabilitTab;
				WebElement buttonRemoveProduct = driver.findElement(
						By.xpath("//*[@id='content']/table/tbody[2]/tr/td[" + availabilityPosition + "]/a"));
				buttonRemoveProduct.click();
			}

		}
		  

	}

	public String putRandomProductToCartAndReturnPrice() {
		int availableColOfProductInTable = addToCartButtons.size() + 1;
		Random random = new Random();
		int randomProductNumber = random.nextInt((availableColOfProductInTable - 2) + 1) + 2;
		WebElement cartButton = driver.findElement(By.xpath(
				"//table[@class='table table-bordered']/tbody[2]/tr/td[" + randomProductNumber + "]/input/self::*"));
		cartButton.click();
		WebElement productPrice = driver.findElement(By.xpath(
				"//table[@class='table table-bordered']/tbody[1]/tr[3]/td[" + randomProductNumber + "]/self::*"));
		String getPriceAddedToCartProduct = productPrice.getText();
		return getPriceAddedToCartProduct;
	}

	public String getPriceFromCart() {
		wait.until(ExpectedConditions.visibilityOf(priceFromCart));
		String textPriceFromCart = priceFromCart.getText();
		String[] priceFromCart = textPriceFromCart.split(" ", 4);
		return priceFromCart[3];
	}

}
