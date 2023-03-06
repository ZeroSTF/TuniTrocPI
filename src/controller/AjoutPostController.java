/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Post;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CRUDPost;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AjoutPostController implements Initializable {
    
    
    
    @FXML
    private Label label_titre;

    @FXML
    private Label label_contenu;

    @FXML
    private Label label_id_user;

    @FXML
    private Label label_like;

    @FXML
    private Label label_dislike;

    @FXML
    private TextField text_titre;

     @FXML
    private TextField text_contenu;

    @FXML
    private TextField text_id_user;

    @FXML
    private TextField text_like;

    @FXML
    private TextField text_dislike;

    @FXML
    private Button btn_ajout_post;
    
    
    @FXML
    private Button btn_retour;
    
    @FXML
    private Button commentaire;
    
    
    
    
    
    public byte[] uploadedImage = null;
    public int i;
    public CRUDUser cr7=new CRUDUser();
    public User currentUser;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    
    
    
    
    
    
    @FXML
    private void click_disconnect(javafx.scene.input.MouseEvent event) throws SQLException {
        CRUDUser sa = new CRUDUser();
        sa.logout(currentUser.getEmail());
        LoginUIController loginUIController = new LoginUIController();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/LoginUI.fxml"));

            // set the controller instance
            loader.setController(loginUIController);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setOnCloseRequest(e -> {
                
                try {
                    CRUDUser cr7=new CRUDUser();
                    cr7.logout(currentUser.getEmail()); // Appelle la fonction supp()
                } catch (SQLException ex) {
                    Logger.getLogger(TableUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
    
    @FXML
    void click_commentaire_menu(ActionEvent event) {
        
              
  try {
        // Create a new instance of AjoutPostController
        TableCommentaireController TableCommentaireController = new TableCommentaireController();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableCommentaire.fxml"));
        loader.setController(TableCommentaireController);
        Parent TableCommentaireParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene TableCommentaireScene = new Scene(TableCommentaireParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(TableCommentaireScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
        

    }


    
    
    
    
    
    @FXML
    void click_retour(ActionEvent event) throws IOException {
        
        try {
        // Create a new instance of AjoutPostController
        TablePostController TablePostController = new TablePostController();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TablePost.fxml"));
        loader.setController(TablePostController);
        Parent tablePostParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene tablePostScene = new Scene(tablePostParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(tablePostScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

    
    
    
    
    @FXML
void click_ajout(ActionEvent event) throws SQLException, IOException {

    if (text_titre.getText().isEmpty() || text_contenu.getText().isEmpty() ) {
        // Afficher un message d'erreur si un champ est vide
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Tous les champs sont obligatoires");
        alert.showAndWait();
    } else if (!text_titre.getText().matches("[a-zA-Z]+")) {
        // Afficher un message d'erreur si le titre contient des chiffres
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Le titre ne doit contenir que des lettres");
        alert.showAndWait();
    } else {
        String titre = text_titre.getText();
        String contenu = text_contenu.getText();
        LocalDateTime date = LocalDateTime.now();
        int id_user = Integer.parseInt(text_id_user.getText());
        int like = Integer.parseInt(text_like.getText());
        int dislike = Integer.parseInt(text_dislike.getText());
        Post e= new Post( titre,contenu,date,id_user,like, dislike);
        CRUDPost es= new CRUDPost();
        es.ajouterPost(e);

        // Afficher un message de confirmation
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Post ajouté");
        alert.setHeaderText(null);
        alert.setContentText("Le post a été ajouté avec succès.");
        alert.showAndWait();

        // Retourner à la vue précédente
        click_retour(event);
    }
}

    
  

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
        
        
    }    
    
}
