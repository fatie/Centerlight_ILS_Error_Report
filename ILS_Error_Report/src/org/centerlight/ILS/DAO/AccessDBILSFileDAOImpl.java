package org.centerlight.ILS.DAO;

import java.sql.Connection;
import java.util.List;

import org.centerlight.ILS.db.ConnectionFactory;

public class AccessDBILSFileDAOImpl<T, Pk> implements GenericILSFileDAO<T, Pk> {
	
	Connection conn = ConnectionFactory.getConnection();

	private Pk getPk(){
		return null;
	}
	@Override
	public Pk create(T t) {
		
		return null;
	}

	@Override
	public T read(Pk pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Pk pk, T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(T t) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
