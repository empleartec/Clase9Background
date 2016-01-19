package edu.empleartec.clase9;

/**
 * Created by gcalero1984 on 1/19/16.
 */
public class ClaseGenerica<T> {

    public void mostrar(T objeto) {
        System.out.println("Estoy mostrando " + objeto.toString());
    }
}
