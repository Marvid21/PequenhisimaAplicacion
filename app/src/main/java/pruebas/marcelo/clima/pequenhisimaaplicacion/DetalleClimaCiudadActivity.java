package pruebas.marcelo.clima.pequenhisimaaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import pruebas.marcelo.clima.pequenhisimaaplicacion.R;

public class DetalleClimaCiudadActivity extends AppCompatActivity {

    String ciudadRecibida;
    TextView tvCiudadReci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_clima_ciudad);

        tvCiudadReci = findViewById(R.id.tvCiudadReci);

        Intent iCiudades =getIntent();

        ciudadRecibida = iCiudades.getExtras().getString("ciudad");

        tvCiudadReci.setText(ciudadRecibida);

    }
}