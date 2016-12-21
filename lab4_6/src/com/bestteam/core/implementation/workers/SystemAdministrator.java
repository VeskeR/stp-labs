package com.bestteam.core.implementation.workers;

import com.bestteam.core.Worker;

/**
 * Created by Andrew on 12/21/2016.
 */
public class SystemAdministrator extends Worker {
    public SystemAdministrator() {
    }

    public SystemAdministrator(String name, int wage) {
        super(WorkerType.SystemAdministrator, name, wage);
    }
}
