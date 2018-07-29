package sample.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDataInTable {

    public void insertDataInTechniqueTable(int idTechnique, String typeTechnique, String nameTechnique, int inventoryNumbersTechnique, float startCostTechnique, float actualCostTechnique) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;

        String insertTableTechniqueExecute = "INSERT INTO technique (idTechnique, type, name_tech, inventory_numbers, start_cost, actual_cost) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try {
            connection = staticJDBC.getConnection();
            statement = connection.prepareStatement(insertTableTechniqueExecute);

            statement.setInt(1, idTechnique);
            statement.setString(2, typeTechnique);
            statement.setString(3, nameTechnique);
            statement.setInt(4, inventoryNumbersTechnique);
            statement.setFloat(5, startCostTechnique);
            statement.setFloat(6, actualCostTechnique);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void insertDataInEnterpriseTable(int idEnterprise, String nameEnterprise, String cityEnterprise, String addressEnterprise, String allNameDirectorEnterprise, String allNameAccountantEnterprise) throws SQLException{

        Connection connection = null;
        PreparedStatement statement = null;

        String insertTableEnterpriseExecute = "INSERT INTO enterprise (idEnterprise, name, city, address, name_director, middle_name_director, last_name_director, name_accountant, middle_name_accountant, last_name_accountant)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        String[] director = allNameDirectorEnterprise.split(" ");
        String[] accountant = allNameAccountantEnterprise.split(" ");

        try {
            connection = staticJDBC.getConnection();
            statement = connection.prepareStatement(insertTableEnterpriseExecute);

            statement.setInt(1, idEnterprise);
            statement.setString(2, nameEnterprise);
            statement.setString(3, cityEnterprise);
            statement.setString(4, addressEnterprise);
            statement.setString(5, director[0]);
            statement.setString(6, director[1]);
            statement.setString(7, director[2]);
            statement.setString(8, accountant[0]);
            statement.setString(9, accountant[1]);
            statement.setString(10, accountant[2]);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void insertDataInEmployeeTable(int idEmployee, String allNameEmployee, String post, boolean liability, int numberPhone, int enterprise, int technique){

        Connection connection;
        PreparedStatement statement;

        String insertTableEmployeeExecute = "INSERT INTO employee (idEmployee, last_name, name, middle_name, post, liability, number_phone, technique_idTechnique, enterprise_idEnterprise)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        String[] allName = allNameEmployee.split(" ");

        try {
            connection = staticJDBC.getConnection();
            statement = connection.prepareStatement(insertTableEmployeeExecute);

            statement.setInt(1, idEmployee);
            statement.setString(2, allName[0]);
            statement.setString(3, allName[1]);
            statement.setString(4, allName[2]);
            statement.setString(5, post);
            statement.setBoolean(6, liability);
            statement.setInt(7, numberPhone);
            statement.setInt(8, technique);
            statement.setInt(9, enterprise);

            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}