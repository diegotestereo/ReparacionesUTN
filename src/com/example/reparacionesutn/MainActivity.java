package com.example.reparacionesutn;



import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
 
	Button btn_Reparacion,btn_Fallas,btn_Modelos,btn_Versiones,btn_buscarEquipos;
	EditText eTxt_Serial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        levantarXML();
        
        SQLHelperAdaptador dao = new SQLHelperAdaptador(MainActivity.this, getString(R.string.DataBase), null, 1);
		//
		Log.d("AplicacionReparaciones", "Cantidad de Reparaciones:"+dao.recuperarCantidadReparaciones());
        botones();
     }

	private void botones() {
		btn_Reparacion.setOnClickListener(new OnClickListener() {
			Intent intento=new Intent(MainActivity.this,Ingresar.class);
			@Override
			public void onClick(View v) {
				intento.st
				Toast.makeText(getApplicationContext(), "apreto boton reparacion", Toast.LENGTH_SHORT).show();
				
			}
		});
		btn_buscarEquipos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "apreto boton buscar equipo", Toast.LENGTH_SHORT).show();
				
				
			}
		});
		btn_Fallas.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Toast.makeText(getApplicationContext(), "apreto boton fallas", Toast.LENGTH_SHORT).show();
		
		
			}
		});
		btn_Modelos.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Toast.makeText(getApplicationContext(), "apreto boton modelos", Toast.LENGTH_SHORT).show();
		
		
			}
		});
		btn_Versiones.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Toast.makeText(getApplicationContext(), "apreto boton version", Toast.LENGTH_SHORT).show();
		
		
			}
		});
	}

	private void levantarXML() {
		btn_Reparacion=(Button) findViewById(R.id.Btn_Reparacion);
		btn_Fallas=(Button) findViewById(R.id.btn_Fallas);
		btn_Modelos=(Button) findViewById(R.id.btn_Modelos);
		btn_Versiones=(Button) findViewById(R.id.btn_Versiones);
		btn_buscarEquipos=(Button) findViewById(R.id.btn_BuscarEquipo);
		
		eTxt_Serial=(EditText) findViewById(R.id.eTxt_Serial);
	}


   

}
