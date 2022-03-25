package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

public class Lectura {

    public static int numTokens = 0;
    public static int numErrores = 0;
    public static int numReservados = 0;
    public static int numEnteros = 0;
    public static int numOperadores = 0;
    public static int numIdentificadores = 0;

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
                    .replaceAll("[ ]+", " ")
                    .trim();

            String[] prueba = linea.split(" ");

            for (int i = 0; i < prueba.length; i++) {
                
                System.out.println(prueba[i]);

                if (prueba[i] != null) {
                    String[] reservado = Lexico.evaluarReservado(prueba[i]);
                    String[] operador = Lexico.evaluarOperador(prueba[i]);
                    String[] entero = Lexico.evaluarEntero(prueba[i]);

                    String[] identificador = (reservado == null) ? Lexico.evaluarIdentidicador(prueba[i]) : null;

                    if (reservado != null) {
                        modelo.addRow(reservado);
                        numReservados++;
                    }
                    if (entero != null) {
                        modelo.addRow(entero);
                        numEnteros++;
                    }
                    if (operador != null) {
                        modelo.addRow(operador);
                        numOperadores++;
                    }
                    if (identificador != null && operador == null) {
                        modelo.addRow(identificador);
                        numIdentificadores++;
                    }
                    if (identificador == null && reservado == null && entero == null && operador == null) {
                        modelo.addRow(new String[]{"ERROR", prueba[i]});
                        numErrores++;
                    }
                }

            }
        }

        numTokens = numReservados + numEnteros + numOperadores + numIdentificadores;

        return modelo;
    }
}
