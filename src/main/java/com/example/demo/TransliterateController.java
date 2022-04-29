package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransliterateController implements Initializable{

    @FXML
    private TableColumn<Comment, String> author;

    @FXML
    private TableColumn<Comment, String> comment;

    @FXML
    private TextArea commentText;

    @FXML
    private TableView<Comment> resultTable;

    @FXML
    private TableColumn<Comment, String> transliteration;

    @FXML
    private TextField urlText;

    @FXML
    private Button back;

    @FXML
    void OnBackButtonClicked(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("Home.fxml");
    }


    @FXML
    void OnButtonClickedT(ActionEvent event) throws SQLException {
        String url=urlText.getText();
        Comment.setStoredId();

        if(!commentText.getText().equals(null) && urlText.getText().equals("")){
            ArrayList<Comment> commentList=new ArrayList<Comment>();
            Comment x=new Comment(commentText.getText(), "---", "---", LogInController.user.getUser());
            commentList.add(x);
            Comment.saveComments(commentList);
            ArrayList<Comment> foru=new ArrayList<Comment>();
            foru=commentList;
            author.setCellValueFactory(new PropertyValueFactory<Comment,String>("auteur"));
            comment.setCellValueFactory(new PropertyValueFactory<Comment,String>("comment"));
            transliteration.setCellValueFactory(new PropertyValueFactory<Comment,String>("translateration"));
            ObservableList<Comment> oList;
            oList = FXCollections.observableArrayList(foru);
            resultTable.setItems(oList);




        }
        else{
            if(url.split("c")[0].equals("https://www.hespress.")){
                Comment.saveComments(LogInController.user.getExpressComment(url));
                ArrayList<Comment> foru=new ArrayList<Comment>();
                foru=LogInController.user.getExpressComment(urlText.getText());

                author.setCellValueFactory(new PropertyValueFactory<Comment,String>("auteur"));
                comment.setCellValueFactory(new PropertyValueFactory<Comment,String>("comment"));
                transliteration.setCellValueFactory(new PropertyValueFactory<Comment,String>("translateration"));
                ObservableList<Comment> oList;
                oList = FXCollections.observableArrayList(foru);
                resultTable.setItems(oList);

            }
            else{
                try {
                    Comment.saveComments(LogInController.user.getYoutubeComment(url));
                    ArrayList<Comment> foru=new ArrayList<Comment>();
                    try {
                        foru=LogInController.user.getYoutubeComment(urlText.getText());
                    } catch (GoogleJsonResponseException e) {
                        e.printStackTrace();
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    author.setCellValueFactory(new PropertyValueFactory<Comment,String>("auteur"));
                    comment.setCellValueFactory(new PropertyValueFactory<Comment,String>("comment"));
                    transliteration.setCellValueFactory(new PropertyValueFactory<Comment,String>("translateration"));
                    ObservableList<Comment> oList;
                    oList = FXCollections.observableArrayList(foru);
                    resultTable.setItems(oList);
                } catch (GoogleJsonResponseException e) {
                    e.printStackTrace();
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }



    @Override
    public void initialize(URL urll,ResourceBundle ressourcebundle){
        /*
            ArrayList<Comment> foru=new ArrayList<Comment>();
            try {
                foru=LogInController.user.getYoutubeComment(urlText.getText());
            } catch (GoogleJsonResponseException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            author.setCellValueFactory(new PropertyValueFactory<Comment,String>("auteur"));
            comment.setCellValueFactory(new PropertyValueFactory<Comment,String>("comment"));
            ObservableList<Comment> oList;
            oList = FXCollections.observableArrayList(foru);
            resultTable.setItems(oList);
*/

    }
}