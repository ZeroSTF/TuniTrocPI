/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire;
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
public class CRUDCommentaire implements InterfaceCRUDCommentaire{
    Connection TuniTrocDB = DBConnection.getConnection();

    @Override
    public void ajouterCommentaire(Commentaire commentaire) throws SQLException {
        
         PreparedStatement stmt = TuniTrocDB.prepareStatement("INSERT INTO commentaire(contenu, date ,id_post, id_user, likes ,dislikes) VALUES(?, ?, ?, ?, ?, ?)");
        
        stmt.setString(1, commentaire.getContenu());
        stmt.setTimestamp(2, Timestamp.valueOf(commentaire.getDate()));
        stmt.setInt(3, commentaire.getId_post());
        stmt.setInt(4, commentaire.getId_user());
        stmt.setInt(5, commentaire.getLike());
        stmt.setInt(6, commentaire.getDislike());

        stmt.executeUpdate();
    }

    @Override
    public void modifierCommentaire(Commentaire post, int id) throws SQLException {
         PreparedStatement stmt = TuniTrocDB.prepareStatement("UPDATE commentaire SET  contenu=?, date=?,id_post=?, id_user=?,likes=?,dislikes=?    WHERE id=?");
        
        stmt.setString(1, post.getContenu());
        stmt.setTimestamp(2, Timestamp.valueOf(post.getDate()));
        stmt.setInt(3, post.getId_post());       
        stmt.setInt(4, post.getId_user());
        stmt.setInt(5, post.getLike());
        stmt.setInt(6, post.getDislike());
        stmt.setInt(7, id);
        stmt.executeUpdate();
        
        
        
        
        
        
    }

    @Override
    public void supprimerCommentaire(int id) throws SQLException {
         PreparedStatement stmt = TuniTrocDB.prepareStatement("DELETE FROM commentaire WHERE id=?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    @Override
    public List<Commentaire> afficherCommentaires() throws SQLException {
        List<Commentaire> commentaires = new ArrayList<>();
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM commentaire");
        while (rs.next()) {
            int id = rs.getInt("id");
            String contenu = rs.getString("contenu");
            LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
         
                int id_post = rs.getInt("id_post");
                   int id_user = rs.getInt("id_user");

             int like = rs.getInt("likes");
              int dislike = rs.getInt("dislikes");
            Commentaire commentaire = new Commentaire(id,contenu, date,id_post, id_user,like,dislike);
            commentaires.add(commentaire);
        }
        System.out.println(commentaires);
        return commentaires;
        
        
        
    }

    @Override
    public List<Commentaire> getCommentaires() throws SQLException {      
    List<Commentaire> commentaires = new ArrayList<>();
    Statement stmt = TuniTrocDB.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM commentaire");
    while (rs.next()) {
        int id = rs.getInt("id");
      
        String contenu = rs.getString("contenu");
        LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
        int id_post = rs.getInt("id_post");
        int id_user = rs.getInt("id_user");
        int like = rs.getInt("likes");
        int dislike = rs.getInt("dislikes");
       Commentaire commentaire = new Commentaire(id,  contenu, date, id_post,id_user, like, dislike);
        commentaires.add(commentaire);
    }
    return commentaires;

    }
    
    /**
     *
     * @param contenu
     * @return
     * @throws SQLException
     */
    @Override
    public List<Commentaire> chercherCommentairesParTitre(String contenu) throws SQLException {
    List<Commentaire> commentaires = new ArrayList<>();
    PreparedStatement stmt = TuniTrocDB.prepareStatement("SELECT * FROM commentaire WHERE contenu LIKE ?");
    stmt.setString(1, "%" + contenu + "%");
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
        int id = rs.getInt("id");
        String contenuCommentaire = rs.getString("contenu");
        LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
         int id_post = rs.getInt("id_post");

        int id_user = rs.getInt("id_user");
        int likes = rs.getInt("likes");
        int dislikes = rs.getInt("dislikes");
        Commentaire commentaire = new Commentaire(id,  contenuCommentaire, date, id_post,id_user,likes, dislikes);
        commentaires.add(commentaire);
    }
    return commentaires;
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
