package sample.JDBC.SelectComputersJDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.JDBC.staticJDBC;
import sample.model.Employee;
import sample.model.Enterprise;
import sample.model.Technique;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectAllDataTableJDBC {

    private static final String sub = "Підписано";
    private static final String unSub = "Не підписано";


    //Enterprise
    public static ObservableList<Enterprise> selectAllEnterprise() {

        ObservableList<Enterprise> enterpriseList = null;

        try {
            String selectEmployee = "SELECT enterprise.idEnterprise, enterprise.name, enterprise.city, enterprise.address,\n" +
                    "  concat(enterprise.name_director, \" \", enterprise.middle_name_director, \" \", enterprise.last_name_director),\n" +
                    "  concat(enterprise.name_accountant, \" \", enterprise.middle_name_accountant, \" \", enterprise.last_name_accountant)\n" +
                    "FROM enterprise;";


            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(selectEmployee);
            enterpriseList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Enterprise enterprise = new Enterprise();

                enterprise.setIdEnterprise(resultSet.getInt("idEnterprise"));
                enterprise.setNameEnterprise(resultSet.getString("name"));
                enterprise.setCity(resultSet.getString("city"));
                enterprise.setAddress(resultSet.getString("address"));
                enterprise.setAllNameDirector(resultSet.getString("concat(enterprise.name_director, \" \", enterprise.middle_name_director, \" \", enterprise.last_name_director)"));
                enterprise.setAllNameAccountant(resultSet.getString("concat(enterprise.name_accountant, \" \", enterprise.middle_name_accountant, \" \", enterprise.last_name_accountant)"));

                enterpriseList.add(enterprise);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return enterpriseList;
    }
    public static List<Integer> selectAllIdEnterpriseExistInEmployee(){

        List<Integer> list = null;

        try {
            String selectIdEnterprise = "SELECT enterprise.idEnterprise\n" +
                    "FROM enterprise, employee\n" +
                    "WHERE EXISTS(SELECT * FROM enterprise, employee WHERE enterprise.idEnterprise = employee.enterprise_idEnterprise)\n" +
                    "AND enterprise.idEnterprise = employee.enterprise_idEnterprise;";

            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectIdEnterprise);

            list = new ArrayList<>();

            while (resultSet.next()){

                int idEnterprise = resultSet.getInt("idEnterprise");

                list.add(idEnterprise);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return list;
    }
    public static List<String> selectAllNameEnterprise(){

        List<String> listAllNameEnterprise = null;

        try {
            String selectAllNameEnterprise = "SELECT concat(enterprise.name, \", \", enterprise.city, \", \", enterprise.address) FROM enterprise;";

            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllNameEnterprise);

            listAllNameEnterprise = new ArrayList<>();
            while (resultSet.next()){
                String allNameEnterprise = resultSet.getString("concat(enterprise.name, \", \", enterprise.city, \", \", enterprise.address)");
                listAllNameEnterprise.add(allNameEnterprise);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return listAllNameEnterprise;
    }
    public static Map<Integer, String> selectAllNameIdEnterprise(){

        Map<Integer, String> listAllNameEnterprise = null;

        try {
            String selectAllNameIdEnterprise = "SELECT enterprise.idEnterprise, concat(enterprise.name, \", \", enterprise.city, \", \", enterprise.address) FROM enterprise;";

            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllNameIdEnterprise);

            listAllNameEnterprise = new HashMap<>();

            while (resultSet.next()){

                int idEnterprise = resultSet.getInt("enterprise.idEnterprise");
                String allAddressEnterprise = resultSet.getString("concat(enterprise.name, \", \", enterprise.city, \", \", enterprise.address)");

                listAllNameEnterprise.put(idEnterprise, allAddressEnterprise);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return listAllNameEnterprise;
    }
    public static int selectIdEnterprise(){
        int id = 0;
        try {
            String selectEnterprise = "SELECT enterprise.idEnterprise\n" +
                    "FROM enterprise\n" +
                    "ORDER BY enterprise.idEnterprise ASC;";

            Connection connection = staticJDBC.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectEnterprise);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                id = resultSet.getInt("idEnterprise");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
    public static ObservableList<Enterprise> selectEnterpriseRecord(String record, String value){

        ObservableList<Enterprise> enterpriseList = null;
        String recordForSelectEnterprise = null;

        switch (record) {
            case "Номер":
                recordForSelectEnterprise = "idEnterprise";
                break;
            case "Назва":
                recordForSelectEnterprise = "name";
                break;
            case "Місто":
                recordForSelectEnterprise = "city";
                break;
            case "Адресса":
                recordForSelectEnterprise = "address";
                break;
            case "Директор":
                recordForSelectEnterprise = "concat(name_director, \" \", middle_name_director, \" \", last_name_director)";
                break;
            case "Бухгалтер":
                recordForSelectEnterprise = "concat(name_accountant, \" \", middle_name_accountant, \" \", last_name_accountant)";
                break;
        }

        try {
            String selectEmployee = "SELECT idEnterprise, " +
                    "name, " +
                    "city, " +
                    "address,\n" +
                    "concat(name_director, \" \", middle_name_director, \" \", last_name_director),\n" +
                    "concat(name_accountant, \" \", middle_name_accountant, \" \", last_name_accountant)\n" +
                    "FROM enterprise\n" +
                    "WHERE "+ recordForSelectEnterprise +" LIKE '"+ value +"%'";


            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(selectEmployee);
            enterpriseList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Enterprise enterprise = new Enterprise();

                enterprise.setIdEnterprise(resultSet.getInt("idEnterprise"));
                enterprise.setNameEnterprise(resultSet.getString("name"));
                enterprise.setCity(resultSet.getString("city"));
                enterprise.setAddress(resultSet.getString("address"));
                enterprise.setAllNameDirector(resultSet.getString("concat(name_director, \" \", middle_name_director, \" \", last_name_director)"));
                enterprise.setAllNameAccountant(resultSet.getString("concat(name_accountant, \" \", middle_name_accountant, \" \", last_name_accountant)"));

                enterpriseList.add(enterprise);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return enterpriseList;
    }

    //Technique
    public static ObservableList<Technique> selectAllTechnique(){

        ObservableList<Technique> list = null;

        try {
            String selectTechnique = "SELECT idTechnique, type, name_tech, inventory_numbers, start_cost, actual_cost FROM technique";

            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectTechnique);

            list = FXCollections.observableArrayList();

            while (resultSet.next()){
                Technique technique = new Technique();

                technique.setIdTechnique(resultSet.getInt("idTechnique"));
                technique.setType(resultSet.getString("type"));
                technique.setNameTech(resultSet.getString("name_tech"));
                technique.setInventoryNumbers(resultSet.getInt("inventory_numbers"));
                technique.setStartCost(resultSet.getFloat("start_cost"));
                technique.setActualCost(resultSet.getFloat("actual_cost"));

                list.add(technique);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return list;
    }
    public static List<Integer> selectAllIdTechniqueExistInTechnique(){

        List<Integer> list = null;

        try {
            String selectIdTechnique = "SELECT technique.idTechnique\n" +
                    "FROM technique, employee\n" +
                    "WHERE EXISTS(SELECT * FROM technique, employee WHERE technique.idTechnique = employee.technique_idTechnique)\n" +
                    "AND technique.idTechnique = employee.technique_idTechnique;";

            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectIdTechnique);

            list = new ArrayList<>();

            while (resultSet.next()){

                int idTechnique = resultSet.getInt("idTechnique");

                list.add(idTechnique);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static Map<Integer, String> selectAllNameTechnique(){

        Map<Integer, String> mapAllNameTechnique = null;

        try {
            String selectAllNameTechnique = "SELECT technique.idTechnique, technique.name_tech\n" +
                    "FROM technique\n" +
                    "WHERE NOT EXISTS(SELECT employee.technique_idTechnique\n" +
                    "                 FROM employee\n" +
                    "                 WHERE technique.idTechnique = employee.technique_idTechnique);";

            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllNameTechnique);

            mapAllNameTechnique = new HashMap<>();

            while (resultSet.next()){
                Integer allIdTechnique = resultSet.getInt("technique.idTechnique");
                String allNameTechnique = resultSet.getString("technique.name_tech");

                mapAllNameTechnique.put(allIdTechnique, allNameTechnique);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return mapAllNameTechnique;
    }
    public static int selectIdTechnique() {
        int id = 0;
        try {
            String selectTechnique = "SELECT technique.idTechnique\n" +
                    "FROM technique\n" +
                    "ORDER BY technique.idTechnique ASC;";

            Connection connection = staticJDBC.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectTechnique);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                id = resultSet.getInt("idTechnique");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
    public static ObservableList<Technique> selectStartCostTechnique(String moreOrLessOrEqual, double cost){

        ObservableList<Technique> list = null;

        try {
            String selectStartCostTechnique = "SELECT *\n" +
                    "FROM technique\n" +
                    "WHERE technique.start_cost "+ moreOrLessOrEqual +" "+ cost +";";

            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectStartCostTechnique);

            list = FXCollections.observableArrayList();

            while (resultSet.next()){
                Technique techniqueCost = new Technique();
                techniqueCost.setIdTechnique(resultSet.getInt("idTechnique"));
                techniqueCost.setType(resultSet.getString("type"));
                techniqueCost.setNameTech(resultSet.getString("name_tech"));
                techniqueCost.setInventoryNumbers(resultSet.getInt("inventory_numbers"));
                techniqueCost.setStartCost(resultSet.getFloat("start_cost"));
                techniqueCost.setActualCost(resultSet.getFloat("actual_cost"));
                list.add(techniqueCost);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return list;
    }
    public static ObservableList<Technique> selectActualCostTechnique(String moreOrLessOrEqual, double cost){

        ObservableList<Technique> list = null;

        try {
            String selectStartCostTechnique = "SELECT *\n" +
                    "FROM technique\n" +
                    "WHERE technique.actual_cost "+ moreOrLessOrEqual +" "+ cost +";";

            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectStartCostTechnique);

            list = FXCollections.observableArrayList();

            while (resultSet.next()){
                Technique techniqueCost = new Technique();
                techniqueCost.setIdTechnique(resultSet.getInt("idTechnique"));
                techniqueCost.setType(resultSet.getString("type"));
                techniqueCost.setNameTech(resultSet.getString("name_tech"));
                techniqueCost.setInventoryNumbers(resultSet.getInt("inventory_numbers"));
                techniqueCost.setStartCost(resultSet.getFloat("start_cost"));
                techniqueCost.setActualCost(resultSet.getFloat("actual_cost"));
                list.add(techniqueCost);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return list;
    }
    public static ObservableList<Technique> selectTechniqueRecord(String record, String value){

        ObservableList<Technique> enterpriseList = null;
        String recordForSelectTechnique = null;

        switch (record) {
            case "Номер":
                recordForSelectTechnique = "idTechnique";
                break;
            case "Тип":
                recordForSelectTechnique = "type";
                break;
            case "Назва":
                recordForSelectTechnique = "name_tech";
                break;
            case "Інвентаризаційний номер":
                recordForSelectTechnique = "inventory_numbers";
                break;
            case "Початкова ціна":
                recordForSelectTechnique = "start_cost";
                break;
            case "Актуальна ціна":
                recordForSelectTechnique = "actual_cost";
                break;
        }

        try {
            String selectEmployee = "SELECT idTechnique, \n" +
                    "  type, \n" +
                    "  name_tech, \n" +
                    "  inventory_numbers, \n" +
                    "  start_cost, \n" +
                    "  actual_cost \n" +
                    "FROM technique\n" +
                    "WHERE "+ recordForSelectTechnique +" LIKE '"+ value +"%';";


            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(selectEmployee);
            enterpriseList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Technique technique = new Technique();

                technique.setIdTechnique(resultSet.getInt("idTechnique"));
                technique.setType(resultSet.getString("type"));
                technique.setNameTech(resultSet.getString("name_tech"));
                technique.setInventoryNumbers(resultSet.getInt("inventory_numbers"));
                technique.setStartCost(resultSet.getFloat("start_cost"));
                technique.setActualCost(resultSet.getFloat("actual_cost"));

                enterpriseList.add(technique);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return enterpriseList;
    }

    //Employee
    public static ObservableList<Employee> selectAllEmployee() {

        ObservableList<Employee> employeeList = null;

        try {
            String selectEmployee = "SELECT employee.idEmployee,\n" +
                    "concat(employee.last_name,\" \", employee.name,\" \", employee.middle_name),\n" +
                    "employee.post,\n" +
                    "employee.liability,\n" +
                    "employee.number_phone,\n" +
                    "concat(enterprise.name,\", \", enterprise.city,\", \", enterprise.address), technique.name_tech\n" +
                    "FROM employee, enterprise, technique\n" +
                    "WHERE enterprise.idEnterprise = employee.enterprise_idEnterprise\n" +
                    "AND technique.idTechnique = employee.technique_idTechnique;";


            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(selectEmployee);
            employeeList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setIdEmployee(resultSet.getInt("idEmployee"));
                employee.setAllNameEmployee(resultSet.getString("concat(employee.last_name,\" \", employee.name,\" \", employee.middle_name)"));
                employee.setPost(resultSet.getString("post"));
                if (resultSet.getBoolean("liability")) {
                    employee.setLiability(sub);
                }else {
                    employee.setLiability(unSub);
                }
                employee.setNumberPhone(resultSet.getInt("number_phone"));
                employee.getEnterprise().setNameEnterprise(resultSet.getString("concat(enterprise.name,\", \", enterprise.city,\", \", enterprise.address)"));
                employee.getTechnique().setNameTech(resultSet.getString("technique.name_tech"));

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return employeeList;
    }
    public static ObservableList<Employee> selectAllNameEmployeeSinceIdEnterprise(int idEnterprise){

        ObservableList<Employee> listAllNameEmployeeSinceIdEnterprise = null;

        try {
            String selectAllNameEnterprise = "SELECT employee.idEmployee,\n" +
                    "  concat(employee.last_name, \" \", employee.name, \" \", employee.middle_name)\n" +
                    "FROM employee\n" +
                    "WHERE enterprise_idEnterprise = " + idEnterprise + ";";

            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllNameEnterprise);

            listAllNameEmployeeSinceIdEnterprise = FXCollections.observableArrayList();
            while (resultSet.next()){
                Employee employeeName = new Employee();
                employeeName.setIdEmployee(resultSet.getInt("employee.idEmployee"));
                employeeName.setAllNameEmployee(resultSet.getString("concat(employee.last_name, \" \", employee.name, \" \", employee.middle_name)"));

                listAllNameEmployeeSinceIdEnterprise.add(employeeName);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return listAllNameEmployeeSinceIdEnterprise;
    }
    public static ObservableList<Employee> selectOneEmployee(int idEmployee){

        ObservableList<Employee> employeeObservableList = null;

        try {
            String selectOneEmployeeExecute = "SELECT employee.idEmployee,\n" +
                    "  concat(employee.last_name, \" \", employee.name, \" \", employee.middle_name),\n" +
                    "  technique.name_tech,\n" +
                    "  employee.post,\n" +
                    "  employee.number_phone,\n" +
                    "  employee.liability\n" +
                    "FROM employee, technique\n" +
                    "WHERE technique.idTechnique = employee.technique_idTechnique\n" +
                    "AND employee.idEmployee = "+ idEmployee +";";

            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(selectOneEmployeeExecute);
            employeeObservableList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setIdEmployee(resultSet.getInt("idEmployee"));
                employee.setAllNameEmployee(resultSet.getString("concat(employee.last_name, \" \", employee.name, \" \", employee.middle_name)"));
                employee.getTechnique().setNameTech(resultSet.getString("technique.name_tech"));
                employee.setPost(resultSet.getString("employee.post"));
                employee.setNumberPhone(resultSet.getInt("employee.number_phone"));
                if (resultSet.getBoolean("liability")) {
                    employee.setLiability(sub);
                }else {
                    employee.setLiability(unSub);
                }
                employeeObservableList.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return employeeObservableList;
    }
    public static int selectIdEmployee(){
        int id = 0;
        try {
            String selectEmployee = "SELECT employee.idEmployee\n" +
                    "FROM employee\n" +
                    "ORDER BY employee.idEmployee ASC;";

            Connection connection = staticJDBC.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectEmployee);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                id = resultSet.getInt("employee.idEmployee");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
    public static ObservableList<Employee> selectEmployeeRecord(String record, String value){

        ObservableList<Employee> employeeList = null;

        String recordForSelect = null;

        switch (record){
            case "Номер":
                recordForSelect = "idEmployee";
                break;
            case "Працівник ПІБ":
                recordForSelect = "concat(employee.last_name,\" \", employee.name,\" \", employee.middle_name)";
                break;
            case "Посада":
                recordForSelect = "employee.post";
                break;
            case "Матеріальна відповідальність":
                recordForSelect = "employee.liability";
                break;
            case "Телефонний номер":
                recordForSelect = "employee.number_phone";
                break;
            case "Компанія":
                recordForSelect = "concat(enterprise.name,\", \", enterprise.city,\", \", enterprise.address), technique.name_tech";
                break;
            case "Техніка":
                recordForSelect = "technique.name_tech";
                break;
        }

        if (value.equals("Підписано")){
            value = String.valueOf(1);
        }else if (value.equals("Не підписано")){
            value = String.valueOf(0);
        }

        try {
            String selectEmployee = "SELECT employee.idEmployee,\n" +
                    "concat(employee.last_name,\" \", employee.name,\" \", employee.middle_name),\n" +
                    "employee.post,\n" +
                    "employee.liability,\n" +
                    "employee.number_phone,\n" +
                    "concat(enterprise.name,\", \", enterprise.city,\", \", enterprise.address), " +
                    "technique.name_tech\n" +
                    "FROM employee, enterprise, technique\n" +
                    "WHERE enterprise.idEnterprise = employee.enterprise_idEnterprise\n" +
                    "AND technique.idTechnique = employee.technique_idTechnique " +
                    "AND "+ recordForSelect +" LIKE '"+ value +"%';";

            Connection connection = staticJDBC.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(selectEmployee);
            employeeList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Employee employeeFilter = new Employee();

                employeeFilter.setIdEmployee(resultSet.getInt("idEmployee"));
                employeeFilter.setAllNameEmployee(resultSet.getString("concat(employee.last_name,\" \", employee.name,\" \", employee.middle_name)"));
                employeeFilter.setPost(resultSet.getString("post"));
                if (resultSet.getBoolean("liability")) {
                    employeeFilter.setLiability(sub);
                }else {
                    employeeFilter.setLiability(unSub);
                }
                employeeFilter.setNumberPhone(resultSet.getInt("number_phone"));
                employeeFilter.getEnterprise().setNameEnterprise(resultSet.getString("concat(enterprise.name,\", \", enterprise.city,\", \", enterprise.address)"));
                employeeFilter.getTechnique().setNameTech(resultSet.getString("technique.name_tech"));

                employeeList.add(employeeFilter);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return employeeList;
    }
}