package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;
import com.thoughtworks.selenium.webdriven.commands.IsVisible;

import pages.BasePage;
import pages.ComparePage;

public class TopNavigation extends BasePage {

	@FindBy(id = "currency")
	WebElement currencyDropDown;

	@FindBy(xpath = ".//*[@id='currency']//button/strong")
	WebElement currentCurrencySymbol;

	public TopNavigation(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickCurrencyDropDown() {
		currencyDropDown.click();
	}

	public char setCurrency(String currencyToSet) {
		WebElement setCurrencyVal = driver.findElement(By.name(currencyToSet.toUpperCase()));
		String currencyName = setCurrencyVal.getText();
		setCurrencyVal.click();
		char newCurrencySymbol = currencyName.charAt(0);
		return newCurrencySymbol;
	}

	public char getCurrentCurrencySymbol() {
		char currentSymbol = currentCurrencySymbol.getText().charAt(0);
		return currentSymbol;
	}

}
