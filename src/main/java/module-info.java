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
    requires javafx.web;
    requires exp4j;
    requires java.desktop;
    requires org.reflections;
    requires java.logging;

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

    // Pract6 packages
    exports net.pvtoari.ipcmaven.pract6.setrootIPC.application to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract6.setrootIPC.application;

    exports net.pvtoari.ipcmaven.pract6.setrootIPC.controller to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract6.setrootIPC.controller;

    exports net.pvtoari.ipcmaven.pract6.setrootIPC.model to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract6.setrootIPC.model;

    exports net.pvtoari.ipcmaven.pract6.tableviewIPC.model to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract6.tableviewIPC.model;

    exports net.pvtoari.ipcmaven.pract6.tableviewIPC.controller to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract6.tableviewIPC.controller;

    exports net.pvtoari.ipcmaven.pract6.tableviewIPC.application to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract6.tableviewIPC.application;

    // Pract7 packages
    exports net.pvtoari.ipcmaven.pract7.menudialogstoolbarsIPC.application to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract7.menudialogstoolbarsIPC.application;

    exports net.pvtoari.ipcmaven.pract7.menudialogstoolbarsIPC.controller to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract7.menudialogstoolbarsIPC.controller;

    exports net.pvtoari.ipcmaven.pract7.internationalizationIPC.application to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract7.internationalizationIPC.application;

    exports net.pvtoari.ipcmaven.pract7.internationalizationIPC.controller to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract7.internationalizationIPC.controller;

    // Pract8 packages
    exports net.pvtoari.ipcmaven.pract8.calculatorIPC.application to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract8.calculatorIPC.application;

    exports net.pvtoari.ipcmaven.pract8.calculatorIPC.controller to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract8.calculatorIPC.controller;

    exports net.pvtoari.ipcmaven.pract8.exampleIPC.application to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract8.exampleIPC.application;

    exports net.pvtoari.ipcmaven.pract8.exampleIPC.controller to javafx.fxml;
    opens net.pvtoari.ipcmaven.pract8.exampleIPC.controller;
}