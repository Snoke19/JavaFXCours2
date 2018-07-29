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
import sample.AdditionalClasses.Excel;
import sample.AdditionalClasses.KeyValuePair;
import sample.AdditionalClasses.MessageAllTable;
import sample.JDBC.DeleteDataInTable;
import sample.JDBC.InsertDataInTable;
import sample.JDBC.SelectComputersJDBC.SelectAllDataTableJDBC;
import sample.JDBC.UpdateDataInTable;
import sample.model.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.WARNING;
import static sample.JDBC.SelectComputersJDBC.SelectAllDataTableJDBC.*;

public class EmployeeTable implements Initializable{

    private ObservableList<Employee> dataEmployee = FXCollections.observableArrayList();
    private IntegerProperty index = new SimpleIntegerProperty();
    private InsertDataInTable insertDataInTable = new InsertDataInTable();
    private MessageAllTable messageAllTable = new MessageAllTable();
    private DeleteDataInTable deleteDataInTable = new DeleteDataInTable();

    private boolean ifAdd;

    @FXML
    private TableView<Employee> tableEmployee;
    @FXML
    private TableColumn<Employee, Integer> columnIdEmployee;
    @FXML
    private TableColumn<Employee, String> columnAllNameEmployee;
    @FXML
    private TableColumn<Employee, String> columnPostEmployee;
    @FXML
    private TableColumn<Employee, String> columnLiabilityEmployee;
    @FXML
    private TableColumn<Employee, Integer> columnNumberPhoneEmployee;
    @FXML
    private TableColumn<Employee, Object> columnEnterpriseEmployee;
    @FXML
    private TableColumn<Employee, Object> columnTechniqueEmployee;

    @FXML
    private TextField textFieldAllNameEmployee;
    @FXML
    private TextField textFieldPostEmployee;
    @FXML
    private TextField textFieldNumberPhoneEmployee;
    @FXML
    private TextField textFieldForSearchEmployee;

    @FXML
    private ComboBox<String> comboBoxAllNameEnterprise;
    @FXML
    private ComboBox<KeyValuePair> comboBoxAllNameTechnique = new ComboBox<>();
    @FXML
    private ComboBox<String> stringComboBoxForSearch = new ComboBox<>();

    @FXML
    private CheckBox checkBoxLiability;

