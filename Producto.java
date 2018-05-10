package ProyectoAdministrador;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;







public class Producto {
    private String  desc;   //descripcion del producto
    private int codigoProd; //[9999]
    private int existencia; //numero de productos en existencia
    private double precioMayoreo, precioMenudeo;//precios posibles
    private int numeroMinimoMayoreo;//camtidades apartir de cuando se considera aplicar un precio
    private String dirFoto;//Direccion de la foto
    private File folder = new File("C:\\Archivos de programa\\DeltaSoftDB\\Archivos\\Productos");
    
    
    public Producto(double precioMayoreo,double precioMenudeo, String desc, int numeroMinimoMayoreo,String dirFoto, int existencia){
        this.precioMayoreo= precioMayoreo;
        this.precioMenudeo= precioMenudeo;
        this.desc= desc;
        this.numeroMinimoMayoreo= numeroMinimoMayoreo;
        if (dirFoto.equals("")) {
            this.dirFoto="SinFoto";
        }else{
            this.dirFoto= dirFoto;        
        }
        this.existencia= existencia;
        this.codigoProd=0;
         
    }
    
    public Producto(double precioMayoreo,double precioMenudeo, String desc, int numeroMinimoMayoreo,String dirFoto, int existencia, int codigoProd){
        this.precioMayoreo= precioMayoreo;
        this.precioMenudeo= precioMenudeo;
        this.desc= desc;
        this.numeroMinimoMayoreo= numeroMinimoMayoreo;
        if (dirFoto.equals("")) {
            this.dirFoto="SinFoto";
        }else{
            this.dirFoto= dirFoto;        
        }
        this.existencia= existencia;
        this.codigoProd=codigoProd;
         
    }
    
    
    public void asignarCodigo(){
        if (codigoProd==0) {
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;
            try {
                // Apertura del fichero y creacion de BufferedReader para poder
               // hacer una lectura comoda (disponer del metodo readLine()).

                archivo = new File (folder+"\\CodigoProd.txt");
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
                    codigoProd++;
                }
                codigoProd++;
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
                fichero = new FileWriter(folder+"\\CodigoProd.txt");
                pw = new PrintWriter(fichero);
                for (int i = 0; i < codigoProd; i++){ 
                    pw.println(""+codigoProd);
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
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCodigoProd() {
        return codigoProd;
    }

    public void setCodigoProd(int codigoProd) {
        this.codigoProd = codigoProd;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecioMayoreo() {
        return precioMayoreo;
    }

    public void setPrecioMayoreo(double precioMayoreo) {
        this.precioMayoreo = precioMayoreo;
    }

    public double getPrecioMenudeo() {
        return precioMenudeo;
    }

    public void setPrecioMenudeo(double precioMenudeo) {
        this.precioMenudeo = precioMenudeo;
    }

    public int getNumeroMinimoMayoreo() {
        return numeroMinimoMayoreo;
    }

    public void setNumeroMinimoMayoreo(int numeroMinimoMayoreo) {
        this.numeroMinimoMayoreo = numeroMinimoMayoreo;
    }

    public String getDirFoto() {
        return dirFoto;
    }

    public void setDirFoto(String dirFoto) {
        this.dirFoto = dirFoto;
    }

    

}
