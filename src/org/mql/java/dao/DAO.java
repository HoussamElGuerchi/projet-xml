package org.mql.java.dao;

import java.util.Vector;

public interface DAO<T> {
	Vector<T> getAll();
	void add(T t);
	void delete(int id);
	void update(int id, T t);
}
