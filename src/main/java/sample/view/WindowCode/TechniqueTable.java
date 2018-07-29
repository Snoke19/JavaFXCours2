package sample.view.WindowCode;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.JDBC.DeleteDataInTable;
import sample.JDBC.InsertDataInTable;
import sample.JDBC.SelectComputersJDBC.SelectAllDataTableJDBC;
import sample.JDBC.UpdateDataInTable;
import sample.AdditionalClasses.Excel;
import sample.AdditionalClasses.MessageAllTable;
import sample.AdditionalClasses.Validation;
import sample.model.Technique;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.WARNING;
import static sample.JDBC.SelectComputersJDBC.SelectAllDataTableJDBC.*;

public class TechniqueTable implements Initializable {

    private DeleteDataInTable deleteDataInTable = new DeleteDataInTable();
    private IntegerProperty index = new SimpleIntegerProperty();
    private MessageAllTable messageAllTable = new MessageAllTable();
    private Validation validation = new Validation();
    private Tooltip tooltip = new Tooltip();

    private boolean ifAdd = true;

    private ObservableList<Technique> dataTechnique = FXCollections.observableArrayList();

    @FXML
    public TableView<Technique> tableTechnique = new TableView<>();
    @FXML
    private TableColumn<Technique, Integer> columnIdTech;
    @FXML
    private TableColumn<Technique, String> columnType;
    @FXML
    private TableColumn<Technique, String> columnNameTech;
    @FXML
    private TableColumn<Technique, Integer> columnInventoryNumbers;
    @FXML
    private TableColumn<Technique, Float> columnStartCost;
    @FXML
    private TableColumn<Technique, Float> columnActualCost;

    @FXML
    private TextField techNameTechniqueTextField;
    @FXML
    private TextField inventoryNumbersTechniqueTextField;
    @FXML
    private TextField startCostTechniqueTextField;
    @FXML
    private TextField actualCostTechniqueTextField;

    @FXML
    private TextField textFieldForSearchTechnique;
    @FXML
    private TextField textFieldForStartCost = new TextField();
    @FXML
    private TextField textFieldForActualCost = new TextField();

    @FXML
    private ComboBox<String> stringTypeTechniqueComboBox = new ComboBox<>();
    @FXML
    private ComboBox<String> stringComboBoxTechniqueSearch = new ComboBox<>();
    @FXML
    private ComboBox<String> stringComboBoxForStartCost = new ComboBox<>();
    @FXML
    private ComboBox<String> stringComboBoxForActualCost = new ComboBox<>();

    @FXML
    private Button buttonRestartTable = new Button();
    @FXML
    private Button buttonClear = new Button();
    @FXML
    private Button buttonAddData = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            initData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         * init column in TableView
         */
        columnInventoryNumbers.setStyle("-fx-alignment: CENTER;");
        columnStartCost.setStyle("-fx-alignment: CENTER;");
        columnActualCost.setStyle("-fx-alignment: CENTER;");

        columnIdTech.setCellValueFactory(new PropertyValueFactory<>("idTechnique"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnNameTech.setCellValueFactory(new PropertyValueFactory<>("nameTech"));
        columnInventoryNumbers.setCellValueFactory(new PropertyValueFactory<>("inventoryNumbers"));
        columnStartCost.setCellValueFactory(new PropertyValueFactory<>("startCost"));
        columnActualCost.setCellValueFactory(new PropertyValueFactory<>("actualCost"));

        tableTechnique.setItems(dataTechnique);

        /**
         * get index row TableView
         */
        tableTechnique.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            index.setValue(newValue.getIdTechnique());
            System.out.println("OK is index: " + dataTechnique.indexOf(newValue));
        });

        /**
         * Search
         */
        textFieldForSearchTechnique.textProperty().addListener((observable, oldValue, newValue) -> {
            String criterion = stringComboBoxTechniqueSearch.getSelectionModel().getSelectedItem();
            String value = textFieldForSearchTechnique.getText();

            System.out.println(criterion);
            System.out.println(value);

            if (stringComboBoxTechniqueSearch.getSelectionModel().isEmpty()) {
                messageAllTable.alertTable("", "", "Виберіть критерій пошуку", WARNING);
                stringComboBoxTechniqueSearch.setStyle("-fx-background-color: #E57373;");
            } else {
                stringComboBoxTechniqueSearch.setStyle(null);
            }

            ObservableList<Technique> listTechnique = FXCollections.observableArrayList();

            listTechnique.addAll(selectTechniqueRecord(criterion, value));

            tableTechnique.getItems().clear();
            dataTechnique.addAll(listTechnique);
            tableTechnique.setItems(dataTechnique);
        });


