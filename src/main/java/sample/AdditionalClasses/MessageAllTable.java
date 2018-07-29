package sample.AdditionalClasses;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class MessageAllTable {

    public void alertTable(String setTitle, String setHeaderText, String setContentText, Alert.AlertType type, Exception e){

        Alert alert = new Alert(type);
        alert.setTitle(setTitle);
        alert.setHeaderText(setHeaderText);
        alert.setContentText(setContentText);

        String exceptionText = e.toString();


        Label label = new Label("The exception stacktrace was:");
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }

    public void alertTable(String setTitle, String setHeaderText, String setContentText, Alert.AlertType type){

        Alert alert = new Alert(type);
        alert.setTitle(setTitle);
        alert.setHeaderText(setHeaderText);
        alert.setContentText(setContentText);

        alert.showAndWait();
    }

    public ButtonType alertDelete(String setTitle, String setHeaderText, String setContentText, Alert.AlertType type){

        Alert alert = new Alert(type, setContentText , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setTitle(setTitle);
        alert.setHeaderText(setHeaderText);
        alert.showAndWait();

        return alert.getResult();
    }
}