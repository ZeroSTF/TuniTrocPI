/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Commentaire;
import entities.Post;
import entities.User;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.CRUDCommentaire;
import services.CRUDPost;
import services.CRUDUser;
import tunitrockhayri.Gestionproduit1Controller;
import tunitrockhayri.PanierinterfaceController;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class TableCommentaireController implements Initializable {
    
    @FXML
    private TableView<Commentaire> table_commentaires;

    @FXML
    private TableColumn<Commentaire, String> id_commentaire;

    @FXML
    private TableColumn<Commentaire, String> contenu_commentaire;

    @FXML
    private TableColumn<Commentaire, String> date_commentaire;

    @FXML
    private TableColumn<Commentaire, String> id_post_commentaire;

    @FXML
    private TableColumn<Commentaire, String> id_user;

    @FXML
    private TableColumn<Commentaire, String> likes_commentaire;

    @FXML
    private TableColumn<Commentaire, String> dislikes_commentaire;

    @FXML
    private TextField recherche_commentaire;

    @FXML
    private Button btn_ajout_commentaire;

    @FXML
    private Button btn_supprimer_commentaire;

    @FXML
    private Button btn_modifier_commentaire;
    
   
    @FXML
    private MenuItem btn_tri_commentaire;
    
    
    @FXML
    private Button post;
    
    
    @FXML
    private MenuItem btn_pdf_commentaire;
    
    @FXML
    private Button btn_user;
    
    
     @FXML
    private Button btn_evenment;
     
     
    @FXML
    private Button btn_produit;
    
    
    @FXML
    private Button btn_panier;
    
    @FXML
    private Button btn_echange;
    
    @FXML
    private Button btn_transporteur;
    
    
    @FXML
    private Button btn_reclamation;
    
    @FXML
    private Button btn_fidelite;
    
       
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
    void click_fidelite(ActionEvent event) {
        
          try {
        // Create a new instance of AjoutPostController
        TableFideliteController   TableFideliteController= new   TableFideliteController();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableFidelite.fxml"));
        loader.setController( TableFideliteController);
        Parent TableFideliteParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene TableFideliteScene = new Scene(TableFideliteParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(TableFideliteScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
        
        
        
        

    }
    
    
    
    
    
    @FXML
    void click_reclamation(ActionEvent event) {
         try {
        // Create a new instance of AjoutPostController
        TableReclamationController   TableReclamationController= new   TableReclamationController();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableReclamation.fxml"));
        loader.setController( TableReclamationController);
        Parent TableReclamationParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene TableReclamationScene = new Scene(TableReclamationParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(TableReclamationScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
        
        
        

    }
    
    
    
    
    
    
    
    
    
    @FXML
    void click_transporteur(ActionEvent event) {
         try {
        // Create a new instance of AjoutPostController
        TableTransporteurController   TableTransporteurController= new   TableTransporteurController();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableTransporteur.fxml"));
        loader.setController( TableTransporteurController);
        Parent TableTransporteurParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene TableTransporteurScene = new Scene(TableTransporteurParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(TableTransporteurScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
        
        
        

    }
    
    
    
    
    
    
    
    
    
    @FXML
    void click_echange(ActionEvent event) {
        
                                     
  try {
        // Create a new instance of AjoutPostController
        TableEchangeController   TableEchangeController= new   TableEchangeController();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableEchange.fxml"));
        loader.setController( TableEchangeController);
        Parent TableEchangeParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene TableEchangeScene = new Scene(TableEchangeParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(TableEchangeScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
        

    }
    
    
    
    
    @FXML
    void click_panier(ActionEvent event) {
        
                               
  try {
        // Create a new instance of AjoutPostController
        PanierinterfaceController    PanierinterfaceController= new   PanierinterfaceController();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Panierinterface.fxml"));
        loader.setController(  PanierinterfaceController);
        Parent TablePanierParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene TablePanierScene = new Scene(TablePanierParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(TablePanierScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
        

    }
    
    
    
     
     
     
     
     
    @FXML
    void click_produit(ActionEvent event) {
                          
  try {
        // Create a new instance of AjoutPostController
        Gestionproduit1Controller Gestionproduit1Controller = new  Gestionproduit1Controller();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Produitinterface.fxml"));
        loader.setController( Gestionproduit1Controller);
        Parent TableProduitParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene TableProduitScene = new Scene(TableProduitParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(TableProduitScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
        
        

    }
    
    
    
    
    
    
    
    
    @FXML
    void click_event(ActionEvent event) {
                         
  try {
        // Create a new instance of AjoutPostController
        TableEventController TableEventController = new TableEventController();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableEvent.fxml"));
        loader.setController(TableEventController);
        Parent TableEventParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene TableEventScene = new Scene(TableEventParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(TableEventScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
        

    }
    
    
    
    
    @FXML
    void click_user(ActionEvent event) {
                   
  try {
        // Create a new instance of AjoutPostController
        TableUserController TableUserController = new TableUserController();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableUser.fxml"));
        loader.setController(TableUserController);
        Parent TableUserParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene TableUserScene = new Scene(TableUserParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(TableUserScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }  

    }

    
     
    
    
    private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("..\\ListCommentaires.pdf"));
        d.open();
        
        PdfPTable pTable = new PdfPTable(2);
       

     
        
        table_commentaires.getItems().forEach((t) -> {
             pTable.addCell(String.valueOf(t.getId()));
       pTable.addCell(String.valueOf(t.getContenu()));
              pTable.addCell(String.valueOf(t.getDate()));
                     pTable.addCell(String.valueOf(t.getLike()));
                            pTable.addCell(String.valueOf(t.getDislike()));



    


       
        });
        
                        d.add(pTable);

        d.close();
        Desktop.getDesktop().open(new File("..\\ListCommentaires.pdf"));

    } 
    
    
    
    
    
    
    
    
    
    @FXML
    void click_pdf_commentaire(ActionEvent event) throws SQLException, IOException, FileNotFoundException, DocumentException {
        
        if (event.getSource() == btn_pdf_commentaire) {
            
             printPDF();
            
   
        }
    
        
        
  
    }
    
    
    
    
    
    
     @FXML
    void click_tri_commentaire(ActionEvent event) {
        
          TableColumn<Commentaire, String> date_commentaire = (TableColumn<Commentaire, String>) table_commentaires.getColumns().get(3); // Récupérer la colonne de date
    date_commentaire.setSortType(TableColumn.SortType.DESCENDING); // Définir l'ordre de tri
    table_commentaires.getSortOrder().clear(); // Effacer tous les ordres de tri existants
    table_commentaires.getSortOrder().add(date_commentaire); // Ajouter la colonne de date à l'ordre de tri
    table_commentaires.sort(); // Trier les données

    }
    
    
    
     
    
    @FXML
    void click_post_menu(ActionEvent event) {
             
  try {
        // Create a new instance of AjoutPostController
        TablePostController TablePostController = new TablePostController();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TablePost.fxml"));
        loader.setController(TablePostController);
        Parent TablePostParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene TablePostScene = new Scene(TablePostParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(TablePostScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
    }
    
    
    
    
     
    
    @FXML
private void onSearch(ActionEvent event) {
    String contenu = recherche_commentaire.getText();
    try {
         
    CRUDCommentaire crudCommentaire = new CRUDCommentaire();
    List<Commentaire> commentaires = crudCommentaire.chercherCommentairesParTitre(contenu);
    displayCommentaires(commentaires);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    
private void displayCommentaires(List<Commentaire> commentaires) {
    ObservableList<Commentaire> commentaireList = FXCollections.observableArrayList(commentaires);
    table_commentaires.setItems(commentaireList);
}
    
    
    
    
    
    
    
    
    private int selectedCommentaireId = -1; // initialisation de la variable pour stocker l'id du post sélectionné

    // méthode pour récupérer l'id du post sélectionné
    @FXML
    void selectPost(MouseEvent event) {
        Commentaire commentaire  = table_commentaires.getSelectionModel().getSelectedItem();
        if (commentaire != null) {
            selectedCommentaireId = commentaire.getId();
        }
    }
    
    
    
    

    @FXML
    void click_ajout_commentaire(ActionEvent event) {
        
        
  try {
        // Create a new instance of AjoutPostController
        AjoutCommentaireController ajoutCommentaireController = new AjoutCommentaireController();

        // Load the AjoutPost.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutCommentaire.fxml"));
        loader.setController(ajoutCommentaireController);
        Parent ajoutcommentaireParent = loader.load();

        // Set the AjoutPost.fxml view as the current view
        Scene ajoutcommentaireScene = new Scene(ajoutcommentaireParent);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(ajoutcommentaireScene);
        currentStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }  
    }
    
    
    
    
    @FXML
    void click_modif_comentaire(ActionEvent event) throws IOException, SQLException {           
    if (selectedCommentaireId != -1) { // vérification si un post est sélectionné
            // passer à l'interface ModifPost
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifCommentaire.fxml"));
            ModifCommentaireController modifCommentaireController = new ModifCommentaireController(selectedCommentaireId);
            loader.setController(modifCommentaireController);
            Parent modifCommentaireParent = loader.load();
            Scene modifPostScene = new Scene(modifCommentaireParent);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(modifPostScene);
            currentStage.show();
        } else {
            // afficher un message d'erreur si aucun post n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un post pour le modifier");
            alert.showAndWait();
        }
        
    }
        
    @FXML
   public  void updateTableCommentaires() throws SQLException {
    // supprimer les données existantes du tableau
    table_commentaires.getItems().clear();
    
    // récupérer les données de la base de données
    CRUDCommentaire crudCommentaire = new CRUDCommentaire();
    List<Commentaire> commentaires = crudCommentaire.getCommentaires();
    
    // ajouter les données au tableau
    ObservableList<Commentaire> observableList = FXCollections.observableArrayList(commentaires);
    table_commentaires.setItems(observableList);
}
        
        
        
        

    

    @FXML
    void click_supprimer(ActionEvent event) throws SQLException {
          if (selectedCommentaireId != -1) { // Vérifier si un post est sélectionné
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce commentaire ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            CRUDCommentaire crudCommentaire = new CRUDCommentaire();
            crudCommentaire.supprimerCommentaire(selectedCommentaireId);
          updateTableCommentaires();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un commentaire à supprimer.");
        alert.showAndWait();
    }
        
        

    }

        
        
        
        
        

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
           
        table_commentaires.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1) {
            // Récupérer le post sélectionné
           Commentaire commentaire = table_commentaires.getSelectionModel().getSelectedItem();
            
            if (commentaire != null) {
                // Stocker l'ID du post sélectionné
                selectedCommentaireId = commentaire.getId();
            }
        }
    });
        
        
        
           
         
    CRUDCommentaire sa = new CRUDCommentaire();
    
    List<Commentaire> postListFromDatabase=null;
        try {
            postListFromDatabase = sa.afficherCommentaires();
        } catch (SQLException ex) {
            Logger.getLogger(controller.TablePostController.class.getName()).log(Level.SEVERE, null, ex);
        }
ObservableList<Commentaire> commentaireList = FXCollections.observableArrayList();
commentaireList.addAll(postListFromDatabase);

id_commentaire.setCellValueFactory(new PropertyValueFactory<>("id"));
contenu_commentaire.setCellValueFactory(new PropertyValueFactory<>("contenu"));
date_commentaire.setCellValueFactory(new PropertyValueFactory<>("date"));
id_post_commentaire.setCellValueFactory(new PropertyValueFactory<>("id_post"));
id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
likes_commentaire.setCellValueFactory(new PropertyValueFactory<>("Likes"));
dislikes_commentaire.setCellValueFactory(new PropertyValueFactory<>("dislikes"));


table_commentaires.setItems(commentaireList);
    }
        
        
        
        
        
        
        
        
        
        
        
        // TODO
    }    
    

