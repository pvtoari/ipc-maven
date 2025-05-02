package net.pvtoari.tonetbeans;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class ToNetbeans {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String packageToPack = JOptionPane.showInputDialog("Enter the package to pack (e.g. net.pvtoari.tonetbeans): ");
        String outputProjectName = JOptionPane.showInputDialog("Enter the output project name (any case or none): ");
        String mainClassName = JOptionPane.showInputDialog("Enter the main class of the output project (case sensitive): ");
        String appVendor = JOptionPane.showInputDialog("Enter the app vendor of the output project: ");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Select the output directory for the project");
        fileChooser.setApproveButtonText("Select");
        int result = fileChooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No directory selected. Exiting...");
            System.exit(0);
        }

        Path outputPath = fileChooser.getSelectedFile().toPath();

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Select the JavaFX libs directory");
        result = fileChooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No directory selected. Exiting...");
            System.exit(0);
        }

        Path javafxPath = fileChooser.getSelectedFile().toPath();

        Parameters params = new Parameters(packageToPack, outputProjectName, mainClassName, appVendor);

        ProjectBuilder projectBuilder = new ProjectBuilder(outputPath, javafxPath, params);
        projectBuilder.build();
    }
}
