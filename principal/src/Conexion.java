import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conexion{

    public String informacionDivisas(String divisa){
        String salida=" ******* VALOR DE LA MONEDA SELECCIONADA FRENTE A OTRAS MONEDAS: *******\n\n";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(
                            URI.create("https://v6.exchangerate-api.com/v6/9819b42792bd26ec283eb7d4/latest/"+divisa)
                    ).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            Gson gson = new Gson();
            RatesApiJson json_divisas = gson.fromJson(response.body(), RatesApiJson.class);

            RatesDivisas lista_Rates_divisas = new RatesDivisas();
            lista_Rates_divisas.setListaDivisas(json_divisas.conversion_rates());

            String[] divisasDefault = {"USD","EUR","CNY","COP","ARS","BRL","JPY","RUB","GBP","INR"};

            for (String i : divisasDefault){
                salida += " "+i+" : "+ lista_Rates_divisas.getDivisaIndividual(i)+"\n";
            }

        }catch (Exception e){
            salida="algo salio mal\n"+e;
        }
        return salida;
    }

    public String compararDivisas(String divisaBase, String divisaObjetivo){
        String salida;

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(
                            URI.create("https://v6.exchangerate-api.com/v6/9819b42792bd26ec283eb7d4/pair/"+divisaBase+"/"+divisaObjetivo)
                    ).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            PairApiJson json_divisas_comparadas = gson.fromJson(response.body(),PairApiJson.class);

            PairDivisas divisas_comparadas = new PairDivisas();
            divisas_comparadas.setCambio_divisas(json_divisas_comparadas.conversion_rate());
            salida = "La moneda base: ("+divisaBase+") vale "+divisas_comparadas.getCambio_divisas()+" veces la moneda objetivo: ("+divisaObjetivo+")";

        }catch (Exception e){
            salida = "algo salio mal: \n"+e;
        }
        return salida;
    }

    public String compararDivisasConCantidad(String divisaBase, String divisaObjetivo, String cantidadConvertible){
        String salida;

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(
                            URI.create("https://v6.exchangerate-api.com/v6/9819b42792bd26ec283eb7d4/pair/"+divisaBase+"/"+divisaObjetivo+"/"+cantidadConvertible)
                    ).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            PairApiJson resultado_operacion_api= gson.fromJson(response.body(),PairApiJson.class);
            PairDivisas resultado_operacion = new PairDivisas();
            resultado_operacion.setResultado_operacion(resultado_operacion_api.conversion_result());
            salida = cantidadConvertible+" ("+divisaBase+") equivale a : "+resultado_operacion.getResultado_operacion()+" ("+divisaObjetivo+") ";

        }catch (Exception e){
            System.out.println("ocurrio un error:\n"+e);
            salida = "algo salio mal: \n"+e;
        }
        return salida;
    }
}
