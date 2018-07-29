package sample.view.WindowCode;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import sample.AdditionalClasses.KeyValuePair;
import sample.Main;
import sample.model.Employee;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static sample.JDBC.SelectComputersJDBC.SelectAllDataTableJDBC.*;

public class MainWindow implements Initializable{

    private SimpleIntegerProperty index = new SimpleIntegerProperty();
    private ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();

    @FXML
    private
    ListView<Employee> employeeListView = new ListView<>();

    @FXML
    private Label labelForNumberEmployee;
    @FXML
    private Label labelForAllNameEmployee;
    @FXML
    private Label labelForNameTechnique;
    @FXML
    private Label labelForPost;
    @FXML
    private Label labelForNumberPhone;
    @FXML
    private Label labelForLiability;

    @FXML
    private Label labelForInformationEnterprise;

    private int indexEnterprise;

    @FXML
    private ComboBox<KeyValuePair> enterpriseComboBox = new ComboBox<>();

    @FXML
    private TextField textFieldSearchForList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (KeyValuePair aListKeyValue : getArrayObjectKeyValuePair()) {
            enterpriseComboBox.getItems().add(aListKeyValue);
        }

        employeeListView.setItems(employeeObservableList);
        employeeObservableList.addAll(selectAllNameEmployeeSinceIdEnterprise(indexEnterprise));
        employeeListView.getItems().clear();
        setEmployeeListView();

        employeeListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            index.setValue(newValue.getIdEmployee());
            System.out.println(index);
        });

    }

    private List<KeyValuePair> getArrayObjectKeyValuePair(){
        Map<Integer, String> map = selectAllNameIdEnterprise();
        List<KeyValuePair> listKeyValue = new ArrayList<>();
        for (Object o : map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            listKeyValue.add(new KeyValuePair(pair.getKey(), pair.getValue()));
        }
        return listKeyValue;
    }
    private void setEmployeeListView(){
        employeeListView.setCellFactory(new Callback<ListView<Employee>, ListCell<Employee>>() {
            @Override
            public ListCell<Employee> call(ListView<Employee> param) {
                ListCell<Employee> cell = new ListCell<Employee>() {
                    protected void updateItem(Employee employee, boolean bln) {
                        super.updateItem(employee, bln);
                        if (employee != null) {
                            setText(employee.getAllNameEmployee());
                        }
                    }
                };
                return cell;
            }
        });
    }

    public void setEnterpriseComboBox(){
        String nameEnterprise = (String) enterpriseComboBox.getSelectionModel().getSelectedItem().getValue();
        labelForInformationEnterprise.setText(nameEnterprise);

        indexEnterprise = (int) enterpriseComboBox.getValue().getKey();
        System.out.println(indexEnterprise);

        employeeListView.getItems().clear();
        employeeObservableList.addAll(selectAllNameEmployeeSinceIdEnterprise(indexEnterprise));
        employeeListView.setItems(employeeObservableList);
        setEmployeeListView();
    }

    public void viewEmployeeInformation() {
        int id = index.get();
        ObservableList<Employee> observableListOneEmployee = FXCollections.observableArrayList(selectOneEmployee(id));

        for (Employee anObservableListOneEmployee : observableListOneEmployee) {
            labelForNumberEmployee.setText(String.valueOf(anObservableListOneEmployee.getIdEmployee()));
            labelForAllNameEmployee.setText(anObservableListOneEmployee.getAllNameEmployee());
            labelForNameTechnique.setText(anObservableListOneEmployee.getTechnique().getNameTech());
            labelForPost.setText(anObservableListOneEmployee.getPost());
            labelForNumberPhone.setText(String.valueOf(anObservableListOneEmployee.getNumberPhone()));
            labelForLiability.setText(anObservableListOneEmployee.getLiability());
        }
    }

    public void goToTechnique() throws IOException {
        Main main = new Main();
        main.isFrameTechnique();
    }
    public void goToEmployee() throws IOException {
        Main main = new Main();
        main.isFrameEmployee();
    }
    public void goToEnterprise() throws IOException {
        Main main = new Main();
        main.isFrameEnterprise();
    }
}