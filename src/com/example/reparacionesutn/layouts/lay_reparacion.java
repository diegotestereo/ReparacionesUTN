package com.example.reparacionesutn.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.reparacionesutn.R;

public class lay_reparacion extends Activity
{

	CheckBox hs24;
	TextView reparacion, fecha, serial, version, modelo, falla, observacion;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_reparacion);
		levantarXML();
	}

	private void levantarXML()
	{
		reparacion = (TextView) findViewById(R.id.txtV_Reparacion_REP);
		fecha = (TextView) findViewById(R.id.txtV_fecha_REP);

		serial = (TextView) findViewById(R.id.txtV_Serial_REP);
		version = (TextView) findViewById(R.id.txtV_version_REP);
		modelo = (TextView) findViewById(R.id.txtV_modelo_REP);
		falla = (TextView) findViewById(R.id.txtV_falla_REP);
		observacion = (TextView) findViewById(R.id.txtV_observacion_REP);
	}

}
