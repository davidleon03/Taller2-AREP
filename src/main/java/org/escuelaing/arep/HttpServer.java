package org.escuelaing.arep;

import java.net.*;
import java.io.*;
import java.security.PublicKey;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        int cont = 0;
        while (running) {
            try {
                System.out.println("RECIBIENDO PETICIONES");
                clientSocket = serverSocket.accept();
                if (cont == 0) {
                    front(clientSocket);
                    cont += 1;
                }
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));
                String inputLine, outputLine;

                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);
                    if (!in.ready()) {
                        break;
                    }
                }
                } catch(IOException e){
                    System.err.println("Accept failed.");
                    System.exit(1);
                }
            }
        }
        /**
         * Funcion generada para mostrar el front de la API
         * @param clientSocket El socket creado
         * @return
         * @throws IOException Exception
         */
        public static void front (Socket clientSocket) throws IOException {
            String outputLine;
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            outputLine = "HTTP/1.1 200 OK\r\n" + "Content-Type:  text/html\r\n" + "\r\n" + htmlForm();
            out.println(outputLine);
        }
        /**
         * Funcion generada para realizar la busqueda de datos en la API
         * @param clientSocket El socket creado
         * @return
         * @throws IOException Exception
         */
        public static void busqueda (Socket clientSocket) throws IOException {
            String res;
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String[] datos = in.readLine().split(" ");
            if (datos[0].equals("POST")) {
            }
            in.close();
        }
        /**
         * Funcion generada para almacenar el HTML
         * @return String
         */
        public static String htmlForm () {
            return "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <head>\n" +
                    "        <title>Form Example</title>\n" +
                    "        <meta charset=\"UTF-8\">\n" +
                    "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "\n" +
                    "        <h1>Seleccione el archivo</h1>\n" +
                    "        <form action=\"/hellopost\">\n" +
                    "            <label for=\"postname\">Name:</label><br>\n" +
                    "            <input type=\"file\" id=\"file\" name=\"avatar\" accept=\"image/png, image/jpeg\">\n" +
                    "            <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(file)\">\n" +
                    "        </form>\n" +
                    "        \n" +
                    "        <div id=\"postrespmsg\"></div>\n" +
                    "        \n" +
                    "        <script>\n" +
                    "            function loadPostMsg(name){\n" +
                    "                let url = \"\" + file.value;\n" +
                    "\n" +
                    "                fetch (url, {method: 'POST'})\n" +
                    "                    .then(x => x.text())\n" +
                    "                    .then(y => document.getElementById(\"postrespmsg\").innerHTML = y);\n" +
                    "            }\n" +
                    "        </script>\n" +
                    "    </body>\n" +
                    "</html>";
        }
    }