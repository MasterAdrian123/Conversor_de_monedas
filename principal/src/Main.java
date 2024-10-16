import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        boolean seguir = true;

        System.out.println("***************    Bienvenido a mi conversor de monedas    ***************\n");
        while (seguir){
            System.out.println(mostrarOpciones("menu principal"));
            seguir = seleccionDelUsuario(entrada());
        }
    }
    public static String entrada(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static String mostrarOpciones(String cualOpcion){
        String salida="";
        switch (cualOpcion){
            case "menu principal":
                salida=
                        "digite el numero de alguna de las siguientes opciones:\n" +
                        "1 -> Quiero conocer el valor actual de una moneda con respecto a otras monedas.\n" +
                        "2 -> Quiero saber cuanto vale una moneda al cambio de otra moneda.\n" +
                        "3 -> Quiero saber cuanto es cierta cantidad al cambio en otra moneda.\n" +
                        "4 -> Salir del programa\n";
                break;
            case "lista de divisas":
                salida="digite el numero de la opcion que quiera usar de referencia:\n"+
                    "1 -> Dolar estadounidense (USD)\n" +
                    "2 -> Euro (EUR)\n" +
                    "3 -> Yuan chino (CNY)\n" +
                    "4 -> Peso colombiano (COP)\n"+
                    "5 -> Peso argentino (ARS)\n"+
                    "6 -> Real brasileño (BRL)\n"+
                    "7 -> Yen japones (JPY)\n"+
                    "8 -> Rublo ruso (RUB)\n"+
                    "9 -> Libra británica (GBP)\n"+
                    "10 -> Rupia india (INR)\n";
                break;
            default:
                salida="no se seleccionó ningun menu de opciones";
        }
        return salida;
    }

    public static boolean seleccionDelUsuario(String seleccion){ // opcion del menu principal
        boolean seguir=true;
        switch (seleccion) {
            case "1":
                System.out.println(mostrarOpciones("lista de divisas"));
                String divisaSeleccionada = divisaSeleccionada(entrada());
                mostrarDivisas(divisaSeleccionada);
                break;

            case "2":
                System.out.println("\n\nfavor digite la moneda base\n"+mostrarOpciones("lista de divisas"));
                String referencia1= divisaSeleccionada(entrada());

                System.out.println("\n\nAhora favor digite la moneda objetivo\n"+mostrarOpciones("lista de divisas"));
                String referencia2= divisaSeleccionada(entrada());

                conversorMonedas(referencia1,referencia2);
                break;

            case "3":
                System.out.println("\n\nfavor digite la moneda base\n"+mostrarOpciones("lista de divisas"));
                String base= divisaSeleccionada(entrada());

                System.out.println("ahora porfavor digite la cantidad a convertir");
                String cantidad = verificarCantidadValida(entrada());

                System.out.println("\n\nAhora favor digite la moneda objetivo\n"+mostrarOpciones("lista de divisas"));
                String objetivo= divisaSeleccionada(entrada());

                conversorCantidad(base,objetivo, cantidad);
                break;

            case "4":
                System.out.println("gracias por probar mi programa, vuelva pronto.");
                seguir = false;
                break;

            default:
                System.out.println("\nFavor digite solo el numero de las opciones proporcionadas.\n");

        }
        return seguir;
    }

    public static String divisaSeleccionada(String divisa){
        String salida="";
        switch (divisa){
            case "1":
                salida= "USD";
                break;
            case "2":
                salida="EUR";
                break;
            case "3":
                salida="CNY";
                break;
            case "4":
                salida="COP";
                break;
            case "5":
                salida="ARS";
                break;
            case "6":
                salida="BRL";
                break;
            case "7":
                salida="JPY";
                break;
            case "8":
                salida="RUB";
                break;
            case "9":
                salida="GBP";
                break;
            case "10":
                salida="INR";
                break;
            default:
                System.out.println("NO DIGITÓ UN NUMERO VALIDO, POR DEFECTO SERÁ (USD)");
                salida="USD";
        };
        return salida;
    }

    public static void mostrarDivisas(String divisa){
        try{
            Conexion conexion = new Conexion();
            System.out.println(conexion.informacionDivisas(divisa));
        }catch (Exception e){
            System.out.println("error encontrado en mostrarDivisas:\n"+e);
        }
    }

    public static void conversorMonedas(String divisa1, String divisa2){
        try{
            Conexion conexion = new Conexion();
            String json = conexion.compararDivisas(divisa1,divisa2);
            System.out.println(json);
        }catch (Exception e){
            System.out.println("error encontrado en conversorMonedas:\n"+e);
        }
    }

    public static void conversorCantidad(String divisaBase, String divisaObjetivo, String cantidad){
        try{
            Conexion conexion = new Conexion();
            String json = conexion.compararDivisasConCantidad(divisaBase,divisaObjetivo,cantidad);
            System.out.println(json);
        }catch (Exception e){
            System.out.println("error encontrado en conversorCantidad:\n"+e);
        }
    }

    public static String verificarCantidadValida(String usuario_entrada){
        String respuesta ="5";
        try{
            double usuario_respuesta = Double.parseDouble(usuario_entrada);
            if (usuario_respuesta>=1){
                respuesta = usuario_entrada;
            }
        } catch (NumberFormatException e) {
            System.out.println("NO DIGITÓ UNA CANTIDAD VALIDA, SE LE ASIGNÓ LA CANTIDAD DE 5 POR DEFECTO");
        }
        return respuesta;
    }
}