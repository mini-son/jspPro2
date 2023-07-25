package member.service;

import java.util.Map;

//p594 - 회원가입페이지(joinForm.jsp)에서의 필수입력, 비번과 비번확인 일치여부 등
public class JoinRequest {
	//필드
	private String  id; 			 //회원id
	private String  name; 			 //회원명
	private String  password;		 //비번
	private String  confirmPassword; //비번확인용
	
	//생성자
	//메서드
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	//p594 44라인-  비번과 비번확인 일치여부
	//password필드에 저장되어있는 값이 null아니면서 그리고
	//password필드의 값과 confirmPassword필드의 값이 일치하면 true리턴
	public boolean isPasswordEqualToConfirm() {
		return password!=null && password.equals(confirmPassword);
	}
	
	//p595 48라인-  항목별 에러체크
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors,id,"id");  //회원id
		checkEmpty(errors,name,"name"); //회원명
		checkEmpty(errors,password,"password"); //비밀번호
		checkEmpty(errors,confirmPassword,"confirmPassword"); //비밀번호확인
		
		//비번과 비번확인 일치하는지 체크-p595 53라인
		if(!errors.containsKey("confirmPassword") ) {
			if(!isPasswordEqualToConfirm()) {
				errors.put("notMatch", Boolean.TRUE ); //비번과 비번확인이 불일치	
			}
		}
	}
	
	
	//p595 60라인-  필수입력
	/*파라미터   Map<String, Boolean> error - 에러정보
	 * String value : 필드안의 값=>user가 입력한 id,회원명,비번,비번확인 값
	 * String fieldName : 필드명
	 */
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		System.out.printf("checkEmpty() %s, %s \r\n",value, fieldName);
		if(value==null || value.isEmpty()) {
			//에러가 있다면
			//Map참조변수 errors에  필드명을 key로, True를 값으로 저장 
			errors.put( fieldName, Boolean.TRUE );
			System.out.println("errors.get("+fieldName+")="+errors.get( fieldName));
		}
	}
	
	@Override
	public String toString() {
		return "JoinRequest [id=" + id + ", name=" + name + ", password=" + password + ", confirmPassword="
				+ confirmPassword + "]";
	}
	
	
}









