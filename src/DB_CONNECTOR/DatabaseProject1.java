package DB_CONNECTOR;

import java.sql.*;

public class DatabaseProject1 {

    String example;

    public static void main(String[] args) {
        DatabaseProject1 project = new DatabaseProject1();
        project.createConnection();


        // OPTIONAL WAY TO DECLARE.
        //    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        //    public static final String DATABASE_URL = "jdbc:mysql://localhost/db_149900";
        //    public static final String USER = "root";
        //    public static final String PASSWORD = "";

    }
    void createConnection(){

        //LOADING OF THE DRIVER
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //ESTABLISHING CONNECTION TO THE DATABASE
            Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost/db_149900","root","");

            // CODE THAT HAS TO BE PUT IN CONSEQUENT CLASSES THA LINK TO THE DATABASE IS BELOW
            //try{
            //  Class.forName("com.mysql.cj.jdbc.Driver");
            //  Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost/db_149900","root","");
            //}
            //catch (ClassNotFoundException | SQLException e) {throw new RuntimeException(e);}

            //CONFIRMING ESTABLISHED CONNECTION
            System.out.println("Database Connection Success");

            // SELECT QUERY FOR EXECUTION - OUTPUTS ALL VALUE IN THE TABLE
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TBL_NAME");

            // CODE WILL KEEP EXECUTING UNTIL THERE IS NO NEXT VALUE
            while (resultSet.next()){
                //ACCESS A COLUMN BY COLUMN NAME
                String name = resultSet.getString("columnName");
                System.out.println(name);
                //OR BY INDEX
                //String name = resultSet.getString(1);
            }
            //statement.close();
            //connection.close();


            // SELECT QUERY FOR EXECUTION - OUTPUTS SPECIFIC VALUE(S) IN THE TABLE
            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM TBL_NAME WHERE columnName like '%"+example+"%'");
            while (resultSet1.next()){
                String name = resultSet1.getString(1);
                System.out.println(name);
            }
            statement.close();
            connection.close();


            // INSERT QUERY FOR EXECUTION - METHOD 1
            String insertOperation = "INSERT INTO TBL_NAME VALUES '"+example+"'";
            statement.execute(insertOperation); // we use .execute() because INSERT is an OPERATION NOT QUERY
            //statement.close();
            //connection.close();

            // INSERT QUERY FOR EXECUTION - METHOD 2 (PREPARED STATEMENT)
            //String name = example.getText();
            PreparedStatement preparedStatement = connection.prepareStatement(" INSERT INTO TBL_NAME VALUES (?)");
            preparedStatement.setString(1,example);
            preparedStatement.execute();
            System.out.println("Insertion Completed.");

            //            int addedRows = preparedStatement.executeUpdate();
            //            if (addedRows > 0) {
            //                user = new User();
            //                user.name = name;
            //                user.password = password;
            //                user.confirm = confirm;
            //            }
            preparedStatement.close();

            //SELECT DATA FROM A SQL TABLE AND PROJECT IT ONTO A JTABLE
            // EXAMPLE - when button clicked.
            // DefaultTableModel tableModel = table.getModel();
            //
            // try   {
            //      Statement statement = connection.createStatement();
            //      ResultSet resultSet = statement.executeQuery(SELECT * FROM TBL_NAME);
            //      while (resultSet.next()){
            //              String name = resultSet.getString("name");
            //              int age = Integer.parseInt(resultSet.getInt("age");
            //              tableModel.addRow(new Object(){name,age});
            //      }
            //      statement.close();
            // catch(Exception e){
            //      e.printStackTrace;
            //}

        }


        catch (ClassNotFoundException | SQLException e ) {
            throw new RuntimeException(e);
        }


    }
}
