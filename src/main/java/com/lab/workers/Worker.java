package com.lab.workers;

import java.util.Objects;

public abstract class Worker {

    protected String name;
    protected String surname;
    protected int salary;
    protected double bonus;
    protected int workingHours;

    public void setWorkingHours(int workingHours) { this.workingHours = workingHours; }
    public void setSalary(int salary) { this.salary = salary; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return salary == worker.salary &&
                workingHours == worker.workingHours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, workingHours);
    }

    public int getSalary() {
        return salary;
    }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public int getWorkingHours() { return workingHours; }
    public double getBonus() { return bonus; }
}