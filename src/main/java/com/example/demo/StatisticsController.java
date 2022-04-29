package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class StatisticsController implements Initializable {
    public final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public final static String DATABASE_URL = "jdbc:mysql://localhost:3306/project";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet;
    ResultSetMetaData metaData;
    PreparedStatement pst;
    @FXML
    private Button returntomenubtn;

    @FXML
    private void returntomenu(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Home.fxml");
    }

    @FXML
    private BarChart<?, ?> barchart;

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("AUTOMATIQUE");
        XYChart.Series series2 = new XYChart.Series();

        series2.setName("YOUTUBE");

        XYChart.Series series3 = new XYChart.Series();

        series3.setName("HESPRESS");

        try {
            series1.getData().add(new XYChart.Data("HESPRESS", number( "https://www.hespress" )));
            series1.getData().add(new XYChart.Data("YOUTUBE", number("https://www.youtube.")));
            series1.getData().add(new XYChart.Data("Manuel", number("non")));

            //series3.getData().add(new XYChart.Data("YOUSSEF", number( "HESPRESS")));

            //series1.getData().add(new XYChart.Data("AYOUB", number("ayoub", "AUTO")));
            //series1.getData().add(new XYChart.Data("YOUSSEF", number("youssef", "AUTO")));
//            series2.getData().add(new XYChart.Data("CHAOUKI", number("chaouki", "YOUTUBE")));
//            series2.getData().add(new XYChart.Data("AYOUB", number("ayoub", "YOUTUBE")));
//            series2.getData().add(new XYChart.Data("YOUSSEF", number("youssef", "YOUTUBE")));
//            series3.getData().add(new XYChart.Data("CHAOUKI", number("chaouki", "HESPRESS")));
//            series3.getData().add(new XYChart.Data("AYOUB", number("ayoub", "HESPRESS")));
//            series3.getData().add(new XYChart.Data("YOUSSEF", number("youssef", "HESPRESS")));
            //series1.getData().add(new XYChart.Data("CHAOUKI",number("chaouki","YOUTUBE")));
            //series1.getData().add(new XYChart.Data("CHAOUKI",number("chaouki","HESPRESS")));
            //series1.getData().add(new XYChart.Data("AYOUB",number("ayoub")));
            //series1.getData().add(new XYChart.Data("YOUSSEF",number("youssef")));
            //
            //series2.getData().add(new XYChart.Data("CHAOUKI",number("chaouki")));
			/*
			series2.getData().add(new XYChart.Data("AUTOMATIQUE",number("ayoub","AUTO")));
			series2.getData().add(new XYChart.Data("YOUTUBE",number("ayoub","YOUTUBE")));
			series2.getData().add(new XYChart.Data("AUTOMATIQUE",number("ayoub","HESPRESS")));
			//series2.getData().add(new XYChart.Data("YOUSSEF",number("youssef")));
			//
			//series3.getData().add(new XYChart.Data("CHAOUKI",number("chaouki")));
			//series3.getData().add(new XYChart.Data("AYOUB",number("ayoub")));
			series3.getData().add(new XYChart.Data("AUTOMATIQUE",number("youssef","AUTO")));
			series3.getData().add(new XYChart.Data("AUTOMATIQUE",number("youssef","YOUTUBE")));
			series3.getData().add(new XYChart.Data("AUTOMATIQUE",number("youssef","HESPRESS")));
			*/

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        barchart.getData().addAll(series1);
    }

    public int number( String golut) throws IOException, SQLException, ClassNotFoundException {

        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DATABASE_URL, "root", "");
        statement = connection.createStatement();
        pst = connection.prepareStatement(String.format("SELECT COUNT(User) AS total FROM comments where User='%s' and substr(url,1,20) like '%s';", LogInController.user.getUser(),golut));


        resultSet = pst.executeQuery();
        int num = 0;
        while (resultSet.next()) {
            num = (resultSet.getInt(1));
        }
        return num;
    }
}