        /**
         * select >, <, = start cost
         */
        textFieldForStartCost.textProperty().addListener((observable, oldValue, newValue) -> {
            String sign = stringComboBoxForStartCost.getSelectionModel().getSelectedItem();

            if (stringComboBoxForStartCost.getSelectionModel().isEmpty()) {
                messageAllTable.alertTable("", "", "Виберіть більше, менше, рівно", WARNING);
                stringComboBoxForStartCost.setStyle("-fx-background-color: #E57373;");
            } else {
                stringComboBoxForStartCost.setStyle(null);
            }

            ObservableList<Technique> observableListTechnique = FXCollections.observableArrayList();
            switch (sign) {
                case "більше":
                    observableListTechnique.addAll(selectStartCostTechnique(">", Double.parseDouble(newValue)));
                    break;
                case "менше":
                    observableListTechnique.addAll(selectStartCostTechnique("<", Double.parseDouble(newValue)));
                    break;
                case "рівно":
                    observableListTechnique.addAll(selectStartCostTechnique("=", Double.parseDouble(newValue)));
                    break;
            }

            tableTechnique.getItems().clear();
            dataTechnique.addAll(observableListTechnique);
            tableTechnique.setItems(dataTechnique);
        });

        /**
         * select >, <, = actual cost
         */
        textFieldForActualCost.textProperty().addListener((observable, oldValue, newValue) -> {
            String sign1 = stringComboBoxForActualCost.getSelectionModel().getSelectedItem();

            if (stringComboBoxForActualCost.getSelectionModel().isEmpty()){
                messageAllTable.alertTable("", "", "Виберіть більше, менше, рівно", WARNING);
                stringComboBoxForActualCost.setStyle("-fx-background-color: #E57373;");
            } else {
                stringComboBoxForActualCost.setStyle(null);
            }

            ObservableList<Technique> observableListTechnique1 = FXCollections.observableArrayList();
            switch (sign1){
                case "більше":
                    observableListTechnique1.addAll(selectActualCostTechnique(">", Double.parseDouble(newValue)));
                    break;
                case "менше":
                    observableListTechnique1.addAll(selectActualCostTechnique("<", Double.parseDouble(newValue)));
                    break;
                case "рівно":
                    observableListTechnique1.addAll(selectActualCostTechnique("=", Double.parseDouble(newValue)));
                    break;
            }

            tableTechnique.getItems().clear();
            dataTechnique.addAll(observableListTechnique1);
            tableTechnique.setItems(dataTechnique);
        });

