package com.example.demo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CommentControl {
    String user;

    public CommentControl(String user) {
        setUser(user);

    }

    public List<String> getUserComment(String user) {
        return null;

    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<Comment> getExpressComment(String url) { // store hesspress comment in
        // list(url,comment,author)
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        try {
            Document doc = Jsoup.connect(url)
                    .get();

            Elements comments = doc.select("div.comment-text");
            Elements authors = doc.select("div.comment-author.vcard");

            int commentCount = 1;
            for (Element comment : comments) {
                commentCount++;
            }

            String[] commentsList = new String[commentCount];// commentaires
            int i = 0;
            for (Element comment : comments) {
                commentsList[i] = comment.text();
                i++;
            }

            String[] authorsList = new String[commentCount];// auteur
            int c = 0;
            for (Element author : authors) {
                authorsList[c] = author.text();
                c++;
            }
            Comment registration;
            int k = 0;
            while (k < commentCount - 1) {

                registration = new Comment(commentsList[k], authorsList[k], url, this.user);

                k++;
                commentList.add(registration);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentList;

    }

    public ArrayList<Comment> getYoutubeComment(String url)
            throws GoogleJsonResponseException, GeneralSecurityException, IOException, SQLException {
        YoutubeApiControl x = new YoutubeApiControl(this.user);
        return x.getYoutubeComments(url);
    }

    public char urlNature(String field) {
        if (field.charAt(0) == 'H') {
            return 'H';

        } else {
            return 'Y';

        }

    }


    //public static void main(String[] args) throws GoogleJsonResponseException, GeneralSecurityException, IOException {
    //Comment.setStoredId();
    //commentControl x = new commentControl("ysf");
    // System.out.println(Comment.geCommentByUser(x.user).get(3).getAuteur());
    // System.out.println(x.getExpressComment("https://www.hespress.com/%d8%b7%d8%a8%d9%82-%d9%81%d9%8a-%d8%a7%d9%84%d8%b2%d9%86%d9%82%d8%a9-%d8%a7%d9%84%d9%85%d8%af%d9%81%d9%88%d9%86%d8%a9-974269.html").get(4).getAuteur());
    //System.out.println(x.getYoutubeComment("https://www.youtube.com/watch?v=_r0c17bfTC8").get(0).getComment());
    /*
     * System.out.println(Comment.geCommentByUser(x.user).get(0)[0]);
     * ArrayList<String[]> testlist =new ArrayList<String[]>(0);
     * String test[]={"6","la fista","ultra","ben"};
     * String testo[]={"7","la ich","lieb","zek"};
     *
     * testlist.add(test);
     * testlist.add(testo);
     *
     *
     * Comment.saveComment("ysf", testlist);
     */
    // Comment.saveComment(
    // x.getExpressComment("https://www.hespress.com/%d8%b7%d8%a8%d9%82-%d9%81%d9%8a-%d8%a7%d9%84%d8%b2%d9%86%d9%82%d8%a9-%d8%a7%d9%84%d9%85%d8%af%d9%81%d9%88%d9%86%d8%a9-974269.html"));

    // System.out.println(getYoutubeComment("https://www.youtube.com/watch?v=_r0c17bfTC8").get(0)[1]);
    //Comment.saveComment(x.getYoutubeComment("https://www.youtube.com/watch?v=_r0c17bfTC8"));
    //String url="https://www.hespress.com/%d8%b7%d8%a8%d9%82-%d9%81%d9%8a-%d8%a7%d9%84%d8%b2%d9%86%d9%82%d8%a9-%d8%a7%d9%84%d9%85%d8%af%d9%81%d9%88%d9%86%d8%a9-974269.html";
    // System.out.println(url.split("p")[0]);
    // }

}