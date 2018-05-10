package ProyectoAdministrador;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Cliente {
    private int IDC;
    private String nombre;
    double telefono;
    private ArrayList<String> IDVentas;
    private File folder = new File("C:\\Archivos de programa\\DeltaSoftDB\\Archivos\\Clientes");
    
    
    public int getIDC() {
        return IDC;
    }

    public void setIDC(int IDC) {
        this.IDC = IDC;
    }

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getIDVentas() {
        return IDVentas;
    }

    public void setIDVentas(ArrayList<String> IDVentas) {
        this.IDVentas = IDVentas;
    }
    
    
    
    public void asignarCodigo(){
        if (IDC==0) {
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;
            try {
                // Apertura del fichero y creacion de BufferedReader para poder
               // hacer una lectura comoda (disponer del metodo readLine()).

                archivo = new File (folder+"\\IDClientes.txt");
                if (!archivo.exists()) {
                    JOptionPane.showMessageDialog(null,"Creando Archivo");
                    try {
                        // A partir del objeto File creamos el fichero físicamente
                        if (archivo.createNewFile())
                          System.out.println("El fichero se ha creado correctamente");
                        else
                          System.out.println("No ha podido ser creado el fichero");
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }

                fr = new FileReader (archivo);
                br = new BufferedReader(fr);
                   // Lectura del fichero
                String linea;

                while((linea=br.readLine())!=null){
                    IDC++;
                }
                IDC++;
            }catch(Exception e){

                e.printStackTrace();
            }finally{
                   // En el finally cerramos el fichero, para asegurarnos
                   // que se cierra tanto si todo va bien como si salta 
                   // una excepcion.
                try{                    
                    if( null != fr ){   
                        fr.close();     
                    }                  
                }catch (Exception e2){ 
                      e2.printStackTrace();
                }
            }
            
        
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(folder+"\\IDClientes.txt");
            pw = new PrintWriter(fichero);
            for (int i = 0; i < IDC; i++){ 
                pw.println(""+(i+1));
            }
        }catch(Exception ed) {
            ed.printStackTrace();
        } finally {
            try {
                        // Nuevamente aprovechamos el finally para 
                        // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
            e2.printStackTrace();
            }
        }
        }else{
            System.out.println("No se hace nada por que ya tiene codigo");
        }
        
    }

    
    
    
    
    
    
    public Cliente(String nombre, double telefono){
        this.IDVentas= new ArrayList();
        this.nombre=nombre;
        this.telefono=telefono;
        this.IDC=0;
    }    
    
    public Cliente(String nombre, double telefono, int IDC, ArrayList<String> IDVentas){
        this.IDVentas= IDVentas;
        this.nombre=nombre;
        this.telefono=telefono;
        this.IDC=IDC;
    }
    
}
