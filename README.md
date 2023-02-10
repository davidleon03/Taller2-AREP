# TALLER 2 | APLICACIONES DISTRIBUIDAS

El siguiente repositorio se crea con el fin de afianzar conocimientos frente a temas MVN, GIT, JAVA. También la interacción de archivos (HTML, JS, CSS, PNG) externos mediante protocolos REST.


### Prerequisites

 - Maven
 - Java 
 - Git 

### Installing

Para descargar el proyecto y compilarlo de manera local puede clonar el repositorio con el siguiente comando:

    git clone https://github.com/davidleon03/Taller2-AREP.git
     

Ejecutar el programa mediante consola con el siguiente comando. También genera el ciclo de vida de este.

    mvn clean package exec:java -Dexec.mainClass="org.escuelaing.arep.HttpServer"
    
Para acceder a la navegacion web para vusualizar lo realizado

    http://localhost:35000/
    
Se debe ingresar el nombre con la extencion y el PATH

Ejemplo PATH

![image](https://user-images.githubusercontent.com/98216838/217991387-9aaed9d4-dbab-4b54-8e23-2445286324ff.png)

Archvivos para pruebas

![image](https://user-images.githubusercontent.com/98216838/217992182-75f515af-827e-48e7-b6fa-7d13af141a81.png)



## Running the tests

Para ejecutar las pruebas unitarias se ejecutan con el siguiente comando: 

    mvn test

## Documentation

Para crear la documentación del proyecto se hace con el siguiente comando:
    
    mvn javadoc:javadoc
    

## Authors

* **David Leon** 
