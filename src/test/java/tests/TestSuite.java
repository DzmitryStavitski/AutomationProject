package tests;

import api.UnionReportingApi;
import constants.AssertMessages;
import constants.JsFunctions;
import db.dao.TestDao;
import db.models.Test;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import pages.ProjectPage;
import pages.ProjectsPage;
import pages.TestPage;
import utils.*;

import java.util.ArrayList;

@Listeners(tests.TestListener.class)
public class TestSuite extends BaseTest {
    private final ProjectsPage projectsPage = new ProjectsPage();
    private final ProjectPage projectPage = new ProjectPage();
    private final TestPage testPage = new TestPage();

    public void test() {
        // STEP 1
        int variant = Integer.parseInt(TestDataUtils.getValueFromSettings("variant"));
        String token = UnionReportingApi.getToken(variant);
        Assert.assertNotNull(token, AssertMessages.TOKEN_ERROR);

        // STEP 2
        browser.goTo(UrlUtils.getAuthorizationUrl());
        browser.waitForPageToLoad();
        CookieUtils.addCookie(new Cookie("token", token));
        browser.refresh();
        Assert.assertEquals(projectsPage.getVersion(), variant, AssertMessages.COOKIE_ERROR);

        // STEP 3
        projectsPage.getProjectForm().goToTheProject(TestDataUtils.getValueFromSettings("test.project.name"));
        browser.waitForPageToLoad();
        ArrayList<models.Test> testsFromApi = UnionReportingApi.getTestsFromProject(1);
        Assert.assertNotNull(testsFromApi, AssertMessages.TEST_API_ERROR);
        ArrayList<models.Test> testsFromPage = projectPage.getTestsList();
        Assert.assertTrue(TestSortedCheckUtils.ifTestsSortedByDate(testsFromPage), AssertMessages.TEST_SORT_ERROR);
        Assert.assertTrue(testsFromApi.containsAll(testsFromPage), AssertMessages.TEST_CONTAINS_ERROR);

        // STEP 4
        browser.goBack();
        browser.waitForPageToLoad();
        projectsPage.clickAddProjectButton();
        String projectName = RandomUtils.randomString(Integer.parseInt(TestDataUtils.getValueFromSettings("max.sentence.random.length")),
                Integer.parseInt(TestDataUtils.getValueFromSettings("max.word.random.length")));
        projectsPage.getAddProjectForm().projectNameEnter(projectName);
        projectsPage.getAddProjectForm().saveBtnClick();
        Assert.assertTrue(projectsPage.getAddProjectForm().ifSaveSuccess(), AssertMessages.PROJECT_SAVE_ERROR);
        browser.executeScript(JsFunctions.CLOSE_UP);
        Assert.assertFalse(projectsPage.getAddProjectForm().ifAddProjectWindowOpen(), AssertMessages.PROJECT_ADD_WINDOW_ERROR);
        browser.refresh();
        projectsPage.updateProjectsList();
        Assert.assertTrue(projectsPage.getProjectForm().isProjectExists(projectName), AssertMessages.NEW_PROJECT_ERROR);

        // STEP 5
        long projectId = projectsPage.getProjectForm().getProjectId(projectName);
        projectsPage.getProjectForm().goToTheProject(projectName);
        browser.waitForPageToLoad();
        Test test = TestDataUtils.getRandomTest(projectId);
        new TestDao().save(test);

        // STEP 6
        ProjectPage.waitForFirstTestAppeared();
        projectPage.getTestForms().get(0).goToTheTestPage();
        Assert.assertEquals(testPage.getLogs(), test.getLogsContent(), AssertMessages.LOGS_ERROR);
        Assert.assertEquals(testPage.getAttachments().get(0).getContent(), test.getAttachments().get(0).getContent(), AssertMessages.ATTACHMENTS_CONTENT_ERROR);
        Assert.assertEquals(testPage.getAttachments().get(0).getType(), test.getAttachments().get(0).getContentType(), AssertMessages.ATTACHMENTS_TYPE_ERROR);
    }
}
