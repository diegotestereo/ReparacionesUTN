package com.example.reparacionesutn.layouts;

import java.util.ArrayList;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;
import com.example.reparacionesutn.objetos.ReparacionesClase;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class Lay_buscar extends Activity {
	TextView num_reparaciones;
	 ListView lista_equipos;
	 ArrayList<ReparacionesClase> listadoReparaciones;
   	 SQLHelperAdaptador dao ;
   	 
   	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_buscar);
		levantarXML();
        dao = new SQLHelperAdaptador(this, getString(R.string.DataBase), null, 1);
        
	}  

	private void levantarXML() {
	lista_equipos =(ListView) findViewById(R.id.listView1);
	num_reparaciones =(TextView) findViewById(R.id.tv_num_reparaciones);
	}

}
