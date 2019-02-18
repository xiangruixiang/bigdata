package aftership.bigdata.com.Onboarding;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestCloudSql {


    public static void main(String[] args) throws IOException, SQLException {
        // TODO: fill this in
        // The instance connection name can be obtained from the instance overview page in Cloud Console
        // or by running "gcloud sql instances describe <instance> | grep connectionName".
        String instanceConnectionName = "aftership-team-maotai:us-central1:xiang";

        // TODO: fill this in
        // The database from which to list tables.
        String databaseName = "test";

        String username = "xiang";

        // TODO: fill this in
        // This is the password that was set via the Cloud Console or empty if never set
        // (not recommended).
        String password = "xiangrui987";

        if (instanceConnectionName.equals("xiang")) {
            System.err.println("Please update the sample to specify the instance connection name.");
            System.exit(1);
        }

        if (password.equals("xiangru")) {
            System.err.println("Please update the sample to specify the mysql password.");
            System.exit(1);
        }

        //[START doc-example]
        String jdbcUrl = String.format(
                "jdbc:mysql://google/%s?cloudSqlInstance=%s"
                        + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
                databaseName,
                instanceConnectionName);

        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        //[END doc-example]

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select values from xiang");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        }
    }


}
