/*
    * This file is necessary for the project to work properly, it is configured as follows:
    *
    * 1. The modules required for the project to function correctly are specified.
    * 2. For each package/directory with code in your project, you must specify the package that is opened and exported.
    *
    * If a new package is not specified, the project will not work properly.
    * That is, for each completed activity in the project, the package that is opened and exported must be specified in the module-info.java.
*/

module net.pvtoari.ipcmaven {
    requires javafx.controls;
    requires javafx.fxml;
    requires exp4j;
    requires java.desktop;

    // Sample package
    exports net.pvtoari.ipcmaven.sample;
    opens net.pvtoari.ipcmaven.sample to javafx.fxml;

    // Pract1 packages
    exports net.pvtoari.ipcmaven.pract1.loginIPC;
    opens net.pvtoari.ipcmaven.pract1.loginIPC to javafx.fxml;

    opens net.pvtoari.ipcmaven.pract1.myFirstIpcProjectArielRoque;
    exports net.pvtoari.ipcmaven.pract1.myFirstIpcProjectArielRoque to javafx.fxml;

    opens net.pvtoari.ipcmaven.pract1.calculadoraIPC;
    exports net.pvtoari.ipcmaven.pract1.calculadoraIPC to javafx.fxml;

    // Pract2 packages
    opens net.pvtoari.ipcmaven.pract2.parte1;
    exports net.pvtoari.ipcmaven.pract2.parte1 to javafx.fxml;

    exports net.pvtoari.ipcmaven.pract2.parte2 to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract2.parte2;

    exports net.pvtoari.ipcmaven.pract4 to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract4;
}