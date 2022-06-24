package models;

public class Attachment {
    private byte[] content;
    private String type;

    public Attachment(byte[] content, String type) {
        this.content = content;
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
