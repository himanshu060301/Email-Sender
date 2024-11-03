package com.mail.mailSender.DTO;

public class Message {

    private String from;
    private String content;
    private String[] files;
    private String subjects;

    // Private constructor to enforce the use of Builder
    private Message(Builder builder) {
        this.from = builder.from;
        this.content = builder.content;
        this.files = builder.files;
        this.subjects = builder.subjects;
    }

    public String getFrom() {
        return from;
    }

    public String getContent() {
        return content;
    }

    public String[] getFiles() {
        return files;
    }

    public String getSubjects() {
        return subjects;
    }

    // Static Builder class
    public static class Builder {
        private String from;
        private String content;
        private String[] files;
        private String subjects;

        public Builder() {
            // Default constructor
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder files(String[] files) {
            this.files = files;
            return this;
        }

        public Builder subjects(String subjects) {
            this.subjects = subjects;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
