package ProyectoAdministrador;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;





public class ListaDeProductos {
    private ArrayList<Producto> listaProductos;
    private int indiceDeProd;
    
    
    private File folder = new File("C:\\Archivos de programa\\DeltaSoftDB\\Archivos\\Productos");
    private File folder2 = new File("C:\\Archivos de programa\\DeltaSoftDB\\Archivos\\Notas");
    private File folder3 = new File("C:\\Archivos de programa\\DeltaSoftDB\\ImagenesDeProducto");
    
    
    public ListaDeProductos(){    
        indiceDeProd=0;
        listaProductos= new ArrayList<>();
    }
    
    
    public void escribir(){
        System.out.println("/* * * * * * * * * * * * * * * * * * * L i s t a   d e   P r o d u c t o s * * * * * * * * * * * * * * * * * * * * * * * * * * /" );
        for (int i = 0; i < listaProductos.size(); i++) {
            System.out.println("/ / / / / / / / / P r o d u c t o / / / / / / / / /  / / / /");
            System.out.println(listaProductos.get(i).getDesc());
            System.out.println(listaProductos.get(i).getCodigoProd());
            System.out.println(listaProductos.get(i).getDirFoto());
            System.out.println(listaProductos.get(i).getPrecioMayoreo());
        
        }
    }

    
    public void cargarArchivo(){
        
       if(!folder.exists()){
           JOptionPane.showMessageDialog(null,"Creando Directorio ");
           folder.mkdirs();
        }
       if(!folder2.exists()){
           JOptionPane.showMessageDialog(null,"Creando Directorio");
           folder2.mkdirs();
        }
       if(!folder3.exists()){
           JOptionPane.showMessageDialog(null,"Creando Directorio");
           folder3.mkdirs();
        }
       
       
       
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).

            archivo = new File (folder+"\\Productos.txt");
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
        String descripcion="", dirFoto="",precioMay="", precioMen="", codigoPro="", numMinMay="" , existencia="";
        int bandera=0;          //indica lo que se esta leyendo 0=Descripcion, 1 = Precio, 2 = Direccion de la Imagen
        char[] nProducto= producto.toCharArray();
        
        for (int i = 0; i < nProducto.length; i++) {
            switch(nProducto[i]){
                case '$':
                    bandera++;
                    i++;
                break;
                
                default:
                break;
            }            
            switch(bandera){
                case 0:
                    descripcion+=nProducto[i];
                break;
                case 1:
                    codigoPro+=nProducto[i];
                break;
                case 2:
                    precioMay+=nProducto[i];
                break;
                case 3:
                    precioMen+=nProducto[i];
                break;
                case 4:
                    numMinMay+=nProducto[i];
                break;
                case 5:
                    dirFoto+=nProducto[i];
                break;
                case 6:
                    existencia+=nProducto[i];
                break;
                
            }
            
        }
        
        double may, men;
        int ex, IDp, pMM;
        IDp=Integer.valueOf(codigoPro);
        may=Double.valueOf(precioMay);
        men=Double.valueOf(precioMen);
        pMM=Integer.valueOf(numMinMay);
        ex=Integer.valueOf(existencia);
        
        
        Producto nuevoProducto= new Producto( may, men,descripcion, pMM, dirFoto, ex ,IDp);
        this.incertarNuevo(nuevoProducto);
        
    }

    public ArrayList<Producto> buscar(String prodFoud){   //Algorithmo de busqueda
        if (prodFoud.equals("ALL")) {
            return listaProductos;
        }
        ArrayList<Producto> prod= new ArrayList();
        String prodFound;
        String prodBuscado;
        for (int i = 0; i < listaProductos.size(); i++) {
            prodFound=convertidor(listaProductos.get(i).getDesc().toCharArray());
            prodBuscado=convertidor(prodFoud.toCharArray());
            if (prodFound.contains(prodBuscado)) {
                prod.add(listaProductos.get(i));
            }    
        }
        return prod;
    }

    
    
    public void incertarNuevo(Producto pNuevo){     //Algoritmo de incercion directa
        
        if (listaProductos.isEmpty()) {
            pNuevo.asignarCodigo();
            listaProductos.add(pNuevo);
        }else{
            //las letras A son anteriores B, C, D no se distinguen mayusculas de minusculas
            if(antesDespues(listaProductos.get(0), pNuevo)== 1){        //Caso donde el producto nuevo va antes que el primer producto en lista
                pNuevo.asignarCodigo();
                listaProductos.add(0, pNuevo);
            }else if(antesDespues(listaProductos.get(listaProductos.size()-1), pNuevo)== 2){ //Caso donde el producto nuevo va despues del producto final en lista
                pNuevo.asignarCodigo();
                listaProductos.add(pNuevo);
            }else{                                                      //Caso en el que se tiene que recorrer todo el array para encontrar el lugar de la incescion
                for (int i = 1; i < listaProductos.size(); i++) {
                    if (antesDespues(listaProductos.get(i), pNuevo)== 1) {
                        pNuevo.asignarCodigo();
                        listaProductos.add(i, pNuevo);
                        break;
                       
                    }else{          //Caso en donde los productos son iguales por lo tanto se sobre escriben los datos que coiciden
                            
                        if (antesDespues(listaProductos.get(i), pNuevo)== 3) {
                            listaProductos.get(i).setDesc(pNuevo.getDesc());
                            listaProductos.get(i).setDirFoto(pNuevo.getDirFoto());
                            listaProductos.get(i).setPrecioMayoreo(pNuevo.getPrecioMayoreo());
                            listaProductos.get(i).setPrecioMenudeo(pNuevo.getPrecioMenudeo());
                            listaProductos.get(i).setNumeroMinimoMayoreo(pNuevo.getNumeroMinimoMayoreo());
                            listaProductos.get(i).setCodigoProd(pNuevo.getCodigoProd());
                            break;
                        }
                    }
                    
                    
                }
            }
            
        }
        
        escribir();
    }
    
    public void eliminar(Producto prod){
        for (int i = 0; i < listaProductos.size(); i++) {
            if (prod.getDesc().equals(listaProductos.get(i).getDesc())) {
                listaProductos.remove(i);
                break;
            }
        }
    }
    
    public void guardar(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(folder+"\\Productos.txt");
            pw = new PrintWriter(fichero);
            for (int i = 0; i < listaProductos.size(); i++){ 
                pw.println(listaProductos.get(i).getDesc()+"$"+listaProductos.get(i).getCodigoProd()+"$"+listaProductos.get(i).getPrecioMayoreo()+"$"+listaProductos.get(i).getPrecioMenudeo()+"$"+listaProductos.get(i).getNumeroMinimoMayoreo()+"$"+listaProductos.get(i).getDirFoto()+"$"+listaProductos.get(i).getExistencia());
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
    
    public String convertidor(char[] mayusMinus){ //Funcion para tener un texto libre de Espacios y sin distincion de mayusculas y minusculas
        char[] homogeneo = sinEspacios(deMayusculasAMinusculas(mayusMinus));
        return String.valueOf(homogeneo);
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
    
    public int antesDespues(Producto pAnterior, Producto pNuevo){
        
        char[] pAnt= pAnterior.getDesc().toCharArray();
        char[] pNue= pNuevo.getDesc().toCharArray();
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
    
    
}
