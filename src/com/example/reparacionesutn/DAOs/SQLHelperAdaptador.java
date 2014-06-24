package com.example.reparacionesutn.DAOs;

import java.sql.Date;
import java.util.ArrayList;


import com.example.reparacionesutn.objetos.Fallas;

import com.example.reparacionesutn.objetos.Modelos;

import com.example.reparacionesutn.objetos.Reparaciones;

import com.example.reparacionesutn.objetos.Versiones;

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
		db.execSQL("CREATE TABLE Tabla_Reparaciones (id_reparacion INTEGER PRIMARY KEY AUTONINCREMENT , fecha_in DATE"
				+ ",id_modelo INT, id_version INT, id_falla INT,observaciones TEXT)");
				//creo tabla modelos de equipos
		db.execSQL("CREATE TABLE Tabla_Modelos (id INTEGER PRIMARY KEY AUTONINCREMENT , nom_modelo TEXT)");
		
		//creo tabla tipo de version de firmware
		db.execSQL("CREATE TABLE Tabla_versiones (id INTEGER PRIMARY KEY AUTONINCREMENT , nom_version TEXT)");
		
		//creo tabla tipo de reparaciones
		db.execSQL(	"CREATE TABLE Tabla_fallas (id INTEGER PRIMARY KEY AUTONINCREMENT , nom_falla TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		  // borro tabla
			db.execSQL("DROP TABLE IF EXISTS Tabla_Reparaciones");
			// Creo la tabla reparaciones
			db.execSQL("CREATE TABLE Tabla_Reparaciones (id_reparacion INTEGER PRIMARY KEY AUTONINCREMENT , fecha_in DATE"
					+ ",serial INT,id_modelo INT, id_version INT, id_falla INT,observaciones TEXT)");
			
			// borro tabla
			db.execSQL("DROP TABLE IF EXISTS Tabla_Modelos");
			//creo tabla modelos de equipos
			db.execSQL("CREATE TABLE Tabla_Modelos (id INTEGER PRIMARY KEY AUTONINCREMENT , nom_modelo TEXT)");
			
			// borro tabla
			db.execSQL("DROP TABLE IF EXISTS Tabla_versiones");
			//creo tabla tipo de version de firmware
			db.execSQL("CREATE TABLE Tabla_versiones (id INTEGER PRIMARY KEY AUTONINCREMENT , nom_version TEXT)");
			
			// borro tabla
			db.execSQL("DROP TABLE IF EXISTS Tabla_fallas");
			//creo tabla tipo de reparaciones
			db.execSQL(	"CREATE TABLE Tabla_fallas (id INTEGER PRIMARY KEY AUTONINCREMENT , nom_falla TEXT)");

	}
	
	
	public void insertarReparacion(Date fecha_in,int serial, int id_modelo , int id_version , int id_falla ,String observaciones ){
		

		 SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("INSERT INTO Tabla_Reparaciones (serial , fecha_in ,id_modelo  , id_version , id_falla , observaciones ) "
		 		+ "VALUES ("+fecha_in+",'"+serial+"','"+id_modelo+"','"+id_version+"'"
		 		+","+id_falla+",'"+observaciones+"'"
		 		+")");
		 baseDatos.close(); 
		
		
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
		 baseDatos.execSQL("DELETE FROM Tabla_Modelos  WHERE nom_modelo ='"+oVersion.getNom_version()+"'");
		 baseDatos.close(); 	
	}
	public void borrarNombreFalla(Fallas oFalla) 
	{
		 SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("DELETE FROM Tabla_Modelos  WHERE nom_modelo ='"+oFalla.getNom_falla()+"'");
		 baseDatos.close(); 	
	}
/*
	public ArrayList<Libro> recuperarDatos()
	{
		 SQLiteDatabase baseDatos = getWritableDatabase(); 
		 String sql = "SELECT * FROM libros"; 
		 Cursor cursor = baseDatos.rawQuery(sql, null); 
		 ArrayList<Libro >libros=new ArrayList<Libro>();  
		 while (cursor.moveToNext()) 
		 { 
			 Libro oLibro=new Libro(); 
			 oLibro.setCantidadHojas(cursor.getInt(0));
			 oLibro.setNombre(cursor.getString(1));
			 oLibro.setAutor(cursor.getString(2));
			 oLibro.setPrecio(cursor.getInt(3));
			 oLibro.setCodigo(cursor.getString(4));
			 libros.add(oLibro);
		 }       
		 cursor.close();
		 baseDatos.close();
		 return libros;
	}
	
	
	
	
	public void borrarLibro(Libro olibro) 
	{
		 SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("DELETE FROM libros where nombre ='"+olibro.getNombre()+"'");
		 baseDatos.close(); 	
	}

	public void actualizarLibro(Libro olibro)
	{
		 SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("UPDATE libros set precio ="+olibro.getPrecio()+", cantidadhojas ="+olibro.getCantidadHojas()+" where nombre = '"+olibro.getNombre()+"';" );
		 baseDatos.close(); 
	}*/
}
