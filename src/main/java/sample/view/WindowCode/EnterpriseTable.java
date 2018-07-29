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
import sample.model.Enterprise;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.WARNING;
import static sample.JDBC.SelectComputersJDBC.SelectAllDataTableJDBC.selectAllIdEnterpriseExistInEmployee;
import static sample.JDBC.SelectComputersJDBC.SelectAllDataTableJDBC.selectEnterpriseRecord;

public class EnterpriseTable implements Initializable {

    private Tooltip tooltip = new Tooltip();
    private MessageAllTable messageAllTable = new MessageAllTable();
    private DeleteDataInTable deleteDataInTable = new DeleteDataInTable();
    private IntegerProperty index = new SimpleIntegerProperty();
    private boolean ifAdd = true;

    private ObservableList<Enterprise> dataEnterprise = FXCollections.observableArrayList();

    @FXML
    private TableView<Enterprise> tableEnterprise = new TableView<>();
    @FXML
    private TableColumn<Enterprise, Integer> columnIdEnterprise;
    @FXML
    private TableColumn<Enterprise, String> columnNameEnterprise;
    @FXML
    private TableColumn<Enterprise, String> columnCity;
    @FXML
    private TableColumn<Enterprise, String> columnAddress;
    @FXML
    private TableColumn<Enterprise, String> columnAllNameDirector;
    @FXML
    private TableColumn<Enterprise, String> columnAllNameAccountant;

    @FXML
    TextField nameEnterpriseTextField;
    @FXML
    TextField cityEnterpriseTextField;
    @FXML
    TextField addressEnterpriseTextField;
    @FXML
    TextField allNameDirectorTextField;
    @FXML
    TextField allNameAccountantTextField;
    @FXML
    Button buttonClear = new Button();
    @FXML
    Button buttonClearSearch = new Button();
    @FXML
    Button buttonExcel = new Button();
    @FXML
    Button buttonAddData = new Button();
    @FXML
    TextField textFieldForSearchEnterprise;
    @FXML
    private ComboBox<String> stringComboBoxForEnterpriseSearch;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            initData();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        /**
         * init column in TableView
         */
        columnIdEnterprise.setCellValueFactory(new PropertyValueFactory<>("idEnterprise"));
        columnNameEnterprise.setCellValueFactory(new PropertyValueFactory<>("nameEnterprise"));
        columnCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnAllNameDirector.setCellValueFactory(new PropertyValueFactory<>("allNameDirector"));
        columnAllNameAccountant.setCellValueFactory(new PropertyValueFactory<>("allNameAccountant"));

        tableEnterprise.setItems(dataEnterprise);

