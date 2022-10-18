import controllers.IDS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;


public class LoginController
{
    private Stage stage;
    private Scene scene;
    private Parent root;
    // Hash map of the IDS:
    HashMap<String,String> validIds;
    @FXML
    Text authCheck;
    @FXML
    TextField usrID;
    Alert actorWelcome = new Alert(Alert.AlertType.NONE);


    public void checkUsrID(String ID, HashMap<String,String> ids, ActionEvent event) throws IOException, InterruptedException {
        if(ids.containsKey(ID)){ // if the id exists in the hashmap
            String actor = ids.get(ID); // then what is the second key value of the ID?
            // do the comparsion now:
            if(actor == "Student"){
                actorWelcome.setTitle("Welcome " + actor);
                actorWelcome.setAlertType(Alert.AlertType.CONFIRMATION);
                actorWelcome.setContentText("Welcome " + actor);
                actorWelcome.show();
                authCheck.setText("Logged In!");

                // sleep for 10 secs
                Thread.sleep(100);
                // do the student view here!
                FXMLLoader studentPage = new FXMLLoader(getClass().getResource("/resources/StudentView.fxml"));
                root = studentPage.load();

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setTitle("Welcome || Student");
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
            if(actor.equals("Agent")) {
                actorWelcome.setAlertType(Alert.AlertType.CONFIRMATION);
                actorWelcome.setContentText("Welcome " + actor);
                actorWelcome.show();
                authCheck.setText("Logged In!");
                // do the agent view here!

            }
            if(actor.equals("Chef")) {
                actorWelcome.setAlertType(Alert.AlertType.CONFIRMATION);
                actorWelcome.setContentText("Welcome " + actor);
                actorWelcome.show();
                authCheck.setText("Logged In!");

                // do the chef view here!
            }
        }else {
            actorWelcome.setAlertType(Alert.AlertType.ERROR);
            actorWelcome.setContentText("User not found!");
            actorWelcome.show();
        }


    }
    public void loginClicked(ActionEvent actionEvent) throws IOException, InterruptedException {
        IDS asuIDS = new IDS(); // make new IDS first
        validIds = asuIDS.getValidIds(); // get the valid IDSs
        authCheck.setText("Parsing Information");   // tell the user that IDS are being checked!
        Thread.sleep(100);
        checkUsrID(usrID.getText(), validIds, actionEvent);  // check the IDS

    }

}
