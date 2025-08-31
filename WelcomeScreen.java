package application;

public class WelcomeScreen {
    /*
    As per the documentation and the README, this class contains the code necessary for sections 4.1 - 4.2, i.e.
    the setup screen and the general welcome screen.

    When the user launches the app for the first time, they shall be directed to a questionnaire serving to taylor the
    app to their needs. This, in a control flow sense, can be implemented by simply calling the constructor of this
    class - eliminating the need for a boolean field to indicate if it is the users first time launching the app. From
    here, a method will be called containing the questionnaire, and populating the remaining fields in this class with
    the answers. This seems to be the cleanest approach.

    Then when the user launches the app again, now the fields and methods can be manipulated as usual, the constructor
    will never be called again, eliminating an erroneous trigger of the questionnaire for a second time.

    The fields of this class will represent the answers to the questionnaire - having these persist as field values will
    enable the user to go back and change the settings. A separate edit/update settings method will be implemented in
    this class allowing the user to modify the fields at their discretion.
     */

    // Fields go here - mainly to be answered by questionnaire.

    // Constructor
    public WelcomeScreen() {
        answerQuestionnaire();
    }

    // Getters, Setters and other Methods
    public void answerQuestionnaire() {
        // This is where the questionnaire is answered, populating the fields above.
    }

    public void editSettings() {
        // This is where the user will be able to edit their settings later.
    }
}
