package com.example.proj.rest;

import com.atlassian.jira.issue.Issue;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyRestResourceModel {


    @XmlElement(name = "value")
    private String message;

    @XmlElement(name = "issues")
    private List<Issue> issues;

    public MyRestResourceModel() {
    }

    public MyRestResourceModel(String message, List<Issue> issues) {
        this.message = message;
        this.issues = issues;
    }

    public MyRestResourceModel(String message) {
        this.message = message;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}