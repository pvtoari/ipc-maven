# **IPC-Maven**

Este proyecto es una alternativa al entorno de trabajo propuesto por los profesores respecto a la asignatura de **Interfaces Persona Computador**.

La intención de este es motivar a los estudiantes de la **Universidad Politècnica de València** a utilizar tecnologías alternativas para el desarrollo en Java, como lo son el gestor de dependencias **Maven** y el entorno de desarrollo **IntelliJ IDEA**.

No solo es usar tecnologías alternativas, sino más bien usar las más usadas y estandarizadas, para que las personas interesadas en este proyecto puedan tener una primera toma de contacto con un **stack de tecnologías real**.

**El trabajo duro siempre es bien recompensado, léete todo el documento venga :P**

## Sumario

- [**IPC-Maven**](#ipc-maven)
  - [Sumario](#sumario)
  - [¿Qué es Maven?](#qué-es-maven)
  - [¿Qué es IntelliJ IDEA?](#qué-es-intellij-idea)
  - [Estructura del proyecto](#estructura-del-proyecto)
  - [¿Por qué usar esta alternativa?](#por-qué-usar-esta-alternativa)
  - [Requisitos previos](#requisitos-previos)
  - [Instalación y configuración](#instalación-y-configuración)
    - [Clonando el repositorio](#clonando-el-repositorio)
    - [Instalación de Maven](#instalación-de-maven)
    - [Usando el wrapper de Maven](#usando-el-wrapper-de-maven)
    - [Configuración en IntelliJ IDEA](#configuración-en-intellij-idea)
    - [Añadiendo dependencias](#añadiendo-dependencias)
    - [Rutinas](#rutinas)
  - [Lugares de las cosas](#lugares-de-las-cosas)
    - [Código fuente](#código-fuente)
    - [Recursos](#recursos)
  - [Empleando el proyecto para IPC](#empleando-el-proyecto-para-ipc)
    - [Observaciones](#observaciones)
  - [Sobre los archivos especiales](#sobre-los-archivos-especiales)
    - [module-info.java](#module-infojava)
    - [package-info.java](#package-infojava)
  - [Modularizando nuestras prácticas](#modularizando-nuestras-prácticas)
  - [**Últimos pasos**](#últimos-pasos)
  - [Problemas](#problemas)

## ¿Qué es Maven?

**Apache Maven** es una herramienta de gestión y construcción de proyectos utilizada principalmente en proyectos Java. Proporciona un modelo de configuración basado en **POM (Project Object Model)** que permite gestionar dependencias, compilar código, ejecutar pruebas y empaquetar aplicaciones de manera eficiente.

**Características principales:**
- Gestión automática de dependencias.
- Estructura estandarizada de proyectos.
- Soporte para plugins y extensiones.
- Integración con diversas herramientas de desarrollo.

## ¿Qué es IntelliJ IDEA?

**IntelliJ IDEA** es un entorno de desarrollo integrado (IDE) para Java desarrollado por **JetBrains**. Ofrece una experiencia de desarrollo fluida con potentes herramientas de análisis de código, depuración y soporte para múltiples lenguajes de programación.

**Beneficios de IntelliJ IDEA:**
- Autocompletado inteligente de código.
- Refactorización avanzada.
- Depuración visual.
- Integración nativa con Maven y otros frameworks.

## Estructura del proyecto

El proyecto sigue la estructura estándar de Maven:
```
IPC-Maven/
├── src/
│   ├── main/
│   │   ├── java/         # Código fuente
│   │   └── resources/    # Archivos de recursos
│   ├── test/
│       ├── java/         # Pruebas unitarias
│       └── resources/    # Recursos para pruebas
├── pom.xml               # Archivo de configuración de Maven
├── README.md             # Documentación del proyecto
└── .gitignore            # Archivos a excluir en Git
```

## ¿Por qué usar esta alternativa?

- **Facilidad en la gestión de dependencias:** Maven permite incluir bibliotecas fácilmente sin necesidad de gestionar manualmente los archivos JAR.
- **Estructura organizada:** Proyectos bien estructurados y estandarizados.
- **Mayor eficiencia y productividad:** IntelliJ IDEA proporciona herramientas avanzadas para mejorar la experiencia de desarrollo.
- **Portabilidad asegurada:** Usar una estructura de proyecto estandarizada y una gestión de dependencias portable te permite poder empezar a programar sin tener que adaptarte a las condiciones de la máquina en la que se programa/compila.

## Requisitos previos

Antes de comenzar, asegúrate de tener instalados:
- **Java 23**
- **Apache Maven** (opcional, [échale un vistazo a esto](#usando-el-wrapper-de-maven))
- **IntelliJ IDEA**.
- **Git** (valga la redundancia).

## Instalación y configuración

### Clonando el repositorio
Para hacer una copia de la estructura del proyecto y empieces a usarla, haz un **fork** del repositorio, luego ejecuta el siguiente comando en tu terminal:
```sh
git clone https://github.com/tu-usuario/IPC-Maven.git -b base-project
```

Sustituye "tu-usuario" por tu usuario; si todo ha ido bien entonces se habrá generado una carpeta que se llame `IPC-Maven` con la estructura del proyecto preparado para usarse.

### Instalación de Maven

Si prefieres no instalar Maven y utilizar su versión portable incorporada en este proyecto, salta [al siguiente punto](#usando-el-wrapper-de-maven).

Si aún no tienes Maven instalado, puedes hacerlo de la siguiente manera:

En **Linux** (Ubuntu/Debian):
```sh
sudo apt update
sudo apt install maven
```

En **macOS** con Homebrew:
```sh
brew install maven
```

En **Windows**:
1. Descarga Maven desde [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).
2. Configura la variable de entorno `MAVEN_HOME`.
3. Agrega el directorio `bin` de Maven a la variable de entorno `PATH`.

### Usando el wrapper de Maven

**El wrapper de Maven** es una herramienta que permite ejecutar Maven sin necesidad de tenerlo instalado en tu sistema. Esto es especialmente útil para asegurar que todos los desarrolladores de un proyecto utilicen la misma versión de Maven.

Consiste en un conjunto de archivos que se incluyen en el proyecto:
- `mvnw` y `mvnw.cmd`: **Scripts** para ejecutar Maven en **Unix** y **Windows**, respectivamente.
- `.mvn/wrapper/maven-wrapper.jar` y `.mvn/wrapper/maven-wrapper.properties`: **Archivos de configuración** del wrapper.

Cuando ejecutas `./mvnw` (en Unix) o `mvnw.cmd` (en Windows), seguido de los argumentos habituales de Maven, todo funcionará como si lo tuvieras instalado desde un principio.

Esta alternativa garantiza tener una versión funcional de Maven sin la necesidad de instalarlo, pero no es una buena costumbre si eres un desarrollador activo, así que mejor **instala Maven** y ya.

### Configuración en IntelliJ IDEA
Si no tienes instalado **IntelliJ IDEA**, puedes descargarte la versión de comunidad, que es gratis [aquí](https://www.jetbrains.com/idea/download/download-thanks.html?code=IIC).

Luego:
1. Abre **IntelliJ IDEA**.
2. Selecciona **File > New > Project from Existing Sources**.
3. Elige el archivo `pom.xml` del proyecto.
4. Asegúrate de que el **SDK de Java** y **Maven** estén correctamente configurados, se explicará posteriormente.

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>net.pvtoari</groupId>
    <artifactId>proyecto-guay</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!-- Dependencias del proyecto -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        ...
    </dependencies>

    <build>
        <plugins>
            <!-- Plugins de construcción -->
        </plugins>
    </build>
</project>
```
De todos los parámetros que se pueden configurar en el `pom.xml`, vamos a enfocarnos en los siguientes:

- **groupId:** Identifica de manera única el proyecto en el [espacio de nombres de Maven](https://maven.apache.org/guides/mini/guide-naming-conventions.html). Es una forma de añadirle una identidad a tus proyectos (por ejemplo, `net.pvtoari`).

- **artifactId:** Es el identificador único del proyecto dentro del grupo especificado por el `groupId`. Por ejemplo, `proyecto-guay`. Si tuvieramos varios proyectos hechos podríamos denominarlos por el `groupId` seguido del `artifactId`. Por ejemplo: `com.etsinf.ipc`, `com.etsinf.csd`, ...

- **version:** Define la versión del proyecto. Por ejemplo, `1.0-SNAPSHOT` indica que es una versión en desarrollo. Si no vamos a ser muy profesionales, dejemos eso como está.

- **dependencies:** Lista las dependencias del proyecto, es decir, las bibliotecas externas que el proyecto necesita. Cada dependencia se define con un `groupId`, `artifactId` y `version`.

- **plugins:** Define los plugins que se usarán durante el ciclo de vida de construcción del proyecto. Los plugins pueden realizar diversas tareas, como compilación, empaquetado, pruebas, etc.

### Añadiendo dependencias

Para buscar y usar dependencias nuevas, podemos usar Maven Repository. Es una gran base de datos de librerías y dependencias que puedes utilizar en tu proyecto. Para encontrar una librería específica, sigue estos pasos:

1. Visita [Maven Repository](https://mvnrepository.com/).
2. Usa la barra de búsqueda para encontrar la librería que necesitas.
3. Selecciona la versión de la librería que deseas utilizar.
4. Copia la configuración de la dependencia proporcionada y pégala en la sección `<dependencies>` de tu `pom.xml`.

Por ejemplo, para añadir la librería Gson, busca "Gson" en Maven Repository y añade la dependencia correspondiente:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.8</version>
</dependency>
```

Esto descargará automáticamente la librería y la incluirá en tu proyecto cuando ejecutes Maven, por lo que a la hora de programar ya contarás con esas librerías a tu disposición.

**Nota:** muchos IDEs y editores como **VSCode** tienen extensiones o integran facilidades para añadir dependencias desde un buscador dentro de la propia aplicación, **échale un ojo si eso**.

### Rutinas
A nivel real el proyecto no aprovecha todas las características y aspectos guays de Maven, pero de todas formas las explicaré brevemente.

Maven incorpora ciertas **"rutinas"** que hacen tareas específicas sobre el proyecto, desde **compilar** cada uno de los archivos de éste, **empaquetar** todo tu proyecto en un **JAR**, **limpiar** los residuos de la compilación del proyecto, etc...

Es interesante utilizar aquellas que involucren generar un archivo **JAR** para poder distribuir nuestro proyecto de forma empaquetada si lo necesitaramos; además de que para que esto se pueda hacer, Maven tratará de construir todo el proyecto, por lo que podrás encontrar errores a arreglar, si no los hubieras encontrado antes.

- **Instalar dependencias y compilar el proyecto:**
    ```sh
    mvn install # Este y los demás genera una carpeta "target"
    ```

- **Construir el proyecto:**
    ```sh
    mvn build
    ```

- **Empaquetar el proyecto en un archivo JAR:**
    ```sh
    mvn package
    ```

- **Limpiar los archivos generados durante la compilación:**
    ```sh
    mvn clean # Elimina la carpeta "target"
    ```

## Lugares de las cosas
Pese a que el título sea un poco ambiguo, se quiere referir a cómo respetar y mantener [la estructura del proyecto](#estructura-del-proyecto)

- `src/`: Este es el directorio raíz que contiene todo el código fuente del proyecto.
  - `main/`: Contiene el código fuente principal del proyecto.
    - `java/`: Aquí se colocan los archivos `.java` que contienen el código fuente de la aplicación.
      - `com/tuempresa/tuapp/`: Este es un ejemplo de estructura de paquetes. Los paquetes ayudan a organizar el código en diferentes módulos o funcionalidades.
    - `resources/`: Este directorio contiene recursos no compilados que la aplicación necesita en tiempo de ejecución, como archivos de configuración, imágenes, archivos de propiedades, etc.

Aclarada la estructura del proyecto, diferenciaremos los elementos del proyecto por código fuente y recursos.

### Código fuente

El **código fuente** son los archivos `.java` dentro de `src/main/java` se compilan y se convierten en bytecode que puede ser ejecutado por la JVM. Por ejemplo, si tienes una clase `App` en el paquete `com.groupId.artifactId`, su ruta sería `src/main/java/com/groupId/artifactId/.../App.java`.

### Recursos

Los **recursos** son los archivos dentro de `src/main/resources`, se empaquetan junto con el código compilado y se pueden acceder en tiempo de ejecución. Por ejemplo, si tienes un archivo `FXMLDocument.fxml` en `src/main/resources/...`, puedes acceder a él en tu código Java usando:
  ```java
  InputStream inputStream = App.class.getClassLoader().getResourceAsStream("/com/pvtoari/ipc/pract1/FXMLDocument.fxml");
  ```

Mantener esta estructura organizada ayuda a que el proyecto sea más mantenible y comprensible tanto para los desarrolladores actuales como para los futuros, además de que nos resuelve problemas de acceso cuando usamos rutas relativas, etc.

## Empleando el proyecto para IPC

Tras explicar detalladamente la estructura y uso de este proyecto, ya podemos aplicarlo respecto la asignatura de Interfaces Persona Computador.
Esto es, voy a dejar sugerencias o ejemplos de cómo he aprovechado yo esta forma de gestionar un proyecto de la asignatura con esta base que se ha explicado.

Por ejemplo, a día 2 de marzo de 2025, así es como yo personalmente he estructurado el proyecto:

```
.
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   ├── module-info.java
        │   └── net
        │       └── pvtoari
        │           └── ipcmaven
        │               ├── package-info.java
        │               ├── pract1
        │               │   ├── calculadoraIPC
        │               │   │   ├── FXMLDocumentController.java
        │               │   │   └── JavaFXMLApplication.java
        │               │   ├── loginIPC
        │               │   │   ├── FXMLDocumentController.java
        │               │   │   └── JavaFXMLApplication.java
        │               │   └── myFirstIpcProjectArielRoque
        │               │       ├── FXMLDocumentController.java
        │               │       └── JavaFXMLApplication.java
        │               ├── pract2
        │               │   ├── parte1
        │               │   │   ├── FXMLDocumentController.java
        │               │   │   ├── JavaFXMLApplication.java
        │               │   │   └── Utils.java
        │               │   └── parte2
        │               │       ├── FXMLDocumentController.java
        │               │       ├── JavaFXMLApplication.java
        │               │       ├── NodeCircle.java
        │               │       └── Utils.java
        │               └── sample
        │                   ├── HelloApplication.java
        │                   └── HelloController.java
        └── resources
            └── net
                └── pvtoari
                    └── ipcmaven
                        ├── pract1
                        │   ├── calculadoraIPC
                        │   │   └── FXMLDocument.fxml
                        │   ├── loginIPC
                        │   │   └── FXMLDocument.fxml
                        │   └── myFirstIpcProjectArielRoque
                        │       └── FXMLDocument.fxml
                        ├── pract2
                        │   ├── parte1
                        │   │   ├── circulo.png
                        │   │   └── FXMLDocument.fxml
                        │   └── parte2
                        │       ├── circulo.png
                        │       └── FXMLDocument.fxml
                        └── sample
                            └── FXMLDocument.fxml
```
### Observaciones

- Los `.java` y `.fxml` se reparten entre las carpetas `java` y `resources`; los `.fxml` se consultarán usando rutas [como en el ejemplo de acceso a un recurso](#lugares-de-las-cosas).
- Se crean paquetes para cada una de las prácticas, y si fuera necesario, para cada una de sus partes.
- De forma similar, para los `.fxml` también se crean carpetas por cada práctica y parte, no por otra cosa, si no para tener una estructura similar entre código y recursos.
- Se usan dos archivos "especiales", `module-info.java` y `package-info.java`, explicaremos su funcionamiento [más tarde](#sobre-los-archivos-especiales).

## Sobre los archivos especiales

### module-info.java
El archivo `module-info.java` es una característica introducida en Java 9 como parte del sistema de módulos de Java. Este archivo define un módulo y especifica qué paquetes están disponibles para otros módulos y qué dependencias tiene el módulo. Aquí tienes un ejemplo básico:

```java
module com.pvtoari.labsmaven {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.pvtoari.labsmaven to javafx.fxml;
  exports com.pvtoari.labsmaven;
}
```

En este ejemplo:
- `module com.pvtoari.labsmaven` define el nombre del módulo.
- `requires` especifica las dependencias del módulo.
- `opens` permite el acceso reflexivo a los paquetes especificados.
- `exports` hace que los paquetes estén disponibles para otros módulos.

### package-info.java
El archivo `package-info.java` se utiliza para proporcionar documentación a nivel de paquete y anotaciones. Este archivo no es obligatorio, pero es útil para agregar comentarios y anotaciones que se aplican a todo el paquete. Aquí tienes un ejemplo:

```java
package com.pvtoari.labsmaven;
```

En este proyecto se cuenta con un archivo `module-info.java` que **deberás modificar**, y un `package-info` que **NO debes modificar**. 

**Me explico:** la **modularización** a la que se ha optado en este proyecto está hecha no por otra cosa sino más que para evitar **problemas de librerías** en algunos sistemas. Personalmente, como usuario de **Linux**, vi cómo en **Windows** proyectos con dependencias funcionaban genial; sin embargo, en un sistema **Linux** presentaban problemas hasta que se definiese la **modularización**.

Por esto, **aprenderemos todos a modularizar y se acabó**.

## Modularizando nuestras prácticas

El título es más imponente de lo que parece, y este punto va a ser breve y sencillo.

Como se ha mencionado antes, este proyecto cuenta con un archivo `module-info.java`, que puede tener esta pinta:

```java
module net.pvtoari.labsmaven {
    requires javafx.controls;
    requires javafx.fxml;
    requires exp4j;

    // Sample package
    exports net.pvtoari.labsmaven.sample;
    opens net.pvtoari.labsmaven.sample to javafx.fxml;

    // Pract1 packages
    exports net.pvtoari.labsmaven.pract1.loginIPC;
    opens net.pvtoari.labsmaven.pract1.loginIPC to javafx.fxml;

    opens net.pvtoari.labsmaven.pract1.myFirstIpcProjectArielRoque;
    exports net.pvtoari.labsmaven.pract1.myFirstIpcProjectArielRoque to javafx.fxml;

    opens net.pvtoari.labsmaven.pract1.calculadoraIPC;
    exports net.pvtoari.labsmaven.pract1.calculadoraIPC to javafx.fxml;

    // Pract2 packages
    opens net.pvtoari.labsmaven.pract2.parte1;
    exports net.pvtoari.labsmaven.pract2.parte1 to javafx.fxml;

    exports net.pvtoari.labsmaven.pract2.parte2 to javafx.fxml;
    opens net.pvtoari.labsmaven.pract2.parte2;

    //Pract3 packages
    ...
}
```

Como podéis ver, si contrastamos con [la estructura de mi proyecto personal](#empleando-el-proyecto-para-ipc), vemos que:

+ Cada dependencia que necesite el proyecto, desde JavaFX o librerías complementarias para funcionalidades extra de nuestros proyectos, deberán ser marcadas como **requeridas**.
+ Para cada paquete **NO vacío** en el que tengamos una aplicación de JavaFX, debemos **"abrirlo"** y **"exportarlo"** a `javafx.fxml`.

Un error muy común es programar nuestros proyectos, acabarlos, probar a arrancar la aplicación de JavaFX y ver que nos sale un error constante de acceso o cosas raras de reflexión. Si eso pasa, revisa que estés **"declarando"** el paquete de la aplicación de tu práctica en el `module-info.java`, seguro que se soluciona :^).

## **Últimos pasos**

Ahora que ya conoces cómo funcionan los **módulos**, **Maven**, la **estructura del proyecto**, y que cuentas con un **IDE decente**, me alegra informarte de que no vas a tener que molestarte en añadir dependencias o configurar cualquier `pom.xml` porque este repositorio ya cuenta con una rama con el proyecto en blanco, para que cualquier persona pueda usarlo desde cero, con todas las dependencias de JavaFX.

De hecho, esta rama a usar es la que se clona cuando usas el comando especificado [en la instalación](#instalación-y-configuración), así que, tu último paso es **programar**.

¡Ojo! La rama `master` del repositorio es la de **mis prácticas**, la que usaré para ir colgando el trabajo que vaya haciendo en la asignatura. Puedes echarle un ojo si quieres también, pero cada persona programa de una forma distinta.

**¡Que te vaya bien jejeje!**

## Problemas

Si necesitas añadir alguna **dependencia adicional** o realizar **cambios en el proyecto**, abre un **issue en GitHub**. Esto permitirá que otros colaboradores puedan revisar y discutir los cambios propuestos antes de implementarlos. Para abrir un issue, sigue estos pasos:

1. Ve al **repositorio del proyecto en GitHub**.
2. Haz clic en la pestaña **"Issues"**.
3. Haz clic en el botón **"New issue"**.
4. Describe detalladamente la **dependencia o el cambio** que necesitas.
5. Haz clic en **"Submit new issue"**.

De esta manera, mantendremos el proyecto **organizado y colaborativo**.
