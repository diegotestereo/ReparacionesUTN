package com.example.reparacionesutn.layouts;


import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Lay_ingresar extends Activity{
	
	Spinner spin_modelos,spin_fallas,spin_versiones;
	EditText etxt_serial,observaciones;
	Button btn_IngresarReparacion;
	ArrayAdapter<String> adaptadorModelos,adaptadorVersiones,adaptadorFallas;
	SQLHelperAdaptador dao;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_ingresar);
		levantarXML();
		Botones();
		cargaAdaptadores();
	}

	private void cargaAdaptadores() {
		//instancio el dao
		dao=new SQLHelperAdaptador(getApplicationContext(),getString(R.string.DataBase), null, 1);
		
		adaptadorFallas=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,dao.recuperarnombresFallas());
		adaptadorModelos=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,dao.recuperarNombresModelos());
		adaptadorVersiones=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,dao.recuperarnombresVersiones());
		
		spin_fallas.setAdapter(adaptadorFallas);
		spin_modelos.setAdapter(adaptadorModelos);
		spin_versiones.setAdapter(adaptadorVersiones);
		
	}

	private void Botones() {
		btn_IngresarReparacion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "Reparacion Serial:", Toast.LENGTH_SHORT).show();finish();
				
			}
		});
	}

	private void levantarXML() {
		spin_modelos=(Spinner) findViewById(R.id.spin_modelos);
		spin_fallas=(Spinner) findViewById(R.id.spin_fallas);
		spin_versiones=(Spinner) findViewById(R.id.spin_versiones);
		etxt_serial=(EditText) findViewById(R.id.eTxt_Serial_ingresar);
		observaciones=(EditText) findViewById(R.id.etxt_observaciones);
		btn_IngresarReparacion = (Button) findViewById(R.id.btn_IngresarReparacion);
	}

}
