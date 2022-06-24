package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FrameFrom extends Form {
    public FrameFrom(By locator, String name) {
        super(locator, name);
    }

    public void doInFrame(Runnable action) {
        Browser browser = AqualityServices.getBrowser();
        browser.getDriver()
                .switchTo()
                .frame(browser.getDriver().findElement(getLocator()));
        action.run();
        browser.getDriver().switchTo().defaultContent();
    }

    public boolean ifElementExist(IElement element) {
        Browser browser = AqualityServices.getBrowser();
        browser.getDriver()
                .switchTo()
                .frame(browser.getDriver().findElement(getLocator()));
        boolean result = element.state().isExist();
        browser.getDriver().switchTo().defaultContent();

        return result;
    }
}
