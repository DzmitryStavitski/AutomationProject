package testrail;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import org.json.simple.JSONObject;
import testrail.gurock.testrail.*;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailAPI {
    private static APIClient client;
    
    private static final String ADD_ATTACHMENT = "add_attachment_to_result";
    private static final String ADD_RESULT = "add_result_for_case";

    private static APIClient getClient() {
        if (client == null) {
            client = new APIClient(AqualityServices.get(ISettingsFile.class).getValue("/test.rail.url").toString());
            client.setUser(AqualityServices.get(ISettingsFile.class).getValue("/test.rail.login").toString());
            client.setPassword(AqualityServices.get(ISettingsFile.class).getValue("/test.rail.password").toString());
        }
        return client;
    }

    private static int getId(JSONObject object) {
        return Integer.parseInt(object.get("id").toString());
    }

    public static void addAttachmentToResult(int resultId, String path) {
        try {
            getClient().sendPost(ADD_ATTACHMENT + "/" + resultId, path);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static int addResult(int runId, int caseId, int statusId) {
        int id = 0;
        TestRailStatus testRailStatus = TestRailStatus.getTestRailStatus(statusId);
        Map<String, Object> data = new HashMap<>();
        data.put("status_id", testRailStatus.getId());
        data.put("comment", testRailStatus.getName());
        try {
            JSONObject result = (JSONObject) getClient().sendPost(ADD_RESULT + "/" + runId + "/" + caseId, data);
            id = getId(result);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static int createSuite(int projectId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        try {
            JSONObject suite = (JSONObject) getClient().sendPost("add_suite/" + projectId, data);
            id = getId(suite);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static int createSection(int projectId, int suiteId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("suite_id", suiteId);
        data.put("name", name);
        try {
            JSONObject section = (JSONObject) getClient().sendPost("add_section/" + projectId, data);
            id = getId(section);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static int createTestCase(int sectionId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("title", name);
        try {
            JSONObject testCase = (JSONObject) getClient().sendPost("add_case/" + sectionId, data);
            id = getId(testCase);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static int createRun(int projectId, int suiteId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("suite_id", suiteId);
        data.put("name", name);
        try {
            JSONObject run = (JSONObject) getClient().sendPost("add_run/" + projectId, data);
            id = getId(run);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static void deleteRun(int runId) {
        Map<String, Object> data = new HashMap<>();
        try {
            getClient().sendPost("delete_run/" + runId, data);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static void deleteSuite(int suiteId) {
        Map<String, Object> data = new HashMap<>();
        try {
            getClient().sendPost("delete_suite/" + suiteId, data);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static void deleteRunSuite(int runId, int suiteId) {
        deleteRun(runId);
        deleteSuite(suiteId);
    }
}