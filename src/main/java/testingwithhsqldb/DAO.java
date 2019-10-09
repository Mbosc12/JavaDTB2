package testingwithhsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DAO {

    private final DataSource myDataSource;

    public DAO(DataSource dataSource) {
        myDataSource = dataSource;
    }

    /**
     * Renvoie le nom d'un client à partir de son ID
     *
     * @param id la clé du client à chercher
     * @return le nom du client (LastName) ou null si pas trouvé
     * @throws SQLException
     */
    public String nameOfCustomer(int id) throws SQLException {
        String result = null;

        String sql = "SELECT LastName FROM Customer WHERE ID = ?";
        try (Connection myConnection = myDataSource.getConnection();
                PreparedStatement statement = myConnection.prepareStatement(sql)) {
            statement.setInt(1, id); // On fixe le 1° paramètre de la requête
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // est-ce qu'il y a un résultat ? (pas besoin de "while", 
                    // il y a au plus un enregistrement)
                    // On récupère les champs de l'enregistrement courant
                    result = resultSet.getString("LastName");
                }
            }
        }
        // dernière ligne : on renvoie le résultat
        return result;
    }

    public void ajoutProduit(Produit produit) throws SQLException {
        String sql = "INSERT INTO PRODUCT VALUES(?, ?, ?)";

        try (Connection myConnection = myDataSource.getConnection();
                PreparedStatement stmt = myConnection.prepareStatement(sql)) {

            stmt.setInt(1, produit.getId());
            stmt.setString(2, produit.getName());
            stmt.setFloat(3, produit.getPrice());

            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
    }

    public Produit trouverProduit(int produitID) throws SQLException {
		Produit result = null;
		String sql = "SELECT * FROM Product WHERE ID = ?";
                
		try (Connection myConnection = myDataSource.getConnection(); 
		     PreparedStatement stmt = myConnection.prepareStatement(sql)) {
			stmt.setInt(1, produitID);
			try ( ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					result = new Produit(produitID, 
							rs.getString("NAME"),
							rs.getFloat("PRICE"));
				}
			}
		} catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
		return result;		
        }
}
