package com.example.reparacionesutn.layouts;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;
import com.example.reparacionesutn.objetos.ModelosClase;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class Lay_modelos extends Activity{
	ModelosClase oModelo; 
	Spinner spin_modelos;
	//ArrayAdapter<oModelo> adaptadorModelos;
	// SQLHelperAdaptador dao = new SQLHelperAdaptador(getApplicationContext(), getString(R.string.DataBase), null, 1);
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.lay_modelos);
	levantarXML();
	
	//adaptadorModelos=new ArrayAdapter<com.example.reparacionesutn.objetos.Modelos>(this,android.R.layout.simple_spinner_item,);
	//spin_modelos.setAdapter(adaptadorModelos);
	
	}

	private void levantarXML() {
		// TODO Auto-generated method stub
		spin_modelos=(Spinner) findViewById(R.id.spin_modelos);
	}
}
