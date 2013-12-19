package org.Iteracion1;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;



public class Usuario {
	
	private String dni;
	private String password;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String nacimiento;
	private String telefono;
	private boolean penalizado;
	private String direccion;
	

	public Usuario() {
		
	}
	
	public String getDNI(){
		return dni;
	}
	public String getnombre(){
		return nombre;
	}
	public String getapellido1(){
		return apellido1;
	}
	public String getapellido2(){
		return apellido2;
	}
	public String getnacimiento(){
		return nacimiento;
	}
	
	public String getTelefono(){
		return telefono;
	}
	
	public String getDireccion(){
		return direccion;
	}
	
	public String getPassword(){
		return password;
	}
///////////////

	public void setdni(String dni) {
		this.dni = dni;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	public void setapellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setapellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setnacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}

	public void setpenalizado(boolean penalizado) {
		this.penalizado = penalizado;
	}


	
	public void setDireccionCompleta(String dir){
		direccion=dir;
	}
	
	public void setPassword(String pass){
		password=pass;
	}
	
	public void setTelefonoUser(String telefono){
		this.telefono=telefono;
	}
	

	public void setDireccion(String tipoVia, String nombreVia)  {
		direccion=tipoVia+" "+nombreVia;
	}

	public boolean setContrasena(String oldPass, String newPass) throws SQLException, Exception {
		boolean res=false;
		if (password.equals(oldPass)){
			password=newPass;
			res=true;
		}
		return res;
	}
	
	/////////////////////////
	
	public boolean autenticar(String password) {
		return this.password.equals(password);
	}

	public boolean estaPenalizado() {
		return penalizado;
	}


	

	public ArrayList<Usuario> leerTodos() throws SQLException, Exception {
		GestorUsuario guser=new GestorUsuario();
		guser.readAll();
		return guser.getListaUsuario();/*los usuarios no tienen cargadas la lista de prestamos*/
	}

	public boolean leerUsuario(String dni) throws SQLException, Exception {
		GestorUsuario guser=new GestorUsuario();
		
		Usuario user=guser.read(dni);
		boolean res=false;
		
		if (user != null) {

			this.dni = user.getDNI();
			nombre = user.getnombre();
			apellido1 = user.getapellido1();
			apellido2 = user.getapellido2();
			password = user.getPassword();
			nacimiento = user.getnacimiento();
			direccion = user.getDireccion();
			telefono = user.getTelefono();
			penalizado = user.estaPenalizado();

			
			res=true;
		}
		
		return res;
	}

	public boolean actualizarUsuario() throws SQLException, Exception {
		GestorUsuario guser=new GestorUsuario();
		return guser.update(this); //se actualizan solo sus datos personales
	}

	
	public Vector getAll(){
		Vector data=new Vector();
		data.add(dni);
		data.add(password);
		data.add(nombre);
		data.add(apellido1);
		data.add(apellido2);
		data.add(nacimiento);
		data.add(telefono);
		data.add(penalizado);
		data.add(direccion);
		
		return data;
	}
}
