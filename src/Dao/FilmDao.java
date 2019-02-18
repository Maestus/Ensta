package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Film;
import utils.EstablishConnection;

public class FilmDao {

	public boolean insert(Film film) throws SQLException {

		String sql = "INSERT INTO film (titre, realisateur) VALUES (?, ?)";
		boolean rowInserted = false;

		try (Connection connection = EstablishConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, film.getTitre());
			statement.setString(2, film.getRealisateur());
			rowInserted = statement.executeUpdate() > 0;
		}
		return rowInserted;
	}

	public List<Film> getAll() throws SQLException {
		List<Film> listFilm = new ArrayList<>();

		String sql = "SELECT * FROM film";

		try (Connection connection = EstablishConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String titre = resultSet.getString("titre");
				String realisateur = resultSet.getString("realisateur");
				Film film = new Film(id, titre, realisateur);
				listFilm.add(film);
			}
		}
		return listFilm;
	}

	public boolean delete(Film film) throws SQLException {

		String sql = "DELETE FROM film where id = ?";
		boolean rowDeleted = false;

		try (Connection connection = EstablishConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setInt(1, film.getId());
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean update(Film film) throws SQLException {

		String sql = "UPDATE film SET titre = ?, realisateur = ?";
		sql += " WHERE id = ?";
		boolean rowUpdated = false;

		try (Connection connection = EstablishConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, film.getTitre());
			statement.setString(2, film.getRealisateur());
			statement.setInt(3, film.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public Film get(int id) throws SQLException {
		Film film = null;
		String sql = "SELECT * FROM film WHERE id = ?";

		try (Connection connection = EstablishConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);) {

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String titre = resultSet.getString("titre");
				String realisateur = resultSet.getString("realisateur");
				film = new Film(id, titre, realisateur);
			}

			resultSet.close();
		}
		return film;
	}
}