        /**
         * contextMenu delete and edit
         */
        tableTechnique.setRowFactory(param -> {
            final TableRow row = new TableRow();
            final ContextMenu rowMenu = new ContextMenu();
            ContextMenu tableMenu = param.getContextMenu();
            if (tableMenu != null) {
                rowMenu.getItems().addAll(tableMenu.getItems());
                rowMenu.getItems().add(new SeparatorMenuItem());
            }

            MenuItem editItem = new MenuItem("Редагувати");
            editItem.setOnAction(event -> updateRecord());

            MenuItem removeItem = new MenuItem("Видалити");
            removeItem.setOnAction(event -> deleteDataInTechnique());


            rowMenu.getItems().addAll(editItem, removeItem);
            row.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(row.itemProperty()))
                            .then(rowMenu)
                            .otherwise((ContextMenu) null));
            return row;
        });

        /**
         * all comboBox for Technique
         */
        stringComboBoxTechniqueSearch.getItems().add("Номер");
        stringComboBoxTechniqueSearch.getItems().add("Тип");
        stringComboBoxTechniqueSearch.getItems().add("Назва");
        stringComboBoxTechniqueSearch.getItems().add("Інвентаризаційний номер");
        stringComboBoxTechniqueSearch.getItems().add("Початкова ціна");
        stringComboBoxTechniqueSearch.getItems().add("Актуальна ціна");

        stringTypeTechniqueComboBox.getItems().add("ПК");
        stringTypeTechniqueComboBox.getItems().add("Ноутбук");
        stringTypeTechniqueComboBox.getItems().add("Моноблок");
        stringTypeTechniqueComboBox.getItems().add("Прінтер");
        stringTypeTechniqueComboBox.getItems().add("Монітор");

        stringComboBoxForStartCost.getItems().add("більше");
        stringComboBoxForStartCost.getItems().add("менше");
        stringComboBoxForStartCost.getItems().add("рівно");

        stringComboBoxForActualCost.getItems().add("більше");
        stringComboBoxForActualCost.getItems().add("менше");
        stringComboBoxForActualCost.getItems().add("рівно");

        /**
         * all tooltip for Technique
         */
        tooltip.setText("Очистити всі поля");
        buttonClear.setTooltip(tooltip);

        tooltip.setText("Оновити таблицю");
        tooltip.setStyle("-fx-background-color: #E53935;");
        tooltip.setStyle("-fx-font-size: 12;");
        buttonRestartTable.setTooltip(tooltip);
    }

    private void initData() throws SQLException {
        dataTechnique.addAll(SelectAllDataTableJDBC.selectAllTechnique());
    }

    public void addDataInTechnique() throws SQLException {
        InsertDataInTable insertDataInTable = new InsertDataInTable();

        String stringTypeTech = stringTypeTechniqueComboBox.getSelectionModel().getSelectedItem();
        String stringTechName = techNameTechniqueTextField.getText();
        String intInventoryNumbers = inventoryNumbersTechniqueTextField.getText();
        String floatStartCost = startCostTechniqueTextField.getText();
        String floatActualCost = actualCostTechniqueTextField.getText();
        int i = SelectAllDataTableJDBC.selectIdTechnique();

        if (techNameTechniqueTextField.getText().isEmpty() || inventoryNumbersTechniqueTextField.getText().isEmpty() || startCostTechniqueTextField.getText().isEmpty() || actualCostTechniqueTextField.getText().isEmpty()) {
            messageAllTable.alertTable("Заповніть поля", null, "Заповніть всі поля", Alert.AlertType.ERROR);
        } else {
            if (validation.validateOnlyTextExistEngAndNumber(stringTechName, techNameTechniqueTextField)
                    && validation.validateOnlyNumbers(intInventoryNumbers, inventoryNumbersTechniqueTextField)
                    && validation.validateOnlyNumbers(floatStartCost, startCostTechniqueTextField)
                    && validation.validateOnlyNumbers(floatActualCost, actualCostTechniqueTextField)) {
                if (ifAdd) {
                    if (stringTypeTech.isEmpty()
                            || stringTechName.isEmpty()
                            || intInventoryNumbers.isEmpty()
                            || floatStartCost.isEmpty()
                            || floatActualCost.isEmpty()) {
                        messageAllTable.alertTable("", null, "Заповніть всі поля", ERROR);
                    } else {

                        insertDataInTable.insertDataInTechniqueTable(++i, stringTypeTech, stringTechName, Integer.parseInt(intInventoryNumbers), Float.parseFloat(floatStartCost), Float.parseFloat(floatActualCost));

                        techNameTechniqueTextField.clear();
                        inventoryNumbersTechniqueTextField.clear();
                        startCostTechniqueTextField.clear();
                        actualCostTechniqueTextField.clear();
                    }
                } else {
                    int id = index.get();

                    UpdateDataInTable.updateTechnique(id,
                            stringTypeTechniqueComboBox.getSelectionModel().getSelectedItem(),
                            techNameTechniqueTextField.getText(),
                            Integer.parseInt(inventoryNumbersTechniqueTextField.getText()),
                            Float.parseFloat(startCostTechniqueTextField.getText()),
                            Float.parseFloat(actualCostTechniqueTextField.getText()));
                    ifAdd = true;

                    buttonAddData.setText("Додати");
                    clearButtonTechnique();
                }
            }
        }

        tableTechnique.getItems().clear();
        initData();
    }

    public void deleteDataInTechnique() {
        int i = index.get();

        try {
            if (i == 0) {
                messageAllTable.alertTable("Видалення", null, "Виберіть поле для видалення", Alert.AlertType.INFORMATION);
            } else {
                if (messageAllTable.alertDelete("Видалення запису", "", "Ви хочете видалити запис?", Alert.AlertType.WARNING) == ButtonType.YES) {
                    if (selectAllIdTechniqueExistInTechnique().contains(i)) {
                        messageAllTable.alertTable("Видалення даних", null, "Цей запис видалити не можливо!\n" +
                                "Дані зв'язані з таблицею Працівники!", Alert.AlertType.ERROR);
                    } else {
                        deleteDataInTable.deleteDataInTechniqueTable(i);

                        tableTechnique.getItems().clear();
                        initData();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRecord() {
        TablePosition pos = tableTechnique.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        Technique CellRow = tableTechnique.getItems().get(row);
        stringTypeTechniqueComboBox.setValue(CellRow.getType());
        techNameTechniqueTextField.setText(CellRow.getNameTech());
        inventoryNumbersTechniqueTextField.setText(String.valueOf(CellRow.getInventoryNumbers()));
        startCostTechniqueTextField.setText(String.valueOf(CellRow.getStartCost()));
        actualCostTechniqueTextField.setText(String.valueOf(CellRow.getActualCost()));
        ifAdd = false;
        buttonAddData.setText("Оновити дані");
    }

    public void importDataTechniqueExcel() {
        Excel excel = new Excel();
        excel.importExcelTechnique(dataTechnique, columnIdTech, columnType, columnNameTech, columnInventoryNumbers, columnStartCost, columnActualCost);
    }

    public void clearButtonTechnique() {
        techNameTechniqueTextField.clear();
        inventoryNumbersTechniqueTextField.clear();
        startCostTechniqueTextField.clear();
        actualCostTechniqueTextField.clear();
    }
    public void clearTextFieldSearch() {
        textFieldForSearchTechnique.clear();
    }
    public void clearTextFieldStartCost() throws SQLException {
        textFieldForStartCost.clear();
        tableTechnique.getItems().clear();
        initData();
    }
    public void clearTextFieldActualCost() throws SQLException {
        textFieldForActualCost.clear();
        tableTechnique.getItems().clear();
        initData();
    }
}