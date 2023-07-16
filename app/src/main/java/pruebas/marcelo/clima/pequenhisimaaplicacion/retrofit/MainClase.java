package pruebas.marcelo.clima.pequenhisimaaplicacion.retrofit;

import com.google.gson.annotations.SerializedName;

public class MainClase {
    @SerializedName("main")
    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
