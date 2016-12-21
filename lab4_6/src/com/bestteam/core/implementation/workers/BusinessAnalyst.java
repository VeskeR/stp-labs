package com.bestteam.core.implementation.workers;

import com.bestteam.core.Worker;

/**
 * Created by Andrew on 12/21/2016.
 */
public class BusinessAnalyst extends Worker {
    public BusinessAnalyst() {

    }

    public BusinessAnalyst(String name, int wage) {
        super(WorkerType.BusinessAnalyst, name, wage);
    }
}
