package com.Benjamin;
//
//public class spadesDB {
//
//
//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
//    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/spades";     //Connection string – where's the database?
//    static final String USER = "user";   //TODO replace with your username
//    static final String PASSWORD = System.getenv("kittens");   //TODO remember to set the environment variable
//
//
//
//    public static void main(String[] args) {
//
//        try {
//            Class.forName(JDBC_DRIVER);
//        } catch (ClassNotFoundException cnfe) {
//            System.out.println("Can't instantiate driver class; check you have drivers and classpath configured correctly?");
//            cnfe.printStackTrace();
//            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
//        }
//
//        https://github.com/minneapolis-edu/dogsql
//
//        //Try with resources to open the connection and create a statement. Make sure your language level is 1.7+
//        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
//             Statement statement = conn.createStatement()) {
//            //You should have already created a database via terminal/command prompt OR MySQL Workbench
//
//            //Create a table in the database, if it does not exist already
//            String createTableSQL = "CREATE TABLE IF NOT EXISTS dogs (Name varchar(30), Age int)";
//            statement.executeUpdate(createTableSQL);
//            System.out.println("Created Dogs table");
//
//            //Add some data
//            String addDataSQL = "INSERT INTO dogs VALUES ('Poppy', 3)";
//            statement.executeUpdate(addDataSQL);
//
//            addDataSQL = "INSERT INTO dogs VALUES ('Zelda', 5)";
//            statement.executeUpdate(addDataSQL);
//            System.out.println("Added two rows of data");
//
//            statement.close();
//            conn.close();
//
//        } catch (SQLException se) {
//            se.printStackTrace();
//        }
//
//        System.out.println("End of program");
//    }
//}