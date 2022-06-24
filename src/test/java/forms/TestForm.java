package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import models.Test;
import org.openqa.selenium.By;

import java.sql.Timestamp;

public class TestForm extends Form {
    private final ILink testNameLink = getFormLabel().findChildElement(By.xpath("//td[1]/a"), ElementType.LINK);
    private final ILabel testMethodLabel = getFormLabel().findChildElement(By.xpath("//td[2]"), ElementType.LABEL);
    private final ILabel latestResultLabel = getFormLabel().findChildElement(By.xpath("//td[3]"), ElementType.LABEL);
    private final ILabel latestStartTime = getFormLabel().findChildElement(By.xpath("//td[4]"), ElementType.LABEL);
    private final ILabel latestEndTime = getFormLabel().findChildElement(By.xpath("//td[5]"), ElementType.LABEL);
    private final ILabel latestDurationTime = getFormLabel().findChildElement(By.xpath("//td[6]"), ElementType.LABEL);

    public TestForm(int number) {
        super(By.xpath(String.format("//table[@class='table']//tr[%d]", number)), "Test Container: " + number);
    }

    public String getTestName() {
        return testNameLink.getText();
    }

    public String getTestMethod() {
        return testMethodLabel.getText();
    }

    public String getLatestResult() {
        return latestResultLabel.getText();
    }

    public Timestamp getLatestStartTime() {
        return Timestamp.valueOf(latestStartTime.getText());
    }

    public Timestamp getLatestEndTime() {
        String result = latestEndTime.getText();
        if(result.isEmpty())
            return null;
        else
            return Timestamp.valueOf(latestEndTime.getText());
    }

    public String getLatestDurationTime() {
        return latestDurationTime.getText();
    }

    public Test getTestModel() {
        return new Test(getLatestDurationTime(), getTestMethod(), getTestName(),
                getLatestStartTime(), getLatestEndTime(), getLatestResult());
    }

    public void goToTheTestPage() {
        testNameLink.click();
    }
}
