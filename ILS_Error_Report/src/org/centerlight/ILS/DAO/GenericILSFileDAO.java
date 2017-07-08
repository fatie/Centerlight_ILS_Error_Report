package org.centerlight.ILS.DAO;

import java.util.List;

public interface GenericILSFileDAO <T, Pk> {
	
	Pk create(T t);
	T read(Pk pk);
	List<T> readAll();
	boolean update(Pk pk, T t);
	boolean delete(T t);

}
