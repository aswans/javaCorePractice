package com.aswans.classloader.jarloader;

import org.junit.Test;

import com.aswans.classloader.jarloader.common.Executor;
import com.aswans.classloader.jarloader.proxy.ExecutorProxy;

public class ExecutorTest {
	@Test
    public void testExecuteV1() {

        Executor executor = new ExecutorProxy("v1");

        executor.execute("TOM");
    }

    @Test
    public void testExecuteV2() {

        Executor executor = new ExecutorProxy("v2");

        executor.execute("TOM");
    }
}
