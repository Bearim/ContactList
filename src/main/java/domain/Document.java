package domain;

import java.sql.Blob;

public class Document {
    private int contact_id;
    private String fileName;
    private Blob fileData;
    private String filePath;
    private String comment;

    public Document(int contact_id, String fileName, Blob fileData, String comment) {
        this.contact_id = contact_id;
        this.fileName = fileName;
        this.fileData = fileData;
        this.comment = comment;
    }
    public Document(String fileName, Blob fileData, String comment) {
        this.fileName = fileName;
        this.fileData = fileData;
        this.comment = comment;
    }

    public Document(int contact_id, String fileName, String comment){
        this.contact_id = contact_id;
        this.fileName = fileName;
        this.comment = comment;
    }

    public Document() {
    }

    public static class Builder{
        private Document document;

        public Builder(){
            document = new Document();
        }

        public Builder withContactId(int contact_id){
            document.contact_id = contact_id;
            return this;
        }

        public Builder withFileName(String fileName){
            document.fileName = fileName;
            return this;
        }

        public Builder withFileData(Blob fileData){
            document.fileData = fileData;
            return this;
        }

        public Builder withFilePath(String filePath){
            document.filePath = filePath;
            return this;
        }

        public Builder withComment(String comment){
            document.comment = comment;
            return this;
        }

        public Document build(){
            return document;
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Blob getFileData() {
        return fileData;
    }

    public void setFileData(Blob fileData) {
        this.fileData = fileData;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
