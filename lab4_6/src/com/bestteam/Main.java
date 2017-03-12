package com.bestteam;


import com.bestteam.core.Worker;
import com.bestteam.core.implementation.Company;
import com.bestteam.core.implementation.Date;
import com.bestteam.core.implementation.Task;
import com.bestteam.core.implementation.schedule.Schedule;
import com.bestteam.core.implementation.workers.*;
import com.bestteam.exceptions.NotEnoughHoursException;
import com.bestteam.exceptions.NotFoundScheduleException;
import com.bestteam.exceptions.NotFoundWorkerException;
import com.bestteam.exceptions.ScheduleAlreadyExistsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Andrew on 12/21/2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
//        Company cmp = new Company("BestTeamCompany", 2016, "Kyiv Polytechnic Institute");
//
//        cmp.addWorker(new Developer("Andrii Bulat", 10));
//        cmp.addWorker(new Developer("Andrii Sherbyna", 10));
//        cmp.addWorker(new Developer("Ivan Zavertkin", 10));
//
//        cmp.addWorker(new BusinessAnalyst("Random Name", 8));
//        cmp.addWorker(new Designer("Random Name 2", 7));
//        cmp.addWorker(new Manager("Mano Garo", 11));
//        cmp.addWorker(new SystemAdministrator("Sys Admin", 11));
//
//        cmp.addWorker(new Tester("Tess Tur", 6));
//        cmp.addWorker(new Tester("Tuss Ter", 7));
//
//        cmp.getWorkers("Mano Garo").get(0).addSchedule(Schedule.createBaseSchedule());
//
//        cmp.save("D:\\cmp1.json");
//
//        Company cmp2 = Company.loadFromFile("D:\\cmp1.json", Company.class);
        new Interface().start();
    }

    private static class Interface {
        private Scanner scanner;
        private Company cmp;
        private boolean exit;

        public void start() {
            scanner = new Scanner(System.in);
            exit = false;
            help();

            while (!exit) {
                try {
                    next();
                    String input = readString().toLowerCase();
                    switch (input) {
                        case "l":
                            load();
                            break;
                        case "s":
                            save();
                            break;
                        case "cc":
                            createCompany();
                            break;
                        case "cw":
                            createWorker();
                            break;
                        case "cs":
                            createSchedule();
                            break;
                        case "ct":
                            createTask();
                            break;
                        case "oc":
                            outputCompany();
                            break;
                        case "os":
                            outputSchedules();
                            break;
                        case "ot":
                            outputTasks();
                            break;
                        case "h":
                            help();
                            break;
                        case "x":
                            exit();
                            break;
                        default:
                            unknown();
                            break;
                    }
                } catch (Exception e) {
                    println("Unhandled exception: " + e.getMessage());
                }
            }
        }

        private void next() {
            print("Input next command: ");
        }

        private void load() {
            print("Input file path to read from: ");
            String filePath = readString();
            try {
                cmp = cmp.loadFromFile(filePath, Company.class);
            } catch (IOException e) {
                println("Error while loading company: " + e.getMessage());
            }
        }

        private void save() {
            print("Input file path to save to: ");
            String filePath = readString();
            try {
                cmp.save(filePath);
            } catch (IOException e) {
                println("Error while saving company: " + e.getMessage());
            }
        }

        private void createCompany() {
            print("Input company name: ");
            String name = readString();
            print("Input year when company was found: ");
            int year = readInt();
            print("Input company address: ");
            String address = readString();

            cmp = new Company(name, year, address);
        }

        private void createWorker() {
            print("Input worker type: ");
            String workerType = readString().toLowerCase();
            print("Input name: ");
            String name = readString();
            print("Input wage: ");
            int wage = readInt();

            Worker w = null;

            switch (workerType) {
                case "businessanalyst":
                    w = new BusinessAnalyst(name, wage);
                    break;
                case "designer":
                    w = new Designer(name, wage);
                    break;
                case "developer":
                    w = new Developer(name, wage);
                    break;
                case "manager":
                    w = new Manager(name, wage);
                    break;
                case "systemadministrator":
                    w = new SystemAdministrator(name, wage);
                    break;
                case "tester":
                    w = new Tester(name, wage);
                    break;
                default:
                    println("Wrong worker type.");
                    break;
            }

            if (w != null) {
                cmp.addWorker(w);
            }
        }

        private void createSchedule() {
            print("Input year: ");
            int year = readInt();
            print("Input month: ");
            int month = readInt();
            print("Input day: ");
            int day = readInt();
            print("Input worker id to assign schedule to: ");
            int id = readInt();

            try {
                Worker w = cmp.getWorker(id);
                w.addSchedule(Schedule.createBaseSchedule(year, month, day));
            } catch (NotFoundWorkerException e) {
                println("Error while looking for worker: " + e.getMessage());
            } catch (ScheduleAlreadyExistsException e) {
                println("Error while creating schedule: " + e.getMessage());
            }
        }

        private void createTask() {
            print("Input task name: ");
            String name = readString();
            print("Input task description: ");
            String description = readString();
            print("Input task hours: ");
            int hours = readInt();
            print("Input task year: ");
            int year = readInt();
            print("Input task month: ");
            int month = readInt();
            print("Input task day: ");
            int day = readInt();
            print("Input worker id to assign task to: ");
            int id = readInt();

            Task task = new Task(name, description, hours);
            Date date = new Date(year, month, day);

            try {
                Worker w = cmp.getWorker(id);
                w.assignTask(task, date);
            } catch (NotFoundWorkerException e) {
                println("Error while looking for worker: " + e.getMessage());
            } catch (NotEnoughHoursException e) {
                println("Error while assigning task: " + e.getMessage());
            } catch (NotFoundScheduleException e) {
                println("Error while assigning task: " + e.getMessage());
            }
        }

        private void outputCompany() {
            println("Company information:");
            println(cmp.toString());
            println("");
            println("Workers:");
            println(cmp.workersToString());
        }

        private void outputSchedules() {
            print("Input id of worker to output his schedules: ");
            int id = readInt();

            try {
                Worker w = cmp.getWorker(id);
                println("");
                println(w.toString());
                println("Schedules:");
                println(w.schedulesToString());
            } catch (NotFoundWorkerException e) {
                println("Error while getting worker: " + e.getMessage());
            }
        }

        private void outputTasks() {
            print("Input id of worker to output his tasks: ");
            int id = readInt();
            print("Input schedule year: ");
            int year = readInt();
            print("Input schedule month: ");
            int month = readInt();
            print("Input schedule day: ");
            int day = readInt();

            Date date = new Date(year, month, day);

            try {
                Worker w = cmp.getWorker(id);
                Schedule s = w.getSchedule(date);
                println("");
                println(w.toString());
                println(s.toString());
                println("Tasks:");
                println(s.tasksToString());
            } catch (NotFoundWorkerException e) {
                println("Error while getting worker: " + e.getMessage());
            } catch (NotFoundScheduleException e) {
                println("Error while getting schedule: " + e.getMessage());
            }
        }

        private void help() {
            println("--- HELP ---");
            println("l -- load");
            println("s -- save");
            println("cc -- createCompany");
            println("cw -- createWorker");
            println("cs -- createSchedule");
            println("ct -- createTask");
            println("oc -- outputCompany");
            println("os -- outputSchedules");
            println("ot -- outputTasks");
            println("h -- help");
            println("x -- exit");
            println("--- HELP ---");
        }

        private void exit() {
            println("Bye!");
            exit = true;
        }

        private void unknown() {
            println("You entered unknown command. Please try again. Press 'h' for help.");
        }

        private <T> void print(T msg) {
            System.out.print(msg);
        }

        private <T> void println(T msg) {
            System.out.println(msg);
        }

        private String readString() {
            return scanner.next();
        }

        private int readInt() {
            return scanner.nextInt();
        }

        private double readDouble() {
            return scanner.nextDouble();
        }
    }
}