        /**
         * get index column Table View, from Enterprise
         */
        tableEnterprise.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            index.setValue(newValue.getIdEnterprise());
        });

        /**
         * Search for Enterprise
         */
        textFieldForSearchEnterprise.textProperty().addListener((observable, oldValue, newValue) -> {
            String criterion = stringComboBoxForEnterpriseSearch.getSelectionModel().getSelectedItem();
            String value = textFieldForSearchEnterprise.getText();

            System.out.println(criterion);
            System.out.println(value);

            if (stringComboBoxForEnterpriseSearch.getSelectionModel().isEmpty()){
                messageAllTable.alertTable("", "", "Виберіть критерій пошуку", WARNING);
                stringComboBoxForEnterpriseSearch.setStyle("-fx-background-color: #E57373;");
            }else {
                stringComboBoxForEnterpriseSearch.setStyle(null);
            }

            ObservableList<Enterprise> listEnterprise = FXCollections.observableArrayList();

            listEnterprise.addAll(selectEnterpriseRecord(criterion, value));

            tableEnterprise.getItems().clear();
            dataEnterprise.addAll(listEnterprise);
            tableEnterprise.setItems(dataEnterprise);
        });

        /**
         * contextMenu delete and edit
         */
        tableEnterprise.setRowFactory(param -> {
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
            removeItem.setOnAction(event -> deleteDataInEnterprise());
            rowMenu.getItems().addAll(editItem, removeItem);
            row.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(row.itemProperty()))
                            .then(rowMenu)
                            .otherwise((ContextMenu) null));
            return row;
        });

        /**
         * all comboBox for Enterprise
         */
        stringComboBoxForEnterpriseSearch.getItems().add("Номер");
        stringComboBoxForEnterpriseSearch.getItems().add("Назва");
        stringComboBoxForEnterpriseSearch.getItems().add("Місто");
        stringComboBoxForEnterpriseSearch.getItems().add("Адресса");
        stringComboBoxForEnterpriseSearch.getItems().add("Директор");
        stringComboBoxForEnterpriseSearch.getItems().add("Бухгалтер");

        /**
         * all tooltip for Enterprise
         */
        tooltip.setText("Очистити всі поля");
        buttonClear.setTooltip(tooltip);

        tooltip.setText("Очисти всі поля");
        tooltip.setStyle("-fx-background-color: #E53935;");
        tooltip.setStyle("-fx-font-size: 12;");
        buttonClear.setTooltip(tooltip);

        tooltip.setText("Очистити поле");
        tooltip.setStyle("-fx-background-color: #E53935;");
        tooltip.setStyle("-fx-font-size: 12;");
        buttonClearSearch.setTooltip(tooltip);
    }

    private void initData() throws SQLException {
        dataEnterprise.addAll(SelectAllDataTableJDBC.selectAllEnterprise());
    }

    public void addDataInEnterprise() throws SQLException {

        InsertDataInTable insertDataInTable = new InsertDataInTable();
        Validation validate = new Validation();

        String stringNameEnterprise = nameEnterpriseTextField.getText();
        String stringCityEnterprise = cityEnterpriseTextField.getText();
        String stringAddressEnterprise = addressEnterpriseTextField.getText();
        String stringAllNameDirector = allNameDirectorTextField.getText();
        String stringAllNameAccountant = allNameAccountantTextField.getText();
        int i = SelectAllDataTableJDBC.selectIdEnterprise();

        if (ifAdd) {
            if (stringNameEnterprise.isEmpty() || stringCityEnterprise.isEmpty() || stringAddressEnterprise.isEmpty() || stringAllNameDirector.isEmpty() || stringAllNameAccountant.isEmpty()) {
                messageAllTable.alertTable("Заповніть поля", null, "Заповніть всі поля", Alert.AlertType.ERROR);
            } else {
                if (validate.validateOnlyTextExistEng(stringNameEnterprise, nameEnterpriseTextField)
                        && validate.validateForCity(stringCityEnterprise, cityEnterpriseTextField)
                        && validate.validateForStreet(stringAddressEnterprise, addressEnterpriseTextField)
                        && validate.validateOnlyAllName(stringAllNameDirector, allNameDirectorTextField)
                        && validate.validateOnlyAllName(stringAllNameAccountant, allNameAccountantTextField)) {

                    insertDataInTable.insertDataInEnterpriseTable(++i, stringNameEnterprise, stringCityEnterprise, stringAddressEnterprise, stringAllNameDirector, stringAllNameAccountant);

                    tableEnterprise.getItems().clear();
                    initData();

                    nameEnterpriseTextField.clear();
                    cityEnterpriseTextField.clear();
                    addressEnterpriseTextField.clear();
                    allNameDirectorTextField.clear();
                    allNameAccountantTextField.clear();
                }
            }
        } else {
            int id = index.get();
            UpdateDataInTable.updateEnterprise(id,
                    nameEnterpriseTextField.getText(),
                    cityEnterpriseTextField.getText(),
                    addressEnterpriseTextField.getText(),
                    allNameDirectorTextField.getText(),
                    allNameAccountantTextField.getText());
            ifAdd = true;

            tableEnterprise.getItems().clear();
            initData();

            buttonAddData.setText("Додати");
            clearAllTextFieldEnterprise();
        }
    }

    public void deleteDataInEnterprise(){
        int i = index.get();

        try {
            if (i == 0){
                messageAllTable.alertTable("Видалення", null, "Виберіть поле для видалення", Alert.AlertType.INFORMATION);
            }else {
                if (messageAllTable.alertDelete("Видалення запису", "", "Ви хочете видалити запис?", Alert.AlertType.WARNING) == ButtonType.YES) {
                    if (selectAllIdEnterpriseExistInEmployee().contains(i)) {
                        messageAllTable.alertTable("Видалення даних", null, "Цей запис видалити не можливо!\n" +
                                "Дані зв'язані з таблицею Працівники!", Alert.AlertType.ERROR);
                    } else {
                        deleteDataInTable.deleteDataInEnterpriseTable(i);

                        tableEnterprise.getItems().clear();
                        initData();
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateRecord() {
        TablePosition pos = tableEnterprise.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        Enterprise CellRow = tableEnterprise.getItems().get(row);
        nameEnterpriseTextField.setText(CellRow.getNameEnterprise());
        cityEnterpriseTextField.setText(CellRow.getCity());
        addressEnterpriseTextField.setText(CellRow.getAddress());
        allNameDirectorTextField.setText(CellRow.getAllNameDirector());
        allNameAccountantTextField.setText(CellRow.getAllNameAccountant());
        ifAdd = false;
        buttonAddData.setText("Оновити дані");
    }

    public void importDataEnterpriseExcel() throws IOException {
        Excel excel = new Excel();
        excel.importExcelEnterprise(dataEnterprise, columnIdEnterprise, columnNameEnterprise, columnCity, columnAddress, columnAllNameDirector, columnAllNameAccountant);
    }

    public void clearAllTextFieldEnterprise(){
        nameEnterpriseTextField.clear();
        cityEnterpriseTextField.clear();
        addressEnterpriseTextField.clear();
        allNameDirectorTextField.clear();
        allNameAccountantTextField.clear();
    }

    public void clearButtonSearch(){
        textFieldForSearchEnterprise.clear();
    }
}