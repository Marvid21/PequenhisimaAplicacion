package pruebas.marcelo.clima.pequenhisimaaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import pruebas.marcelo.clima.pequenhisimaaplicacion.R;

public class DetalleClimaCiudadActivity extends AppCompatActivity {

    String ciudadRecibida, temperaturaReci, sensaTermiReci, tempeMiniReci, tempeMaxiReci;
    TextView tvCiudadReci, tvTempe, tvSensa, tvTempeMin, tvTempeMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_clima_ciudad);

        tvCiudadReci = findViewById(R.id.tvCiudadReci);
        tvTempe = findViewById(R.id.tvTemperatura);
        tvSensa = findViewById(R.id.tvSensaTermi);
        tvTempeMin = findViewById(R.id.tvTempeMin);
        tvTempeMax = findViewById(R.id.tvTempeMax);

        Intent iCiudades =getIntent();

        ciudadRecibida = iCiudades.getExtras().getString("ciudad");
        temperaturaReci = iCiudades.getExtras().getString("temperatura");
        sensaTermiReci = iCiudades.getExtras().getString("sensacion");
        tempeMiniReci = iCiudades.getExtras().getString("tempe_min");
        tempeMaxiReci = iCiudades.getExtras().getString("tempe_max");

        tvCiudadReci.setText(ciudadRecibida);
        tvTempe.setText("Temperatura: "+temperaturaReci+"C°");
        tvSensa.setText("Sensación Térmica: "+sensaTermiReci+"C°");
        tvTempeMin.setText("Temperatura Mínima: "+tempeMiniReci+"C°");
        tvTempeMax.setText("Temperatura Máxima: "+tempeMaxiReci+"C°");

    }
}