import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String dbName = "rdsdbtraining";
        String userName = "postgres";
        String password = "adminadmin";
        String hostname = "myHost";
        String port = "5432";
        String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;

        try {
            // Charger le pilote JDBC MySQL
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded!");

            // Établir la connexion
            System.out.println("Connecting to the database...");
            Connection connection = DriverManager.getConnection(jdbcUrl);
            System.out.println("Connected to the database!");

            // Créer la requête
            String query = "SELECT * FROM voiture";

            // Exécuter la requête
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            // Parcourir les résultats et les afficher
            while (resultSet.next()) {
                // Récupérer les valeurs de chaque colonne
                String marque = resultSet.getString("marque");
                String modele = resultSet.getString("modele");
                // Afficher les valeurs
                System.out.println("Marque: " + marque + ", Modèle: " + modele);
            }

            // Fermer les ressources
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Cannot find the MySQL JDBC driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("An error occurred while connecting to the database!");
            e.printStackTrace();
        }
    }
}
