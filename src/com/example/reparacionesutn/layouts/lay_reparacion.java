package com.example.reparacionesutn.layouts;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.DAOs.SQLHelperAdaptador;

public class lay_reparacion extends Activity
{

	int posSpinModelo, posSpinVersion, posSpinFalla, posSpinComponente, posCantidadComponentes;
	int hs24;

	private CheckBox CkBox_24hs;
	private TextView txt_date, txtV_Reparacion;
	private Spinner spin_modelos, spin_fallas, spin_versiones, spin_componentes, spin_cantidad_componentes;
	private EditText etxt_serial, observaciones, etxt_componentes1;
	private Button btn_GuardarReparacion;
	private ArrayAdapter<String> adaptadorModelos, adaptadorVersiones, adaptadorFallas, adaptadorComponentes, adaptadorCantidadComponentes;
	private SQLHelperAdaptador dao;
	private java.util.Date date = new Date();
	private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
	private String fecha = sdf.format(date);

	// variables intento getextras

	Intent intento;
	int modeloE, versionE, fallaE, serialE, reparacionE, editarE;
	int hs24E;
	String observacionE, SfechaE;
	boolean borrarE;
	Date fechaE;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_ingresar);
		levantarXML();
		txt_date.setText(fecha);
		setcheck();
		Botones();
		cargaAdaptadores();
		Spinners();

		importarExtras();
		cargarEXTRAS();

	}

	private void cargarEXTRAS()
	{

		txtV_Reparacion.setText(Integer.toString(reparacionE));
		txt_date.setText(SfechaE);
		etxt_serial.setText(Integer.toString(serialE));

		if (hs24E == 1)
		{
			CkBox_24hs.setChecked(true);
		}
		else
		{
			CkBox_24hs.setChecked(false);
		}
		observaciones.setText(observacionE);
		spin_fallas.setSelection(fallaE);
		spin_modelos.setSelection(modeloE);
		spin_versiones.setSelection(versionE);

	}

	private void importarExtras()
	{

		intento = getIntent();

		editarE = intento.getExtras().getInt("editar");
		reparacionE = intento.getExtras().getInt("reparacion");
		serialE = intento.getExtras().getInt("serial");
		hs24E = intento.getExtras().getInt("hs24");
		SfechaE = intento.getExtras().getString("fecha");
		observacionE = intento.getExtras().getString("observacion");
		fallaE = intento.getExtras().getInt("falla");
		modeloE = intento.getExtras().getInt("modelo");
		versionE = intento.getExtras().getInt("version");

		if (editarE == 0)
		{

			// Toast.makeText(getApplicationContext(), "bloqueado",
			// Toast.LENGTH_SHORT).show();
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
			btn_GuardarReparacion.setVisibility(View.GONE);
			etxt_serial.setFocusable(false);
			etxt_componentes1.setFocusable(false);
			observaciones.setFocusable(false);

		}
		else
		{

			// Toast.makeText(getApplicationContext(), "editable",
			// Toast.LENGTH_SHORT).show();

			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

			btn_GuardarReparacion.setVisibility(View.VISIBLE);
			// etxt_serial.setFocusable(true);
		}

	}

	private void setcheck()
	{

		CkBox_24hs.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if (isChecked)
				{
					hs24 = 1;
				}
				else
				{
					hs24 = 0;
				}

			}
		});

	}

	private void Spinners()
	{
		etxt_componentes1.setText("");
		posSpinVersion = spin_versiones.getSelectedItemPosition();
		posSpinModelo = spin_modelos.getSelectedItemPosition();
		posSpinFalla = spin_fallas.getSelectedItemPosition();
		posSpinComponente = spin_componentes.getSelectedItemPosition();
		posCantidadComponentes = spin_cantidad_componentes.getSelectedItemPosition();

		spin_cantidad_componentes.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				posCantidadComponentes = position;

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
				posSpinComponente = position;
				etxt_componentes1.setText(etxt_componentes1.getText().toString() + posCantidadComponentes + " - " + dao.recuperarNombresComponentes()[position]
						+ "\n");
				spin_cantidad_componentes.setSelection(0);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				// TODO Auto-generated method stub

			}
		});

		spin_modelos.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				posSpinModelo = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});

		spin_fallas.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				posSpinFalla = position;
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
				posSpinVersion = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
	}

	private void cargaAdaptadores()
	{

		String[] cantidad = new String[] { "1", "2", "3", "4", "5" };

		// instancio el dao
		dao = new SQLHelperAdaptador(getApplicationContext(), getString(R.string.DataBase), null, 1);

		adaptadorFallas = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text, dao.recuperarNombresFallas());
		adaptadorModelos = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text, dao.recuperarNombresModelos());
		adaptadorVersiones = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text, dao.recuperarNombresVersiones());
		adaptadorComponentes = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text, dao.recuperarNombresComponentes());
		adaptadorCantidadComponentes = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text, cantidad);
		txtV_Reparacion.setText(String.valueOf(dao.recuperarCantidadReparaciones() + 1));

		spin_componentes.setAdapter(adaptadorComponentes);
		spin_fallas.setAdapter(adaptadorFallas);
		spin_modelos.setAdapter(adaptadorModelos);
		spin_versiones.setAdapter(adaptadorVersiones);
		spin_cantidad_componentes.setAdapter(adaptadorCantidadComponentes);
	}

	private void Botones()
	{

		btn_GuardarReparacion.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				if (!(etxt_serial.getText().toString().equals("")))
				{

					dao.actualizarReparacion(reparacionE, Integer.parseInt(etxt_serial.getText().toString()), posSpinModelo, posSpinVersion, posSpinFalla,
							fecha + ": " + observaciones.getText().toString(), hs24);

					etxt_serial.setText("");
					observaciones.setText("");
					txtV_Reparacion.setText(String.valueOf(dao.recuperarCantidadReparaciones() + 1));

					Toast.makeText(getApplicationContext(), "Modificacion Actualizada !!!", Toast.LENGTH_SHORT).show();

				}
				else
				{
					Toast.makeText(getApplicationContext(), "Ingrese Numero de Serie", Toast.LENGTH_SHORT).show();
				}

				spin_fallas.setSelection(0);
				spin_modelos.setSelection(0);
				spin_versiones.setSelection(0);
				spin_componentes.setSelection(0);
				spin_cantidad_componentes.setSelection(0);

				finish();
			}

		});

	}

	private void levantarXML()
	{
		hs24 = 0;
		spin_modelos = (Spinner) findViewById(R.id.spin_modelos_REP);
		spin_fallas = (Spinner) findViewById(R.id.spin_fallas_REP);
		spin_versiones = (Spinner) findViewById(R.id.spin_versiones_REP);
		spin_componentes = (Spinner) findViewById(R.id.spin_componentes);
		spin_cantidad_componentes = (Spinner) findViewById(R.id.spin_cantidad_componentes);

		CkBox_24hs = (CheckBox) findViewById(R.id.cbox_24hs_REP);
		etxt_serial = (EditText) findViewById(R.id.eTxt_Serial_ingresar);
		etxt_componentes1 = (EditText) findViewById(R.id.etxt_componentes1);
		observaciones = (EditText) findViewById(R.id.etxt_observaciones_REP);

		txt_date = (TextView) findViewById(R.id.txtV_fecha_REP);
		txtV_Reparacion = (TextView) findViewById(R.id.txtV_Reparacion_REP);
		btn_GuardarReparacion = (Button) findViewById(R.id.btn_IngresarReparacion);

	}
}
