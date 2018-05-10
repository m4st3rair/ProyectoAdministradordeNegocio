package ProyectoAdministrador;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class ListaClientes {
    private ArrayList<Cliente> listaClientes;

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    private int indiceCliente;
    private File folder1 = new File("C:\\Archivos de programa\\DeltaSoftDB\\Archivos\\Clientes");
    
    
    public ListaClientes(){
        this.listaClientes = new ArrayList();
        this.indiceCliente=0;
    
    }
     
    public void incertarNuevo(Cliente pNuevo){     //Algoritmo de incercion directa
        
        if (listaClientes.isEmpty()) {
            pNuevo.asignarCodigo();
            listaClientes.add(pNuevo);
        }else{
            //las letras A son anteriores B, C, D no se distinguen mayusculas de minusculas
            if(antesDespues(listaClientes.get(0), pNuevo)== 1){        //Caso donde el producto nuevo va antes que el primer producto en lista
                pNuevo.asignarCodigo();
                listaClientes.add(0, pNuevo);
            }else if(antesDespues(listaClientes.get(listaClientes.size()-1), pNuevo)== 2){ //Caso donde el producto nuevo va despues del producto final en lista
                pNuevo.asignarCodigo();
                listaClientes.add(pNuevo);
            }else{                                                      //Caso en el que se tiene que recorrer todo el array para encontrar el lugar de la incescion
                for (int i = 1; i < listaClientes.size(); i++) {
                    if (antesDespues(listaClientes.get(i), pNuevo)== 1) {
                        System.err.println("Se encontro un lugar para el prro");
                        pNuevo.asignarCodigo();
                        listaClientes.add(i, pNuevo);
                        break;
                       
                    }else{          //Caso en donde los productos son iguales por lo tanto se sobre escriben los datos que coiciden
                            
                        if (antesDespues(listaClientes.get(i), pNuevo)== 3) {
                            JOptionPane.showMessageDialog(null,"Cliente duplicado no agregado"); 
                            break;
                        }
                    }
                    
                }
            }
            
        }
        
        escribir();
    }
    
    public void escribir(){
        System.out.println("/* * * * * * * * * * * * * * * * * * * L i s t a   d e   C l i e n t e s  * * * * * * * * * * * * * * * * * * * * * * * * * * /" );
        for (int i = 0; i < listaClientes.size(); i++) {
            System.out.println("/ / / / / / / / / C l i e n t e / / / / / / / / /  / / / /");
            System.out.println(listaClientes.get(i).getNombre());
            System.out.println(listaClientes.get(i).getIDC());
            System.out.println(listaClientes.get(i).getTelefono());
            
        }
    }

    public int antesDespues(Cliente pAnterior, Cliente pNuevo){
        
        char[] pAnt= pAnterior.getNombre().toCharArray();
        char[] pNue= pNuevo.getNombre().toCharArray();
        pAnt=convertidorC(pAnt);
        pNue=convertidorC(pNue);
        int menor;
        
        
        if(pAnt.length<pNue.length){
            menor=pAnt.length;
        }else{
            menor=pNue.length;
        }
       
        for (int i = 0; i < menor; i++) {
            
            if ((int)pAnt[i]>(int)pNue[i]) {
                return 1;
            }else if((int)pAnt[i]<(int)pNue[i]){
                return 2;
            }
            
        }
        if(pAnt.length<pNue.length){
            return 2;
        }else if (pAnt.length>pNue.length) {
            return 1;
        }
        return 3;//para cuando los productos son iguales
    }
    
    public char[] convertidorC(char[] mayusMinus){ //Funcion para tener un texto libre de Espacios y sin distincion de mayusculas y minusculas
        char[] homogeneo = sinEspacios(deMayusculasAMinusculas(mayusMinus));
        return homogeneo;
    }
    
    public char[] sinEspacios(char[] espacios){
        int numeroEspacios=espacios.length;
        for(int i=0;i<espacios.length;i++){
            if(espacios[i]==32){
                numeroEspacios--;
            }
        }
        char[] nuevo= new char[numeroEspacios];
        int j=0;
        for (int i = 0; i < espacios.length; i++) {
            if(espacios[i]!=' '){
                nuevo[j]=espacios[i];
                j++;
            }
        }
        return nuevo;
    }
    
    public  char[] deMayusculasAMinusculas(char[] convinado){
        char[] homogeneo= new char[convinado.length];
        for (int i = 0; i < convinado.length; i++) {
            if (convinado[i]>64 && convinado[i]<91) {
                int conv=convinado[i]+32;
                homogeneo[i]= (char)conv;
            }else{
                homogeneo[i]= convinado[i];
            }
        }
        return homogeneo;
    }
        
    public void guardar(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(folder1+"\\Clientes.txt");
            pw = new PrintWriter(fichero);
            for (int i = 0; i < listaClientes.size(); i++){
                String compras="";
                for (int j = 0; j < listaClientes.get(i).getIDVentas().size(); j++) {
                    compras +="$"+listaClientes.get(i).getIDVentas().get(j);
                }
                pw.println(listaClientes.get(i).getNombre()+"$"+listaClientes.get(i).getIDC()+"$"+listaClientes.get(i).getTelefono()+compras);
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
        
    public void cargarArchivo(){
        
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).

            archivo = new File (folder1+"\\Clientes.txt");
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
                
                incertarNuevoString(linea);// manda a traer la funcion que limpia el archivo
                escribir();
            }
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
        
    }

    public void incertarNuevoString(String producto){//Limpieza de la linea del archivo que se esta leyendo
        String nombre="", telefono="",idClie="", idCompra="";
        int bandera=0;          //indica lo que se esta leyendo 0=Descripcion, 1 = Precio, 2 = Direccion de la Imagen
        char[] nProducto= producto.toCharArray();
        ArrayList<String> idCom=new ArrayList();
        
        for (int i = 0; i < nProducto.length; i++) {
            switch(nProducto[i]){
                case '$':
                    bandera++;
                    i++;
                    if (bandera>2) {
                        idCom.add(idCompra);
                        idCompra="";
                    }
                    
                break;
                
                default:
                break;
            }            
            switch(bandera){
                case 0:
                    nombre+=nProducto[i];
                break;
                case 1:
                    idClie+=nProducto[i];
                break;
                case 2:
                    telefono+=nProducto[i];
                break;
                default:

                    idCompra+=nProducto[i];
                break;                
            }
            
        }
        ArrayList<Integer> IDCOM= new ArrayList();
        Double tel= Double.valueOf(telefono);
        int idCl= Integer.valueOf(idClie);
 
        
        
        Cliente clienTE= new Cliente(nombre,tel, idCl, idCom);
        this.incertarNuevo(clienTE);
        
    }

    

}
