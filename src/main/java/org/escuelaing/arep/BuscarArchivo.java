package org.escuelaing.arep;
import java.io.*;
/**
 * Clase que busca un archivo en especifico dada una ruta raiz.
 *
 * @author Felipe Marín
 */
public class BuscarArchivo
{
    File archivoEncontrado = null;

    public BuscarArchivo() { }

    /**
     * Metodo que retorna el File del archivo si este se encuentra E.O.C retornara null.
     * @param nombre El nombre del archivo que se esta buscando
     * @param raiz La ruta raiz donde comienza a buscar el archivo
     * @return archivoEncontrado El File del archivo
     */
    public File buscador(String nombre, File raiz){

        File[] lista = raiz.listFiles();
        if(lista != null) {
            for(File elemento : lista) {
                if (elemento.isDirectory())  {
                    buscador(nombre, elemento);
                } else if (nombre.equalsIgnoreCase(elemento.getName()))
                { archivoEncontrado = elemento; System.out.println("El Archivo ha sido encontrado.");}
            }
        }
        return archivoEncontrado;
    }
}