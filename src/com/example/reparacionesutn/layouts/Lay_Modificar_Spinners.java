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
import android.widget.TextView;
import android.widget.Toast;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;
import com.example.reparacionesutn.objetos.ComponentesClase;
import com.example.reparacionesutn.objetos.FallasClase;
import com.example.reparacionesutn.objetos.ModelosClase;
import com.example.reparacionesutn.objetos.VersionesClase;

public class Lay_Modificar_Spinners extends Activity
{

	Spinner spin_modelos, spin_versiones, spin_fallas, spin_componentes;
	int pos_spin_modelo, pos_spin_version, pos_spin_componente, pos_spin_falla;
	Button btn_insertar_c, btn_eliminar_c, btn_editar_c, btn_insertar_m,
			btn_eliminar_m, btn_editar_m, btn_insertar_v, btn_eliminar_v,
			btn_editar_v, btn_insertar_f, btn_eliminar_f, btn_editar_f;
	EditText eTxt_Modelos, eTxt_Versiones, eTxt_Fallas, eTxt_Componentes;
	TextView txtV_posicion;
	ArrayAdapter<String> adaptadorModelos, adaptadorFallas, adaptadorVersiones,adaptadorComponentes;
	SQLHelperAdaptador dao;
	ModelosClase oModelo;
	FallasClase oFalla;
	VersionesClase oVersion;
	ComponentesClase oComponente;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_modificar_spinners);
		levantarXML();
		dao = new SQLHelperAdaptador(getApplicationContext(), getString(R.string.DataBase), null, 1);

		oComponente = new ComponentesClase();
		oVersion = new VersionesClase();
		oModelo = new ModelosClase();
		oFalla = new FallasClase();

		setAdaptadores();
		botones();
		spinners();

		eTxt_Versiones.setText("");

	}

	private void spinners()
	{
		spin_modelos.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				eTxt_Modelos.setText(dao.recuperarNombresModelos()[position]);
				txtV_posicion.setText(""+position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		spin_versiones.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				eTxt_Versiones.setText(dao.recuperarNombresVersiones()[position]);
				txtV_posicion.setText(""+position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				// TODO Auto-generated method stub

			}
		});

		spin_fallas.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				eTxt_Fallas.setText(dao.recuperarNombresFallas()[position]);
				txtV_posicion.setText(""+position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				// TODO Auto-generated method stub

			}
		});

		spin_componentes.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				pos_spin_componente = position;
				txtV_posicion.setText(""+position);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				// TODO Auto-generated method stub

			}
		});
	}

	private void setAdaptadores()
	{

		adaptadorModelos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dao.recuperarNombresModelos());
		adaptadorVersiones = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dao.recuperarNombresVersiones());
		adaptadorFallas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dao.recuperarNombresFallas());
		adaptadorComponentes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dao.recuperarNombresComponentes());

		spin_componentes.setAdapter(adaptadorComponentes);
		spin_fallas.setAdapter(adaptadorFallas);
		spin_modelos.setAdapter(adaptadorModelos);
		spin_versiones.setAdapter(adaptadorVersiones);
	}

	private void botones()
	{

		// /////////botones modelos////////////////////////////////
		btn_insertar_m.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				dao.insertarModelo(eTxt_Modelos.getText().toString());
				setAdaptadores();
				Toast.makeText(getApplicationContext(), "Modelo agregado", Toast.LENGTH_SHORT).show();
				eTxt_Modelos.getText().clear();

			}
		});

		btn_editar_m.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

			}
		});

		btn_eliminar_m.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dao.borrarNombreModelo(pos_spin_componente);
				setAdaptadores();
				Toast.makeText(getApplicationContext(), "Modelo eliminado", Toast.LENGTH_SHORT).show();
				eTxt_Modelos.getText().clear();

			}
		});

		// *********** cargar objeto a modificar**********

		// /////////////botones versiones//////////////////////////////
		btn_insertar_v.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dao.insertarVersion(eTxt_Versiones.getText().toString());				
				setAdaptadores();
				Toast.makeText(getApplicationContext(), "Version agregada", Toast.LENGTH_SHORT).show();
				eTxt_Versiones.getText().clear();
			}
		});

		btn_editar_v.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

			}
		});

		btn_eliminar_v.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				oVersion = new VersionesClase();
				oVersion.setNom_version(eTxt_Versiones.getText().toString());
				dao.borrarNombreVersion(oVersion);
				setAdaptadores();
				Toast.makeText(getApplicationContext(), "Version eliminada", Toast.LENGTH_SHORT).show();
				eTxt_Versiones.getText().clear();
			}
		});

		// //////////////botones fallas//////////////////////////
		btn_insertar_f.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dao.insertarFalla(eTxt_Fallas.getText().toString());
				setAdaptadores();
				Toast.makeText(getApplicationContext(), "Falla agregada", Toast.LENGTH_SHORT).show();
				eTxt_Fallas.getText().clear();
			}
		});

		btn_editar_f.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

			}
		});

		btn_eliminar_f.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				oFalla = new FallasClase();
				oFalla.setNom_falla(eTxt_Fallas.getText().toString());
				dao.borrarNombreFalla(oFalla);
				setAdaptadores();
				Toast.makeText(getApplicationContext(), "Falla eliminada", Toast.LENGTH_SHORT).show();
				eTxt_Fallas.getText().clear();
			}
		});

		// //////////////botones componentes//////////////////////////

		btn_insertar_c.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dao.insertarComponentes(eTxt_Componentes.getText().toString());
				setAdaptadores();
				Toast.makeText(getApplicationContext(), "Componente agregado", Toast.LENGTH_SHORT).show();
				eTxt_Componentes.getText().clear();
			}
		});

		btn_editar_c.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

			}
		});

		btn_eliminar_c.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				oComponente = new ComponentesClase();
				oComponente.setNom_componente(eTxt_Componentes.getText().toString());
				dao.borrarNombreComponente(oComponente);
				setAdaptadores();
				Toast.makeText(getApplicationContext(), "Componente eliminado", Toast.LENGTH_SHORT).show();
				eTxt_Componentes.getText().clear();

			}
		});

	}

	private void levantarXML()
	{
		spin_modelos = (Spinner) findViewById(R.id.spin_modelos);
		spin_versiones = (Spinner) findViewById(R.id.spin_versiones);
		spin_fallas = (Spinner) findViewById(R.id.spin_fallas);
		spin_componentes = (Spinner) findViewById(R.id.spin_Componentes);

		btn_editar_m = (Button) findViewById(R.id.btn_Editar_lay_modelos);
		btn_eliminar_m = (Button) findViewById(R.id.btn_Eliminar_lay_modelos);
		btn_insertar_m = (Button) findViewById(R.id.btn_Insertar_lay_modelos);

		btn_editar_v = (Button) findViewById(R.id.btn_Editar_lay_versiones);
		btn_eliminar_v = (Button) findViewById(R.id.btn_Eliminar_lay_versiones);
		btn_insertar_v = (Button) findViewById(R.id.btn_Insertar_lay_versiones);

		btn_editar_f = (Button) findViewById(R.id.btn_Editar_lay_fallas);
		btn_eliminar_f = (Button) findViewById(R.id.btn_Eliminar_lay_fallas);
		btn_insertar_f = (Button) findViewById(R.id.btn_Insertar_lay_fallas);

		btn_editar_c = (Button) findViewById(R.id.btn_Editar_componentes);
		btn_eliminar_c = (Button) findViewById(R.id.btn_Eliminar_componentes);
		btn_insertar_c = (Button) findViewById(R.id.btn_Insertar_componentes);

		eTxt_Modelos = (EditText) findViewById(R.id.eTxt_Modelos);
		eTxt_Versiones = (EditText) findViewById(R.id.eTxt_Versiones);
		eTxt_Fallas = (EditText) findViewById(R.id.eTxt_Fallas);
		eTxt_Componentes = (EditText) findViewById(R.id.eTxt_Componentes);

		txtV_posicion = (TextView) findViewById(R.id.txtV_posicion);
	}
}
