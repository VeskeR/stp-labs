package com.bestteam.core.implementation.schedule;

import com.bestteam.core.implementation.Date;
import com.bestteam.core.implementation.Task;
import com.bestteam.exceptions.NotFoundTaskException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

/**
 * Created by Andrew on 12/21/2016.
 */
public class Schedule {
    private Date date;
    private ScheduleType scheduleType;
    private int maxHours;
    private ArrayList<Task> tasks;

    public Date getDate() {
        return date;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public int getMaxHours() {
        return maxHours;
    }

    @JsonIgnore
    public int getUsedHours() {
        int hours = 0;
        for (int i = 0; i < tasks.size(); i++) {
            hours += tasks.get(i).getHours();
        }
        return hours;
    }

    @JsonIgnore
    public int getFreeHours() {
        return getMaxHours() - getUsedHours();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    public void setMaxHours(int maxHours) {
        this.maxHours = maxHours;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Schedule() {
    }

    public Schedule(int maxHours, ScheduleType scheduleType) {
        this(new Date(), maxHours, scheduleType);
    }

    public Schedule(int year, int month, int day, int maxHours, ScheduleType scheduleType) {
        this(new Date(year, month, day), maxHours, scheduleType);
    }

    public Schedule(Date date, int maxHours, ScheduleType scheduleType) {
        this.date = date;
        this.maxHours = maxHours;
        this.scheduleType = scheduleType;
        this.tasks = new ArrayList<>();
    }

    public static Schedule createBaseSchedule() {
        return new Schedule(8, ScheduleType.Regular);
    }

    public static Schedule createBaseSchedule(int year, int month, int day) {
        return new Schedule(year, month, day, 8, ScheduleType.Regular);
    }

    @JsonIgnore
    public double getWageMultiplier() {
        switch (scheduleType) {
            case Regular:
                return 1;
            case Overtime:
                return 1.5;
            case Weekend:
                return 2;
            default:
                return 1;
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(String name) throws NotFoundTaskException {
        int index = findTask(name);
        if (index == -1) {
            throw new NotFoundTaskException();
        } else {
            removeTask(findTask(name));
        }
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public int findTask(String name) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName() == name) {
                return i;
            }
        }
        return -1;
    }

    public String tasksToString() {
        String msg = "";
        for (int i = 0; i < tasks.size(); i++) {
            msg += tasks.get(i).toString() + (i < tasks.size() - 1 ? "\n" : "");
        }
        return msg;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "date=" + date +
                ", scheduleType=" + scheduleType +
                ", maxHours=" + maxHours +
                '}';
    }
}
