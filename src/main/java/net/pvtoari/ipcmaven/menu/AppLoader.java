package net.pvtoari.ipcmaven.menu;

import javafx.application.Application;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.*;

public class AppLoader {
    public Map<String, List<Class<?>>> loadJavaFXMLApplications() {
        Reflections reflections = new Reflections("net.pvtoari.ipcmaven", new SubTypesScanner(false));
        Set<Class<? extends Application>> classes = reflections.getSubTypesOf(Application.class);

        Map<String, List<Class<?>>> groupedApps = new TreeMap<>(Comparator.comparingInt(this::extractPractNumber));
        for (Class<?> appClass : classes) {
            String practGroup = extractPractGroup(appClass.getPackage().getName());
            groupedApps.computeIfAbsent(practGroup, k -> new ArrayList<>()).add(appClass);
        }

        for (List<Class<?>> appList : groupedApps.values()) {
            appList.sort(Comparator.comparing(Class::getName));
        }

        return groupedApps;
    }

    private String extractPractGroup(String packageName) {
        String[] parts = packageName.split("\\.");

        StringBuilder group = new StringBuilder();
        for (String part : parts) {
            if (!group.isEmpty()) group.append(".");

            group.append(part);

            if (part.startsWith("pract")) break;
        }

        return group.toString();
    }

    private int extractPractNumber(String packageName) throws NumberFormatException {
        String[] parts = packageName.split("\\.");
        for (String part : parts) {
            if (part.startsWith("pract")) {
                return Integer.parseInt(part.substring(5));
            }
        }

        return Integer.MAX_VALUE;
    }
}