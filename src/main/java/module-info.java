module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.jfoenix;
    requires java.sql;
    requires org.jetbrains.annotations;
    requires org.jsoup;
    requires google.api.client;
    requires com.google.api.client;
    requires com.google.api.client.json.gson;
    requires google.api.services.youtube.v3.rev222;
    requires language.detector;
    requires com.google.common;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}