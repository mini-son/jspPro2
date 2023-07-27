package common.command;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.service.SidoSelectService;
import mvc.command.CommandHandler;

//요청주소 http://localhost/sidoSelect.do
//동적 select 구현 담당컨트롤러이다

public class SidoSelectController implements CommandHandler {

	private SidoSelectService  sidoSelectService = new SidoSelectService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		//2.비즈니스로직<->Service<->DAO<->DB

		//sido별 시군구
		/*  ("서울시", ["강남구","강서구","구로구","서초구","송파구","양천구","영등포구"]  )
		 *  ("경기도", ["김포시","고양시","남양주시","수원시","양평군","포천시"]  )
		 */
		Map<String,List<String>> sigunguMap = sidoSelectService.getSigungu();
		
		//3.Model & 4.View
		//view에 응답할 때에는 JSONObject객체를 문자열로 변환시켜서 넘긴다
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObjMap = new JSONObject();
		 
		for(String sidoKey: sigunguMap.keySet() ) { //map의 key의 개수만큼=<=(중복되지않는)sido의 개수만큼
			List<String> sigunguList = sigunguMap.get(sidoKey);
			JSONArray jsonArr = new JSONArray(); //배열준비
			System.out.printf("시도별%s 시군구개수%d \r\n",sidoKey,sigunguList.size());
			for( String sigungu :sigunguList) { //sido별  sigungu의 개수만큼 
				jsonArr.add(sigungu);
			}
			jsonObjMap.put(sidoKey,jsonArr);
		}
				
		String jsonStr = jsonObjMap.toJSONString();
		System.out.println("jsonStr ="+jsonStr); //콘솔출력.확인용
		out.print(jsonStr); //client로 보내기
		return null;
	}

}
