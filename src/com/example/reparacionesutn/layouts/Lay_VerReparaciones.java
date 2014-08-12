package com.example.reparacionesutn.layouts;


import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.AdaptadorCustomizado;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;







import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Lay_VerReparaciones extends Activity{
	TextView num_reparaciones;
	 ListView lista_equipos;
	
  	 SQLHelperAdaptador dao ;
  	 
  	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		setContentView(R.layout.lay_buscar);
		levantarXML();
		setAdapter();
		super.onCreate(savedInstanceState);
		
		lista_equipos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
	}  

	
	
	
	
	
	@Override
	protected void onRestart() {
		// actualiza el dao para volver a mostrar los datos actualizados
		 dao = new SQLHelperAdaptador(this, getString(R.string.DataBase), null, 1);

		super.onRestart();
	}
	
		
	private void setAdapter() {
		
		//Intent intento = getIntent();
	//	int valor =intento.getExtras().getInt("serial");
		//tv_Serial.setText(String.valueOf(valor));	
		 dao = new SQLHelperAdaptador(this, getString(R.string.DataBase), null, 1);
		 num_reparaciones.setText(Integer.toString(dao.recuperarCantidadReparaciones()));
	     AdaptadorCustomizado adap= new AdaptadorCustomizado(dao.recuperarReparaciones(),getApplicationContext());
	     lista_equipos.setAdapter(adap);
	}

	

	private void levantarXML() {
	lista_equipos =(ListView) findViewById(R.id.listView1);
	num_reparaciones =(TextView) findViewById(R.id.tv_num_reparaciones);
	
	}
}
