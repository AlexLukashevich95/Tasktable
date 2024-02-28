package com.alex.tasktable.bean;

import java.util.Date;

public class Task {
    private Integer task_id;
    private String task_name;
    private String task_description;
    private Date task_deadline;
    private String task_status;

    public Task(Integer task_id, String task_name, String task_description, Date task_deadline, String task_status) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_description = task_description;
        this.task_deadline = task_deadline;
        this.task_status = task_status;
    }

    public Task(){

    }

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public Date getTask_deadline() {
        return task_deadline;
    }

    public void setTask_deadline(Date task_deadline) {
        this.task_deadline = task_deadline;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }
}
