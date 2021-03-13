package org.mql.java.dao;

import java.util.Vector;

public interface DAO<T> {
	Vector<T> getAll();
	T get(int id);
	void add(T t);
	void delete(int id);
	void update(int id, T t);
}
