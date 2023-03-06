/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Commentaire;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ZeroS TF
 */
public interface InterfaceCRUDCommentaire {
    
      public void ajouterCommentaire(Commentaire commentaire) throws SQLException;

    public void modifierCommentaire(Commentaire commentaire, int id) throws SQLException;

    public void supprimerCommentaire(int id) throws SQLException;

    public List<Commentaire> afficherCommentaires() throws SQLException;
    
    List<Commentaire> getCommentaires() throws SQLException;
    
     public List<Commentaire> chercherCommentairesParTitre(String contenu) throws SQLException;
    
    
   
    
}
