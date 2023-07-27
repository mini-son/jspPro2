package common.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import common.dao.SidoSelectDAO;
import jdbc.connection.ConnectionProvider;

//동적 select요청 관련 서비스클래스이다
public class SidoSelectService {

	private SidoSelectDAO sidoSelectDAO = new SidoSelectDAO();
	
	//sido별 시군구제공
	/*  ("서울시", ["강남구","강서구","구로구","서초구","송파구","양천구","영등포구"]  )
	 *  ("경기도", ["김포시","고양시","남양주시","수원시","양평군","포천시"]  )
	 */
	public Map<String, List<String>> getSigungu() throws SQLException {
		Connection conn = null;
		conn = ConnectionProvider.getConnection();
		Map<String,List<String>> resultMap= sidoSelectDAO.getSigungu(conn);
		
		
		return null;
	}

	
}
