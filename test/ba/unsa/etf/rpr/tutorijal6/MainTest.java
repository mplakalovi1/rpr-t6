package ba.unsa.etf.rpr.tutorijal6;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
class MainTest {

    @Start
    public void start(Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("/fxml/formular.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    public void nameFieldTest(FxRobot robot) {
        TextField nameField = robot.lookup("#nameField").queryAs(TextField.class);
        robot.clickOn("#nameField").write("Matej");
        assertEquals("Matej", nameField.getText());
    }

    @Test
    public void surnameFieldTest(FxRobot robot) {
        TextField surnameField = robot.lookup("#surnameField").queryAs(TextField.class);
        robot.clickOn("#surnameField").write("Plakalovic");
        assertEquals("Plakalovic", surnameField.getText());
    }

    @Test
    public void indexFieldTest(FxRobot robot) {
        TextField indexField = robot.lookup("#indexField").queryAs(TextField.class);
        robot.clickOn("#indexField").write("17808");
        assertEquals("17808", indexField.getText());
    }

    @Test
    public void genderBoxTest(FxRobot robot) {
        ChoiceBox genderBox = robot.lookup("#genderBox").queryAs(ChoiceBox.class);
        robot.clickOn("#genderBox").clickOn("Muški");
        assertEquals("Muški",genderBox.getValue().toString());
    }

    @Test
    public void emailTest(FxRobot robot) {
        TextField emailBox = robot.lookup("#eMailField").queryAs(TextField.class);
        robot.clickOn("#eMailField").write("mplakalovi1@etfunsa.ba");
        assertEquals("mplakalovi1@etfunsa.ba", emailBox.getText());
    }

    @Test
    public void telephoneTest(FxRobot robot) {
        TextField telephoneField = robot.lookup("#telephoneField").queryAs(TextField.class);
        robot.clickOn("#telephoneField").write("062-911818");
        assertEquals("062-911818", telephoneField.getText());
    }

    @Test
    public void adressTest(FxRobot robot) {
        TextField adressField = robot.lookup("#adressField").queryAs(TextField.class);
        robot.clickOn("#adressField").write("Kucukovici 14");
        assertEquals("Kucukovici 14", adressField.getText());
    }

    @Test
    public void podaciOStudijutest(FxRobot robot) {
        ChoiceBox odsjek = robot.lookup("#departmentBox").queryAs(ChoiceBox.class);
        robot.clickOn("#departmentBox").clickOn("AE");
        ChoiceBox godina = robot.lookup("#yearBox").queryAs(ChoiceBox.class);
        robot.clickOn("#yearBox").clickOn("Treća");
        ChoiceBox ciklus = robot.lookup("#cycleBox").queryAs(ChoiceBox.class);
        robot.clickOn("#cycleBox").clickOn("Bachelor");
        ChoiceBox status = robot.lookup("#statusBox").queryAs(ChoiceBox.class);
        robot.clickOn("#statusBox").clickOn("Redovan");
        ChoiceBox kategorija = robot.lookup("#categoryBox").queryAs(ChoiceBox.class);
        robot.clickOn("#categoryBox").clickOn("NE");
        assertAll("podaci o studiju",()->{
            assertEquals("AE", odsjek.getValue().toString());
            assertEquals("Treća", godina.getValue().toString());
            assertEquals("Bachelor", ciklus.getValue().toString());
            assertEquals("Redovan", status.getValue().toString());
            assertEquals("NE", kategorija.getValue().toString());
        });
    }
}