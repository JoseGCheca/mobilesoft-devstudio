package org.Iteracion2;

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
	
	
	public boolean salirUsuario(){
		usuario=null;
		return true;
	}
}
