package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testrail.TestRailManager;
import testrail.annotation.AnnotationAnalyzer;
import utils.ImageUtils;
import utils.TestDataUtils;

public class TestListener implements ITestListener {
    private TestRailManager testRailManager;

    public int getCaseId() {
        return Integer.parseInt(TestDataUtils.getValueFromSettings("test.rail.caseId"));
    }

    @Override
    public void onStart(ITestContext context) {
        int projectId = Integer.parseInt(AqualityServices
                .get(ISettingsFile.class)
                .getValue("/test.rail.projectId").toString());
        int suiteId = Integer.parseInt(AqualityServices
                .get(ISettingsFile.class)
                .getValue("/test.rail.suiteId").toString());
        testRailManager = new TestRailManager(projectId, suiteId, context.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ImageUtils.saveScreenshot(result.getMethod().getMethodName());
        testRailManager.addResult(result, getCaseId());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ImageUtils.saveScreenshot(result.getMethod().getMethodName());
        testRailManager.addResult(result, getCaseId());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testRailManager.addResult(result, getCaseId());
    }

    @Override
    public void onFinish(ITestContext context) {
        testRailManager.deleteScreenshotsFromDirectory(context);
    }
}
