package fr.univ_lyon1.info.m1.mes.utils;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 * Interface for clipboard.
 */
public interface EasyClipboard {
    /**
     * copy text in clipboard.
     * @param data String
     */
    static void copy(String data) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(data);
        clipboard.setContent(content);
    }

    /**
     * Return text from clipboard.
     * @return String
     */
    static String read() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        return clipboard.getString();
    }
}
