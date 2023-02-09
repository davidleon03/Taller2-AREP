package org.escuelaing.arep;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import org.apache.commons.io.FilenameUtils;

public class HttpServer {
    public static String name;
    public static String path;
    public static BuscarArchivo file;
    public static void main(String[] args) throws IOException {
        file = new BuscarArchivo();
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
                busqueda(clientSocket);
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        }
    }

    /**
     * Funcion generada para realizar la busqueda de archivos
     *
     * @param clientSocket El socket creado
     * @return
     * @throws IOException Exception
     */
    public static void busqueda(Socket clientSocket) throws IOException {
        String res;
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String[] datos = in.readLine().split(" ");
        if (datos[0].equals("POST")) {
            name = datos[1].split(",")[0];
            path = datos[1].split(",")[1];
            String result = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8);
            File archivo = new File(result+name);
            System.out.println("como debe ir --- path "+result+name);
            try {
                String extension = "PNG";
                BufferedImage image = ImageIO.read(archivo);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                ByteArrayOutputStream ArrBytes = new ByteArrayOutputStream();
                DataOutputStream writeimg = new DataOutputStream(clientSocket.getOutputStream());
                ImageIO.write(image, extension, ArrBytes);
                writeimg.writeBytes("HTTP/1.1 200 OK \r\n" + "Content-Type: image/png\r\n" + "\r\n");
                writeimg.write(ArrBytes.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        in.close();
    }

    /**
     * Funcion generada para mostrar el front de la API
     *
     * @param clientSocket El socket creado
     * @return
     * @throws IOException Exception
     */
    public static void front(Socket clientSocket) throws IOException {
        String outputLine;
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        outputLine = "HTTP/1.1 200 OK\r\n" + "Content-Type:  text/html\r\n" + "\r\n" + htmlForm();
        out.println(outputLine);
    }

    /**
     * Funcion generada para almacenar el HTML
     *
     * @return String
     */
    public static String htmlForm() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Form Example</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "\n" +
                "        <h1>Ingrese el Nombre y Path del archivo, separados por una ,</h1>\n" +
                "        <form action=\"/hellopost\">\n" +
                "            <label for=\"postname\">NOMBRE,PATH</label><br>\n" +
                "            <input type=\"text\" id=\"postname\" name=\"name\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postname)\">\n" +
                "        </form>\n" +
                "        \n" +
                "        <div id=\"postrespmsg\"></div>\n" +
                "        \n" +
                "        <script>\n" +
                "            function loadPostMsg(name){\n" +
                "                let url = \"\" + postname.value;\n" +
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