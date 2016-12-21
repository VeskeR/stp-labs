package com.bestteam.core;

import com.bestteam.core.implementation.Date;
import com.bestteam.core.implementation.Task;
import com.bestteam.core.implementation.schedule.Schedule;
import com.bestteam.core.implementation.workers.WorkerType;
import com.bestteam.exceptions.NotEnoughHoursException;
import com.bestteam.exceptions.NotFoundScheduleException;
import com.bestteam.exceptions.ScheduleAlreadyExistsException;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;

/**
 * Created by Andrew on 12/21/2016.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class Worker {
    private static int counter = 0;
    protected WorkerType workerType;
    protected String name;
    protected String experience;
    protected int wage;
    protected int id;
    protected ArrayList<Schedule> schedules;

    public WorkerType getWorkerType() {
        return workerType;
    }

    protected void setWorkerType(WorkerType workerType) {
        this.workerType = workerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    protected Worker() {

    }

    protected Worker(WorkerType workerType, String name, int wage) {
        this.workerType = workerType;
        this.name = name;
        this.wage = wage;
        this.id = Worker.counter++;
        this.schedules = new ArrayList<>();
    }

    public void assignTask(Task task, Date date) throws NotFoundScheduleException, NotEnoughHoursException {
        int index = findSchedule(date);
        if (index == -1) {
            throw new NotFoundScheduleException();
        } else {
            Schedule schedule = schedules.get(index);
            if (schedule.getFreeHours() < task.getHours()) {
                throw new NotEnoughHoursException();
            } else {
                schedule.addTask(task);
            }
        }
    }

    public Schedule getSchedule(Date date) throws NotFoundScheduleException {
        int index = findSchedule(date);
        if (index == -1) {
            throw new NotFoundScheduleException();
        } else {
            return schedules.get(index);
        }
    }

    public void addSchedule(Schedule schedule) throws ScheduleAlreadyExistsException {
        int index = findSchedule(schedule);
        if (index != -1) {
            throw new ScheduleAlreadyExistsException();
        } else {
            schedules.add(schedule);
        }
    }

    public int findSchedule(Date date) {
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).getDate().equals(date)) {
                return i;
            }
        }
        return -1;
    }

    public int findSchedule(Schedule schedule) {
        return findSchedule(schedule.getDate());
    }

    public void removeSchedule(Date date) throws NotFoundScheduleException {
        Schedule schedule = getSchedule(date);
        schedules.remove(schedule);
    }

    public String schedulesToString() {
        String msg = "";
        for (int i = 0; i < schedules.size(); i++) {
            msg += schedules.get(i).toString() + (i < schedules.size() - 1 ? "\n" : "");
        }
        return msg;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "workerType=" + workerType +
                ", name='" + name + '\'' +
                ", wage=" + wage +
                ", id=" + id +
                '}';
    }
}
