package com.framework.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by Marinko on 2016-05-22.
 */
public class ExecutorWithTimeout {


    private final static Logger logger = LoggerFactory.getLogger(ExecutorWithTimeout.class);

    public static void execute(int timeout, TimeUnit timeUnit, final CallBack callback) throws Exception{

        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit(new Callable() {

                public String call() throws Exception {
                    callback.task();
                    return "OK";
                }
            });
            future.get(timeout, timeUnit);
        } catch (Exception e) {
            logger.info("Timeout EXCEPTION");
            //throw e;
        } finally {
            executor.shutdownNow();
        }
    }
}
