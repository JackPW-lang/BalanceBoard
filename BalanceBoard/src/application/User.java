package application;
import java.util.ArrayList;

public class User {

    // Fields
    private String name;
    private final boolean level; // 0 = High School, 1 = University
    private double gpa;
    private double productivityScore;
    private boolean[] complexityPreferences; // stores simple or complex mode preferences for each screen. {WelcomeScreen, AgendaScreen, SchedulerScreen, AnalyticsScreen}
    private boolean[] introQuestionnaireAnswers;
    private ArrayList <Task> taskList;

    // Constructors
    public User(String name, boolean level) {

        this.name = name;
        this.level = level;
        this.complexityPreferences = new boolean[4];
        this.taskList = new ArrayList <Task> ();
    }

    // Methods
    public void addTask(Task t) {
        this.taskList.add(t);
    }
    public void removeTask(Task t) {
        this.taskList.remove(t);
    }

    // Setters
    public void setName(String n) {
        this.name = name;
    }
    public void setGpa(double g) {
        this.gpa = g;
    }
    public void setProductivityScore(double p) {
        this.productivityScore = p;
    }
    public void setComplexityPreferences(boolean [] c) {
        this.complexityPreferences = c;
    }
    public void updateWelcomeComplexity(boolean b) {
        this.complexityPreferences[0] = b;
    }
    public void updateSchedulerComplexity(boolean b) {
        this.complexityPreferences[2] = b;
    }
    public void updateAnalyticsComplexity(boolean b) {
        this.complexityPreferences[3] = b;
    }
    public void setIntroQuestionnaireAnswers(boolean [] i) {
        this.introQuestionnaireAnswers = i;
    }

    // Getters
    public String getName() {
        return this.name;
    }
    public double getGpa() {
        return this.gpa;
    }
    public double getProductivityScore() {
        return this.productivityScore;
    }
    public boolean [] getComplexityPreferences() {
        return this.complexityPreferences;
    }
    public boolean [] getIntroQuestionnaireAnswers() {
        return this.introQuestionnaireAnswers;
    }
    public ArrayList <Task> getTaskList() { return this.taskList; }
}