    @FXML
    private Button addButtonEmployee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        columnIdEmployee.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));
        columnAllNameEmployee.setCellValueFactory(new PropertyValueFactory<>("allNameEmployee"));
        columnPostEmployee.setCellValueFactory(new PropertyValueFactory<>("post"));
        columnLiabilityEmployee.setCellValueFactory(new PropertyValueFactory<>("liability"));
        columnNumberPhoneEmployee.setCellValueFactory(new PropertyValueFactory<>("numberPhone"));
        columnEnterpriseEmployee.setCellValueFactory(new PropertyValueFactory<>("enterprise"));
        columnTechniqueEmployee.setCellValueFactory(new PropertyValueFactory<>("technique"));

        tableEmployee.setItems(dataEmployee);

        /**
         * init comboBox Technique, Enterprise
         */
        comboBoxAllNameEnterprise.getItems().addAll(selectAllNameEnterprise());
        for (KeyValuePair aListKeyValue : getArrayObjectKeyValuePair()) {
            comboBoxAllNameTechnique.getItems().add(aListKeyValue);
        }

        /**
         * get index row TableView
         */
        tableEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            index.setValue(newValue.getIdEmployee());
            System.out.println("OK is index: " + dataEmployee.indexOf(newValue));
        });

        /**
         * Search employee
         */
        textFieldForSearchEmployee.textProperty().addListener((observable, oldValue, newValue) -> {
            String criterion = stringComboBoxForSearch.getSelectionModel().getSelectedItem();
            String value = textFieldForSearchEmployee.getText();

            System.out.println(criterion);
            System.out.println(value);

            if (stringComboBoxForSearch.getSelectionModel().isEmpty()){
                messageAllTable.alertTable("", "", "Виберіть більше, менше, рівно", WARNING);
                stringComboBoxForSearch.setStyle("-fx-background-color: #E57373;");
            }else {
                stringComboBoxForSearch.setStyle(null);
            }

            ObservableList<Employee> listEmployee = FXCollections.observableArrayList();

            listEmployee.addAll(selectEmployeeRecord(criterion, value));

            tableEmployee.getItems().clear();
            dataEmployee.addAll(listEmployee);
            tableEmployee.setItems(dataEmployee);
        });

        /**
         * contextMenu delete and edit
         */
        tableEmployee.setRowFactory(param -> {
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
            removeItem.setOnAction(event -> deleteDataInEmployee());
            rowMenu.getItems().addAll(editItem, removeItem);
            row.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(row.itemProperty()))
                            .then(rowMenu)
                            .otherwise((ContextMenu) null));
            return row;
        });

        stringComboBoxForSearch.getItems().add("Номер");
        stringComboBoxForSearch.getItems().add("Працівник ПІБ");
        stringComboBoxForSearch.getItems().add("Посада");
        stringComboBoxForSearch.getItems().add("Матеріальна відповідальність");
        stringComboBoxForSearch.getItems().add("Телефонний номер");
        stringComboBoxForSearch.getItems().add("Компанія");
        stringComboBoxForSearch.getItems().add("Техніка");

    }

    private void updateRecord() {
        TablePosition pos = tableEmployee.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        Employee CellRow = tableEmployee.getItems().get(row);
        textFieldAllNameEmployee.setText(CellRow.getAllNameEmployee());
        textFieldPostEmployee.setText(CellRow.getPost());
        checkBoxLiability.setText(CellRow.getLiability());
        textFieldNumberPhoneEmployee.setText(String.valueOf(CellRow.getNumberPhone()));
        ifAdd = false;
        addButtonEmployee.setText("Оновити дані");
    }

    private List<KeyValuePair> getArrayObjectKeyValuePair(){
        Map<Integer, String> map = selectAllNameTechnique();
        List<KeyValuePair> listKeyValue = new ArrayList<>();
        for (Object o : map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            listKeyValue.add(new KeyValuePair(pair.getKey(), pair.getValue()));
        }

        return listKeyValue;
    }

    private void initData() throws SQLException {
        dataEmployee.addAll(SelectAllDataTableJDBC.selectAllEmployee());
    }

    public void addDataInTableEmployee(){
        try {
            String allNameEmployee = textFieldAllNameEmployee.getText();
            String postEmployee = textFieldPostEmployee.getText();
            boolean liabilityEmployee = checkBoxLiability.isSelected();
            int numberPhoneEmployee = Integer.parseInt(textFieldNumberPhoneEmployee.getText());
            int allNameEnterprise = comboBoxAllNameEnterprise.getSelectionModel().getSelectedIndex() + 1;
            int allNameTechnique = (int) comboBoxAllNameTechnique.getValue().getKey();
            int i = selectIdEmployee();

            System.out.println(allNameEnterprise);
            if (ifAdd) {
                insertDataInTable.insertDataInEmployeeTable(++i, allNameEmployee, postEmployee, liabilityEmployee, numberPhoneEmployee, allNameEnterprise, allNameTechnique);
            } else {
                int id = index.get();

                UpdateDataInTable.updateEmployee(id,
                        textFieldAllNameEmployee.getText(),
                        textFieldPostEmployee.getText(),
                        checkBoxLiability.isSelected(),
                        Integer.parseInt(textFieldNumberPhoneEmployee.getText()),
                        comboBoxAllNameEnterprise.getSelectionModel().getSelectedIndex() + 1,
                        (Integer) comboBoxAllNameTechnique.getValue().getKey());


                ifAdd = true;

                tableEmployee.getItems().clear();
                initData();

                addButtonEmployee.setText("Додати");
            }

            tableEmployee.getItems().clear();
            initData();

            comboBoxAllNameTechnique.getItems().clear();
            for (KeyValuePair aListKeyValue : getArrayObjectKeyValuePair()) {
                comboBoxAllNameTechnique.getItems().add(aListKeyValue);
            }

            textFieldAllNameEmployee.clear();
            textFieldPostEmployee.clear();
            textFieldNumberPhoneEmployee.clear();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

       addButtonEmployee.setText("Додати");
    }

    public void deleteDataInEmployee() {
        int i = index.get();

        if (i == 0) {
            messageAllTable.alertTable("Видалення", null, "Виберіть поле для видалення", Alert.AlertType.INFORMATION);
        }else if (messageAllTable.alertDelete("Видалення запису", "", "Ви хочете видалити запис?", Alert.AlertType.WARNING) == ButtonType.YES) {
            try {

                deleteDataInTable.deleteDataInEmployeeTable(i);
                tableEmployee.getItems().clear();
                initData();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        comboBoxAllNameTechnique.getItems().clear();
        for (KeyValuePair aListKeyValue : getArrayObjectKeyValuePair()) {
            comboBoxAllNameTechnique.getItems().add(aListKeyValue);
        }
    }

    public void importDataEnterpriseExcel() throws IOException{
        Excel excel = new Excel();

        excel.importExcelEmployee(dataEmployee,
                columnIdEmployee,
                columnAllNameEmployee,
                columnPostEmployee,
                columnLiabilityEmployee,
                columnNumberPhoneEmployee,
                columnEnterpriseEmployee,
                columnTechniqueEmployee);
    }

    public void clearSearchButtonEmployee(){
        textFieldForSearchEmployee.clear();
    }
}
