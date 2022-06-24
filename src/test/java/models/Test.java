package models;

import java.sql.Timestamp;
import java.util.Objects;

public class Test {
    private String duration;
    private String method;
    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;

    public Test() {
       super();
    }

    public Test(String duration, String method, String name, Timestamp startTime, Timestamp endTime, String status) {
        this.duration = duration;
        this.method = method;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status.toUpperCase();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(duration, test.duration) &&
                Objects.equals(method, test.method) &&
                Objects.equals(name, test.name) &&
                Objects.equals(startTime, test.startTime) &&
                Objects.equals(endTime, test.endTime) &&
                status.equalsIgnoreCase(test.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, method, name, startTime, endTime, status);
    }
}
