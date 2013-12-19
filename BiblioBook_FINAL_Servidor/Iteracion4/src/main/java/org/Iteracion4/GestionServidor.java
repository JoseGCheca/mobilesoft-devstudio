package org.Iteracion4;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class GestionServidor extends Usuario {
	private Usuario usuario;

	public GestionServidor(){
		usuario=null;
	}
	
	public boolean autenticar(String pass,String dni) throws SQLException, Exception{
		usuario=new Usuario();
		boolean res=false;
		if(usuario.leerUsuario(dni)){
			res=usuario.autenticar(pass);
		}
		if(!res)
			usuario=null;
		
		return res;
	}
	
	public boolean modificarContrasena(String oldPass,String newPass) throws SQLException, Exception{
		boolean res=false;
		if(usuario!=null){
			if(usuario.setContrasena(oldPass, newPass))
				res=usuario.actualizarUsuario();
		}
		return res;
	}
	
	public boolean solicitarPrestamo(String signatura) throws SQLException, Exception{
		Libro lib=new Libro();
		
		boolean res=false;
		if(usuario!=null){
			lib.setSignatura(signatura);
			res=usuario.anadirPrestamo(lib);
		}
		return res;
	}
	
	public boolean renovarPrestamo(String signatura) throws SQLException, Exception{
		Prestamo prest;
		Libro lib=new Libro();
		boolean res=false;
		
		if(usuario!=null){
			lib.setSignatura(signatura);
			prest=usuario.buscarPrestamo(lib);
			res=prest.aumentarPlazo(14);
		}
		return res;
	}
	
	
	
	public boolean modificarTelefono(String telefono) throws SQLException, Exception{
		boolean res=false;
		if(usuario!=null){
			usuario.setTelefonoUser(telefono);
			res=usuario.actualizarUsuario();
		}
		return res;
	}
	public boolean modificarDireccion(String tipoVia,String nombreVia) throws SQLException, Exception{
		boolean res=false;
		if(usuario!=null){
			usuario.setDireccion(tipoVia,nombreVia);
			res=usuario.actualizarUsuario();
		}
		return res;
	}
	
	public String consultarMisDatos(){
		Vector v;
		String res="";
		
		if(usuario!=null){
			v=usuario.getAll();
			for(int i=0;i<v.size();i++){
				res+=":"+v.get(i).toString();
			}
		}
		return res;
	}
	
	public String consultarLibro(String titulo) throws SQLException, Exception{
		Libro lib=new Libro();
		String res="";
		
		if (usuario!=null){
			if(lib.leerLibroTitulo(titulo)){
				res+=":"+lib.getTitulo();
				res+=":"+lib.getNombreAutor();
				res+=":"+lib.getEditorial();
				res+=":"+lib.getEdicion();
				res+=":"+lib.getSignatura();
				res+=":"+lib.getISBN();
				res+=":"+lib.getFechaPublicacion();
				res+=":"+lib.getEjemplares();
			}
		}
		return res;
	}
	public boolean devolverLibro(String signatura) throws SQLException, Exception{
		Libro lib=new Libro();
		boolean res=false;
		
		if(usuario!=null){
			lib.setSignatura(signatura);
			res=usuario.borrarPrestamo(lib);
		}
		return res;
	}
	
	public boolean salirUsuario(){
		usuario=null;
		return true;
	}
}
