package com.bestteam.core.implementation;

/**
 * Created by Andrew on 12/21/2016.
 */
public class Task {
    private String name;
    private String description;
    private int hours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHours() {
        return hours;
    }

    public Task() {
    }

    public Task(String name, String description, int hours) {
        this.name = name;
        this.description = description;
        this.hours = hours;
    }

    public Task(Task task) {
        this(task, task.getHours());
    }

    public Task(Task task, int hours) {
        this.name = task.getName();
        this.description = task.getDescription();
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hours=" + hours +
                '}';
    }
}
