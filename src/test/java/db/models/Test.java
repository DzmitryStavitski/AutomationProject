package db.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test")
public class Test {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "env")
    private String env;

    @Column(name = "browser")
    private String browser;

    @Column(name = "author_id")
    private Long authorId;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn (name = "test_id")
    private List<Attachment> attachments = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn (name = "test_id")
    private List<Log> logs = new ArrayList<>();

    public Test() {
    }

    public Test(String name, Integer statusId, String methodName, Long projectId, Long sessionId,
                Timestamp startTime, Timestamp endTime, String env, String browser, Long authorId) {
        this.name = name;
        this.statusId = statusId;
        this.methodName = methodName;
        this.projectId = projectId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.env = env;
        this.browser = browser;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
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

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachment) {
        this.attachments = attachment;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
    }

    public void addLog(Log log) {
        logs.add(log);
    }

    public List<String> getLogsContent() {
        ArrayList<String> logsContent = new ArrayList<>();
        for (Log log : logs) {
            logsContent.add(log.getContent());
        }
        return logsContent;
    }
}
