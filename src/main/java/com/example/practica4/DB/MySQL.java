package com.example.practica4.DB;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL {
    private  static Connection conn = null;
    private Statement statement;
    private boolean conectado=false;
    private static String hostname   = "localhost";
    private static String dbname = "taqueriadb";
    private static String dbuser = "root";
    private static String dbpass = "123456";


    public static void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+ hostname +":3306/" + dbname, dbuser, dbpass);
            System.out.println("e");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Connection getConnection()
    {
        if(conn == null) conectar();
        return conn;
    }

    public void CERRAR(){
        try {
            if(conn!=null){
                conn.close();
            }
            if (statement!=null){
                statement.close();
            }
            System.out.println("CONEXION CERRADA");
        }catch (SQLException ex){
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
