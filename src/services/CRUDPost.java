/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

/**
 *
 * @author ZeroS TF
 */
public class CRUDPost implements InterfaceCRUDPost{
    Connection TuniTrocDB = DBConnection.getConnection();
    
    public void ajouterPost(Post post) throws SQLException {
     PreparedStatement stmt = TuniTrocDB.prepareStatement("INSERT INTO post(titre ,contenu, date, id_user, likes ,dislikes) VALUES(?, ?, ?, ?, ?, ?)");
        stmt.setString(1, post.getTitre());
        stmt.setString(2, post.getContenu());
        stmt.setTimestamp(3, Timestamp.valueOf(post.getDate()));
        stmt.setInt(4, post.getId_user());
        stmt.setInt(5, post.getLike());
        stmt.setInt(6, post.getDislike());
        stmt.executeUpdate();
    }
    
    

        public   void modifierPost(Post post, int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("UPDATE post SET titre=?, contenu=?, date=?, id_user=?,Likes=?,dislikes=?    WHERE id=?");
        stmt.setString(1, post.getTitre());
        stmt.setString(2, post.getContenu());
        stmt.setTimestamp(3, Timestamp.valueOf(post.getDate()));
        stmt.setInt(4, post.getId_user());
        stmt.setInt(5, post.getLike());
        stmt.setInt(6, post.getDislike());

        stmt.setInt(7, id);
        stmt.executeUpdate();
    }

   
    public void supprimerPost(int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("DELETE FROM post WHERE id=?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

 
    public List<Post> afficherPosts() throws SQLException {
        List<Post> posts = new ArrayList<>();
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM post");
        while (rs.next()) {
            int id = rs.getInt("id");
            String titre = rs.getString("titre");
            String contenu = rs.getString("contenu");
            LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
            int id_user = rs.getInt("id_user");
             int likes = rs.getInt("likes");
              int dislikes = rs.getInt("dislikes");
            Post post = new Post(id, titre, contenu, date, id_user,likes  ,dislikes);
            posts.add(post);
        }
        System.out.println(posts);
        return posts;
    }

    
    public List<Post> getPosts() throws SQLException {      
    List<Post> posts = new ArrayList<>();
    Statement stmt = TuniTrocDB.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM post");
    while (rs.next()) {
        int id = rs.getInt("id");
        String titre = rs.getString("titre");
        String contenu = rs.getString("contenu");
        LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
        int id_user = rs.getInt("id_user");
        int likes = rs.getInt("likes");
        int dislikes = rs.getInt("dislikes");
        Post post = new Post(id, titre, contenu, date, id_user, likes, dislikes);
        posts.add(post);
    }
    return posts;

    }
    
    
    
    
public List<Post> chercherPostsParTitre(String titre) throws SQLException {
    List<Post> posts = new ArrayList<>();
    PreparedStatement stmt = TuniTrocDB.prepareStatement("SELECT * FROM post WHERE titre LIKE ?");
    stmt.setString(1, "%" + titre + "%");
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
        int id = rs.getInt("id");
        String titrePost = rs.getString("titre");
        String contenu = rs.getString("contenu");
        LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
        int id_user = rs.getInt("id_user");
        int likes = rs.getInt("Likes");
        int dislikes = rs.getInt("dislikes");
        Post post = new Post(id, titrePost, contenu, date, id_user, likes, dislikes);
        posts.add(post);
    }
    return posts;
}
    
    
    
    
    
    
    
    
    
    }
    

