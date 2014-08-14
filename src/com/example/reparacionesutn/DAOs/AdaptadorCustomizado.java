package com.example.reparacionesutn.DAOs;

import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.layouts.Lay_VerReparaciones;
import com.example.reparacionesutn.layouts.lay_reparacion;
import com.example.reparacionesutn.objetos.ReparacionesClase;

public class AdaptadorCustomizado extends BaseAdapter
{
	private SQLHelperAdaptador dao;
	private ArrayList<ReparacionesClase> reparaciones;
	private Context context;
	private int modelo, version, falla, serial, hs24, reparacion, editar;
	private String observacion, Sfecha;
	private boolean borrar;
	private Date fecha;
	private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");

	public AdaptadorCustomizado(ArrayList<ReparacionesClase> reparacion, Context Context)
	{
		this.reparaciones = reparacion;
		this.context = Context;

	}

	@Override
	public int getCount()
	{
		return reparaciones.size();
	}

	@Override
	public ReparacionesClase getItem(int position)
	{
		return reparaciones.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	// creo objeto contenedor
	static class ViewHolder
	{
		TextView text_Titulo;
		TextView text_Descripcion;
		ImageView imagen;
		RelativeLayout ll_row;
		Button btn_borrar, btn_editar;

	}

	
	//***********************************************************////
	////////// alert dialog Eliminar/////////////////////////////
	
	
	@SuppressWarnings("deprecation")
	public void dialogoEliminar()
	{
 
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);

		dialog.setMessage("¿Eliminar?");
		dialog.setCancelable(false);
		dialog.setPositiveButton("Si", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{

				borrar = true;
				Toast.makeText(getApplicationContext(), "BORRADO !!!", Toast.LENGTH_SHORT).show();

			}
		});
		dialog.setNegativeButton("No", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				borrar = false;
				Toast.makeText(getApplicationContext(), "CANCELADO !!!", Toast.LENGTH_SHORT).show();

				dialog.cancel();
			}
		});
		dialog.show();
	
	
	
		//return borrar;

	}
	
	//***********************************************************////
	///// FIN / alert dialog Eliminar/////////////////////////////
	//*************************************************************
	
	protected Context getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ReparacionesClase item = getItem(position);

		ViewHolder holder;

		if (convertView == null)// es la primera vez
		{
			LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = li.inflate(R.layout.list_customizada, parent, false);

			// aca defino el objeto... lo instancio
			holder = new ViewHolder();
			holder.text_Titulo = (TextView) convertView.findViewById(R.id.tv_titulo);
			holder.text_Descripcion = (TextView) convertView.findViewById(R.id.tv_descripcion);
			holder.imagen = (ImageView) convertView.findViewById(R.id.imv_icono);
			holder.ll_row = (RelativeLayout) convertView.findViewById(R.id.ll_row);
			holder.btn_borrar = (Button) convertView.findViewById(R.id.btn_borrarReparacion);
			holder.btn_editar = (Button) convertView.findViewById(R.id.btn_editarReparacion);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.text_Titulo.setText("Serial: " + item.getSerial());
		holder.text_Descripcion.setText(item.getObservaciones());

		holder.ll_row.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// / para hacer no tactil la ventana

				Intent intento = new Intent(context, lay_reparacion.class);
				intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				editar = 0;
				intento.putExtra("editar", editar);

				reparacion = item.getId_Reparacion();
				serial = item.getSerial();
				hs24 = item.getHs24();

				observacion = item.getObservaciones();
				fecha = item.getFecha();

				Sfecha = sdf.format(fecha);
				modelo = item.getId_modelo();
				version = item.getId_version();
				falla = item.getId_falla();

				intento.putExtra("reparacion", reparacion);
				intento.putExtra("serial", serial);
				intento.putExtra("hs24", hs24);
				intento.putExtra("fecha", Sfecha);
				intento.putExtra("observacion", observacion);
				intento.putExtra("falla", falla);
				intento.putExtra("modelo", modelo);
				intento.putExtra("version", version);

				context.startActivity(intento);
			}
		});

		holder.btn_borrar.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				
				//dialogoEliminar(); //cuando llega aca hayt error
				
				// LA BASE DE DATOS ESTA HARDCODEADA.. HAY QUE ARREGLARLO
				dao = new SQLHelperAdaptador(context,context.getString(R.string.DataBase), null, 1);

				dao.borrarReparacion(item.getId_Reparacion());
				Toast.makeText(context, "Reparacion " + item.getId_Reparacion() + " Borrada !!! ", Toast.LENGTH_SHORT).show();

				//llamo a la misma asi se vuelve a setear el adapter
				//si, es algo primitivo pero es la forma más eficaz
				Intent intent = new Intent(context, Lay_VerReparaciones.class); 
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);//limpio la pila de actividades
				context.startActivity(intent);

			}

		});

		holder.btn_editar.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Intent intento = new Intent(context, lay_reparacion.class);
				intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				editar = 1;
				intento.putExtra("editar", editar);

				reparacion = item.getId_Reparacion();
				serial = item.getSerial();
				hs24 = item.getHs24();

				observacion = item.getObservaciones();
				fecha = item.getFecha();

				Sfecha = sdf.format(fecha);
				modelo = item.getId_modelo();
				version = item.getId_version();
				falla = item.getId_falla();

				intento.putExtra("reparacion", reparacion);
				intento.putExtra("serial", serial);
				intento.putExtra("hs24", hs24);
				intento.putExtra("fecha", Sfecha);
				intento.putExtra("observacion", observacion);
				intento.putExtra("falla", falla);
				intento.putExtra("modelo", modelo);
				intento.putExtra("version", version);

				context.startActivity(intento);

			}
		});

		return convertView;
	}

}
