package sample.JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.JDBC.staticJDBC.getConnection;

public class UpdateDataInTable {

    public static void updateEnterprise(int idEnterprise, String nameEnterprise, String cityEnterprise, String addressEnterprise, String allNameDirector, String allNameAccountant) throws SQLException {

        Connection dbConnection = null;
        Statement statement = null;

        String[] allNameDirectorEnterprise = allNameDirector.split(" ");
        String[] allNameAccountantEnterprise = allNameAccountant.split(" ");

        String updateTableEnterprise = "UPDATE enterprise\n" +
                "SET name = '" + nameEnterprise + "',\n" +
                "  city = '" + cityEnterprise + "',\n" +
                "  address = '" + addressEnterprise + "',\n" +
                "  \n" +
                "  last_name_director = '" + allNameDirectorEnterprise[0] + "',\n" +
                "  name_director = '" + allNameDirectorEnterprise[1] + "',\n" +
                "  middle_name_director = '" + allNameDirectorEnterprise[2] + "',\n" +
                "  \n" +
                "  last_name_accountant = '" + allNameAccountantEnterprise[0] + "',\n" +
                "  name_accountant = '" + allNameAccountantEnterprise[1] + "',\n" +
                "  middle_name_accountant = '" + allNameAccountantEnterprise[2] + "'\n" +
                "  \n" +
                "WHERE idEnterprise = " + idEnterprise + ";";
        try {

            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            statement.execute(updateTableEnterprise);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public static void updateTechnique(int idTechnique, String type, String name_technique, int inventory_numbers, float start_cost, float actual_cost) throws SQLException{

        Connection Connection = null;
        Statement statement = null;

        String updateTableTechnique = "UPDATE technique\n" +
                "SET technique.type = '"+ type +"',\n" +
                "  technique.name_tech = '"+ name_technique +"',\n" +
                "  technique.inventory_numbers = '"+ inventory_numbers +"',\n" +
                "\n" +
                "  technique.start_cost = '"+ start_cost +"',\n" +
                "  technique.actual_cost = '"+ actual_cost +"'\n" +
                "\n" +
                "WHERE idTechnique = "+ idTechnique +";";
        try {

            Connection = getConnection();
            statement = Connection.createStatement();
            statement.execute(updateTableTechnique);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
    }

    public static void updateEmployee(int idEmployee, String allNameEmployee, String post, boolean liabilitys, int numberPhone, int enterprise, int technique) throws SQLException{

        Connection Connection = null;
        Statement statement = null;

        String[] allName = allNameEmployee.split(" ");

        String updateTableTechnique = "UPDATE employee\n" +
                "SET employee.name = '"+ allName[0] +"',\n" +
                "  employee.middle_name = '"+ allName[1] +"',\n" +
                "  employee.last_name = '"+ allName[2] +"',\n" +
                "  employee.post = '"+ post +"',\n" +
                "  employee.liability = '"+ liabilitys +"',\n" +
                "  employee.number_phone = '"+ numberPhone +"',\n" +
                "  employee.enterprise_idEnterprise = '"+ enterprise +"',\n" +
                "  employee.technique_idTechnique = '"+ technique +"'\n" +
                "WHERE employee.idEmployee = "+ idEmployee +";";
        try {

            Connection = getConnection();
            statement = Connection.createStatement();
            statement.execute(updateTableTechnique);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (Connection != null) {
                Connection.close();
            }
        }
    }
}