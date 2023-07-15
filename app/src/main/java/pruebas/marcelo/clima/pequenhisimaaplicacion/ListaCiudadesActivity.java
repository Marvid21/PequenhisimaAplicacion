package pruebas.marcelo.clima.pequenhisimaaplicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import pruebas.marcelo.clima.pequenhisimaaplicacion.R;

public class ListaCiudadesActivity extends AppCompatActivity {

    ArrayList<CiudadesVo> listDatos;
    RecyclerView rvCities;
    String ciudadSeleccionada;

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
                Intent iCiudad = new Intent(getBaseContext(), DetalleClimaCiudadActivity.class);
                iCiudad.putExtra("ciudad", ciudadSeleccionada);
                startActivity(iCiudad);
            }
        });

        rvCities.setAdapter(adapterRecycler);
    }

    private void llenarCiudades() {
        listDatos.add(new CiudadesVo("Asunción"));
        listDatos.add(new CiudadesVo("Ciudad del Este"));
        listDatos.add(new CiudadesVo("Encarnación"));
        listDatos.add(new CiudadesVo("Loma Plata"));
        listDatos.add(new CiudadesVo("Villa Rica"));
    }
}