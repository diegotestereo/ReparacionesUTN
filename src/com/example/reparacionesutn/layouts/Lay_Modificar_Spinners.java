package com.example.reparacionesutn.layouts;

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

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;
import com.example.reparacionesutn.objetos.FallasClase;
import com.example.reparacionesutn.objetos.ModelosClase;
import com.example.reparacionesutn.objetos.VersionesClase;

public class Lay_Modificar_Spinners extends Activity
{
	//ModelosClase oModelo;
	Spinner spin_modelos,spin_versiones,spin_fallas;
	Button btn_insertar_m,btn_eliminar_m,btn_editar_m,btn_insertar_v,btn_eliminar_v,btn_editar_v,btn_insertar_f,btn_eliminar_f,btn_editar_f;
	EditText eTxt_Modelos,eTxt_Versiones,eTxt_Fallas;
	ArrayAdapter<String> adaptadorModelos,adaptadorFallas,adaptadorVersiones;
	SQLHelperAdaptador dao;
	ModelosClase oModelo;
	FallasClase oFalla;
	VersionesClase oVersion;
	//ModelosClase oModelo;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_modificar_spinners);
		levantarXML();
		dao =   new SQLHelperAdaptador(getApplicationContext(),getString(R.string.DataBase), null, 1);
		setAdaptadores();
		botones();
	
		oModelo =new ModelosClase();
		oVersion =new VersionesClase();
		oFalla =new FallasClase();
		
		spinners();
		
		
		
	}

	private void spinners() {
		spin_modelos.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				eTxt_Modelos.setText("dato "+position);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		spin_versiones.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				eTxt_Versiones.setText("dato "+position);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		spin_fallas.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				eTxt_Fallas.setText("dato "+position);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	private void setAdaptadores() {
		adaptadorModelos=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dao.recuperarNombresModelos());
		adaptadorVersiones=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dao.recuperarNombresVersiones());
		adaptadorFallas=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dao.recuperarNombresFallas());
		spin_fallas.setAdapter(adaptadorFallas);
		spin_modelos.setAdapter(adaptadorModelos);
		spin_versiones.setAdapter(adaptadorVersiones);
	}

	private void botones() {
		btn_insertar_m.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dao.insertarModelo(eTxt_Modelos.getText().toString());
				setAdaptadores();
				eTxt_Modelos.setText("");
				
			}
		});
		
		btn_editar_m.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				
			}
		});

		btn_eliminar_m.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//oModelo.setNom_modelo(spin_modelos.getDisplay().toString());
				//eTxt_Modelos.setText(spin_modelos.getDisplay().toString());
				dao.borrarNombreModelo(oModelo);
		
			}
		});
		
		btn_insertar_v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dao.insertarVersion(eTxt_Versiones.getText().toString());
				setAdaptadores();
				spin_versiones.setAdapter(adaptadorVersiones);
				eTxt_Versiones.setText("");
				
				
			}
		});
		
		btn_editar_v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				
			}
		});

		btn_eliminar_v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		
		
			}
		});
		
		btn_insertar_f.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dao.insertarFalla(eTxt_Fallas.getText().toString());
				setAdaptadores();
				spin_fallas.setAdapter(adaptadorFallas);
				eTxt_Fallas.setText("");
				
			}
		});
		
		btn_editar_f.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				
			}
		});

		btn_eliminar_f.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		
		
			}
		});
	}

	private void levantarXML()
	{
		spin_modelos = (Spinner) findViewById(R.id.spin_modelos);
		spin_versiones = (Spinner) findViewById(R.id.spin_versiones);
		spin_fallas = (Spinner) findViewById(R.id.spin_fallas);
		
		btn_editar_m=(Button) findViewById(R.id.btn_Editar_lay_modelos);
		btn_eliminar_m=(Button) findViewById(R.id.btn_Eliminar_lay_modelos);
		btn_insertar_m=(Button) findViewById(R.id.btn_Insertar_lay_modelos);
		
		btn_editar_v=(Button) findViewById(R.id.btn_Editar_lay_versiones);
		btn_eliminar_v=(Button) findViewById(R.id.btn_Eliminar_lay_versiones);
		btn_insertar_v=(Button) findViewById(R.id.btn_Insertar_lay_versiones);
		
		btn_editar_f=(Button) findViewById(R.id.btn_Editar_lay_fallas);
		btn_eliminar_f=(Button) findViewById(R.id.btn_Eliminar_lay_fallas);
		btn_insertar_f=(Button) findViewById(R.id.btn_Insertar_lay_fallas);
		
		eTxt_Modelos =(EditText) findViewById(R.id.eTxt_Modelos);
		eTxt_Versiones=(EditText) findViewById(R.id.eTxt_Versiones);
		eTxt_Fallas =(EditText) findViewById(R.id.eTxt_Fallas);
	}
}
