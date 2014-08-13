package com.example.reparacionesutn.layouts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class TareaAsincrona extends AsyncTask<Void, Void, Void>
{
	Activity ac;
	ProgressDialog pd;

	// constructor
	public TareaAsincrona(Activity ac)
	{
		this.ac = ac;
	}

	@Override
	protected void onPreExecute()
	{

		Toast.makeText(ac, "Comienza la Tarea", Toast.LENGTH_SHORT).show();
		pd = ProgressDialog.show(ac, "Tarea Asincrona", "descargando...");
		super.onPreExecute();

	}

	@Override
	protected Void doInBackground(Void... voids)
	{

		Log.d("pepe", "errorororoorororor");
		try
		{

			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result)
	{
		// Toast.makeText(getApplicationContext(), "AsincTask",
		// Toast.LENGTH_SHORT).show();
		pd.dismiss();
		Toast.makeText(ac, "Termino la Tarea", Toast.LENGTH_SHORT).show();
		super.onPostExecute(result);
	}

}
