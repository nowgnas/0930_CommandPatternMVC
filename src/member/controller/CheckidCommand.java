package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import controller.Command;
import member.dto.Member;
import member.service.MemberService;

public class CheckidCommand implements Command{
	
	MemberService memberService;
		
	public CheckidCommand() {
		memberService=MemberService.getInstance();
	}


	@Override
	public void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject reJson)
			throws ServletException, IOException {
		String id = json.get("id").getAsString();

		if ("".equals(id)) {
			reJson.addProperty("msg", "id를 입력해주세요.");
		} else {
			int i = memberService.checkid(id);
			if (i > 0) {
				reJson.addProperty("flag", true);
				reJson.addProperty("msg", "사용 가능한 아이디 입니다.");
			} else {
				reJson.addProperty("msg", "사용이 불가능한 아이디입니다.");
			}
		}

	}
}
