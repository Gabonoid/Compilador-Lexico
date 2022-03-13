package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

public class Lectura {

    public static DefaultTableModel leerArchivo(File archivo) throws FileNotFoundException, IOException {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Token");
        modelo.addColumn("Lexema");

        BufferedReader bf = new BufferedReader(new FileReader(archivo));

        while (bf.ready()) {
            String linea = bf.readLine()
                    .replace("+", " + ")
                    .replace("-", " - ")
                    .replace("*", " * ")
                    .replace("/", " / ")
                    .replace("=", " = ")
                    .replace(",", " , ")
                    .replace("(", " ( ")
                    .replace(")", " ) ")
                    .replace(";", " ; ")
                    .replaceAll("[ ]+", " ");

            //System.out.println(linea);

            String[] prueba = linea.split(" ");
            comprobador: for (int i = 0; i < prueba.length; i++) {

                String[] reservado = Lexico.evaluarReservado(prueba[i]);
                String[] entero = Lexico.evaluarEntero(prueba[i]);
                String[] operador = Lexico.evaluarOperador(prueba[i]);
                String[] identificador = Lexico.evaluarIdentidicador(prueba[i]);

                if (reservado != null) {
                    modelo.addRow(reservado);
                }
                if (entero != null) {
                    modelo.addRow(entero);
                }
                if (operador != null) {
                    modelo.addRow(operador);
                }
                if (identificador != null && operador == null) {
                    modelo.addRow(identificador);
                }
                if (identificador == null && reservado == null && entero == null && operador == null) {
                    modelo.addRow(new String[]{"ERROR", prueba[i]});
                }

            }
        }

        return modelo;

    }

}
