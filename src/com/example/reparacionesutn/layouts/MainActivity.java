package com.example.reparacionesutn.layouts;



import java.util.ArrayList;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;
import com.example.reparacionesutn.objetos.FallasClase;
import com.example.reparacionesutn.objetos.ModelosClase;
import com.example.reparacionesutn.objetos.ReparacionesClase;
import com.example.reparacionesutn.objetos.VersionesClase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
 
	Button btn_Reparacion,btn_Modificar_spin,btn_buscarEquipos;
	EditText eTxt_Serial;
	TextView txtV_Observaciones,txtV_Falla,txtV_Fecha,txtV_Serial,txtV_nReparaciones; 
	///OBJETOS
	ModelosClase oModelo;
   	FallasClase oFalla;
   	VersionesClase oVersion;
   	ReparacionesClase oReparacion = new ReparacionesClase();
   	ArrayList<ReparacionesClase> listadoReparaciones;
   	
    SQLHelperAdaptador dao ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        levantarXML();
     
        dao = new SQLHelperAdaptador(MainActivity.this, getString(R.string.DataBase), null, 1);
		//
		Log.d("AplicacionReparaciones", "Cantidad de Reparaciones:"+dao.recuperarCantidadReparaciones());
        botones();
     }

	private void botones() {
		btn_Reparacion.setOnClickListener(new OnClickListener() {
			Intent intento=new Intent(MainActivity.this,Lay_ingresar.class);
			@Override
			public void onClick(View v) {
				startActivity(intento);
				
			}
		});
		btn_buscarEquipos.setOnClickListener(new OnClickListener() {
			Intent intento=new Intent(MainActivity.this,Lay_buscar.class);
			
			@Override
			public void onClick(View v) {
				//startActivity(intento);
			txtV_nReparaciones.setText(Integer.toString(dao.recuperarCantidadReparaciones()));	
			startActivity(intento);
			listadoReparaciones=dao.recuperarReparaciones();
			/////////
			}
		});
		
		btn_Modificar_spin.setOnClickListener(new OnClickListener() {
			Intent intento=new Intent(MainActivity.this,Lay_Modificar_Spinners.class);
			@Override
			public void onClick(View v) {
				startActivity(intento);
		
			}
		});
	
	}

	private void levantarXML() {
		btn_Reparacion=(Button) findViewById(R.id.Btn_Reparacion);
		btn_Modificar_spin=(Button) findViewById(R.id.btn_Spinners);
		btn_buscarEquipos=(Button) findViewById(R.id.btn_BuscarEquipo);
	
		txtV_nReparaciones=(TextView) findViewById(R.id.txtV_nReparaciones); 
		txtV_Observaciones=(TextView) findViewById(R.id.txtV_observaciones);
		txtV_Falla=(TextView) findViewById(R.id.txtV_Falla);
		txtV_Fecha=(TextView) findViewById(R.id.txtV_Fecha);
		txtV_Serial=(TextView) findViewById(R.id.txtV_Serial);
		
		eTxt_Serial=(EditText) findViewById(R.id.eTxt_Serial);
	}


   

}
