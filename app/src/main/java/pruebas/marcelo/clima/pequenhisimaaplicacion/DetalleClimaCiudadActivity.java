package pruebas.marcelo.clima.pequenhisimaaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import pruebas.marcelo.clima.pequenhisimaaplicacion.R;

public class DetalleClimaCiudadActivity extends AppCompatActivity {

    String ciudadRecibida, temperaturaReci, sensaTermiReci, tempeMiniReci, tempeMaxiReci;
    TextView tvCiudadReci, tvTempe, tvSensa, tvTempeMin, tvTempeMax;
    Double aux1, aux2, aux3, aux4;
    Spinner spGrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_clima_ciudad);

        tvCiudadReci = findViewById(R.id.tvCiudadReci);
        tvTempe = findViewById(R.id.tvTemperatura);
        tvSensa = findViewById(R.id.tvSensaTermi);
        tvTempeMin = findViewById(R.id.tvTempeMin);
        tvTempeMax = findViewById(R.id.tvTempeMax);
        spGrados = findViewById(R.id.spConver);

        datosRecibidos();
        spinnerSelect();
    }

    private void datosRecibidos(){
        Intent iCiudades =getIntent();

        ciudadRecibida = iCiudades.getExtras().getString("ciudad");
        temperaturaReci = iCiudades.getExtras().getString("temperatura");
        sensaTermiReci = iCiudades.getExtras().getString("sensacion");
        tempeMiniReci = iCiudades.getExtras().getString("tempe_min");
        tempeMaxiReci = iCiudades.getExtras().getString("tempe_max");

        tvCiudadReci.setText(ciudadRecibida);
    }

    private void spinnerSelect(){
        ArrayAdapter<CharSequence> spiAdapter = ArrayAdapter.createFromResource(this,
                R.array.grados, android.R.layout.simple_spinner_item);
        spiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGrados.setAdapter(spiAdapter);

        spGrados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0://CELSIUS
                        tvTempe.setText("Temperatura: "+temperaturaReci+"°C");
                        tvSensa.setText("Sensación Térmica: "+sensaTermiReci+"°C");
                        tvTempeMin.setText("Temperatura Mínima: "+tempeMiniReci+"°C");
                        tvTempeMax.setText("Temperatura Máxima: "+tempeMaxiReci+"°C");
                        break;
                    case 1: //KELVIN
                        aux1 = Integer.parseInt(temperaturaReci)+273.15;
                        aux2 = Integer.parseInt(sensaTermiReci)+273.15;
                        aux3 = Integer.parseInt(tempeMiniReci)+273.15;
                        aux4 = Integer.parseInt(tempeMaxiReci)+273.15;
                        tvTempe.setText("Temperatura: "+aux1+" K");
                        tvSensa.setText("Sensación Térmica: "+aux2+" K");
                        tvTempeMin.setText("Temperatura Mínima: "+aux3+" K");
                        tvTempeMax.setText("Temperatura Máxima: "+aux4+" K");
                        break;
                    case 2: //FAHRENHEIT
                        aux1 = Integer.parseInt(temperaturaReci)*1.8+32;
                        aux2 = Integer.parseInt(sensaTermiReci)*1.8+32;
                        aux3 = Integer.parseInt(tempeMiniReci)*1.8+32;
                        aux4 = Integer.parseInt(tempeMaxiReci)*1.8+32;
                        tvTempe.setText("Temperatura: "+aux1+"°F");
                        tvSensa.setText("Sensación Térmica: "+aux2+"°F");
                        tvTempeMin.setText("Temperatura Mínima: "+aux3+"°F");
                        tvTempeMax.setText("Temperatura Máxima: "+aux4+"°F");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}