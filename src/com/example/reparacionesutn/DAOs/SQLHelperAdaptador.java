package com.example.reparacionesutn.DAOs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.reparacionesutn.objetos.ComponentesClase;
import com.example.reparacionesutn.objetos.FallasClase;
import com.example.reparacionesutn.objetos.ModelosClase;
import com.example.reparacionesutn.objetos.ReparacionesClase;
import com.example.reparacionesutn.objetos.VersionesClase;

public class SQLHelperAdaptador extends SQLiteOpenHelper
{

	public SQLHelperAdaptador(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{

		// Creo la tabla reparaciones
		db.execSQL("CREATE TABLE Tabla_Reparaciones (id_reparacion INTEGER PRIMARY KEY ,fecha_in TEXT"
				+ ",serial INT,id_modelo INT,id_version INT,id_falla INT,observaciones TEXT,hs24 INT, FOREIGN KEY (id_modelo) REFERENCES Tabla_Modelos(id_modelo),"
				+ "FOREIGN KEY (id_version) REFERENCES Tabla_Versiones(id_version),FOREIGN KEY (id_falla) REFERENCES Tabla_Fallas(id_falla))");

		// creo tabla modelos de equipos
		db.execSQL("CREATE TABLE Tabla_Modelos (id_modelo INTEGER PRIMARY KEY,nom_modelo TEXT)");

		// creo tabla tipo de version de versiones
		db.execSQL("CREATE TABLE Tabla_Versiones (id_version INTEGER PRIMARY KEY,nom_version TEXT)");

		// creo tabla tipo de reparaciones
		db.execSQL("CREATE TABLE Tabla_Fallas (id_falla INTEGER PRIMARY KEY,nom_falla TEXT)");

		// creo tabla componentes

		db.execSQL("CREATE TABLE Tabla_Componentes (id_componente INTEGER PRIMARY KEY,nom_componente TEXT)");

		// creo tabla componentes usados

		db.execSQL("CREATE TABLE Tabla_Comp_Usados (id_comp_usado INTEGER PRIMARY KEY," + "id_componente INT,id_reparacion INT, cantidadComp INT,"
				+ " FOREIGN KEY (id_componente) REFERENCES Tabla_Componentes(id_componente),"
				+ " FOREIGN KEY (id_reparacion) REFERENCES Tabla_Reparaciones(id_reparacion))");

		// / defaults valores

		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('3100')");
		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('3125')");
		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('5100')");
		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('5350')");
		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('M0D1')");
		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('M1D1')");

		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('6.0.9.0')");
		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('6.0.9.6')");
		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('7.0')");
		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('8.0')");
		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('9.0')");
		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('IDX')");

		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Vcc 24v')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Vcc 3.3')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Vcc TX')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Vcc RX')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('CPLD')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('FPGA TX')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('FPGA RX')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('uP')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Flash')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Ram')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('RedBoot')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Linux')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Opt')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Otros')");

		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('uP ')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FPGA XC3S1000')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FPGA XC3S400')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('DS2430')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('AMP H482')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('TPS 40061')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('RAM 48LC16M16A2')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET 10P06')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET 6690A')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET 9407')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET 4850')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET FR3410')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET IRF5210S')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('BUFFER 74LVC273')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('MOD MAX 2150')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('TPS 40009')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FILTRO GBP202SE')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('DIODO SS14')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('DEM MAX3223')");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

		// borro tabla
		db.execSQL("DROP TABLE IF EXISTS Tabla_Reparaciones");
		// Creo la tabla reparaciones
		db.execSQL("CREATE TABLE Tabla_Reparaciones (id_reparacion INTEGER PRIMARY KEY , fecha_in TEXT"
				+ ",serial INT,id_modelo INT, id_version INT, id_falla INT,observaciones TEXT,hs24 INT");

		// borro tabla
		db.execSQL("DROP TABLE IF EXISTS Tabla_Modelos");
		// creo tabla modelos de equipos
		db.execSQL("CREATE TABLE Tabla_Modelos (id_modelo INTEGER PRIMARY KEY , nom_modelo TEXT)");

		// borro tabla
		db.execSQL("DROP TABLE IF EXISTS Tabla_versiones");
		// creo tabla tipo de version de firmware
		db.execSQL("CREATE TABLE Tabla_versiones (id_version INTEGER PRIMARY KEY , nom_version TEXT)");

		// borro tabla fallas
		db.execSQL("DROP TABLE IF EXISTS Tabla_fallas");
		// creo tabla tipo de fallas
		db.execSQL("CREATE TABLE Tabla_fallas (id_falla INTEGER PRIMARY KEY , nom_falla TEXT)");

		// borro tabla componentes
		db.execSQL("DROP TABLE IF EXISTS Tabla_Componentes");
		// creo tabla componentes
		db.execSQL("CREATE TABLE Tabla_Componentes (id_componente INTEGER PRIMARY KEY,nom_componente TEXT)");

		// borro tabla componentes usados
		db.execSQL("DROP TABLE IF EXISTS Tabla_Comp_Usados");
		// creo tabla componentes usados
		db.execSQL("CREATE TABLE Tabla_Comp_Usados (id_comp_usado INTEGER PRIMARY KEY," + "id_componente INT,id_reparacion INT, cantidadComp INT,"
				+ " FOREIGN KEY (id_componente) REFERENCES Tabla_Componentes(id_componente),"
				+ " FOREIGN KEY (id_reparacion) REFERENCES Tabla_Reparaciones(id_reparacion))");

		// / defaults valores

		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('3100')");
		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('3125')");
		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('5100')");
		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('5350')");
		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('M0D1')");
		db.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('M1D1')");

		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('6.0.9.0')");
		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('6.0.9.6')");
		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('7.0')");
		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('8.0')");
		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('9.0')");
		db.execSQL("INSERT INTO Tabla_Versiones (nom_version) VALUES ('IDX')");

		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Vcc 24v')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Vcc 3.3')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Vcc TX')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Vcc RX')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('CPLD')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('FPGA TX')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('FPGA RX')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('uP')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Flash')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Ram')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('RedBoot')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Linux')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Opt')");
		db.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('Otros')");

		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('uP ')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FPGA XC3S1000')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FPGA XC3S400')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('DS2430')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('AMP H482')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('TPS 40061')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('RAM 48LC16M16A2')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET 10P06')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET 6690A')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET 9407')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET 4850')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET FR3410')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FET IRF5210S')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('BUFFER 74LVC273')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('MOD MAX 2150')");

		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('TPS 40009')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('FILTRO GBP202SE')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('DIODO SS14')");
		db.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('DEM MAX3223')");

	}

	// ***********************************************************************************************************
	// /////////////////// INSERTAR EN TABLAS
	// //////////////////////////////////////////////////////////////////7

	public void insertarReparacion(String fecha_in, int serial, int id_modelo, int id_version, int id_falla, String observaciones, int hs24)
	{

		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("INSERT INTO Tabla_Reparaciones (fecha_in,serial,id_modelo,id_version,id_falla, observaciones,hs24) " + "VALUES ('" + fecha_in + "',"
				+ serial + "," + id_modelo + "," + id_version + "," + id_falla + ",'" + observaciones + "'," + hs24 + " )");
		baseDatos.close();
	}

	public void actualizarReparacion(int id_reparacion, int serial, int id_modelo, int id_version, int id_falla, String observaciones, int hs24)
	{

		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("UPDATE Tabla_Reparaciones SET serial=" + serial + ",id_modelo=" + id_modelo + ",id_version=" + id_version + "," + "id_falla="
				+ id_falla + ", observaciones='" + observaciones + "',hs24=" + hs24 + "  WHERE id_reparacion=" + id_reparacion);
		baseDatos.close();
	}

	public void insertarFalla(String nom_falla)
	{

		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ('" + nom_falla + "')");
		baseDatos.close();
	}

	public void insertarModelo(String nom_modelo)
	{

		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ('" + nom_modelo + "')");
		baseDatos.close();
	}

	public void insertarVersion(String nom_version)
	{

		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("INSERT INTO Tabla_versiones (nom_version) VALUES ('" + nom_version + "')");
		baseDatos.close();
	}

	public void insertarComponentes(String nom_componente)
	{

		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("INSERT INTO Tabla_Componentes (nom_componente) VALUES ('" + nom_componente + "')");
		baseDatos.close();
	}

	// ////////////////////FIN INSERTAR EN TABLAS
	// //////////////////////////////////////////////////////////////////7
	// ***********************************************************************************************************
	// ////////////////// RECUPERAR CANTIDAD FILAS
	// TABLAS///////////////////////////////////////////////////////

	public int recuperarCantidadReparaciones(int serial)
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Reparaciones WHERE serial=" + serial;
		Cursor cursor = baseDatos.rawQuery(sql, null);
		int cantidad = cursor.getCount();
		cursor.close();
		baseDatos.close();
		return cantidad;
	}

	public int recuperarCantidadReparaciones()
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Reparaciones";
		Cursor cursor = baseDatos.rawQuery(sql, null);
		int cantidad = cursor.getCount();
		cursor.close();
		baseDatos.close();
		return cantidad;
	}

	public int recuperarCantidadFallas()
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Fallas";
		Cursor cursor = baseDatos.rawQuery(sql, null);
		int cantidad = cursor.getCount();
		cursor.close();
		baseDatos.close();
		return cantidad;
	}

	public int recuperarCantidadModelos()
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Modelos";
		Cursor cursor = baseDatos.rawQuery(sql, null);
		int cantidad = cursor.getCount();
		cursor.close();
		baseDatos.close();
		return cantidad;
	}

	public int recuperarCantidadVersiones()
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Versiones";
		Cursor cursor = baseDatos.rawQuery(sql, null);
		int cantidad = cursor.getCount();
		cursor.close();
		baseDatos.close();
		return cantidad;
	}

	public int recuperarCantidadComponentes()
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Componentes";
		Cursor cursor = baseDatos.rawQuery(sql, null);
		int cantidad = cursor.getCount();
		cursor.close();
		baseDatos.close();
		return cantidad;
	}

	// /////////////////FIN RECUPERAR CANTIDAD FILAS

	// /////////////////////// BORRAR FILA TABLAS
	public void borrarReparacion(int id_reparacion)
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("DELETE FROM Tabla_Reparaciones WHERE id_reparacion = " + id_reparacion);
		baseDatos.close();
	}

	public void borrarReparacion(ReparacionesClase oReparacion)
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("DELETE FROM Tabla_Reparaciones WHERE serial = " + oReparacion.getSerial());
		baseDatos.close();
	}

	public void borrarNombreModelo(ModelosClase oModelo)
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("DELETE FROM Tabla_Modelos  WHERE NOM_modelo = '" + oModelo.getNom_modelo() + "'");
		baseDatos.close();
	}

	public void borrarNombreVersion(VersionesClase oVersion)

	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("DELETE FROM Tabla_Versiones  WHERE nom_version ='" + oVersion.getNom_version() + "'");
		baseDatos.close();
	}

	public void borrarNombreFalla(FallasClase oFalla)
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("DELETE FROM Tabla_Fallas  WHERE nom_falla ='" + oFalla.getNom_falla() + "'");
		baseDatos.close();
	}

	public void borrarNombreComponente(ComponentesClase nom_componente)
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("DELETE FROM Tabla_Componentes  WHERE nom_componente ='" + nom_componente.getNom_componente() + "'");
		baseDatos.close();
	}

	// /////////////////////////////////////FIN BORRAR FILA
	// TABLAS/////////////////////////////////

	// ///////////////////////////////////RECUPERAR DATOS DE TABLAS

	// public ArrayList<ReparacionesClase> recuperarReparaciones()
	public ArrayList<ReparacionesClase> recuperarReparaciones(int serie)
	{
		SQLiteDatabase baseDatos = getWritableDatabase();

		String sql = "SELECT * FROM Tabla_Reparaciones WHERE serial=" + serie; // name
																				// LIKE
																				// '%LIM%'

		Cursor cursor = baseDatos.rawQuery(sql, null);
		ArrayList<ReparacionesClase> reparacionArray = new ArrayList<ReparacionesClase>();

		while (cursor.moveToNext())
		{
			ReparacionesClase oReparacion = new ReparacionesClase();

			oReparacion.setId_Reparacion(cursor.getInt(0));

			// / dor formato de fecha... levanto string y lo paso a DATE

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String fecha = cursor.getString(1);
			try
			{
				Date date = formatter.parse(fecha);
				oReparacion.setFecha(date);

			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			oReparacion.setSerial(cursor.getInt(2));
			oReparacion.setId_modelo(cursor.getInt(3));
			oReparacion.setId_version(cursor.getInt(4));
			oReparacion.setId_falla(cursor.getInt(5));
			oReparacion.setObservaciones(cursor.getString(6));
			oReparacion.setHs24(cursor.getInt(7));
			reparacionArray.add(oReparacion);
		}
		cursor.close();
		baseDatos.close();
		return reparacionArray;
	}

	public ArrayList<ReparacionesClase> recuperarReparaciones()

	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		// String sql = "SELECT * FROM Tabla_Reparaciones";
		String sql = "SELECT * FROM Tabla_Reparaciones ORDER BY serial"; // name
																			// LIKE
																			// '%LIM%'

		Cursor cursor = baseDatos.rawQuery(sql, null);
		ArrayList<ReparacionesClase> reparacionArray = new ArrayList<ReparacionesClase>();

		while (cursor.moveToNext())
		{
			ReparacionesClase oReparacion = new ReparacionesClase();

			oReparacion.setId_Reparacion(cursor.getInt(0));

			// / dor formato de fecha... levanto string y lo paso a DATE

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String fecha = cursor.getString(1);
			try
			{
				Date date = formatter.parse(fecha);
				oReparacion.setFecha(date);

			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			oReparacion.setSerial(cursor.getInt(2));
			oReparacion.setId_modelo(cursor.getInt(3));
			oReparacion.setId_version(cursor.getInt(4));
			oReparacion.setId_falla(cursor.getInt(5));
			oReparacion.setObservaciones(cursor.getString(6));
			oReparacion.setHs24(cursor.getInt(7));
			reparacionArray.add(oReparacion);
		}
		cursor.close();
		baseDatos.close();
		return reparacionArray;
	}

	public ArrayList<FallasClase> recuperarFallas()
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Fallas ORDER BY nom_falla";
		Cursor cursor = baseDatos.rawQuery(sql, null);
		ArrayList<FallasClase> fallaArray = new ArrayList<FallasClase>();

		while (cursor.moveToNext())
		{
			FallasClase oFalla = new FallasClase();

			oFalla.setId_falla(cursor.getInt(0));
			oFalla.setNom_falla(cursor.getString(1));

			fallaArray.add(oFalla);
		}
		cursor.close();
		baseDatos.close();
		return fallaArray;
	}

	public ArrayList<ModelosClase> recuperarModelos()
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Modelos ORDER BY nom_modelo ";
		Cursor cursor = baseDatos.rawQuery(sql, null);
		ArrayList<ModelosClase> modeloArray = new ArrayList<ModelosClase>();

		while (cursor.moveToNext())
		{
			ModelosClase oModelo = new ModelosClase();

			oModelo.setIn_modelo(cursor.getInt(0));
			oModelo.setNom_modelo(cursor.getString(1));

			modeloArray.add(oModelo);
		}
		cursor.close();
		baseDatos.close();
		return modeloArray;
	}

	public ArrayList<VersionesClase> recuperarVersiones()
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Versiones";
		Cursor cursor = baseDatos.rawQuery(sql, null);
		ArrayList<VersionesClase> versionArray = new ArrayList<VersionesClase>();

		while (cursor.moveToNext())
		{
			VersionesClase oversion = new VersionesClase();

			oversion.setIn_version(cursor.getInt(0));
			oversion.setNom_version(cursor.getString(1));

			versionArray.add(oversion);
		}
		cursor.close();
		baseDatos.close();
		return versionArray;
	}

	public String[] recuperarNombresModelos()
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Modelos";
		Cursor cursor = baseDatos.rawQuery(sql, null);
		String[] modeloArray = new String[cursor.getCount()];
		int i = 0;
		while (cursor.moveToNext())
		{
			modeloArray[i] = cursor.getString(1);
			i++;
		}
		cursor.close();
		baseDatos.close();
		return modeloArray;
	}

	public String[] recuperarNombresVersiones()
	{
		// levanto base de datos escribible
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Versiones";
		// 7 la cargo en un cursor
		Cursor cursor = baseDatos.rawQuery(sql, null);
		// declaro un arreglo string para levantar nombres de la tabla versiones
		String[] versionArray = new String[cursor.getCount()];// definola
																// cantidad de
																// componentes
		int i = 0;
		while (cursor.moveToNext())
		{
			versionArray[i] = cursor.getString(1);
			i++;
		}
		cursor.close();
		baseDatos.close();
		return versionArray;
	}

	public String[] recuperarNombresFallas()
	{
		// levanto base de datos escribible
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Fallas";
		// 7 la cargo en un cursor
		Cursor cursor = baseDatos.rawQuery(sql, null);
		// declaro un arreglo string para levantar nombres de la tabla versiones
		String[] fallaArray = new String[cursor.getCount()];// definola cantidad
															// de componentes
		int i = 0;
		while (cursor.moveToNext())
		{
			fallaArray[i] = cursor.getString(1);
			i++;
		}
		cursor.close();
		baseDatos.close();
		return fallaArray;
	}

	public String[] recuperarNombresComponentes()
	{

		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Componentes";

		Cursor cursor = baseDatos.rawQuery(sql, null);

		String[] componentesArray = new String[cursor.getCount()];

		int i = 0;

		while (cursor.moveToNext())
		{
			componentesArray[i] = cursor.getString(1);
			i++;
		}
		cursor.close();
		baseDatos.close();

		return componentesArray;

	}

}
