package com.LabJUnitTest.workers;

import com.Lab.customer.Customer;
import com.Lab.workers.Manager;
import org.junit.Assert;
import org.junit.Test;

public class ManagerTest {

    private final Manager manager = new Manager();
    {
        manager.setWorkingHours(7);
    }

    @Test
    public void testGetOpinion() {
        Customer customer1 = new Customer("Alex", "Samoylov");
        Customer customer2 = new Customer("Kateryna", "Ivanova");
        Customer customer3 = new Customer("John", "Smith");

        manager.getOpinion(customer1, "Nice shop");
        manager.getOpinion(customer2, "Sellers are impolite!");
        manager.getOpinion(customer3, "I like this market.");

        Assert.assertEquals(3, manager.getReviews().size());
    }

}