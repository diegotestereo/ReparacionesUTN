package com.example.reparacionesutn.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;
import com.example.reparacionesutn.objetos.ModelosClase;

public class Lay_modelos extends Activity
{
	//ModelosClase oModelo;
	Spinner spin_modelos;
	Button btn_insertar,btn_eliminar,btn_editar;
	EditText eTxt_campo;
	ArrayAdapter<String> adaptadorModelos;
	SQLHelperAdaptador dao;
	ModelosClase oModelo;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_modelos);
		levantarXML();
		dao =   new SQLHelperAdaptador(getApplicationContext(),getString(R.string.DataBase), null, 1);
		adaptadorModelos=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dao.recuperarNombresModelos());
		spin_modelos.setAdapter(adaptadorModelos);
		
		botones();
		
		

	}

	private void botones() {
		btn_insertar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dao.insertarModelo(eTxt_campo.getText().toString());
				
			}
		});
		
		btn_editar.setOnClickListener(new OnClickListener() {
			
			
			
			@Override
			public void onClick(View v) {
				oModelo.setIn_modelo(spin_modelos.getAdapter());
				dao.borrarNombreModelo();
				
			}
		});

		btn_eliminar.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		
			}
		});
		
	}

	private void levantarXML()
	{
		spin_modelos = (Spinner) findViewById(R.id.spin_modelos);
		btn_editar=(Button) findViewById(R.id.btn_Editar_lay_modelos);
		btn_eliminar=(Button) findViewById(R.id.btn_Eliminar_lay_modelos);
		btn_insertar=(Button) findViewById(R.id.btn_Insertar_lay_modelos);
		eTxt_campo =(EditText) findViewById(R.id.eTxt_campo_LyModelos);
	}
}
