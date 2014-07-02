package com.example.reparacionesutn.layouts;


import java.util.Date;
import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Lay_ingresar extends Activity{
	
	
	int posSpinModelo,posSpinVersion,posSpinFalla;
	TextView txt_date,txtV_prueba,txtV_Reparacion;
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
		Spinners();
		txt_date.setText(fecha);
	}

	private void Spinners() {
		posSpinVersion= spin_versiones.getSelectedItemPosition()+1;
		posSpinModelo= spin_modelos.getSelectedItemPosition()+1;
		posSpinFalla= spin_fallas.getSelectedItemPosition()+1;
	
		spin_modelos.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				posSpinModelo= position+1;
			}
	@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		spin_fallas.setOnItemSelectedListener(new OnItemSelectedListener() {
		
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				posSpinFalla = position +1;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
		}
		});
		
		spin_versiones.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				posSpinVersion=position+1;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
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
		
		txtV_Reparacion.setText(Integer.toString(dao.recuperarCantidadReparaciones()+1));
		
	}

	private void Botones() {
			
		
		btn_IngresarReparacion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!etxt_serial.getText().toString().equals("")){
				if (!observaciones.getText().toString().equals("")){	
					
			dao.insertarReparacion(fecha, Integer.parseInt(etxt_serial.getText().toString()), posSpinModelo,posSpinVersion, posSpinFalla, observaciones.getText().toString());	
			txtV_prueba.setText("fecha:"+fecha+" Serial:"+ Integer.parseInt(etxt_serial.getText().toString())+"posicion modelo:"+posSpinModelo+" Posicion Falla: "+posSpinFalla +" Posicion Version: "+posSpinVersion+" observacion:"+observaciones.getText().toString());
			Toast.makeText(getApplicationContext(), "Reparacion N° "+dao.recuperarCantidadReparaciones()+" Ingresada !!!", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplicationContext(), "Ingrese Observaciones", Toast.LENGTH_SHORT).show();
					}
				
				}
				else{
					Toast.makeText(getApplicationContext(), "Ingrese Numero de Serie", Toast.LENGTH_SHORT).show();
					}
				
				etxt_serial.setText("");
				observaciones.setText("");
				spin_fallas.setSelection(0);
				spin_modelos.setSelection(0);
				spin_versiones.setSelection(0);
				txtV_Reparacion.setText(Integer.toString(dao.recuperarCantidadReparaciones()+1));
				
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
		txtV_Reparacion=(TextView) findViewById(R.id.txtV_Reparacion);
		btn_IngresarReparacion = (Button) findViewById(R.id.btn_IngresarReparacion);
	}

}
