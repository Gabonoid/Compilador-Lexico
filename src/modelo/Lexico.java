package modelo;

public class Lexico {

    private static String[] reservadas = new String[]{
        "int",
        "double",
        "long",
        "float",
        "chart",
        "estruct",
        "false",
        "true",
        "if",
        "for"};

    public static String[] evaluarReservado(String posibleId) {
        String id = posibleId.toLowerCase();
        String[] token = null;

        for (int i = 0; i < reservadas.length; i++) {
            if (id.equals(reservadas[i])) {
                token = new String[]{reservadas[i].toUpperCase(), id};
            }
        }
        return token;
    }

    public static String[] evaluarEntero(String posibleId) {
        String[] token = null;
        try {
            int entero = Integer.valueOf(posibleId);
            token = new String[]{"ENTERO", entero + ""};

        } catch (Exception e) {
        }
        return token;
    }

    public static String[] evaluarOperador(String posibleOperador) {
        String[] token = null;

        if (posibleOperador.equalsIgnoreCase("+")) {
            token = new String[]{"SUMA", posibleOperador};

        } else if (posibleOperador.equalsIgnoreCase("-")) {
            token = new String[]{"RESTA", posibleOperador};

        } else if (posibleOperador.equalsIgnoreCase("*")) {
            token = new String[]{"PRODUCTO", posibleOperador};

        } else if (posibleOperador.equalsIgnoreCase("/")) {
            token = new String[]{"DIVISION", posibleOperador};

        } else if (posibleOperador.equalsIgnoreCase("=")) {
            token = new String[]{"ASIGNACION", posibleOperador};

        } else if (posibleOperador.equalsIgnoreCase(",")) {
            token = new String[]{"COMA", posibleOperador};

        } else if (posibleOperador.equalsIgnoreCase("(")) {
            token = new String[]{"PARENTESIS_IN", posibleOperador};

        } else if (posibleOperador.equalsIgnoreCase(")")) {
            token = new String[]{"PARENTESIS_FIN", posibleOperador};

        } else if (posibleOperador.equalsIgnoreCase(";")) {
            token = new String[]{"PUNTOCOMA", posibleOperador};
        }

        return token;
    }

    public static String[] evaluarIdentidicador(String posibleIdentificador) {
        String[] token = null;
        char primeraLetra = posibleIdentificador.charAt(0);
        int letranumero = Character.getNumericValue(primeraLetra);
        System.out.println(letranumero + "\t=>\t" + primeraLetra);
        if (!Character.isDigit(primeraLetra)/*
                || (letranumero >= 97 && letranumero <= 122)
                || (letranumero >= 65 && letranumero <= 90)*/) {
            if (primeraLetra == '_'
                    || letranumero != -1) {
                token = new String[]{"IDENTIFICADOR", posibleIdentificador};
            }
        }
        return token;
    }

}
