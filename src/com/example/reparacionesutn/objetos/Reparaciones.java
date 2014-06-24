package com.example.reparacionesutn.objetos;

import java.sql.Date;

public class Reparaciones {
	
	int id_Reparacion,serial,id_modelo,id_version,id_falla;
	Date fecha;
	
	public Reparaciones(){
				
		
	}

	public int getId_Reparacion() {
		return id_Reparacion;
	}

	public void setId_Reparacion(int id_Reparacion) {
		this.id_Reparacion = id_Reparacion;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public int getId_modelo() {
		return id_modelo;
	}

	public void setId_modelo(int id_modelo) {
		this.id_modelo = id_modelo;
	}

	public int getId_version() {
		return id_version;
	}

	public void setId_version(int id_version) {
		this.id_version = id_version;
	}

	public int getId_falla() {
		return id_falla;
	}

	public void setId_falla(int id_falla) {
		this.id_falla = id_falla;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
