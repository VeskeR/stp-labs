package com.bestteam.core.implementation;

import com.bestteam.core.Worker;
import com.bestteam.core.implementation.workers.Manager;
import com.bestteam.core.implementation.workers.WorkerType;
import com.bestteam.data.JsonConvertible;
import com.bestteam.exceptions.NotFoundWorkerException;

import java.util.ArrayList;

/**
 * Created by Andrew on 12/21/2016.
 */
public class Company extends JsonConvertible<Company> {
    private String name;
    private int yearFound;
    private String address;
    private ArrayList<Worker> workers;
    private ArrayList<Manager> managers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearFound() {
        return yearFound;
    }

    public void setYearFound(int yearFound) {
        this.yearFound = yearFound;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }

    public Company() {
        super(Company.class);
    }

    public Company(String name, int yearFound, String address) {
        super(Company.class);

        this.name = name;
        this.yearFound = yearFound;
        this.address = address;
        this.workers = new ArrayList<>();
        this.managers = new ArrayList<>();
    }

    public ArrayList<Worker> getWorkers(WorkerType workerType) {
        ArrayList<Worker> result = new ArrayList<>();

        for (int i = 0; i < workers.size(); i++) {
            if (workers.get(i).getWorkerType() == workerType) {
                result.add(workers.get(i));
            }
        }

        return result;
    }

    public ArrayList<Worker> getWorkers(String name) {
        ArrayList<Worker> result = new ArrayList<>();

        for (int i = 0; i < workers.size(); i++) {
            if (workers.get(i).getName() == name) {
                result.add(workers.get(i));
            }
        }

        return result;
    }

    public ArrayList<Worker> getWorkers(WorkerType workerType, String name) {
        ArrayList<Worker> result = new ArrayList<>();

        for (int i = 0; i < workers.size(); i++) {
            Worker worker = workers.get(i);
            if (worker.getWorkerType() == workerType && worker.getName() == name) {
                result.add(workers.get(i));
            }
        }

        return result;
    }

    public Worker getWorker(int id) throws NotFoundWorkerException {
        for (int i = 0; i < workers.size(); i++) {
            if (workers.get(i).getId() == id) {
                return workers.get(i);
            }
        }

        throw new NotFoundWorkerException();
    }

    public ArrayList<Manager> getManagers(String name) {
        ArrayList<Manager> result = new ArrayList<>();

        for (int i = 0; i < managers.size(); i++) {
            if (managers.get(i).getName() == name) {
                result.add(managers.get(i));
            }
        }

        return result;
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
        if (worker.getWorkerType() == WorkerType.Manager) {
            managers.add((Manager)worker);
        }
    }

    public void addManager(Manager manager) {
        addWorker(manager);
    }

    public void removeWorker(int id) throws NotFoundWorkerException {
        Worker w = getWorker(id);
        workers.remove(w);
        if (w.getWorkerType() == WorkerType.Manager) {
            managers.remove(w);
        }
    }

    public String workersToString() {
        String msg = "";
        for (int i = 0; i < workers.size(); i++) {
            msg += workers.get(i).toString() + (i < workers.size() - 1 ? "\n" : "");
        }
        return msg;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", yearFound=" + yearFound +
                ", address='" + address + '\'' +
                '}';
    }
}
