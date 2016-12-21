package com.bestteam.core.implementation.workers;

import com.bestteam.core.Worker;

/**
 * Created by Andrew on 12/21/2016.
 */
public class Tester extends Worker {
    public Tester() {
    }

    public Tester(String name, int wage) {
        super(WorkerType.Tester, name, wage);
    }
}
