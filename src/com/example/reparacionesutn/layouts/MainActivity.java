package com.example.reparacionesutn.layouts;



import com.example.reparacionesutn.R;
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
 
	Button btn_Reparacion,btn_Modificar_spin,btn_buscarEquipos;
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
				startActivity(intento);
				
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
		
		eTxt_Serial=(EditText) findViewById(R.id.eTxt_Serial);
	}


   

}
