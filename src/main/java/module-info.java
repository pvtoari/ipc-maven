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
    requires org.reflections;

    // Menu package
    exports net.pvtoari.ipcmaven.menu;
    opens net.pvtoari.ipcmaven.menu to javafx.fxml;

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

    // Pract4 packages
    exports net.pvtoari.ipcmaven.pract4.registerIPC to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract4.registerIPC;

    // Pract5 packages
    exports net.pvtoari.ipcmaven.pract5.exampleIPC.model to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract5.exampleIPC.model;

    exports net.pvtoari.ipcmaven.pract5.exampleIPC.controller to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract5.exampleIPC.controller;

    exports net.pvtoari.ipcmaven.pract5.exampleIPC.application to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract5.exampleIPC.application;

    exports net.pvtoari.ipcmaven.pract5.modalsIPC.model to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract5.modalsIPC.model;

    exports net.pvtoari.ipcmaven.pract5.modalsIPC.controller to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract5.modalsIPC.controller;

    exports net.pvtoari.ipcmaven.pract5.modalsIPC.application to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract5.modalsIPC.application;
}