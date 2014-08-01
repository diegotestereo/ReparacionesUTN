package com.example.reparacionesutn.layouts;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.AdaptadorCustomizado;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class Lay_buscar extends Activity {
	TextView num_reparaciones,tv_Serial;
	 ListView lista_equipos;
	
   	 SQLHelperAdaptador dao ;
   	 
   	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		setContentView(R.layout.lay_buscar);
		levantarXML();
		setAdapter();
		super.onCreate(savedInstanceState);
	}  

	private void setAdapter() {
		
		Intent intento = getIntent();
		int valor =intento.getExtras().getInt("serial");
		tv_Serial.setText(String.valueOf(valor));	
		 dao = new SQLHelperAdaptador(this, getString(R.string.DataBase), null, 1);
		 num_reparaciones.setText(Integer.toString(dao.recuperarCantidadReparaciones(valor)));
	     AdaptadorCustomizado adap= new AdaptadorCustomizado(dao.recuperarReparaciones(valor),getApplicationContext());
	     lista_equipos.setAdapter(adap);
	}

	

	private void levantarXML() {
	lista_equipos =(ListView) findViewById(R.id.listView1);
	num_reparaciones =(TextView) findViewById(R.id.tv_num_reparaciones);
	tv_Serial=(TextView) findViewById(R.id.tv_Serie);
	}

}
