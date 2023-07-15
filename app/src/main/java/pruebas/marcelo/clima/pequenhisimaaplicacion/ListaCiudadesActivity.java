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

    ArrayList<String> listDatos;
    RecyclerView rvCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ciudades);

        rvCities = findViewById(R.id.rvCities);
        rvCities.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        listDatos = new ArrayList<String>();

//        for(int i=0; i<=50; i++){
//            listDatos.add("Dato #"+i+" ");
//        }
        listDatos.add("Asunción");
        listDatos.add("Ciudad del Este");
        listDatos.add("Encarnación");
        listDatos.add("Loma Plata");
        listDatos.add("Villa Rica");

        AdapterRecycler adapterRecycler = new AdapterRecycler(listDatos);

        adapterRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Presionaste: "+listDatos.get(rvCities.getChildAdapterPosition(v)))
                Intent iCiudad = new Intent(getBaseContext(), DetalleClimaCiudadActivity.class);
                startActivity(iCiudad);
            }
        });

        rvCities.setAdapter(adapterRecycler);
    }
}