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

public class SignupCommand implements Command{
	
	MemberService memberService;
		
	public SignupCommand() {
		memberService=MemberService.getInstance();
	}


	@Override
	public void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject reJson)
			throws ServletException, IOException {
		String id = json.get("id").getAsString();
		String pw = json.get("pw").getAsString();
		String name = json.get("name").getAsString();

		if ("".equals(id)) {
			reJson.addProperty("msg", "id를 입력해주세요.");
		} else if ("".equals(name)) {
			reJson.addProperty("msg", "이름을 입력해주세요.");
		} else if ("".equals(pw)) {
			reJson.addProperty("msg", "비밀번호를 입력해주세요.");
		} else {
			int i = memberService.memberInsert(new Member(id, pw, name));
			if (i > 0) {
				reJson.addProperty("flag", true);
				reJson.addProperty("msg", "회원가입이 완료되었습니다.");
			} else {
				reJson.addProperty("msg", "회원가입에 실패했습니다.");
			}
		}

	}
}
