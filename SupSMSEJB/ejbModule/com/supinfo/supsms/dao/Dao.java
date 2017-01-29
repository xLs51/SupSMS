package com.supinfo.supsms.dao;

import java.util.List;

public interface Dao<T>
{
	T add(T newObject);
	void update(T object);
	void remove(long id);
	T getById(long id);
	List<T> getAll();
}
