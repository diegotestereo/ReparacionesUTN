package com.example.reparacionesutn.layouts;

import org.xml.sax.Parser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.reparacionesutn.R;

public class lay_reparacion extends Activity
{

	CheckBox hs24;
	TextView reparacion, fecha, serial, version, modelo, falla, observacion;
	
	String fallaV, fechaV,  versionV, modeloV,  observacionV;
	int serialV,reparacionV,hs24V;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_reparacion);
		levantarXML();
		importarValores();
	
	}

	private void importarValores() {
		Intent intento=getIntent();
		serialV=intento.getExtras().getInt("serial");
		serial.setText(String.valueOf(serialV));
		
		reparacionV=intento.getExtras().getInt("reparacion");
		reparacion.setText(String.valueOf(reparacionV));
		
		hs24V=intento.getExtras().getInt("hs24");
		if(hs24V==1){
			hs24.setChecked(true);
			
		}else{
			hs24.setChecked(false);
		}
		
		
	}

	private void levantarXML()
	{
		reparacion = (TextView) findViewById(R.id.txtV_Reparacion_REP);
		fecha = (TextView) findViewById(R.id.txtV_fecha_REP);
		hs24=(CheckBox) findViewById(R.id.cbox_24hs_REP);
		serial = (TextView) findViewById(R.id.txtV_Serial_REP);
		version = (TextView) findViewById(R.id.txtV_version_REP);
		modelo = (TextView) findViewById(R.id.txtV_modelo_REP);
		falla = (TextView) findViewById(R.id.txtV_falla_REP);
		observacion = (TextView) findViewById(R.id.txtV_observacion_REP);
	}

}
