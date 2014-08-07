package com.example.reparacionesutn.DAOs;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.reparacionesutn.R;
import com.example.reparacionesutn.layouts.lay_reparacion;
import com.example.reparacionesutn.objetos.ReparacionesClase;

;

public class AdaptadorCustomizado extends BaseAdapter
{
	private SQLHelperAdaptador dao;
	private ArrayList<ReparacionesClase> reparaciones;
	private Context context;
 
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
			
			int serial,hs24,reparacion;
			String observacion,fecha,modelo,version,falla;

			
			
			@Override
			public void onClick(View v)
			{
				Intent intento = new Intent(context, lay_reparacion.class);
				intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
				reparacion =item.getId_Reparacion();
				serial =item.getSerial();
				hs24 =item.getHs24();
				
				observacion=item.getObservaciones();
				fecha="1/1/1900";
				modelo="3100";
				version="IDX";
				falla="Tx VCC";
				
				
				
				
				
				
				
				intento.putExtra("reparacion", reparacion);
				intento.putExtra("serial", serial);
				intento.putExtra("hs24", hs24);
				intento.putExtra("fecha", fecha);
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
