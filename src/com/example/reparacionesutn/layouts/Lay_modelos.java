package com.example.reparacionesutn.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;
import com.example.reparacionesutn.objetos.ModelosClase;

public class Lay_modelos extends Activity
{
	ModelosClase oModelo;
	Spinner spin_modelos;

	ArrayAdapter<String> adaptadorModelos;
	SQLHelperAdaptador dao;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_modelos);
		levantarXML();
		dao =   new SQLHelperAdaptador(getApplicationContext(),getString(R.string.DataBase), null, 1);
		adaptadorModelos=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dao.recuperarNombresModelos());
		spin_modelos.setAdapter(adaptadorModelos);

	}

	private void levantarXML()
	{
		spin_modelos = (Spinner) findViewById(R.id.spin_modelos);
	}
}
