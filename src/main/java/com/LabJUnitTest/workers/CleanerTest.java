package com.LabJUnitTest.workers;

import com.Lab.workers.Cleaner;
import org.junit.Assert;
import org.junit.Test;

public class CleanerTest {

    private final Cleaner cleaner = new Cleaner();
    {
        cleaner.setWorkingHours(8);
    }

    @Test
    public void testPrepareRacks() {
        cleaner.prepareRacks();

        Assert.assertEquals(6, cleaner.getRackSet().getRacks().size());
    }

}