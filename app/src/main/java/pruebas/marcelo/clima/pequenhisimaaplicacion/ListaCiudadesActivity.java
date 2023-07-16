package pruebas.marcelo.clima.pequenhisimaaplicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;

import pruebas.marcelo.clima.pequenhisimaaplicacion.R;
import pruebas.marcelo.clima.pequenhisimaaplicacion.retrofit.ApiClient;
import pruebas.marcelo.clima.pequenhisimaaplicacion.retrofit.ApiInterface;
import pruebas.marcelo.clima.pequenhisimaaplicacion.retrofit.Main;
import pruebas.marcelo.clima.pequenhisimaaplicacion.retrofit.MainClase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaCiudadesActivity extends AppCompatActivity {

    ArrayList<CiudadesVo> listDatos;
    RecyclerView rvCities;
    String ciudadSeleccionada, ciudadSelecLimpia;
    Double tempe, sensaTer, tempeMin, tempeMax;
    Integer temperatura, sensaTermi, tempeMini, tempeMaxi;
    final String countryCode = ",PY";
    String url = "https://api.openweathermap.org/data/2.5/weather?q=Ciudad+del+Este,PY&appid=e38a45ab04f410c8b0956c5aeb3d8ab9";
    String url2= "https://api.openweathermap.org/data/2.5/weather?appid=e38a45ab04f410c8b0956c5aeb3d8ab9&q=Loma%2BPlata%2CPY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ciudades);

        listDatos = new ArrayList<>();
        rvCities = findViewById(R.id.rvCities);
        rvCities.setLayoutManager(new LinearLayoutManager(this));

        llenarCiudades();

        AdapterRecycler adapterRecycler = new AdapterRecycler(listDatos);

        adapterRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Presionaste: "+listDatos.get(rvCities.getChildAdapterPosition(v)).getCiudad(), Toast.LENGTH_SHORT).show();
                ciudadSeleccionada = listDatos.get(rvCities.getChildAdapterPosition(v)).getCiudad();
                Log.d("CIUDAD", ciudadSeleccionada);
                ciudadSelecLimpia = limpiaString(ciudadSeleccionada)+countryCode;
                Log.d("CI_FOR", ciudadSelecLimpia);
                getWeatherData(ciudadSelecLimpia);
            }
        });

        rvCities.setAdapter(adapterRecycler);
    }

    private void getWeatherData(String city){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<MainClase> mainClaseCall = apiInterface.getWeatherData(city);

        mainClaseCall.enqueue(new Callback<MainClase>() {
            @Override
            public void onResponse(Call<MainClase> call, Response<MainClase> response) {
                MainClase myData = response.body();
                Main main = myData.getMain();
                tempe = main.getTemp();
                temperatura = (int)(tempe-0);
                sensaTer = main.getFeelsLike();
                sensaTermi = (int)(sensaTer-0);
                tempeMin = main.getTempMin();
                tempeMini = (int)(tempeMin-0);
                tempeMax = main.getTempMax();
                tempeMaxi = (int)(tempeMax-0);
                Log.d("CODE", response.code()+"");
                Intent iCiudad = new Intent(getBaseContext(), DetalleClimaCiudadActivity.class);
                iCiudad.putExtra("ciudad", ciudadSeleccionada);
                iCiudad.putExtra("temperatura", temperatura.toString());
                iCiudad.putExtra("sensacion", sensaTermi.toString());
                iCiudad.putExtra("tempe_min", tempeMini.toString());
                iCiudad.putExtra("tempe_max", tempeMaxi.toString());
                startActivity(iCiudad);
            }

            @Override
            public void onFailure(Call<MainClase> call, Throwable t) {

            }
        });
    }

    public static String limpiaString(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        s = s.replaceAll(" ", "+");
        return s;
    }

    private void llenarCiudades() {
        listDatos.add(new CiudadesVo("Asunción"));
        listDatos.add(new CiudadesVo("Ciudad del Este"));
        listDatos.add(new CiudadesVo("Encarnación"));
        listDatos.add(new CiudadesVo("Loma Plata"));
        listDatos.add(new CiudadesVo("Villarrica"));
    }
}