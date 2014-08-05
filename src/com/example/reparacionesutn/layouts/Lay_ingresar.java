package com.example.reparacionesutn.layouts;


import java.util.Date;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;

import android.R.drawable;
import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Lay_ingresar extends Activity{
	
	
	int posSpinModelo,posSpinVersion,posSpinFalla;
	int hs24; 
	
	private CheckBox CkBox_24hs;
	private TextView txt_date,txtV_Reparacion;
	private Spinner spin_modelos,spin_fallas,spin_versiones;
	private EditText etxt_serial,observaciones;
	private Button btn_IngresarReparacion;
	private ArrayAdapter<String> adaptadorModelos,adaptadorVersiones,adaptadorFallas;
	private SQLHelperAdaptador dao;
	private java.util.Date date = new Date();
	private java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
	private String fecha = sdf.format(date);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_ingresar);
		levantarXML();
		setcheck();
		Botones();
		cargaAdaptadores();
		Spinners();
		txt_date.setText(fecha);
		
	}

	private void setcheck() {
		
		CkBox_24hs.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (isChecked){
					hs24=1;
					
						} 
				else{
					hs24=0;
					
					}
				
			}
		});
		
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
	   adaptadorFallas=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text,dao.recuperarNombresFallas());
		adaptadorModelos=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text,dao.recuperarNombresModelos());
		adaptadorVersiones=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text,dao.recuperarNombresVersiones());
		txtV_Reparacion.setText(String.valueOf(dao.recuperarCantidadReparaciones()+1));

		spin_fallas.setAdapter(adaptadorFallas);
		spin_modelos.setAdapter(adaptadorModelos);
		spin_versiones.setAdapter(adaptadorVersiones);
	
	}

	private void Botones() {
			
		
		btn_IngresarReparacion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!(etxt_serial.getText().toString().equals(""))){
				if (!(observaciones.getText().toString().equals(""))){	
					
			dao.insertarReparacion(fecha, Integer.parseInt(etxt_serial.getText().toString()), posSpinModelo,posSpinVersion, posSpinFalla, observaciones.getText().toString(),hs24);	
		
			etxt_serial.setText("");
			observaciones.setText("");
			txtV_Reparacion.setText(String.valueOf(dao.recuperarCantidadReparaciones()+1));

			Toast.makeText(getApplicationContext(), "Reparacion Nº "+dao.recuperarCantidadReparaciones()+" Ingresada !!!", Toast.LENGTH_SHORT).show();
			
				}else{
					Toast.makeText(getApplicationContext(), "Ingrese Observaciones", Toast.LENGTH_SHORT).show();
					}
				
				}
				else{
					Toast.makeText(getApplicationContext(), "Ingrese Numero de Serie", Toast.LENGTH_SHORT).show();
					}
				
				spin_fallas.setSelection(0);
				spin_modelos.setSelection(0);
				spin_versiones.setSelection(0);	
				}

		});
	}
	
	private void levantarXML() {
		hs24=0;
		spin_modelos=(Spinner) findViewById(R.id.spin_modelos);
		spin_fallas=(Spinner) findViewById(R.id.spin_fallas);
		spin_versiones=(Spinner) findViewById(R.id.spin_versiones);
		CkBox_24hs =(CheckBox) findViewById(R.id.cbox_24hs);
		etxt_serial=(EditText) findViewById(R.id.eTxt_Serial_ingresar);
		observaciones=(EditText) findViewById(R.id.etxt_observaciones);
		
		
		txt_date=(TextView) findViewById(R.id.txtV_Date);
		txtV_Reparacion=(TextView) findViewById(R.id.txtV_Reparacion);
		btn_IngresarReparacion = (Button) findViewById(R.id.btn_IngresarReparacion);
	}

}
