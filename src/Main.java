import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        try{
            connection = helper.getConnection();
            String sql= "update city set Population=162000, District='Bursayeni' where Id=4083";//statement for updating value of row in database
            statement = connection.prepareStatement(sql);
            int result = statement.executeUpdate(); //result shows how many columns are affected
            System.out.println("Kayıt Güncellendi!");
        }catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
        finally{
            statement.close();
            connection.close();
        }
    }
    public static void selectData()throws SQLException{ //function for select operations
            Connection connection = null;
            DbHelper helper = new DbHelper();
            Statement statement = null;
            ResultSet resultSet;
            try{
                connection = helper.getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("select Code,Name,Continent,Region from country"); //results from the executed query
                ArrayList<Country> countries = new ArrayList<Country>(); // generic arraylist to add results
                while ((resultSet.next())){
                    countries.add(new Country(
                            resultSet.getString("Code"),
                            resultSet.getString("Name"),
                            resultSet.getString("Continent"),
                            resultSet.getString("Region")));
                }
                System.out.println(countries.size());
            }catch (SQLException exception) {
                helper.showErrorMessage(exception);
            }
            finally{
                connection.close();
            }
    }
    public static void insertData()throws SQLException{
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        try{
            connection = helper.getConnection();
            String sql= "insert into city (Name,CountryCode,District,Population) values(?,?,?,?)";
            statement =  connection.prepareStatement(sql);
            statement.setString(1,"Bursa2");
            statement.setString(2,"TUR");
            statement.setString(3,"Bursa");
            statement.setInt(4, 160000); //statements for inserting values to database
            int result = statement.executeUpdate(); //result shows how many columns are affected
            System.out.println("Kayıt Eklendi!");
        }catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }
        finally{
            statement.close();
            connection.close();
        }
    }
}
