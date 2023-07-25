package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//p586-Connection제공하는 역할을 하는 클래스이다
/*외부에서 Connection이 필요하면 
 * Connection 변수명= ConnectionProvider.getConnection();
 */
public class ConnectionProvider {

	public static Connection getConnection() throws SQLException {
		/*커넥션풀이름은  web.xml문서에서
		  poolName=board*/
		//"jdbc:apache:commons:dbcp:커넥션풀이름"
		//여기에서의 커넥션풀이름은 
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
	}
}
