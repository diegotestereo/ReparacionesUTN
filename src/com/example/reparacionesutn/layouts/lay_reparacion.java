package com.example.reparacionesutn.layouts;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;

public class lay_reparacion extends Activity
{

	CheckBox hs24;
	TextView reparacion, fecha, serial, version, modelo, falla, observacion;
	 SQLHelperAdaptador dao ;
	String  fechaV,  observacionV;
	int  versionV, modeloV, fallaV,serialV,reparacionV,hs24V,editar;
	Boolean editarBoolean;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_reparacion);
		 dao = new SQLHelperAdaptador(this, getString(R.string.DataBase), null, 1);
		levantarXML();
		importarValores();
	
	}

	
		
		
		
		//return editarBoolean;}
	
	private void importarValores() {
		
		
		Intent intento=getIntent();
		
		
		editar=intento.getExtras().getInt("editar");
		if(editar==1){
			editarBoolean=true;
		}else{
			editarBoolean=false;
		
		}		
		
		serialV=intento.getExtras().getInt("serial");
		serial.setText(String.valueOf(serialV));
		
		reparacionV=intento.getExtras().getInt("reparacion");
		reparacion.setText(String.valueOf(reparacionV));
		
		fechaV=intento.getExtras().getString("fecha");
		fecha.setText(fechaV);
		
		observacionV=intento.getExtras().getString("observacion");
		observacion.setText(String.valueOf(observacionV));
		
		versionV=intento.getExtras().getInt("version");
		version.setText(dao.recuperarNombresVersiones()[versionV]);
		
		modeloV=intento.getExtras().getInt("modelo");
		modelo.setText(dao.recuperarNombresModelos()[modeloV]);
		
		fallaV=intento.getExtras().getInt("falla");
		falla.setText(dao.recuperarNombresFallas()[fallaV]);
		
		
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
