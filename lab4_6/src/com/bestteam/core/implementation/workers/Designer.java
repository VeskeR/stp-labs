package com.bestteam.core.implementation.workers;

import com.bestteam.core.Worker;

/**
 * Created by Andrew on 12/21/2016.
 */
public class Designer extends Worker {
    public Designer() {
    }

    public Designer(String name, int wage) {
        super(WorkerType.Designer, name, wage);
    }
}
