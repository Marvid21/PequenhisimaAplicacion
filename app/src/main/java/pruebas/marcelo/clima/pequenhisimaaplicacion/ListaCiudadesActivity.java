package pruebas.marcelo.clima.pequenhisimaaplicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    EditText etSearch;
    String ciudadSeleccionada, ciudadSelecLimpia;
    Double tempe, sensaTer, tempeMin, tempeMax;
    Integer temperatura, sensaTermi, tempeMini, tempeMaxi;
    final String countryCode = ",PY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ciudades);

        listDatos = new ArrayList<>();
        etSearch = findViewById(R.id.etSearch);
        rvCities = findViewById(R.id.rvCities);
        rvCities.setLayoutManager(new LinearLayoutManager(this));


        llenarCiudades();

        AdapterRecycler adapterRecycler = new AdapterRecycler(listDatos);

        adapterRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ciudadSeleccionada = listDatos.get(rvCities.getChildAdapterPosition(v)).getCiudad();
                ciudadSelecLimpia = limpiaString(ciudadSeleccionada)+countryCode;
                getWeatherData(ciudadSelecLimpia);
            }
        });

        rvCities.setAdapter(adapterRecycler);

        //Ordenamos alfabeticamente la lista
        Collections.sort(listDatos, CiudadesVoAZComparator);
        adapterRecycler.notifyDataSetChanged();

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

        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAssets());
            for(int i=0; i<jsonArray.length(); i++){
                try {
                    JSONObject objName = jsonArray.getJSONObject(i);
                    if(objName.getString("country").contains("PY")){
                        listDatos.add(new CiudadesVo(objName.getString("name")));
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }
    public String loadJSONFromAssets(){
        String json = null;
        try {
            InputStream is = getAssets().open("citylist.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static Comparator<CiudadesVo> CiudadesVoAZComparator = new Comparator<CiudadesVo>() {
        @Override
        public int compare(CiudadesVo o1, CiudadesVo o2) {
            return o1.getCiudad().compareTo(o2.getCiudad());
        }
    };
}