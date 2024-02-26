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
public class Cajero {
    
   int numero;
   boolean ocupado;

    public Cajero() {
    }

    public Cajero(int numero, boolean ocupado) {
        this.numero = numero;
        this.ocupado = ocupado;
    }

    public Cajero(int numero) {
    this.numero=numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    /** 
* Marca al cajero como ocupado al comenzar a atender a un cliente. 
* @param cliente El cliente que será atendido por el cajero. 
* @return No retorna ningún valor.
*/
    public void atenderCliente(Clientes cliente){
        ocupado=true;
    }
    
/**
 * Marca al cajero como desocupado al terminar de atender a un cliente.
 * @return No retorna ningún valor.
 */
    
    public void terminarAtencion(){
        ocupado=false;
    }

    @Override
    public String toString() {
        return "Cajero{" + "numero=" + numero + ", ocupado=" + ocupado + '}';
    }
   
   
    
}
