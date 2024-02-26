/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author lxyen
 */
public class Clientes {
    
   int edad;
   int tiempoAtencion;

    public Clientes() {
    }

    public Clientes(int edad, int tiempoAtencion) {
        this.edad = edad;
        this.tiempoAtencion = tiempoAtencion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void setTiempoAtencion(int tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    @Override
    public String toString() {
        return "Cajero{" + "edad=" + edad + ", tiempoAtencion=" + tiempoAtencion + '}';
    }
   
}
