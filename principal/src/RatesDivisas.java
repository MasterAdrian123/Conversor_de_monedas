import java.util.Map;

public class RatesDivisas {
    private Map<String,String> listaDivisas;

    public Map<String, String> getListaDivisas() {
        return listaDivisas;
    }

    public void setListaDivisas(Map<String, String> divisas) {
        this.listaDivisas = divisas;
    }

    public String getDivisaIndividual(String divisa){
        return listaDivisas.get(divisa);
    }
}
