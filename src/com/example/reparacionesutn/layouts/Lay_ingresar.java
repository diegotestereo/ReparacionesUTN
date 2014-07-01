package com.example.reparacionesutn.layouts;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import android.widget.TextView;
import android.widget.Toast;

public class Lay_ingresar extends Activity{
	TextView txt_date;
	Spinner spin_modelos,spin_fallas,spin_versiones;
	EditText etxt_serial,observaciones;
	Button btn_IngresarReparacion;
	ArrayAdapter<String> adaptadorModelos,adaptadorVersiones,adaptadorFallas;
	SQLHelperAdaptador dao;
	java.util.Date date = new Date();
	java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
	String fecha = sdf.format(date);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_ingresar);
		levantarXML();
		
		Botones();
		cargaAdaptadores();
		txt_date.setText(fecha);
	}

	private void cargaAdaptadores() {
		//instancio el dao
		dao=new SQLHelperAdaptador(getApplicationContext(),getString(R.string.DataBase), null, 1);
		
		adaptadorFallas=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,dao.recuperarNombresFallas());
		adaptadorModelos=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,dao.recuperarNombresModelos());
		adaptadorVersiones=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,dao.recuperarNombresVersiones());
		
		spin_fallas.setAdapter(adaptadorFallas);
		spin_modelos.setAdapter(adaptadorModelos);
		spin_versiones.setAdapter(adaptadorVersiones);
		
	}

	private void Botones() {
		btn_IngresarReparacion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "Reparacion Serial:" + etxt_serial.getText().toString(), Toast.LENGTH_SHORT).show();finish();
		
			}
		});
	}

	private void levantarXML() {
		
		spin_modelos=(Spinner) findViewById(R.id.spin_modelos);
		spin_fallas=(Spinner) findViewById(R.id.spin_fallas);
		spin_versiones=(Spinner) findViewById(R.id.spin_versiones);
		
		etxt_serial=(EditText) findViewById(R.id.eTxt_Serial_ingresar);
		observaciones=(EditText) findViewById(R.id.etxt_observaciones);
		
		txt_date=(TextView) findViewById(R.id.txtV_Date);
			
		btn_IngresarReparacion = (Button) findViewById(R.id.btn_IngresarReparacion);
	}

}
