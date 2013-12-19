package org.Iteracion5;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class GestorPrestamo {

	private ArrayList<Prestamo> lPrestamosUsuario;
	
	public GestorPrestamo(){
		lPrestamosUsuario=new ArrayList<Prestamo>();
		
	}
	
	
	public boolean readAll(Usuario usuario) throws SQLException, Exception{
		String sql= "SELECT * FROM Prestamo WHERE usuario='"+ usuario.getDNI() + "'";
		Vector datos=AgenteBBDD.getAgente().select(sql);
		Prestamo libro;
		Usuario u;
		Libro l;
		boolean res=false;
		
		for(int i=0;i<datos.size();i++){
			libro=new Prestamo();
			u=new Usuario();
			l=new Libro();
			
			u.setdni(((Vector)datos.get(i)).get(0).toString());
			libro.setUsuario(u);
			
			libro.setfechaPrestamo(((Vector)datos.get(i)).get(1).toString());
			
			libro.setfechaDevolucion(((Vector)datos.get(i)).get(2).toString());
			
			if ((Integer)((Vector)datos.get(i)).get(3)==1)
				libro.setSobrepasado(true);
			else
				libro.setSobrepasado(false);
			
			l.setSignatura(((Vector)datos.get(i)).get(4).toString());
			libro.setLibro(l);
			
			libro.setidPrestamo(((Vector)datos.get(i)).get(5).toString());
			
			lPrestamosUsuario.add(libro); //se anade un prestamo a la lista
			res=true;
		}
		return res;
	}
	
	
	public Prestamo read(String idPrestamo) throws SQLException, Exception{
		String sql= "SELECT * FROM Prestamo WHERE idPrestamo='"+ idPrestamo + "'";
		Vector datos=AgenteBBDD.getAgente().select(sql);
		
		Prestamo libro=null;
		Usuario u=new Usuario();
		Libro l=new Libro();
		
		if(datos.size()!=0){
			libro=new Prestamo();

			u.setdni(((Vector)datos.get(0)).get(0).toString());
			libro.setUsuario(u);

			libro.setfechaPrestamo(((Vector)datos.get(0)).get(1).toString());

			libro.setfechaDevolucion(((Vector)datos.get(0)).get(2).toString());

			if ((Integer)((Vector)datos.get(0)).get(3)==1)
				libro.setSobrepasado(true);
			else
				libro.setSobrepasado(false);

			l.setSignatura(((Vector)datos.get(0)).get(4).toString());
			libro.setLibro(l);

			libro.setidPrestamo(((Vector)datos.get(0)).get(5).toString());
		}
		
		return libro;
	}
	
	
	public boolean insert(Prestamo p) throws SQLException, Exception{
		boolean res=true;
		int sob=0; //false
		if (p.getSobrepasado())
			sob=1;
		
		String sql= "INSERT INTO Prestamo VALUES('"+p.getUsuario().getDNI()+ "', '"+  p.getfechaPrestamo() + "', '"+  p.getfechaDevolucion() + "', '"+  sob + "', '"+  p.getLibro().getSignatura() + "', '"+  p.getidPrestamo() + "')";
		if(AgenteBBDD.getAgente().insert(sql)<1){
			res=false;
		}
		return res;
	}
	
	
	public boolean delete(Prestamo p) throws SQLException, Exception{
		boolean res=true;
		String sql="DELETE FROM Prestamo WHERE idPrestamo='" + p.getidPrestamo() + "'";
		if(AgenteBBDD.getAgente().delete(sql)<1){
			res=false;
		}
		return res;
	}
	
	
	public boolean update(Prestamo p) throws SQLException, Exception{
		boolean res=true;
		int sob=0; //false
		if (p.getSobrepasado())
			sob=1;
		String sql= "UPDATE Prestamo SET usuario='"+p.getUsuario().getDNI()+ "', fPrestamo='"+  p.getfechaPrestamo() + "',fDevolucion='"+  p.getfechaDevolucion() + "', sobrepasado='"+  sob + "', libro='"+  p.getLibro().getSignatura() + "', idPrestamo='"+  p.getidPrestamo() + "' WHERE idPrestamo='" + p.getidPrestamo() + "'";
		if(AgenteBBDD.getAgente().update(sql)<1){
			res=false;
		}
		return res;
	}
	
	
	public ArrayList<Prestamo> getListaPrestamo(){
		return lPrestamosUsuario;
	}
}
