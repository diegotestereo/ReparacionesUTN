package com.example.reparacionesutn.DAOs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.example.reparacionesutn.objetos.Fallas;
import com.example.reparacionesutn.objetos.Modelos;
import com.example.reparacionesutn.objetos.Reparaciones;
import com.example.reparacionesutn.objetos.Versiones;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelperAdaptador extends SQLiteOpenHelper {

	public SQLHelperAdaptador(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// Creo la tabla reparaciones
		db.execSQL("CREATE TABLE Tabla_Reparaciones (id_reparacion INTEGER PRIMARY KEY AUTOINCREMENT,fecha_in TEXT"
				+ ",id_modelo INT,id_version INT,id_falla INT,observaciones TEXT)");
				//creo tabla modelos de equipos
		db.execSQL("CREATE TABLE Tabla_Modelos (id_modelo INTEGER PRIMARY KEY AUTOINCREMENT,nom_modelo TEXT)");
		
		//creo tabla tipo de version de firmware
		db.execSQL("CREATE TABLE Tabla_Versiones (id_version INTEGER PRIMARY KEY AUTOINCREMENT,nom_version TEXT)");
		
		//creo tabla tipo de reparaciones
		db.execSQL(	"CREATE TABLE Tabla_Fallas (id_falla INTEGER PRIMARY KEY AUTOINCREMENT,nom_falla TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		  // borro tabla
			db.execSQL("DROP TABLE IF EXISTS Tabla_Reparaciones");
			// Creo la tabla reparaciones
			db.execSQL("CREATE TABLE Tabla_Reparaciones (id_reparacion INTEGER PRIMARY KEY AUTOINCREMENT , fecha_in DATE"
					+ ",serial INT,id_modelo INT, id_version INT, id_falla INT,observaciones TEXT)");
			
			// borro tabla
			db.execSQL("DROP TABLE IF EXISTS Tabla_Modelos");
			//creo tabla modelos de equipos
			db.execSQL("CREATE TABLE Tabla_Modelos (id_modelo INTEGER PRIMARY KEY AUTOINCREMENT , nom_modelo TEXT)");
			
			// borro tabla
			db.execSQL("DROP TABLE IF EXISTS Tabla_versiones");
			//creo tabla tipo de version de firmware
			db.execSQL("CREATE TABLE Tabla_versiones (id_version INTEGER PRIMARY KEY AUTOINCREMENT , nom_version TEXT)");
			
			// borro tabla
			db.execSQL("DROP TABLE IF EXISTS Tabla_fallas");
			//creo tabla tipo de reparaciones
			db.execSQL(	"CREATE TABLE Tabla_fallas (id_falla INTEGER PRIMARY KEY AUTOINCREMENT , nom_falla TEXT)");

	}
	
	
	
	//***********************************************************************************************************
	///////////////////// INSERTAR EN TABLAS //////////////////////////////////////////////////////////////////7
	
	public void insertarReparacion(String fecha_in,int serial, int id_modelo , int id_version , int id_falla ,String observaciones ){
		

		 SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("INSERT INTO Tabla_Reparaciones (serial , fecha_in ,id_modelo,id_version,id_falla, observaciones ) "
		 		+ "VALUES ("+fecha_in+"','"+serial+"','"+id_modelo+"','"+id_version+"','"+id_falla+"','"+observaciones+"'"
		 		+")");
		 baseDatos.close(); 
	}
	
	public void insertarFalla(String nom_falla ){

		SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("INSERT INTO Tabla_Fallas (nom_falla) VALUES ("+nom_falla+")");
		 baseDatos.close(); 
	}
	
	public void insertarModelo(String nom_modelo ){

		SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("INSERT INTO Tabla_Modelos (nom_modelo) VALUES ("+nom_modelo+")");
		 baseDatos.close(); 
	}
		
	public void insertarVersion(String nom_version ){

		SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("INSERT INTO Tabla_versiones (nom_version) VALUES ("+nom_version+")");
		 baseDatos.close(); 
	}
	
	//////////////////////FIN INSERTAR EN TABLAS //////////////////////////////////////////////////////////////////7
	//***********************************************************************************************************
	//////////////////// RECUPERAR CANTIDAD FILAS TABLAS///////////////////////////////////////////////////////
	
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
	
	///////////////////FIN RECUPERAR CANTIDAD FILAS TABLAS///////////////////////////////////////////////////////
	//**************************************************************************************
	///////////////////////// BORRAR FILA TABLAS /////////////////////////////////////////////////////////////////////////////////
	
	public void borrarReparacion(Reparaciones oReparacion) 
	{
		 SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("DELETE FROM Tabla_Reparaciones WHERE serial ='"+oReparacion.getSerial()+"'");
		 baseDatos.close(); 	
	}
		
	public void borrarNombreModelo(Modelos oModelo) 
	{
		 SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("DELETE FROM Tabla_Modelos  WHERE nom_modelo ='"+oModelo.getNom_modelo()+"'");
		 baseDatos.close(); 	
	}
	
	public void borrarNombreVersion(Versiones oVersion) 

	{
		 SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("DELETE FROM Tabla_Versiones  WHERE nom_version ='"+oVersion.getNom_version()+"'");
		 baseDatos.close(); 	
	}
	
	public void borrarNombreFalla(Fallas oFalla) 
	{
		 SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("DELETE FROM Tabla_Fallas  WHERE nom_falla ='"+oFalla.getNom_falla()+"'");
		 baseDatos.close(); 	
	}
	
	
	///////////////////////////////////////FIN BORRAR FILA TABLAS//////////////////////////////////////////////////////////////

	/////////////////////////////////////RECUPERAR DATOS DE TABLAS ////////////////////////////////////
	
	@SuppressLint("SimpleDateFormat") public ArrayList<Reparaciones> recuperarReparaciones()
	{
		 SQLiteDatabase baseDatos = getWritableDatabase(); 
		 String sql = "SELECT * FROM Tabla_Reparaciones"; 
		 Cursor cursor = baseDatos.rawQuery(sql, null); 
		 ArrayList<Reparaciones> reparacionArray =new ArrayList<Reparaciones>();  
		 
	
		 
		 
		 while (cursor.moveToNext()) 
		 { 
			 Reparaciones oReparacion=new Reparaciones(); 
			 
			 oReparacion.setId_Reparacion(cursor.getInt(0));
			
			 /// dor formato de fecha... levanto string y lo paso a DATE
			 
			 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			 String  fecha=cursor.getString(1);
			 try {	Date date = formatter.parse(fecha);
			 		oReparacion.setFecha(date);
			  
			 } catch (ParseException e) {
			 		e.printStackTrace();
			 	}
			 
			 oReparacion.setId_modelo(cursor.getInt(2));
			 oReparacion.setId_version(cursor.getInt(3));
			 oReparacion.setId_falla(cursor.getInt(4));
			 oReparacion.setObservaciones(cursor.getString(5));
			
			 reparacionArray.add(oReparacion);
		 }       
		 cursor.close();
		 baseDatos.close();
		 return reparacionArray;
	}
		
	public ArrayList<Fallas> recuperarFallas()
	{
		 SQLiteDatabase baseDatos = getWritableDatabase(); 
		 String sql = "SELECT * FROM Tabla_Fallas"; 
		 Cursor cursor = baseDatos.rawQuery(sql, null); 
		 ArrayList<Fallas> fallaArray =new ArrayList<Fallas>();  
		 
		 while (cursor.moveToNext()) 
		 { 
			 Fallas oFalla=new Fallas(); 
			 
			 oFalla.setId_falla(cursor.getInt(0));
			 oFalla.setNom_falla(cursor.getString(1));
			
			 fallaArray.add(oFalla);
		 }       
		 cursor.close();
		 baseDatos.close();
		 return fallaArray;
	}
	
	public ArrayList<Modelos> recuperarModelos()
	{
		 SQLiteDatabase baseDatos = getWritableDatabase(); 
		 String sql = "SELECT * FROM Tabla_Modelos"; 
		 Cursor cursor = baseDatos.rawQuery(sql, null); 
		 ArrayList<Modelos> modeloArray =new ArrayList<Modelos>();  
		 
		 while (cursor.moveToNext()) 
		 { 
			 Modelos  oModelo=new Modelos(); 
			 
			 oModelo.setIn_modelo(cursor.getInt(0));
			 oModelo.setNom_modelo(cursor.getString(1));
			
			 modeloArray.add(oModelo);
		 }       
		 cursor.close();
		 baseDatos.close();
		 return modeloArray;
	}
	
	public ArrayList<Versiones> recuperarVersiones()
	{
		 SQLiteDatabase baseDatos = getWritableDatabase(); 
		 String sql = "SELECT * FROM Tabla_Versiones"; 
		 Cursor cursor = baseDatos.rawQuery(sql, null); 
		 ArrayList<Versiones> versionArray =new ArrayList<Versiones>();  
		 
		 while (cursor.moveToNext()) 
		 { 
			 Versiones  oversion=new Versiones(); 
			 
			 oversion.setIn_version(cursor.getInt(0));
			 oversion.setNom_version(cursor.getString(1));
			
			 versionArray.add(oversion);
		 }       
		 cursor.close();
		 baseDatos.close();
		 return versionArray;
	}
	
	
}
