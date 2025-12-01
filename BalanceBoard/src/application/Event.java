package application;

import java.time.LocalTime;
import java.time.LocalDate;

public class Event { // Event class - similar to the Task class, but for events to show up on the schedule.

    // Fields
    private String title;
    private LocalTime start;
    private LocalTime end;
    private LocalDate date;
    private boolean isWeekly;

    // Constructors
    public Event(String title, LocalTime start, LocalTime end, LocalDate date, boolean isWeekly) {

        this.title = title;
        this.start = start;
        this.end = end;
        this.date = date;
        this.isWeekly = isWeekly;
    }

    // Methods

    // Setters
    public void setTitle(String t) {
        this.title = t;
    }
    public void setStart(LocalTime s) {
        this.start = s;
    }
    public void setEnd(LocalTime e) {
        this.end = e;
    }
    public void setDate(LocalDate d) {
        this.date = d;
    }
    public void setWeekly(boolean w) {
        this.isWeekly = w;
    }

    // Getters
    public String getTitle() {
        return this.title;
    }
    public LocalTime getStart() {
        return this.start;
    }
    public LocalTime getEnd() {
        return this.end;
    }
    public LocalDate getDate() {
        return this.date;
    }
    public boolean getWeekly() {
        return this.isWeekly;
    }

}
