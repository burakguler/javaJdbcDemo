import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        try{
            connection = helper.getConnection();
            statement =  connection.prepareStatement
                    ("INSERT INTO world.city (Name,CountryCode,District,Population) VALUES ('Bursa','TUR','Bursa',3000000)"); //statement for inserting values to database
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
    public static void selectDemo()throws SQLException{ //function for select operations
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
}
