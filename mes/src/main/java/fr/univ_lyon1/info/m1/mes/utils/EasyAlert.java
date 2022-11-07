package fr.univ_lyon1.info.m1.mes.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;

/** 
 * A simple utility to display an error message in a popup window. Juste use
 * {@code EasyAlert.alert("message").}
 */
public interface EasyAlert {
    /**
     * Display msg in a popup window and block until the user clicks OK.
     */
    static void alert(String title, String msg) {
        Alert a = new Alert(AlertType.ERROR);
        a.setContentText(msg);
        a.setTitle(title);
        a.showAndWait();
    }

    static void alertPromptYesNo(String title, String msg, Runnable callback) {
        Alert alert = new Alert(AlertType.WARNING,
            msg,
            ButtonType.YES,
            ButtonType.NO);

        alert.setTitle(title);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES) {
            callback.run();
        }
    }
}
