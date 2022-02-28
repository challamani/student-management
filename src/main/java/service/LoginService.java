package service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.CipherUtil;

import java.util.logging.Logger;

public class LoginService {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label status;

    private final static Logger logger = Logger.getLogger(LoginService.class.getName());

    public void Login(ActionEvent event) throws Exception {
        try {
            String usernameText = username.getText();
            String enteredPassword = password.getText();

            String cipherText = SystemConfig.getInstance().getEncryptedPassword(usernameText);
            logger.info("cipherText ::"+cipherText);
            String generatedText = CipherUtil.getSHA256Digest(enteredPassword);
            logger.info("generatedText ::"+generatedText);

            if (cipherText.equals(generatedText)) {
                status.setText("Login successful {} " + usernameText);
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/template/main.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            } else {
                status.setText("Sorry! Login failed, please try with correct credentials.");
            }
        } catch (Exception ex) {
            logger.info("failed to get credentials {}"+ex.getMessage());
            status.setText(ex.getMessage());
        }
    }
}
