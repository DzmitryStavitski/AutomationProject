package testrail;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import utils.ImageUtils;

import static testrail.TestRailAPI.*;

public class TestRailManager {
    private final int projectId;
    private final int suiteId;
    private final int runId;

    public TestRailManager(int projectId, int suiteId, int runId) {
        this.projectId = projectId;
        this.suiteId = suiteId;
        this.runId = runId;
    }

    public TestRailManager(int projectId, int suiteId, String runName) {
        this.projectId = projectId;
        this.suiteId = suiteId;
        runId = createRun(projectId, suiteId, "Final Task");
    }

    public void addResult(ITestResult result, int caseId) {
        int resultId = TestRailAPI.addResult(runId, caseId, result.getStatus());
        TestRailAPI.addAttachmentToResult(resultId, ImageUtils.getScreenshotPath(result.getMethod().getMethodName()));
    }

    public void deleteScreenshotsFromDirectory(ITestContext context) {
        for (ITestNGMethod method : context.getAllTestMethods()) {
            ImageUtils.deleteScreenshot(method.getMethodName());
        }
    }

    public void deleteRunSuite() {
        TestRailAPI.deleteRunSuite(runId, suiteId);
    }

    public void deleteScreenshots(ITestContext context) {
        for (ITestNGMethod method : context.getAllTestMethods()) {
            ImageUtils.deleteScreenshot(method.getMethodName());
        }
    }

    public void deleteAll(ITestContext context) {
        deleteScreenshots(context);
    }
}