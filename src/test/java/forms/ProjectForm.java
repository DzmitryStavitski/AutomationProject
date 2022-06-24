package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

public class ProjectForm extends Form {
    private String projectLinkXpath = "//a[contains(text(), '%s')]";
    private final List<IElement> projectsLinks = getFormLabel()
            .findChildElements(By.xpath("//a[contains(@class, 'list-group-item')]"), "Project link", ElementType.LINK);

    public ProjectForm() {
        super(By.xpath("//div[contains(@class, 'panel panel-default')]"), "Projects Form");
    }

    public void goToTheProject(String projectName) {
        getFormLabel().findChildElement(By.xpath(String.format(projectLinkXpath, projectName)), ElementType.LINK).click();
    }

    public boolean isProjectExists(String projectName) {
        for (IElement element : projectsLinks) {
            if (element.getText().equals(projectName)) {
                return true;
            }
        }
        return false;
    }

    public int getProjectId(String projectName) {
        String projectHref = getFormLabel()
                .findChildElement(By.xpath(String.format(projectLinkXpath, projectName)), ElementType.LINK)
                .getAttribute("href");
        return Integer.parseInt(projectHref.replaceAll((".+="), ""));
    }
}
