package application;

import java.time.LocalTime;
import java.time.LocalDate;

public class Event { // Event class - similar to the Task class, but for events to show up on the schedule.

    // Fields
    private String title;
    private final LocalTime start;
    private final LocalTime end;
    private final LocalDate date;
    private boolean isWeekly;
    private boolean completed;

    // Constructors
    public Event(String title, LocalTime start, LocalTime end, LocalDate date, boolean isWeekly) {

        this.title = title;
        this.start = start;
        this.end = end;
        this.date = date;
        this.isWeekly = isWeekly;
    }

    // Methods

    // Getters
    public String getTitle() { return this.title; }
    public LocalTime getStart() { return this.start; }
    public LocalTime getEnd() { return this.end; }
    public LocalDate getDate() { return this.date; }
    public boolean getWeekly() { return this.isWeekly; }
    public boolean getCompleted() { return this.completed; }
    public void complete() {
        this.completed = true;
    }
}
