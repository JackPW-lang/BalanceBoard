package application;

public class User {

    // Fields
    private String name;
    private final boolean level; // 0 = High School, 1 = University
    private double gpa;
    private double productivityScore;
    private boolean simpleOrComplex;
    private boolean[] complexityPreferences; // stores simple or complex mode preferences for each screen.
    private boolean[] introQuestionnaireAnswers;

    // Constructors
    public User(String name, boolean level) {

        this.name = name;
        this.level = level;
    }

    // Methods

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
    public void setSimpleOrComplex(boolean s) {
        this.simpleOrComplex = s;
    }
    public void setComplexityPreferences(boolean [] c) {
        this.complexityPreferences = c;
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
    public boolean getSimpleOrComplex() {
        return this.simpleOrComplex;
    }
    public boolean [] getComplexityPreferences() {
        return this.complexityPreferences;
    }
    public boolean [] getIntroQuestionnaireAnswers() {
        return this.introQuestionnaireAnswers;
    }
}
