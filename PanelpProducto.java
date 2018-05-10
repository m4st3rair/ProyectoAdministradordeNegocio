package ProyectoAdministrador;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;





public class PanelpProducto extends JPanel{
    private Producto cliente;
    private JLabel ID, desc, precio, total;
    JComboBox combo;
    DefaultListModel<String> lista;
    private int alto=50, ancho=1030;
    private JButton xXx;
    private Double totaltotal;
    private boolean agregado;
    Ventana obj;
    
    public PanelpProducto(Producto cliente){
        super();
        obj= new Ventana(1);
        totaltotal=null;
        agregado=true;
        this.cliente= cliente;
        this.setBackground(Color.white);
        combo= new JComboBox();
        lista= new DefaultListModel();
        lista.addElement("1");
        combo.setModel(new javax.swing.DefaultComboBoxModel<>());
        total= new JLabel("$$");
        this.setSize(ancho, alto);
        ID= new JLabel(this.cliente.getCodigoProd()+"");
        desc= new JLabel(this.cliente.getDesc());
        precio= new JLabel(this.cliente.getPrecioMenudeo()+"");
        xXx= new JButton("X");

        JPanel p1,p2,p3,p4,p5,p6;
        p1= new JPanel(new FlowLayout());
        p2= new JPanel(new FlowLayout());
        p3= new JPanel(new FlowLayout());
        p4= new JPanel(new FlowLayout());
        p5= new JPanel(new FlowLayout());
        p6= new JPanel(new FlowLayout());
        p1.add(ID);
        p2.add(desc);
        p3.add(precio);
        p4.add(combo);
        p5.add(total);
        p6.add(xXx);
        p1.setBackground(Color.WHITE);
        p2.setBackground(Color.WHITE);
        p3.setBackground(Color.WHITE);
        p4.setBackground(Color.WHITE);
        p5.setBackground(Color.WHITE);
        p6.setBackground(Color.WHITE);
        
        this.setLayout(new GridLayout());
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);
        llenarCombo();
        xXx.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        });
    
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int totall= combo.getSelectedIndex()+1;
                if (totaltotal==null) {

                    if (totall<cliente.getNumeroMinimoMayoreo()) {
                        totaltotal=totall*cliente.getPrecioMenudeo();
                        precio.setText(cliente.getPrecioMenudeo()+"");
                    }else{
                        totaltotal=totall*cliente.getPrecioMayoreo();
                        precio.setText(cliente.getPrecioMayoreo()+"");
                    }

                    total.setText(totaltotal+"");
                    Double preTotal=Double.valueOf(obj.jLabel36.getText());
                    preTotal+=totaltotal;
                    obj.jLabel36.setText(""+preTotal);
                    
                }else{

                    Double preTotal=Double.valueOf(obj.jLabel36.getText())-totaltotal;
                    
                    if (totall<cliente.getNumeroMinimoMayoreo()) {
                        totaltotal=totall*cliente.getPrecioMenudeo();
                        precio.setText(cliente.getPrecioMenudeo()+"");
                    }else{
                        totaltotal=totall*cliente.getPrecioMayoreo();
                        precio.setText(cliente.getPrecioMayoreo()+"");
                    }

                    total.setText(totaltotal+"");
                    preTotal+=totaltotal;
                    obj.jLabel36.setText(""+preTotal);
                
                
                }
                
                
                
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            }
	});
        
        
    }
    
    public void llenarCombo(){
        for (int i = 1; i < 1001; i++) {
            combo.addItem(""+i);
        }
    
    
    }

    public Producto getCliente() {
        return cliente;
    }

    public void setCliente(Producto cliente) {
        this.cliente = cliente;
    }

    public JLabel getID() {
        return ID;
    }

    public void setID(JLabel ID) {
        this.ID = ID;
    }

    public JLabel getDesc() {
        return desc;
    }

    public void setDesc(JLabel desc) {
        this.desc = desc;
    }

    public JLabel getPrecio() {
        return precio;
    }

    public void setPrecio(JLabel precio) {
        this.precio = precio;
    }

    public JLabel getTotal() {
        return total;
    }

    public void setTotal(JLabel total) {
        this.total = total;
    }

    public JComboBox getCombo() {
        return combo;
    }

    public void setCombo(JComboBox combo) {
        this.combo = combo;
    }

    public DefaultListModel<String> getLista() {
        return lista;
    }

    public void setLista(DefaultListModel<String> lista) {
        this.lista = lista;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public JButton getxXx() {
        return xXx;
    }

    public void setxXx(JButton xXx) {
        this.xXx = xXx;
    }

    public Double getTotaltotal() {
        return totaltotal;
    }

    public void setTotaltotal(Double totaltotal) {
        this.totaltotal = totaltotal;
    }

    public boolean isAgregado() {
        return agregado;
    }

    public void setAgregado(boolean agregado) {
        this.agregado = agregado;
    }

    
    
    
    
}
