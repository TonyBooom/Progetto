package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComposizioneDAO {
	
	private static final String TABLE_NAME = "composizione";
	
	
	public synchronized  void doSaveAll(OrdineBean user) throws SQLException
	{
		
		String insertSQL = "INSERT INTO " + ComposizioneDAO.TABLE_NAME
				+ " (codi_prodotto, num_ordine, quantita, iva, prezzo)"
				+ " VALUES (?, ?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			 connection = DriverManagerConnectionPool.getConnection();
			 preparedStatement = connection.prepareStatement(insertSQL);
		    	 
		     
			for(ProdottoBean bean : user.getComposizione().keySet()){  // Per scorrere ogni singolo prodotto nella hashmap, utilizzo il for
				
				
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, bean.getCodProdotto());
				preparedStatement.setInt(2, user.getIdOrdine());
				preparedStatement.setDouble(3, user.getComposizione().get(bean).get(0));		// Attraverso la chiave nel bean, prendo la quantit� del prodotto nell'ordine
				preparedStatement.setDouble(4, user.getComposizione().get(bean).get(1));
				preparedStatement.setDouble(5, user.getComposizione().get(bean).get(2));
				
				
				preparedStatement.executeUpdate();
			}
				connection.commit(); //Salva le modifiche sul database
		} 
		finally 
		{
				DriverManagerConnectionPool.releaseConnection(connection);
			
		}
		
	}
	
	public synchronized HashMap<ProdottoBean, ArrayList<Double>> doRetrieveByOrdine(int idordine) throws SQLException			//La funzione mi riporta il prodotto con la sua quantit� nell'ordine
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		HashMap<ProdottoBean, ArrayList<Double>> bean = new HashMap<>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE num_ordine = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idordine);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				
				ProdottoDAO pdao = new ProdottoDAO();
				ArrayList<Double> array = new ArrayList<>();
				array.add(rs.getDouble("quantita"));
				array.add(rs.getDouble("iva"));
				array.add(rs.getDouble("prezzo"));
				
				
				bean.put(pdao.doRetrieveByKey(rs.getInt("codi_prodotto")), array);
				
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
}
