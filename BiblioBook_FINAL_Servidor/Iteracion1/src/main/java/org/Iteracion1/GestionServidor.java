package org.Iteracion1;

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
	
	
}
