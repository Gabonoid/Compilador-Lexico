package vista.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import vista.Principal;
import modelo.*;


public class Controlador implements ActionListener {

    public static Principal principal;
    private File archivo;
    DefaultTableModel modelo;
    private boolean archivoExiste = false;

    public Controlador(Principal objVentana) {
        this.principal = objVentana;
        this.principal.jMenuItemAbrir.addActionListener(this);
        this.principal.btnCompilar.addActionListener(this);

        this.principal.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowAdapter e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Abrir archivo")) {
            abrirArchivo();
        }
        if (e.getActionCommand().equals("Compilar")) {
            try {
                compilar();
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int respuesta = fileChooser.showOpenDialog(principal);
        if (respuesta == fileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().exists()) {
            archivo = fileChooser.getSelectedFile();
        }
        archivoExiste = true;
        principal.infoRuta.setText(archivo.getAbsolutePath());

    }

    private void compilar() throws IOException {
        if (archivoExiste) {
            Lectura.numTokens = 0;
            Lectura.numEnteros = 0;
            Lectura.numErrores = 0;
            Lectura.numIdentificadores = 0;
            Lectura.numOperadores = 0;
            Lectura.numReservados = 0;
            
            principal.jTableDatos.setModel(Lectura.leerArchivo(archivo));
            principal.jTableDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            principal.jTableDatos.getColumnModel().getColumn(0).setPreferredWidth(120);
            principal.jTableDatos.getColumnModel().getColumn(1).setPreferredWidth(180);
            principal.jTableDatos.setEnabled(false);
            principal.infoTokens.setText(Lectura.numTokens+"");
            principal.infoErrores.setText(Lectura.numErrores+"");
            principal.infoId.setText(Lectura.numIdentificadores+"");
            principal.infoOperadores.setText(Lectura.numOperadores+"");
            principal.infoReservadas.setText(Lectura.numReservados+"");
            principal.infoEnteros.setText(Lectura.numEnteros+"");
        } else {
            JOptionPane.showMessageDialog(null, "Primero seleccione un archivo");
        }
    }

}
