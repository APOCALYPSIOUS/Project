package com.example.demo;

import java.sql.*;

public class User {

    private String name;
    private String password;
    public User( String name,String password){
        setName(name);
        setPassword(password);
    }

    private void setPassword(String password) {
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    private void setName(String name) {
        this.name=name;
    }





    public String getName() {
        return name;
    }
    public static boolean  checkUserExist(String name, String password) throws SQLException {
        ResultSet result = DBConnector.executeQuery(String.format("select exists(select * from users where username='%s' && password='%s');",name,password));
        if((int) result.getObject(1)==1){
            return true;
        }
        else{
            return false;
        }


    }

    public void addUser() throws SQLException {

            DBConnector.addItem(String.format("INSERT INTO users VALUES ( '%s', '%s');",this.name,this.password));


    }


    public static void main(String[] args) throws SQLException {
        User user = new User("walid","123");
        user.addUser();


    }
}
