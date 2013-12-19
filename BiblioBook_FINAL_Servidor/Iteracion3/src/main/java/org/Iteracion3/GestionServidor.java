package org.Iteracion3;

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
	
	public boolean salirUsuario(){
		usuario=null;
		return true;
	}
}
