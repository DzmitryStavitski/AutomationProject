package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import forms.AddProjectForm;
import forms.ProjectForm;
import org.openqa.selenium.By;

public class ProjectsPage extends Form {
    private IElementFactory factory = AqualityServices.getElementFactory();

    private AddProjectForm addProjectForm = new AddProjectForm();
    private ProjectForm projectForm = new ProjectForm();
    private ILabel versionLabel = factory.getLabel(By.xpath("//div[@class='container']/p"), "Version label");
    private IButton addProjectBtn = factory.getButton(By.xpath("//button[@data-target='#addProject']"), "Add Project");

    public ProjectsPage() {
        super(By.xpath("//div[contains(@class, 'container main-container')]"), "Projects Page");
    }

    public int getVersion() {
        return Integer.parseInt(versionLabel.getText().replaceAll("\\D", ""));
    }

    public ProjectForm getProjectForm() {
        return projectForm;
    }

    public AddProjectForm getAddProjectForm() {
        return addProjectForm;
    }

    public void clickAddProjectButton() {
        addProjectBtn.click();
    }

    public void updateProjectsList() {
        projectForm = new ProjectForm();
    }
}
