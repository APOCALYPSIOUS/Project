package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.demo.Comment;
import com.example.demo.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.ImageView;

public class HomeController implements Initializable {
    public final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public final static String DATABASE_URL = "jdbc:mysql://localhost:3306/database";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet;
    ResultSetMetaData metaData;
    PreparedStatement pst;

    @FXML
    private Button fetchdatabtn;
    @FXML
    private Button statisticsbtn;
    @FXML
    private Button transliteratebtn;
    @FXML
    private Button transliteration_tablebtn;
    @FXML
    private Label username_home;
    @FXML
    private ImageView arrowimg2;
    @FXML
    private ImageView arrowimg1;
    @FXML
    private Button logoutbtn;
    @FXML
    private TableView<Comment> table_1;
    @FXML
    private TableColumn<Comment, Integer> id;
    @FXML
    private TableColumn<Comment, String> user;
    @FXML
    private TableColumn<Comment, String> author;
    @FXML
    private TableColumn<Comment, String> comment;
    @FXML
    private TableColumn<Comment, String> url;
    @FXML
    private Button transliteratedirectbtn;
    @FXML
    private TextArea texttotransliteratearea;
    @FXML
    private TextArea transliteratedtextarea;
    @FXML
    public void logout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("LogIn.fxml");

    }
    @FXML
    public void OnFetchBtnClicked(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("transliterate.fxml");

    }
    @FXML
    public void showstatisticspage(ActionEvent event) throws IOException {
        Main m= new Main();
        m.changeScene("Statistics.fxml");
    }
    @FXML
    public void transliteratedirect(ActionEvent event) throws IOException, SQLException {// a changer le nom
        Mapper trans = new Mapper();

        transliteratedtextarea.setText(trans.translaterate(gettext().toString()));
        Comment c = new Comment(gettext().toString(),"non","non",LogInController.user1);
        c.storeComment();
    }
    @FXML
    public void showtransliteratemenu(ActionEvent event) throws IOException{
        table_1.setVisible(false);
        //transliteration_tablebtn.setVisible(false);
        transliteratedirectbtn.setVisible(true);
        texttotransliteratearea.setVisible(true);
        transliteratedtextarea.setVisible(true);
        arrowimg2.setVisible(true);
        arrowimg1.setVisible(true);
    }
    int counter = 0;

    @FXML
    public void showtable(ActionEvent event) throws IOException, SQLException {
        table_1.getItems().clear();
        table_1.setVisible(true);
        transliteratedirectbtn.setVisible(false);
        texttotransliteratearea.setVisible(false);
        transliteratedtextarea.setVisible(false);
        arrowimg2.setVisible(false);
        arrowimg1.setVisible(false);

        try {

//            Class.forName(JDBC_DRIVER);
//            connection = DriverManager.getConnection(DATABASE_URL, "root", "samsung11");
//            statement = connection.createStatement();
//            pst = connection.prepareStatement("SELECT * FROM comments");
//            resultSet = pst.executeQuery();
            List<Comment> comments  = Comment.getComments();
            int i=0;

//            while (resultSet.next()) {
//                data.add(new Comment(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
//                        resultSet.getString(4), resultSet.getString(5)));
//            }
            while (i<comments.size()) {
                data.add(comments.get(i));
                i++;
            }
//            connection.close();
            //System.out.println(resultSet.getString(5));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        id.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("id"));
        user.setCellValueFactory(new PropertyValueFactory<Comment, String>("user"));
        author.setCellValueFactory(new PropertyValueFactory<Comment, String>("auteur"));
        comment.setCellValueFactory(new PropertyValueFactory<Comment, String>("comment"));
        url.setCellValueFactory(new PropertyValueFactory<Comment ,String>("url"));

        table_1.setItems(data);


    }

    public ObservableList<Comment> data = FXCollections.observableArrayList();



    public void displayusername() {
        username_home.setText(LogInController.user1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayusername();
    }
    public String gettext() {
        String texttranscripted=texttotransliteratearea.getText().toLowerCase().toString();
        return texttranscripted;
    }
}