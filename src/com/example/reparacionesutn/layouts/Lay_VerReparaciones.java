package com.example.reparacionesutn.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.AdaptadorCustomizado;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;

public class Lay_VerReparaciones extends Activity
{
	TextView num_reparaciones;
	ListView lista_equipos;

	SQLHelperAdaptador dao;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.lay_buscar);
		levantarXML();
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onResume()
	{
		setAdapter();
		super.onResume();
	}

	private void setAdapter()
	{
		// Intent intento = getIntent();
		// int valor =intento.getExtras().getInt("serial");
		// tv_Serial.setText(String.valueOf(valor));
		dao = new SQLHelperAdaptador(this, getString(R.string.DataBase), null, 1);
		num_reparaciones.setText(Integer.toString(dao.recuperarCantidadReparaciones()));
		AdaptadorCustomizado adap = new AdaptadorCustomizado(dao.recuperarReparaciones(), Lay_VerReparaciones.this);
		lista_equipos.setAdapter(adap);
	}

	private void levantarXML()
	{
		lista_equipos = (ListView) findViewById(R.id.listView1);
		num_reparaciones = (TextView) findViewById(R.id.tv_num_reparaciones);
	}
}
