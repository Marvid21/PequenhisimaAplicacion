package pruebas.marcelo.clima.pequenhisimaaplicacion.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("weather?appid=&units=metric")
    Call<MainClase> getWeatherData(@Query(value = "q", encoded = true) String city);
}
