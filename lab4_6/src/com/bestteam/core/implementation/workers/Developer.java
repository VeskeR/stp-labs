package com.bestteam.core.implementation.workers;

import com.bestteam.core.Worker;

/**
 * Created by Andrew on 12/21/2016.
 */
public class Developer extends Worker {
    public Developer() {
    }

    public Developer(String name, int wage) {
        super(WorkerType.Developer, name, wage);
    }
}
