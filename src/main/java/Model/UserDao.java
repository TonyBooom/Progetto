package Model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class UserDao
{
	private static final String TABLE_NAME = "utente";
	
	public synchronized  void doSave(User user) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UserDao.TABLE_NAME
					+ " (email, password, nome, cognome, codice_fiscale)"
					+ " VALUES (?, ?, ?, ?, ?)";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getNome());
			preparedStatement.setString(4, user.getCognome());
			preparedStatement.setString(5, user.getCodiceFiscale());
			
			preparedStatement.executeUpdate();

			connection.commit();
		} 
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}
	
public synchronized void doUpdate (User var, String vecchiaMail) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String upsql = "UPDATE " + UserDao.TABLE_NAME + 
						" SET email = ?, password = ?, nome = ?, cognome = ?, codice_fiscale = ? " + 
						"WHERE (email = ?) ";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(upsql);
			
			preparedStatement.setString(1, var.getEmail());
			preparedStatement.setString(2, var.getPassword());
			preparedStatement.setString(3, var.getNome());
			preparedStatement.setString(4, var.getCognome());
			preparedStatement.setString(5, var.getCodiceFiscale());
			preparedStatement.setString(6, vecchiaMail);		// per sapere quale mail cambiare tenendo come riferimento quella vecchia durante la modifica
			
			preparedStatement.executeUpdate();
			

		} 
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}
	
	
	public User retrieveUserByEmail(String email) throws SQLException {
		Connection connection = null;
    	PreparedStatement statement = null;
    	ResultSet resultSet = null;
    	User user = null;

    	try {
        	connection = DriverManagerConnectionPool.getConnection();// Ottieni la connessione al database

        	String q = "SELECT * FROM users WHERE email = ?";
        	statement = connection.prepareStatement(q);
        	statement.setString(1, email);
        	resultSet = statement.executeQuery();

        	if (resultSet.next()) {
            	user = new User();
            	user.setEmail(resultSet.getString("email"));
            	user.setPassword(resultSet.getString("password"));
            	// Altre informazioni dell'utente che potresti voler recuperare dal database
        	}
    	} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
    	}

    	return user;
	}

	public synchronized User doRetrieveByKey(String Email) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		User bean = null;
		PagamentoDAO pdao = new PagamentoDAO();
		ConsegnaDAO cdao = new ConsegnaDAO();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE email = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, Email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				String email = "email";
				bean = new User();
				bean.setPagamento(pdao.doRetrieveByUtente(rs.getString(email)));
				bean.setConsegna(cdao.doRetrieveByUtente(rs.getString(email)));
				bean.setEmail(rs.getString(email));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setAdmin(rs.getBoolean("tipo_account"));
				bean.setCodiceFiscale(rs.getString("codice_fiscale"));

			}

		} 
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return bean;
	}

	public synchronized boolean doDelete(String Email) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UserDao.TABLE_NAME + " WHERE email = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, Email);

			result = preparedStatement.executeUpdate();

		}
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	

    
	public synchronized Collection<User> doRetrieveAll(String order) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<User> users = new LinkedList<>();
		PagamentoDAO pdao = new PagamentoDAO();
		ConsegnaDAO cdao = new ConsegnaDAO();

		String selectSQL = "SELECT * FROM " + UserDao.TABLE_NAME;

		if (order != null && !order.equals("")) 
		{
			selectSQL += " ORDER BY " + order;
		}

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				User bean = new User();
				
				bean.setPagamento(pdao.doRetrieveByUtente(rs.getString("email")));
				bean.setConsegna(cdao.doRetrieveByUtente(rs.getString("email")));

				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setAdmin(rs.getBoolean("tipo_account"));
				bean.setCodiceFiscale(rs.getString("codice_fiscale"));

				
				users.add(bean);
			}

		} 
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return users;
	}
	}
