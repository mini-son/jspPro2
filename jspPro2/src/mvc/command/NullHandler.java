package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest  request, 
			              HttpServletResponse response) throws Exception {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}

/*  HttpServletResponse의 상수SC_NOT_FOUND는
int javax.servlet.http.HttpServletResponse.SC_NOT_FOUND : 404 [0x194]


SC_NOT_FOUND
static final int SC_NOT_FOUND

Status code (404) indicating that the requested resource is notavailable.
*/