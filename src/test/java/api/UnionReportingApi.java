package api;

import aquality.selenium.browser.AqualityServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import constants.Requests;
import models.Test;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UnionReportingApi {
    public static String getToken(int variant) {
        RequestBody formBody = new FormBody.Builder()
                .add("variant", Integer.toString(variant))
                .build();
        try {
            AqualityServices.getLogger().info("Sending a POST request (get Token) to the api");
            return Api.post(Requests.GET_TOKEN, formBody);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
            return null;
        }
    }

    public static ArrayList<Test> getTestsFromProject(int projectId) {
        RequestBody formBody = new FormBody.Builder()
                .add("projectId", Integer.toString(projectId))
                .build();
        try {
            AqualityServices.getLogger().info("Sending a POST request (get Tests from project) to the api");
            String json = Api.post(Requests.GET_TESTS, formBody).replaceAll("\\.0\"", "\"");

            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            Type listType = new TypeToken<ArrayList<Test>>(){}.getType();

            return gson.fromJson(json, listType);
        } catch (IOException | com.google.gson.JsonSyntaxException e) {
            AqualityServices.getLogger().error(e.getMessage());
            return null;
        }
    }
}
