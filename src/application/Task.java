package application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ListResourceBundle;

public class Task { // Task class - tasks which are displayed on the agenda screen.

    // Fields
    private String title;
    private int daysRemaining;
    private boolean isCompleted;
    private boolean isRecurring;
    //private int daysToSpare; (for analytics purposes)
    //private int daysToStart; (for analytics purposes)

    // Constructors
    public Task (String title, LocalDate dueDate) {

        this.title = title;
        this.daysRemaining = (int) ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
        //this.isRecurring = isRecurring;
        this.isCompleted = false;
    }

    // Methods
    public void complete() { this.isCompleted = true; }

    // Setters
    public void setTitle(String t) { this.title = t; }
    public void setdaysRemaining(int d) { this.daysRemaining = d; }
    public void setCompleted(boolean c) { this.isCompleted = c; }

    // Getters
    public String getTitle() { return this.title; }
    public int getDaysRemaining() { return this.daysRemaining; }
    public boolean getCompleted() { return this.isCompleted; }
}
