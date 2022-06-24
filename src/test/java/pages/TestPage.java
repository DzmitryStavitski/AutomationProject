package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import models.Attachment;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TestPage extends Form {
    private final String LOGS_TABLE_XPATH = "//div[contains(text(),'Logs')]/following-sibling::table";
    private final String ATTACHMENTS_TABLE_XPATH = "//div[contains(text(),'Attachments')]/following-sibling::table";

    public TestPage() {
        super(By.xpath("//div[@class='container main-container']"), "Test info page");
    }

    public List<String> getLogs() {
        List<String> logs = new ArrayList<>();
        List<IElement> logLabels = getFormLabel().findChildElements(By.xpath(LOGS_TABLE_XPATH + "//td"), ElementType.LABEL);

        for (IElement element : logLabels) {
            logs.add(element.getText());
        }

        return logs;
    }

    public List<Attachment> getAttachments() {
        List<Attachment> attachments = new ArrayList<>();
        List<IElement> content = getFormLabel().findChildElements(By.xpath(ATTACHMENTS_TABLE_XPATH + "//tr//td[1]//a"), ElementType.LINK);
        List<IElement> attachmentsType = getFormLabel().findChildElements(By.xpath(ATTACHMENTS_TABLE_XPATH + "//tr//td[2]"), ElementType.LABEL);

        for (int i = 0; i < content.size(); i++) {
            byte[] image = Base64.getDecoder().decode(content.get(i).getAttribute("href").replaceAll("data:image(.)+,", "").getBytes());
            attachments.add(new Attachment(image, attachmentsType.get(i).getText()));
        }

        return attachments;
    }
}
