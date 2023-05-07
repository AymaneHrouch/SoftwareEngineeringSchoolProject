package marksManager.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import marksManager.dao.Dao;
import marksManager.database.Database;
import marksManager.model.Entity;

public abstract class DefaultDao<T extends Entity> implements Dao<T> {
	Database database = Database.getInstance();

	public abstract String getTableName();

	public abstract String getPrimaryKeyLabel();

	public abstract String[] getColumnLabels();

	public abstract T map(Map<String, String> tMap);

	/*
	 * Get T as a map with key=column label and value=column value excluding the
	 * primary key column
	 */
	public abstract Map<String, String> getColumns(T t);

	public List<T> findBy(String columnLabel, String columnValue) {
		System.out.println(
				"Getting all items from " + getTableName() + " WHERE " + columnLabel + "="
						+ columnValue);

		ResultSet resultSet = database.executeQuery(
				"SELECT * FROM " + getTableName() + " WHERE " + columnLabel + "="
						+ columnValue);
		List<T> items = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Map<String, String> hashMap = resultSetToMap(resultSet);
				T bean = map(hashMap);
				items.add(bean);
			}
			return items;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Map<String, String> resultSetToMap(ResultSet resultSet) throws SQLException {
		HashMap<String, String> hashMap = new HashMap<>();
		String[] columns = getColumnLabels();
		for (int i = 0; i < columns.length; i++) {
			hashMap.put(columns[i], resultSet.getString(columns[i]));
		}
		return hashMap;
	}

	public Optional<T> findById(Long id) {
		System.out.println("Fetching element with primary key = " + id + " from table "
				+ getTableName());

		ResultSet resultSet = database
				.executeQuery("SELECT * FROM " + getTableName() + " WHERE "
						+ getPrimaryKeyLabel() + "=" + id);
		try {
			if (resultSet.next()) {
				Map<String, String> hashMap = resultSetToMap(resultSet);
				T bean = map(hashMap);
				return Optional.ofNullable(bean);
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public List<T> findAll() {
		System.out.println("Getting all items from " + getTableName());

		ResultSet resultSet = database.executeQuery("SELECT * FROM " + getTableName());
		List<T> items = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Map<String, String> hashMap = resultSetToMap(resultSet);
				T bean = map(hashMap);
				items.add(bean);
			}
			return items;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	public void save(T entity) {
		System.out.println("Saving " + entity);
		StringBuilder insertStatement = new StringBuilder("INSERT INTO ");
		insertStatement.append(getTableName() + "(");

		Map<String, String> columns = getColumns(entity);

		StringBuilder keys = new StringBuilder();
		StringBuilder values = new StringBuilder();

		for (Entry<String, String> entry : columns.entrySet()) {
			keys.append(entry.getKey() + ", ");
			values.append("'" + entry.getValue() + "', ");
		}
		keys.setLength(keys.length() - 2);
		values.setLength(values.length() - 2);

		insertStatement.append(keys + ") ");
		insertStatement.append("VALUES(");
		insertStatement.append(values + ")");

		database.executeUpdate(insertStatement.toString());

		ResultSet rs = database.executeQuery(
				"SELECT max(" + getPrimaryKeyLabel() + ") FROM " + getTableName());

		// Set the new Id
		Long newId;
		try {
			rs.next();
			newId = rs.getLong(1);
			entity.setId(newId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(T entity) {
		System.out.print("Updating to " + entity);

		StringBuilder updateStatement = new StringBuilder(
				"UPDATE " + getTableName() + " SET ");

		Map<String, String> columns = getColumns(entity);

		StringBuilder keys = new StringBuilder();
		StringBuilder values = new StringBuilder();

		for (Entry<String, String> entry : columns.entrySet()) {
			updateStatement.append(entry.getKey() + "='" + entry.getValue() + "', ");
		}
		updateStatement.setLength(updateStatement.length() - 2);

		updateStatement.append(" WHERE " + getPrimaryKeyLabel() + "=" + entity.getId());

		System.out.println(updateStatement);
		database.executeUpdate(updateStatement.toString());
	}

	public void delete(Long id) {
		System.out.println("Deleting row with primary key " + id + " from table "
				+ getTableName());

		database.executeUpdate(
				"DELETE FROM " + getTableName() + " WHERE " + getPrimaryKeyLabel() + "="
						+ id);
	}

	public void printAll() throws Exception {
		List<T> items = findAll();
		for (T t : items)
			System.out.println(t);
	}

}
