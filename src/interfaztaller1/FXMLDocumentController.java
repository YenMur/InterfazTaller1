/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaztaller1;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import static javax.swing.text.html.HTML.Tag.OL;
import modelo.Cajero;
import modelo.Clientes;

/**
 *
 * @author lxyen
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private ProgressIndicator cajero1;
    
    @FXML
    private ProgressIndicator cajero2;
    
    @FXML
    private ProgressIndicator cajero3;
    
    @FXML
    private ProgressIndicator cajero4;
    
    @FXML
    private ProgressIndicator cajero5;
    
    @FXML
    private ProgressIndicator cajero6;
    
    
    private final Random random=new Random();
    private final LinkedList<Clientes> colaPersonaN=new LinkedList<>();
    private final LinkedList<Clientes> colaClientes=new LinkedList<>();
    private final LinkedList<Clientes> colaPreferencial=new LinkedList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(6);
    private final ArrayList<Cajero> cajeros=new ArrayList<>();
    private final Map<Cajero, Integer> clientesAtendidos=new HashMap<>();
    private final Map<Cajero, Long> tiempoTotalAtencion=new HashMap<>();
    private final Map<Cajero, Double> tiempoPromedioAtencion=new HashMap<>();
    private final Map<Cajero, Integer> edadPromedioAtendida=new HashMap<>();
    private final Map<Cajero, Integer> edadMaximaAtendida=new HashMap<>();
    private final ScheduledExecutorService scheduler=Executors.newScheduledThreadPool(1);
    
    
    private final int edadMax=90;
    private final int tiempoMinA=5;
    private final int tiempoMaxA=60;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    /**
 * Inicializa los cajeros del banco, se les asigna un número, se configura las medidas de atención.
 * @param url La URL del archivo FXML.
 * @param resourceBundle El ResourceBundle que contiene la información localizada.
 * @return No retorna ningún valor.
 */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for(int i=0;i<6;i++){
            Cajero cajero=new Cajero(i+1);
            cajeros.add(cajero);
            tiempoTotalAtencion.put(cajero, OL);
            tiempoPromedioAtencion.put(cajero, 0.0);
            edadPromedioAtendida.put(cajero, 0);
            edadMaximaAtendida.put(cajero, 0);
        }
        
        //Simulacion
        scheduler.scheduleAtFixedRate(this::generarClienteAleatorio, 0, 3, TimeUnit.SECONDS);
    }    
    
       /**
 * Genera clientes de forma aleatoria y lo agrega a una de las colas del banco
 * @param 
 * @return No retorna ningún valor.
 */
    private void generarClienteAleatorio(){
        int probabilidad=random.nextInt(10);
        if(probabilidad<2){
            Clientes cliente=new Clientes(random.nextInt(edadMax)+18,random.nextInt(tiempoMaxA - tiempoMinA)+tiempoMinA);
            int cola=random.nextInt(3);
            
            if(cliente.getEdad()>57){
                colaPreferencial.add(cliente);
            }else if(random.nextBoolean()){
                colaPersonaN.add(cliente);
            }else{
                colaClientes.add(cliente);
            }
            Platform.runLater(()->label.setText("Se ha añadido un cliente a la cola"));
        }
    }
    
    /**
 * Asigna clientes a los cajeros del banco
 * @param 
 * @return No retorna ningún valor.
 */
    private void asignarClienteCajero(){
    for(Cajero cajero: cajeros){
    if(!cajero.isOcupado()){
        LinkedList<Clientes> cola=null;
        if(!colaPersonaN.isEmpty()){
            cola=colaPersonaN;
        }else if(!colaClientes.isEmpty()){
            cola=colaClientes;
        }else if(!colaPreferencial.isEmpty()){
            cola=colaPreferencial;
        }if(cola!=null){
            Clientes cliente=cola.poll();
            cajero.atenderCliente(cliente);
            executorService.submit(new Atencion(cajero,cliente));
        }
    }
}
    }
   
}
