package sample.AdditionalClasses;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class Validation {

    private MessageAllTable messageAllTable = new MessageAllTable();

    public boolean validateOnlyTextExistEngAndNumber(String strTextField, TextField textField){
        Pattern pattern = Pattern.compile("[а-яА-ЯіІA-Za-z0-9. -]+");
        Matcher matcher = pattern.matcher(strTextField);
        if (matcher.find() && matcher.group().equals(strTextField)){
            textField.setStyle(null);
            return true;
        }else {
            String messageForTextFieldEnterprise = "Невірний формат даних поля " + textField.getPromptText() + ": " + textField.getText() + "\n"
                    + "Допустимі символи а - я, А - Я, a - z, A - Z";
            textField.setStyle("-fx-border-color: #E53935; " + "-fx-background-color: #FFCDD2;");
            messageAllTable.alertTable("Формат даних", null, messageForTextFieldEnterprise, ERROR);
            return false;
        }
    }

    public boolean validateOnlyTextExistEng(String strTextField, TextField textField){
        Pattern pattern = Pattern.compile("[а-яА-ЯіІA-Za-z. -]+");
        Matcher matcher = pattern.matcher(strTextField);
        if (matcher.find() && matcher.group().equals(strTextField)){
            textField.setStyle(null);
            return true;
        }else {
            String messageForTextFieldEnterprise = "Невірний формат даних поля " + textField.getPromptText() + ": " + textField.getText() + "\n"
                    + "Допустимі символи а - я, А - Я, a - z, A - Z";
            textField.setStyle("-fx-border-color: #E53935; " + "-fx-background-color: #FFCDD2;");
            messageAllTable.alertTable("Формат даних", null, messageForTextFieldEnterprise, ERROR);
            return false;
        }
    }

    public boolean validateForCity(String getText, TextField textField){
        Pattern pattern = Pattern.compile("[а-яА-ЯіІїЇ. -]+");
        Matcher matcher = pattern.matcher(getText);
        if (matcher.find() && matcher.group().equals(getText)){
            textField.setStyle(null);
            return true;
        }else {
            String messageForTextFieldEnterprise = "Невірний формат даних поля " + textField.getPromptText() + ": " + textField.getText() + "\n"
                    + "Допустимі символи а - я, А - Я, -";
            textField.setStyle("-fx-border-color: #E53935; " + "-fx-background-color: #FFCDD2;");
            messageAllTable.alertTable("Формат даних", null, messageForTextFieldEnterprise, ERROR);
            return false;
        }
    }

    public boolean validateForStreet(String strTextField, TextField textField){
        Pattern pattern = Pattern.compile("[а-яА-ЯіІїЇ0-9. -]+");
        Matcher matcher = pattern.matcher(strTextField);
        if (matcher.find() && matcher.group().equals(strTextField)){
            textField.setStyle(null);
            return true;
        }else {
            String messageForTextFieldEnterprise = "Невірний формат даних поля " + textField.getPromptText() + ": " + textField.getText() + "\n"
                    + "Допустимі символи а - я, А - Я";
            textField.setStyle("-fx-border-color: #E53935; " + "-fx-background-color: #FFCDD2;");
            messageAllTable.alertTable("Формат даних", null, messageForTextFieldEnterprise, ERROR);
            return false;
        }
    }

    public boolean validateOnlyAllName(String strTextField, TextField textField){
        Pattern pattern = Pattern.compile("([А-ЯІ][а-яі]+[\\-\\s]?){3,}");
        Matcher matcher = pattern.matcher(strTextField);
        if (matcher.find() && matcher.group().equals(strTextField)){
            textField.setStyle(null);
            return true;
        }else {
            String messageForAllNameEnterprise = "Невірний формат даних поля " + textField.getPromptText() + ": " + textField.getText() + "\n"
                    + "Правильне введення даних\n\n"
                    + "Наприклад: Тарас Григорович Шевченко";
            textField.setStyle("-fx-border-color: #E53935; " + "-fx-background-color: #FFCDD2;");
            messageAllTable.alertTable("Формат даних", null, messageForAllNameEnterprise, ERROR);
            return false;
        }
    }

    public boolean validateOnlyNumbers(String strTextField, TextField textField){
        Pattern pattern = Pattern.compile("[0-9.]+");
        Matcher matcher = pattern.matcher(strTextField);
        if (matcher.find() && matcher.group().equals(strTextField)){
            textField.setStyle(null);
            return true;
        }else {
            String messageForAllNameEnterprise = "Невірний формат даних поля " + textField.getPromptText() + ": " + textField.getText() + "\n";
            textField.setStyle("-fx-border-color: #E53935; " + "-fx-background-color: #FFCDD2;");
            messageAllTable.alertTable("Формат даних", null, messageForAllNameEnterprise, ERROR);
            return false;
        }
    }
}
