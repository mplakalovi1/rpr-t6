package ba.unsa.etf.rpr.tutorijal6;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public TextField nameField;
    public TextField surnameField;
    public TextField indexField;
    public ChoiceBox genderBox;
    public TextField jmbgField;
    public DatePicker dateField;
    public ComboBox placeOfBirthField;
    public TextField adressField;
    public TextField telephoneField;
    public TextField eMailField;
    public ChoiceBox departmentBox;
    public ChoiceBox yearBox;
    public ChoiceBox cycleBox;
    public ChoiceBox statusBox;
    public ChoiceBox categoryBox;
    public Button confirmButton;
    public boolean imeValidno;
    public boolean prezimeValidno;
    public boolean indeksValidan;
    public boolean jmbgValidan;
    public boolean datumValidan;
    public boolean telBrojValidan;
    public boolean emailValidan;
    public boolean mjestoValidno;

    public boolean validnoIme(String s) {
        if (s.length() > 20 || s.length() < 1) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(Character.isLetter(c) || c == ' ')) return false;
        }
        return true;
    }

    public boolean validnoPrezime(String s) {
        if (s.length() > 20 || s.length() < 1) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(Character.isLetter(c) || c == '-')) return false;
        }
        return true;
    }

    public boolean validanIndeks(String s) {
        if (s.length() != 5) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public boolean validanJmbg(String jmbg) {
        if (jmbg.length() != 13) return false;
        else if (dateField.getValue() == null) return false;
        else if (genderBox.getValue() == null) return false;
        for (int i = 0; i < jmbg.length(); i++) {
            char c = jmbg.charAt(i);
            if (!Character.isDigit(c)) return false;
        }
        int dan = (jmbg.charAt(0) - '0') * 10 + (jmbg.charAt(1) - '0');
        int mjesec = (jmbg.charAt(2) - '0') * 10 + (jmbg.charAt(3) - '0');
        int godina = 1000 + (jmbg.charAt(4) - '0') * 100 + (jmbg.charAt(5) - '0') * 10 + (jmbg.charAt(6) - '0');
        LocalDate date = dateField.getValue();
        if (dan != date.getDayOfMonth() || mjesec != date.getMonthValue() || godina != date.getYear()) return false;
        int politickaRegija = (jmbg.charAt(7) - '0') * 10 + (jmbg.charAt(8) - '0');
        if (politickaRegija < 0 || politickaRegija > 96) return false;
        int jedinstveniBroj = (jmbg.charAt(9) - '0') * 100 + (jmbg.charAt(10) - '0') * 10 + (jmbg.charAt(11) - '0');
        String s = (String) genderBox.getValue();
        if (s == "Muški" && (jedinstveniBroj < 0 || jedinstveniBroj > 499)) return false;
        else if (s == "Ženski" && (jedinstveniBroj > 999 || jedinstveniBroj < 500)) return false;
        int kontrolnaCifra = 11 - ((7 * ((jmbg.charAt(0) - '0') + (jmbg.charAt(6) - '0')) + 6 * ((jmbg.charAt(1) - '0') + (jmbg.charAt(7) - '0')) + 5 * ((jmbg.charAt(2) - '0') + (jmbg.charAt(8) - '0')) + 4 * ((jmbg.charAt(3) - '0') + (jmbg.charAt(9) - '0')) + 3 * ((jmbg.charAt(4) - '0') + (jmbg.charAt(10) - '0')) + 2 * ((jmbg.charAt(5) - '0') + (jmbg.charAt(11) - '0'))) % 11);
        if (kontrolnaCifra > 9) kontrolnaCifra = 0;
        if (kontrolnaCifra != (jmbg.charAt(12) - '0')) return false;
        return true;
    }

    public boolean validanDatum(LocalDate date) {
        if (dateField.getValue() == null) return false;
        LocalDate date1 = LocalDate.now();
        if (date.getYear() > date1.getYear()) return false;
        else if (date.getMonthValue() > date1.getMonthValue() && date.getYear() == date1.getYear()) return false;
        else if (date.getDayOfMonth() > date1.getDayOfMonth() && date.getMonthValue() == date1.getMonthValue() && date.getYear() == date1.getYear())
            return false;
        return true;
    }

    public boolean validnoMjesto(String s) {
        if (s.isEmpty() || s.length() > 20) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(Character.isLetter(c) || c==' ')) return false;
        }
        return true;
    }

    public static boolean isValidEmail(String emailStr){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    public static boolean isValidTelephone(String nmbr){
        if (nmbr.isEmpty()) return true;
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Pattern pattern1 = Pattern.compile("\\d{3}-\\d{6}");
        Matcher matcher = pattern.matcher(nmbr);
        Matcher matcher1 = pattern1.matcher(nmbr);
        return (matcher.matches()|| matcher1.matches());
    }

    @FXML
    public void initialize() {
        imeValidno = false;
        nameField.getStyleClass().add("poljeNeispravno");
        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoIme(n)) {
                    nameField.getStyleClass().removeAll("poljeNeispravno");
                    nameField.getStyleClass().add("poljeIspravno");
                    imeValidno = true;
                } else {
                    nameField.getStyleClass().removeAll("poljeIspravno");
                    nameField.getStyleClass().add("poljeNeispravno");
                    imeValidno = false;
                }
            }
        });
        prezimeValidno = false;
        surnameField.getStyleClass().add("poljeNeispravno");
        surnameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoPrezime(n)) {
                    surnameField.getStyleClass().removeAll("poljeNeispravno");
                    surnameField.getStyleClass().add("poljeIspravno");
                    prezimeValidno = true;
                } else {
                    surnameField.getStyleClass().removeAll("poljeIspravno");
                    surnameField.getStyleClass().add("poljeNeispravno");
                    prezimeValidno = false;
                }
            }
        });
        indeksValidan = false;
        indexField.getStyleClass().add("poljeNeispravno");
        indexField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanIndeks(n)) {
                    indexField.getStyleClass().removeAll("poljeNeispravno");
                    indexField.getStyleClass().add("poljeIspravno");
                    indeksValidan = true;
                } else {
                    indexField.getStyleClass().removeAll("poljeIspravno");
                    indexField.getStyleClass().add("poljeNeispravno");
                    indeksValidan = false;
                }
            }
        });
        jmbgValidan = false;
        jmbgField.getStyleClass().add("poljeNeispravno");
        jmbgField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanJmbg(n)) {
                    jmbgField.getStyleClass().removeAll("poljeNeispravno");
                    jmbgField.getStyleClass().add("poljeIspravno");
                    jmbgValidan = true;
                } else {
                    jmbgField.getStyleClass().removeAll("poljeIspravno");
                    jmbgField.getStyleClass().add("poljeNeispravno");
                    jmbgValidan = false;
                }
            }
        });
        datumValidan = false;
        dateField.getStyleClass().add("poljeNeispravno");
        dateField.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (validanDatum(newValue)) {
                    dateField.getStyleClass().removeAll("poljeNeispravno");
                    dateField.getStyleClass().add("poljeIspravno");
                    datumValidan = true;

                    jmbgValidan = false;
                    jmbgField.getStyleClass().add("poljeNeispravno");
                    jmbgField.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                            if (validanJmbg(n)) {
                                jmbgField.getStyleClass().removeAll("poljeNeispravno");
                                jmbgField.getStyleClass().add("poljeIspravno");
                                jmbgValidan = true;
                            } else {
                                jmbgField.getStyleClass().removeAll("poljeIspravno");
                                jmbgField.getStyleClass().add("poljeNeispravno");
                                jmbgValidan = false;
                            }
                        }
                    });
                } else {
                    dateField.getStyleClass().removeAll("poljeIspravno");
                    dateField.getStyleClass().add("poljeNeispravno");
                    datumValidan = false;

                    jmbgValidan = false;
                    jmbgField.getStyleClass().add("poljeNeispravno");
                    jmbgField.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                            if (validanJmbg(n)) {
                                jmbgField.getStyleClass().removeAll("poljeNeispravno");
                                jmbgField.getStyleClass().add("poljeIspravno");
                                jmbgValidan = true;
                            } else {
                                jmbgField.getStyleClass().removeAll("poljeIspravno");
                                jmbgField.getStyleClass().add("poljeNeispravno");
                                jmbgValidan = false;
                            }
                        }
                    });
                }

            }
        });
        telBrojValidan = true;
        telephoneField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (isValidTelephone(n)) {
                    telephoneField.getStyleClass().removeAll("poljeNeispravno");
                    telephoneField.getStyleClass().add("poljeIspravno");
                    telBrojValidan = true;
                } else {
                    telephoneField.getStyleClass().removeAll("poljeIspravno");
                    telephoneField.getStyleClass().add("poljeNeispravno");
                    telBrojValidan = false;
                }
            }
        });
        emailValidan = false;
        eMailField.getStyleClass().add("poljeNeispravno");
        eMailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (isValidEmail(n)) {
                    eMailField.getStyleClass().removeAll("poljeNeispravno");
                    eMailField.getStyleClass().add("poljeIspravno");
                    emailValidan = true;
                } else {
                    eMailField.getStyleClass().removeAll("poljeIspravno");
                    eMailField.getStyleClass().add("poljeNeispravno");
                    emailValidan = false;
                }
            }
        });
        mjestoValidno = false;
        placeOfBirthField.getStyleClass().add("poljeNeispravno");
        placeOfBirthField.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoMjesto(n)) {
                    placeOfBirthField.getStyleClass().removeAll("poljeNeispravno");
                    placeOfBirthField.getStyleClass().add("poljeIspravno");
                    mjestoValidno = true;
                } else {
                    placeOfBirthField.getStyleClass().removeAll("poljeIspravno");
                    placeOfBirthField.getStyleClass().add("poljeNeispravno");
                    mjestoValidno = false;
                }
            }
        });
    }

    public boolean formularValidan() {
        if (!(imeValidno && prezimeValidno && indeksValidan && jmbgValidan && datumValidan && emailValidan && telBrojValidan && mjestoValidno && genderBox.getValue() != null)) return false;
        return true;
    }

    public void clickOnConfirm(ActionEvent actionEvent) {
        if (!formularValidan()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nije validno");
            alert.setHeaderText("Formular za unos podataka o studentu nije validan!");
            alert.setContentText("Crvenom bojom označeni su podaci koji su pogrešni ili nedostaju.");
            alert.show();
        }
        else {
            System.out.println("-----PODACI O STUDENTU UNESENI PREKO FORMULARA-----");
            System.out.println(" - Osnovni podaci - \n" + "Ime i prezime: " + nameField.getText() + " " + surnameField.getText() + "\nBroj indeksa: " + indexField.getText() + "\nSpol: " + genderBox.getValue().toString());
            System.out.println(" - Detaljni lični podaci - \n" + "JMBG: " + jmbgField.getText() + "\nDatum rođenja: " + dateField.getValue().toString() + "\nMjesto rođenja: " + placeOfBirthField.getValue().toString());
            System.out.println(" - Kontakt podaci - ");
            if (!adressField.getText().isEmpty()) System.out.println("Kontakt adresa: " + adressField.getText());
            else if (!telephoneField.getText().isEmpty()) System.out.println("Kontakt telefon: " + telephoneField.getText());
            System.out.println("E-mail adresa: " + eMailField.getText());
            if (departmentBox.getValue() != null || yearBox.getValue() != null || cycleBox.getValue() != null || statusBox.getValue() != null || categoryBox.getValue() != null)
                System.out.println(" - Podaci o studiju - ");
            if (departmentBox.getValue() != null)
                System.out.println("Smjer: " + departmentBox.getValue().toString());
            if (yearBox.getValue() != null)
                System.out.println("Godina: " + yearBox.getValue().toString());
            if (cycleBox.getValue() != null)
                System.out.println("Ciklus: " + cycleBox.getValue().toString());
            if (statusBox.getValue() != null)
                System.out.println("Status: " + statusBox.getValue().toString());
            if(categoryBox.getValue() != null)
                System.out.println("Boračka kategorija: " + categoryBox.getValue().toString());

        }
    }
}