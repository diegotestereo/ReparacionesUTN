package com.example.reparacionesutn.layouts;




import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;
import com.example.reparacionesutn.objetos.FallasClase;
import com.example.reparacionesutn.objetos.ModelosClase;
import com.example.reparacionesutn.objetos.ReparacionesClase;
import com.example.reparacionesutn.objetos.VersionesClase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	Button btn_VerReparaciones,btn_Reparacion,btn_Modificar_spin,btn_buscarEquipos;
	Button btn_asinc;
	ProgressDialog progress;
	
	EditText eTxt_Serial;
	TextView txtV_Observaciones,txtV_nReparaciones; 
	//Spinner sp_modelo,sp_falla,sp_version;
	ArrayAdapter<String> adaptadorModelos, adaptadorFallas, adaptadorVersiones;
	///OBJETOS
	ModelosClase oModelo;
   	FallasClase oFalla;
   	VersionesClase oVersion;
   	ReparacionesClase oReparacion = new ReparacionesClase();
 
    SQLHelperAdaptador dao ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        levantarXML();
        setAdaptadores();
        botones();
       
     }
    
    
   
	
    
	private void setAdaptadores() {
		 dao = new SQLHelperAdaptador(MainActivity.this, getString(R.string.DataBase), null, 1);
		//los adaptadores recuperan el listado de nombres que luego se asignan a los spinners correspondientes...
				adaptadorModelos = new ArrayAdapter<String>(this, R.layout.spinner_text, dao.recuperarNombresModelos());
				adaptadorVersiones = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dao.recuperarNombresVersiones());
				adaptadorFallas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dao.recuperarNombresFallas());
				// se cargan los spinners con el contenido de los adapatadores...
				
		//		sp_falla.setAdapter(adaptadorFallas);
				//spin_modelos.setAdapter(adaptadorModelos);
			//	sp_modelo.setAdapter(adaptadorModelos);
				//sp_version.setAdapter(adaptadorVersiones);
		
	}

	private void botones() {
	
	/*	
		btn_asinc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				new TareaAsincrona(MainActivity.this).execute();
				
			}
		});*/
		
		btn_Reparacion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intento=new Intent(MainActivity.this,Lay_ingresar.class);
				startActivity(intento);
			}
		});
		
		btn_buscarEquipos.setOnClickListener(new OnClickListener() {
			
			
			
			
			Intent intento=new Intent(MainActivity.this,Lay_buscar.class);
			int numSerie;
			
			@Override
			public void onClick(View v) {
				
			if (!(eTxt_Serial.getText().toString().equals(""))){
			
			numSerie = Integer.parseInt(eTxt_Serial.getText().toString());
			//txtV_nReparaciones.setText(Integer.toString(dao.recuperarCantidadReparaciones(numSerie)));
			
			
			intento.putExtra("serial", numSerie);
			
			if(!(dao.recuperarCantidadReparaciones(numSerie)==0)){
				startActivity(intento);
			}
			else{
				Toast.makeText(getApplicationContext(), "Equipo no encontrado", Toast.LENGTH_SHORT).show();
			
			}
			
			/////////
			}else{
				Toast.makeText(getApplicationContext(), "Ingrese equipo a buscar", Toast.LENGTH_SHORT).show();
			
			}
				}
			
			
		});
		
		btn_VerReparaciones.setOnClickListener(new OnClickListener() {
			int cantidad=0;
			Intent intento=new Intent(MainActivity.this,Lay_VerReparaciones.class);
			
			@Override
			public void onClick(View v) {
				cantidad=dao.recuperarCantidadReparaciones();
				if(cantidad==0){
					Toast.makeText(getApplicationContext(), "Base de Datos Vacia....", Toast.LENGTH_SHORT).show();
					
					
				}else{
					
					
					
				startActivity(intento);
				
				
				
				
				
				}
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
		btn_VerReparaciones=(Button) findViewById(R.id.btn_VerReparaciones);
		//btn_asinc=(Button)findViewById(R.id.btn_asinc);
		//sp_falla=(Spinner) findViewById(R.id.spin_version_main);
		//sp_modelo=(Spinner) findViewById(R.id.spin_modelo_main);
		//sp_version=(Spinner) findViewById(R.id.spin_falla_main);
		txtV_Observaciones=(TextView) findViewById(R.id.txtV_observaciones);
	
		
		eTxt_Serial=(EditText) findViewById(R.id.eTxt_Serial);
	}
  

	
	
	
	

}
