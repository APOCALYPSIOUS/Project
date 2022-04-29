package com.example.demo;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThreadListResponse;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;

public class YoutubeApiControl {
    private String user;
    private static final String DEVELOPER_KEY = "AIzaSyA1UJJ_Tg-PeAMV5DSsqdklcFihIXQy_mY";
    private static final String APPLICATION_NAME = "//youtube.googleapis.com/youtube/v3/commentThreads?part=id%2Csnippet";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public YoutubeApiControl(String user) {
        setUser(user);

    }

    public void setUser(String user) {
        this.user = user;
    }

    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public ArrayList<Comment> getYoutubeComments(String url)
            throws GeneralSecurityException, IOException, GoogleJsonResponseException, SQLException {

        String videoId = url.split("=")[1];
        YouTube youtubeService = getService();
        // Define and execute the API request
        YouTube.CommentThreads.List request = youtubeService.commentThreads()
                .list("id,snippet");
        CommentThreadListResponse response = request.setKey(DEVELOPER_KEY)
                .setOrder("relevance")
                .setVideoId(videoId)
                .execute();
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        Comment registration;
        for (com.google.api.services.youtube.model.CommentThread videoComment : response.getItems()) {

            CommentSnippet snippet = videoComment.getSnippet().getTopLevelComment().getSnippet();

            //registration = new Comments(snippet.getTextOriginal(), snippet.getAuthorDisplayName(), url, this.user);

            commentList.add(new Comment(snippet.getTextOriginal(), snippet.getAuthorDisplayName(), url, this.user));

        }
        return commentList;

    }
}