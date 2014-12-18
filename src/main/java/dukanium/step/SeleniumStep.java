package dukanium.step;

import org.jbehave.core.annotations.Aliases;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 *
 * @author nuboat 
 */
public class SeleniumStep {
    
    private final WebDriver web;
    
    public SeleniumStep(final WebDriver web) {
        this.web = web;
    }

   @Given("page go $pageurl")
    @Aliases(values={
        "user go $pageurl",
        "ผู้ใช้ไปที่ $pageurl"})
	public void access(final String pageurl) {
		web.get(pageurl);
	}

	@When("user enter $value into $elementid")
	public void enterText(final String value, final String elementid) {
        final WebElement element = web.findElement(By.id(elementid));
        element.clear();
		element.sendKeys(value);
	}
    
    @When("user select text $value at $elementid dropdown")
    public void selectText(final String value, final String elementid) {
        final Select select = new Select(web.findElement(By.id(elementid)));
        select.selectByVisibleText(value);
	} 
    
    @When("user select currency $value on class $classid")
    public void selectTextbyClassId(final String value, final String classid) {
        final Select select = new Select(web.findElement(By.className(classid)));
        select.selectByVisibleText(value);
	} 

	@When("user click id $elementid")
	public void clickById(final String elementid) {
		web.findElement(By.id(elementid)).click();
	}
    
    @When("user click class $classname")
	public void clickByClass(final String classname) {
		web.findElement(By.className(classname)).click();
	}
    
    @When("user clickhotel $hotel")
	public void clickHotel(final String hotel) {
//		final WebElement a = web.findHotelLink(hotel);
//        web.get(a.getAttribute("href"));
	}
    
    @When("user clicklink class $classid")
	public void clickLisk(final String classid) {
		web.findElement(By.className(classid)).click();
	}

	@When("thread sleep $sec second")
    @Aliases(values={
        "wait for $sec วินาที",
        "หยุดรอ $sec วินาที"})
	public void waitForSeconds(final int sec) throws InterruptedException {
		Thread.sleep(sec * 1000);
	}

	@Then("system display page as $pagename")
	public void displayPage(final String pagename) {
		final String url = web.getCurrentUrl().split("\\?")[0];
		Assert.assertTrue(url.endsWith(pagename));
	}
    
    @Then("system display hotel as $hotelname")
	public void displayHotel(final String hotelname) {
		final String url = web.getCurrentUrl();
		Assert.assertTrue(url.contains(hotelname));
	}

	@Then("system display title as $title")
	public void displayTitle(final String title) {
		Assert.assertEquals(web.getTitle(), title);
	}
    
    @Then("there is hotel $hotel in hotelInfoPlaceholder")
    public void hotelExisit(final String hotel) {
//        final WebElement a = web.findHotelLink(hotel);
//        Assert.assertNotNull(a);
	}
    
    @Then("system display header price as $price")
	public void displayHeaderPrice(final String price) {
//        final String a = web.findHeaderPrice();
//        
//		Assert.assertEquals(a, price);
	}
    
    @Then("there are prices list $prices in $td_room_rate")
	public void displayHeaderPrice(final String prices, final String td_room_rate) {
//        final List<String> expect = Arrays.asList(prices.split(":"));
//        final List<String> actual = page.findPriceList();
//        
//		Assert.assertEquals(actual, expect);
	}

}
