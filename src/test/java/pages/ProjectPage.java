package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import forms.TestForm;
import models.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ProjectPage extends Form {
    private ArrayList<TestForm> testForms;

    public ProjectPage() {
        super(By.xpath("//div[@class='container main-container']"), "Project Page");
        testFormsInit();
    }

    public ArrayList<Test> getTestsList() {
        testFormsInit();
        ArrayList<Test> tests = new ArrayList<>();
        for (TestForm testForm : testForms) {
            tests.add(testForm.getTestModel());
        }
        return tests;
    }

    public ArrayList<TestForm> getTestForms() {
        testFormsInit();
        return testForms;
    }

    private void testFormsInit() {
        testForms = new ArrayList<>();
        List<IElement> elements = getFormLabel().findChildElements(By.xpath("//table[@class='table']//tr"), ElementType.LABEL);
        for (int i = 1; i < elements.size(); i++) {
            testForms.add(new TestForm(i + 1));
        }
    }

    public static boolean waitForFirstTestAppeared() {
       return AqualityServices.getConditionalWait().waitFor(() -> !AqualityServices
                .getBrowser()
                .getDriver()
                .findElements(By.xpath("//table[@class='table']//tr[2]")).isEmpty());
    }
}
