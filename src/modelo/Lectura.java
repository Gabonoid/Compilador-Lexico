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

            System.out.println(linea);

            String[] prueba = linea.split(" ");
            for (int i = 0; i < prueba.length; i++) {

                String[] identificador = Lexico.evaluarIdentificador(prueba[i]);
                String[] numero = Lexico.evaluarEntero(prueba[i]);
                String[] operador = Lexico.evaluarOperador(prueba[i]);

                if (identificador != null) {
                    modelo.addRow(identificador);
                }
                if (numero != null) {
                    modelo.addRow(numero);
                }
                if (operador != null) {
                    modelo.addRow(operador);
                }

            }
        }

        return modelo;

    }

}
