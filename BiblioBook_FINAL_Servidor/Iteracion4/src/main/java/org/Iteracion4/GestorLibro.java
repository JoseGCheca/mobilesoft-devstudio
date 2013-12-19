package org.Iteracion4;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class GestorLibro {
	
	private ArrayList<Libro> listaLibros;
	
	public GestorLibro(){
		listaLibros=new ArrayList<Libro>();
	}
	
	public boolean readAll() throws SQLException, Exception{
		String sql= "SELECT * FROM Libro";
		Vector datos=AgenteBBDD.getAgente().select(sql);
		Libro libro;
		boolean res=false;
		
		for(int i=0;i<datos.size();i++){
			libro=new Libro();
			
			libro.setISBN(((Vector)datos.get(i)).get(5).toString());
			libro.setTitulo(((Vector)datos.get(i)).get(0).toString());
			libro.setNombreAutor(((Vector)datos.get(i)).get(1).toString());
			libro.setEditorial(((Vector)datos.get(i)).get(2).toString());
			libro.setEdicion((Integer)((Vector)datos.get(i)).get(3));
			libro.setSignatura(((Vector)datos.get(i)).get(4).toString());
			libro.setFechaPublicacion(((Vector)datos.get(i)).get(6).toString());
			libro.setEjemplares((Integer)((Vector)datos.get(i)).get(7));
			
			listaLibros.add(libro); //se anade un libro a la lista
			res=true;
		}
		return res;
	}
	
	public Libro read(String signatura) throws SQLException, Exception{
		String sql= "SELECT * FROM Libro WHERE signatura='"+ signatura + "'";
		Vector datos=AgenteBBDD.getAgente().select(sql);
		
		Libro libro=null;
		
		if (datos.size()!=0){
			libro=new Libro();

			libro.setISBN(((Vector)datos.get(0)).get(5).toString());
			libro.setTitulo(((Vector)datos.get(0)).get(0).toString());
			libro.setNombreAutor(((Vector)datos.get(0)).get(1).toString());
			libro.setEditorial(((Vector)datos.get(0)).get(2).toString());
			libro.setEdicion((Integer)((Vector)datos.get(0)).get(3));
			libro.setSignatura(((Vector)datos.get(0)).get(4).toString());
			libro.setFechaPublicacion(((Vector)datos.get(0)).get(6).toString());
			libro.setEjemplares((Integer)((Vector)datos.get(0)).get(7));
		}
		
		return libro;
	}
	
	public boolean insert(Libro libro) throws SQLException, Exception{
		boolean res=true;
		String sql= "INSERT INTO Libro VALUES('"+libro.getTitulo()+ "', '"+  libro.getNombreAutor() + "', '"+  libro.getEditorial() + "', '"+  libro.getEdicion() + "', '"+  libro.getSignatura() + "', '"+  libro.getISBN() + "', '"+  libro.getFechaPublicacion() + "', '"+  libro.getEjemplares() + "')";
		if(AgenteBBDD.getAgente().insert(sql)<1){
			res=false;
		}
		return res;
	}
	
	public boolean delete(Libro libro) throws SQLException, Exception{
		boolean res=true;
		String sql="DELETE FROM Libro WHERE signatura='" + libro.getSignatura() + "'";
		if(AgenteBBDD.getAgente().delete(sql)<1){
			res=false;
		}
		return res;
		
	}
	
	public boolean update(Libro libro) throws SQLException, Exception{
		boolean res=true;
		String sql= "UPDATE Libro SET titulo='"+libro.getTitulo()+ "', autor='"+  libro.getNombreAutor() + "',editorial='"+  libro.getEditorial() + "', edicion='"+  libro.getEdicion() + "', signatura='"+  libro.getSignatura() + "', isbn='"+  libro.getISBN() + "', fecha='"+  libro.getFechaPublicacion() + "', ejemplares='"+  libro.getEjemplares() + "' WHERE signatura='" + libro.getSignatura() + "'";
		if(AgenteBBDD.getAgente().update(sql)<1){
			res=false;
		}
		return res;
	}
	
	public Libro readTitle(String title) throws SQLException, Exception{
		String sql= "SELECT * FROM Libro WHERE titulo='"+ title + "'";
		Vector datos=AgenteBBDD.getAgente().select(sql);
		
		Libro libro=null;
		
		if (datos.size()!=0){
			libro=new Libro();
			
			libro.setISBN(((Vector)datos.get(0)).get(5).toString());
			libro.setTitulo(((Vector)datos.get(0)).get(0).toString());
			libro.setNombreAutor(((Vector)datos.get(0)).get(1).toString());
			libro.setEditorial(((Vector)datos.get(0)).get(2).toString());
			libro.setEdicion((Integer)((Vector)datos.get(0)).get(3));
			libro.setSignatura(((Vector)datos.get(0)).get(4).toString());
			libro.setFechaPublicacion(((Vector)datos.get(0)).get(6).toString());
			libro.setEjemplares((Integer)((Vector)datos.get(0)).get(7));
		}
		
		return libro;
	}
	
	public ArrayList<Libro> getListaLibros(){
		return listaLibros;
	}
}
