package common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.JdbcUtil;

public class SidoSelectDAO {

	//sido별 시군구제공
	/*  ("서울시", ["강남구","강서구","구로구","서초구","송파구","양천구","영등포구"]  )
	 *  ("경기도", ["김포시","고양시","남양주시","수원시","양평군","포천시"]  )
	 */
	public Map<String,List<String>> getSigungu(Connection conn) {
		Map<String,List<String>> sigunguMap = new HashMap<>();
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		String sql = "select distinct sido from addr";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		
		while(rs.next()) { //addr테이블의 (중복되지않는)sido의 개수만큼
			String sido = rs.getString("sido");
			
			String sql2 = "select sigungu from addr	where sido=?";
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setString(1, sido);
			rs2 = stmt2.executeQuery();
			
			List<String> sigunguList = new ArrayList<>();
			while(rs2.next()) { //sido별  sigungu의 개수만큼
				String sigungu = rs2.getString("sigungu");
				sigunguList.add(sigungu);
			}//내부 while
			sigunguMap.put(sido,sigunguList);
			//sigunguMap.put("서울시",select sigungu from addr	where sido='서울시');
			//sigunguMap.put("경기도",select sigungu from addr	where sido='경기도');
		}//외부 while
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		JdbcUtil.close(rs2);
		JdbcUtil.close(stmt2);
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
	}
	return sigunguMap;
}

	
	
	
	
	
	
	

}
