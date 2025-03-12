package net.pvtoari.ipcmaven.pract4;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextInputControl;

import java.lang.reflect.Field;
import java.util.List;

public class ListenerChecker {
    public static boolean hasTextListeners(TextInputControl control) {
        try {
            StringProperty textProperty = control.textProperty();
            Field listenersField = textProperty.getClass().getDeclaredField("changeListeners");
            listenersField.setAccessible(true);
            List<?> listeners = (List<?>) listenersField.get(textProperty);
            return listeners != null && !listeners.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

