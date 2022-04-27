package com.example.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Comment {
    private String comment;
    private String auteur;
    private String url;
    private static int id = 53;
    private String user;

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Comment( String comment, String auteur, String url, String user) throws SQLException {
        setComment(comment);
        setAuteur(auteur);
        setUrl(url);
        setUser(user);
        setStoredId();
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuteur() {
        return this.auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Comment.id = id;
    }

    public static ArrayList<Comment> geCommentsByUser(String user) throws SQLException { // return list of comments(author,comment,url)
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        Connection connection = DBConnector.createConnection();
        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement
//                    .executeQuery(String.format("select author,comment,url from comments where user='%s'", user));
            ResultSet resultSet =DBConnector.executeQuery(String.format("select author,comment,url from comments where user='%s'", user));
            Comment resgistration;

            while (resultSet.next()) {
                resgistration=new Comment( resultSet.getString("comment"),resultSet.getString("author"),resultSet.getString("url"),user);


                commentList.add(resgistration);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;

    }

    public static void saveComments(ArrayList<Comment> commentList) throws SQLException { // the argument list
        // (url,comment,author)
//        Connection connection = DBConnector.createConnection();
        try {
//            Statement statement = connection.createStatement();
            for (int i = 0; i < commentList.size(); i++) {

                commentList.get(i).setComment(commentList.get(i).getComment().replace("'","''"));
                commentList.get(i).setAuteur(commentList.get(i).getAuteur().replace("'","''"));

//                statement.executeUpdate(String.format(
//                        "insert into comments values('%s','%s','%s','%s','%s')",
//                        id, commentList.get(i).getUrl(), commentList.get(i).getComment(), commentList.get(i).getUser(),
//                        commentList.get(i).getAuteur()));
                DBConnector.executeQuery(String.format(
                        "insert into comments values('%s','%s','%s','%s','%s')",
                        id, commentList.get(i).getUrl(), commentList.get(i).getComment(), commentList.get(i).getUser(),
                        commentList.get(i).getAuteur()));
                id += 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void setStoredId() throws SQLException { // set coorect value of id from database
//        Connection connection = DBConnector.createConnection();

        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement
//                    .executeQuery(String.format("select id from comments order by id desc limit 1;"));
            ResultSet resultSet = DBConnector.executeQuery("select idComments from comments order by idComments desc limit 1;");
                setId(resultSet.getInt("idComments") + 1);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void storeComment() throws SQLException {
        DBConnector.updateQuery(String.format("insert into comments values ('%s','%s','%s','%s','%s');",id,this.url,this.comment,this.user,this.auteur));
    }
    public static  ArrayList<Comment> getComments(){
        ArrayList<Comment> commentList = new ArrayList<Comment>();
//        Connection connection = DBConnector.createConnection();
        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement
//                    .executeQuery(String.format("select author,comment,url from comments where user='%s'", user));
            ResultSet resultSet =DBConnector.executeQuery("SELECT * FROM comments;");
            Comment resgistration;

            while (resultSet.next()) {
                resgistration=new Comment(resultSet.getString("comment"),resultSet.getString("author"),resultSet.getString("url"),resultSet.getString("user"));


                commentList.add(resgistration);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return commentList;

    }

    public static void main(String[] args) throws SQLException {
        Comment c = new Comment("sbgsbg","sfbfgb","sqbfgb","qfbfqb");
        c.storeComment();

    }

}
