package org.Iteracion3;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class GestorUsuario {

	private ArrayList<Usuario> listaUsuario;
	
	public GestorUsuario(){
		listaUsuario=new ArrayList<Usuario>();
	}
	
	public boolean readAll() throws SQLException, Exception{
		String sql= "SELECT * FROM Usuario";
		Vector datos=AgenteBBDD.getAgente().select(sql);
		Usuario user;
		boolean res=false;
		
		for(int i=0;i<datos.size();i++){
			user=new Usuario();
			
			user.setdni(((Vector)datos.get(i)).get(0).toString());
			user.setPassword(((Vector)datos.get(i)).get(1).toString());
			user.setnombre(((Vector)datos.get(i)).get(2).toString());
			user.setapellido1(((Vector)datos.get(i)).get(3).toString());
			user.setapellido2(((Vector)datos.get(i)).get(4).toString());
			user.setnacimiento(((Vector)datos.get(i)).get(5).toString());
			user.setTelefonoUser(((Vector)datos.get(i)).get(6).toString());
			
			if ((Integer)((Vector)datos.get(i)).get(7)==1)
				user.setpenalizado(true);
			else
				user.setpenalizado(false);
			
			user.setDireccionCompleta(((Vector)datos.get(i)).get(8).toString());
			
			listaUsuario.add(user); //se anade un usuario a la lista
			res=true;
		}
		return res;
	}
	
	public Usuario read(String dni) throws SQLException, Exception{
		String sql= "SELECT * FROM Usuario WHERE dni='"+ dni + "'";
		Vector datos=AgenteBBDD.getAgente().select(sql);
		
		Usuario user=null;
		
		if(datos.size()!=0){
			user=new Usuario();
			
			user.setdni(((Vector)datos.get(0)).get(0).toString());
			user.setPassword(((Vector)datos.get(0)).get(1).toString());
			user.setnombre(((Vector)datos.get(0)).get(2).toString());
			user.setapellido1(((Vector)datos.get(0)).get(3).toString());
			user.setapellido2(((Vector)datos.get(0)).get(4).toString());
			user.setnacimiento(((Vector)datos.get(0)).get(5).toString());
			user.setTelefonoUser(((Vector)datos.get(0)).get(6).toString());

			if ((Integer)((Vector)datos.get(0)).get(7)==1)
				user.setpenalizado(true);
			else
				user.setpenalizado(false);

			user.setDireccionCompleta(((Vector)datos.get(0)).get(8).toString());
		}
		
		return user;
	}
	
	public boolean insert(Usuario usuario) throws SQLException, Exception{
		boolean res=true;
		int pen=0; //false
		if (usuario.estaPenalizado())
			pen=1;
		
		String sql= "INSERT INTO Usuario VALUES('"+usuario.getDNI()+ "', '"+  usuario.getPassword() + "', '"+  usuario.getnombre() + "', '"+  usuario.getapellido1() + "', '"+  usuario.getapellido2() + "', '"+  usuario.getnacimiento() + "', '"+  usuario.getTelefono() + "', '"+  pen + "', '"+  usuario.getDireccion() + "')";
		if(AgenteBBDD.getAgente().insert(sql)<1){
			res=false;
		}
		return res;
	}
	
	public boolean delete(Usuario usuario) throws SQLException, Exception{
		boolean res=true;
		String sql="DELETE FROM Usuario WHERE dni='" + usuario.getDNI() + "'";
		if(AgenteBBDD.getAgente().delete(sql)<1){
			res=false;
		}
		return res;
	}
	
	public boolean update(Usuario usuario) throws SQLException, Exception{
		boolean res=true;
		int pen=0; //false
		if (usuario.estaPenalizado())
			pen=1;
		
		String sql= "UPDATE Usuario SET dni='"+usuario.getDNI()+ "', password='"+  usuario.getPassword() + "',nombre='"+  usuario.getnombre() + "', apellido1='"+  usuario.getapellido1() + "', apellido2='"+  usuario.getapellido2() + "', nacimiento='"+  usuario.getnacimiento() + "', telefono='"+  usuario.getTelefono() + "', penalizado='"+  pen + "' , direccion='"+  usuario.getDireccion()+"' WHERE dni='" + usuario.getDNI() + "'";
		if(AgenteBBDD.getAgente().update(sql)<1){
			res=false;
		}
		return res;
	}
	
	public ArrayList<Usuario> getListaUsuario(){
		return listaUsuario;
	}
}
