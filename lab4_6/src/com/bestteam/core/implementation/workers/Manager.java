package com.bestteam.core.implementation.workers;

import com.bestteam.core.Worker;

/**
 * Created by Andrew on 12/21/2016.
 */
public class Manager extends Worker {
    public Manager() {
    }

    public Manager(String name, int wage) {
        super(WorkerType.Manager, name, wage);
    }
}
