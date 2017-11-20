
package com.airhacks.blog;

import com.airhacks.porcupine.configuration.control.ExecutorConfigurator;
import com.airhacks.porcupine.execution.control.ExecutorConfiguration;
import java.util.concurrent.ThreadPoolExecutor;
import javax.enterprise.inject.Specializes;

/**
 *
 * @author airhacks.com
 */
@Specializes
public class ThreadPoolConfigurator extends ExecutorConfigurator {

    //@Inject
    Integer maxPoolSize;

    @Override
    public ExecutorConfiguration forPipeline(String name) {
        if ("mes".equalsIgnoreCase(name)) {
            return new ExecutorConfiguration.Builder().
                    callerRunsPolicy().
                    customRejectedExecutionHandler(this::rejectedExecution).
                    corePoolSize(1).
                    maxPoolSize(1).
                    queueCapacity(1).
                    build();
        }
        return super.forPipeline(name);
    }

    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("-- overloaded: " + r.toString() + " " + executor);
    }


}
