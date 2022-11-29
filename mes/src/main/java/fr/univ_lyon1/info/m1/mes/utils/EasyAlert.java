package fr.univ_lyon1.info.m1.mes.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
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

    static void alertPromptComboBoxOrTextFieldOrBoth(
        String title, 
        String header, 
        String msg, 
        Runnable callbackSelected, 
        Runnable callBackCustom, 
        Runnable callBackBoth) {

        ButtonType selected = new ButtonType("Selectionnée", ButtonBar.ButtonData.OK_DONE);
        ButtonType custom = new ButtonType("Customisée", ButtonBar.ButtonData.OK_DONE);
        ButtonType both = new ButtonType("Les deux", ButtonBar.ButtonData.OK_DONE);
        ButtonType neatherType = new ButtonType("Aucun", ButtonBar.ButtonData.OK_DONE);

        Alert alert = new Alert(AlertType.CONFIRMATION,
        msg,
        selected,
        custom,
        both,
        neatherType);

        alert.setTitle(title);
        alert.setHeaderText(header);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == selected) {
            callbackSelected.run();
        }
        if (result.get() == custom) {
            callBackCustom.run();
        }
        if (result.get() == both) {
            callBackBoth.run();
        }
    }
}
