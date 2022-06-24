package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectForm extends Form {
    private Browser browser = AqualityServices.getBrowser();

    private FrameFrom frame = new FrameFrom(By.id("addProjectFrame"), "Add project frame");
    private ILabel successSaveLbl = getFormLabel().findChildElement(By.xpath("//div[contains(@Class,'alert alert-success')]"), ElementType.LABEL);
    private ITextBox projectNameTxtBox = AqualityServices.getElementFactory().getTextBox((By.xpath("//*[@id='projectName']")), "Project Name");
    private IButton saveBtn = getFormLabel().findChildElement(By.xpath("//button[@type='submit']"), ElementType.BUTTON);
    private IButton cancelBtn = getFormLabel().findChildElement(By.xpath("//button[@type='button']"), ElementType.BUTTON);

    public AddProjectForm() {
        super(By.xpath("//form[@id='addProjectForm']"), "Add project window");
    }

    public void projectNameEnter(String content) {
        frame.doInFrame(() -> projectNameTxtBox.clearAndType(content));
    }

    public void saveBtnClick() {
        frame.doInFrame(() -> saveBtn.click());
    }

    public void cancelBtnClick() {
       frame.doInFrame(() -> cancelBtn.click());
    }

    public boolean ifSaveSuccess() {
        return frame.ifElementExist(successSaveLbl);
    }

    public boolean ifAddProjectWindowOpen() {
        return projectNameTxtBox.state().isDisplayed();
    }
}
