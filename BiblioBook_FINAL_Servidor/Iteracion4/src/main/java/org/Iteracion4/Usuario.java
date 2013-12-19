package org.Iteracion4;

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
	private ArrayList<Prestamo> prestamos;

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

	public ArrayList<Prestamo> getprestamo(){
		return prestamos;
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

	public void setprestamos(ArrayList<Prestamo> prestamo) {
		this.prestamos = prestamo;
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

	public boolean anadirPrestamo(Libro libro) throws SQLException, Exception {
		Libro lib=new Libro();
		boolean estaLibro=false,res=false;
		Date fecha=Calendar.getInstance().getTime(); //fecha actual
		
		for (int i=0;i<prestamos.size()&&(!estaLibro);i++){
			if(prestamos.get(i).getLibro().getSignatura().equals(libro.getSignatura())){
				estaLibro=true;
			}
		}
		
		lib.leerLibro(libro.getSignatura()); //leemos el libro de la base de datos
		
		if(!estaLibro && lib.getEjemplares()>0 && !penalizado){
			Prestamo prest=new Prestamo();
			prest.setLibro(libro);
			prest.setUsuario(this);
			prest.setfechaPrestamo(fecha.getDate()+"-"+(fecha.getMonth()+1)+"-"+(fecha.getYear()+1900));
			
			Calendar c=Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, 14);
			fecha=c.getTime();
			
			prest.setfechaDevolucion(fecha.getDate()+"-"+(fecha.getMonth()+1)+"-"+(fecha.getYear()+1900));
			prest.setidPrestamo(String.valueOf((int)(Math.random()*10000)));
			prest.setSobrepasado(false);
			
			prestamos.add(prest);
			
			lib.setEjemplares(lib.getEjemplares()-1); //se le quita un ejemplar
			lib.actualizarLibro();
			res=prest.insertarPrestamo(); //se inserta en la tabla de prestamos
		}
		
		return res;
		
	}

	public boolean borrarPrestamo(Libro libro) throws SQLException, Exception {
		Prestamo prest=new Prestamo();
		Libro lib=new Libro();
		Date fecha2=Calendar.getInstance().getTime(); //fecha actual
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1;
		boolean res=false;
		
		for (int i=0;i<prestamos.size();i++){
			if(prestamos.get(i).getLibro().getSignatura().equals(libro.getSignatura())){
				fecha1=sdf.parse(prestamos.get(i).getfechaDevolucion());
				res=prestamos.get(i).eliminarPrestamo();
				prestamos.remove(i);
				//aumenta en uno el ejemplar
				lib.leerLibro(libro.getSignatura());
				lib.setEjemplares(lib.getEjemplares()+1);
				lib.actualizarLibro();
				
				if (fecha2.after(fecha1)){
					penalizado=true;
				}
			}
		}
		return res;
	}

	public ArrayList<Usuario> leerTodos() throws SQLException, Exception {
		GestorUsuario guser=new GestorUsuario();
		guser.readAll();
		return guser.getListaUsuario();/*los usuarios no tienen cargadas la lista de prestamos*/
	}

	public boolean leerUsuario(String dni) throws SQLException, Exception {
		GestorUsuario guser=new GestorUsuario();
		Prestamo prest=new Prestamo();
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

			// se produce la lectura de los prestamos
			prestamos = prest.leerTodos(this);
			res=true;
		}
		
		return res;
	}

	public boolean actualizarUsuario() throws SQLException, Exception {
		GestorUsuario guser=new GestorUsuario();
		return guser.update(this); //se actualizan solo sus datos personales
	}

	public Prestamo buscarPrestamo(Libro libro) throws SQLException, Exception {
		Prestamo prest=null;
		
		for (int i=0;i<prestamos.size();i++){
			if(prestamos.get(i).getLibro().getSignatura().equals(libro.getSignatura())){
				prest=new Prestamo();
				prest.leerPrestamo(prestamos.get(i).getidPrestamo());
			}
		}
		return prest;
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
