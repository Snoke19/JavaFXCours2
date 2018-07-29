package sample.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDataInTable {

    public void deleteDataInTechniqueTable(int idTech) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;

        String deleteDataInTechniqueExecute = "DELETE FROM technique WHERE idTechnique = ?;";

        try {
            connection = staticJDBC.getConnection();
            statement = connection.prepareStatement(deleteDataInTechniqueExecute);

            statement.setInt(1, idTech);
            statement.executeUpdate();

            System.out.println("Record is deleted!" + idTech);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteDataInEnterpriseTable(int idEnterprise) throws SQLException{

        Connection connection = null;
        PreparedStatement statement = null;

        String deleteDataInEnterpriseExecute = "DELETE FROM enterprise WHERE idEnterprise = ?;";

        try {
            connection = staticJDBC.getConnection();
            statement = connection.prepareStatement(deleteDataInEnterpriseExecute);

            statement.setInt(1, idEnterprise);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteDataInEmployeeTable(int idEmployee) throws SQLException{

        Connection connection;
        PreparedStatement statement;

        String deleteDataInEmployeeExecute = "DELETE FROM employee WHERE employee.idEmployee = ?;";

        try {
            connection = staticJDBC.getConnection();
            statement = connection.prepareStatement(deleteDataInEmployeeExecute);

            statement.setInt(1, idEmployee);
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}