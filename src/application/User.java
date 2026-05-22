package application;
import java.util.ArrayList;
import java.util.Iterator;

public class User  {

    // Fields
    private String name;
    private Level level;
    private double gpa;
    private double productivityScore;
    private final boolean[] complexityPreferences; // stores simple or complex mode preferences for each screen. {WelcomeScreen, AgendaScreen, SchedulerScreen, AnalyticsScreen}
    private final boolean[] introQuestionnaireAnswers = new boolean[1]; // change to reflect actual properties of form.
    private final ArrayList <Task> taskList;
    private final ArrayList <Event> eventList;

    // Constructors
    public User(String name, Level level) {

        this.name = name;
        this.level = level;
        this.complexityPreferences = new boolean[4]; // to be set once questionnaire answered.
        this.taskList = new ArrayList <Task> ();
        this.eventList = new ArrayList <Event> ();
    }

    // Methods
    public void addTask(Task t) { this.taskList.add(t); }
    public void removeTask(Task t) { this.taskList.remove(t); }
    public void addEvent(Event e) {this.eventList.add(e); }
    public void removeEvent(Event e) {this.eventList.remove(e); }

    // Setters
    public void setName(String n) { this.name = name; }
    public void setGpa(double g) { this.gpa = g; }
    public void setProductivityScore(double p) { this.productivityScore = p; }
    public void updateWelcomeComplexity(boolean b) { this.complexityPreferences[0] = b; }
    public void updateSchedulerComplexity(boolean b) { this.complexityPreferences[2] = b; }
    public void updateAnalyticsComplexity(boolean b) { this.complexityPreferences[3] = b; }
    public void setIntroQuestionnaireAnswers(boolean [] j) {
        for (int i = 0; i < introQuestionnaireAnswers.length; i++) {
            introQuestionnaireAnswers[i] = j[i];
        }
    }


    // Getters
    public String getName() { return this.name; }
    public double getGpa() { return this.gpa; }
    public double getProductivityScore() { return this.productivityScore; }
    public boolean [] getComplexityPreferences() { return this.complexityPreferences; }
    public boolean [] getIntroQuestionnaireAnswers() { return this.introQuestionnaireAnswers; }
    public ArrayList <Task> getTaskList() { return this.taskList; }
    public ArrayList <Event> getEventList() { return this.eventList; }
}
