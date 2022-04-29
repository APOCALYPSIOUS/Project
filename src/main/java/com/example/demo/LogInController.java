package com.example.demo;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LogInController {

    public static String user1;
    public void setUser(String user){
        this.user1 =user;

    }

    @FXML
    private JFXButton LogIn;

    @FXML
    private JFXButton SignIn;

    @FXML
    private TextField password;

    @FXML
    private TextField username;
    @FXML
    private  Label incorrectPasswordUsername;

    public static CommentControl user;

    @FXML
    void OnLogInBtnClicked(ActionEvent event) throws SQLException, IOException {

        Boolean auth =User.checkUserExist(username.getText(),password.getText());
        System.out.println(auth);
        user=new CommentControl(username.getText());
        if(auth == true){
            setUser(username.getText());
            Main m = new Main();
            m.changeScene("Home.fxml");

        }
        else{
            incorrectPasswordUsername.setText("username or password incorrect");

        }




    }




    @FXML
    void OnSignInBtnClicked(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("SignIn.fxml");

    }

    /*public  void accountCreated(){
        incorrectpasswordusername.setText("account created");
    }
    */

}
