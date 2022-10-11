package house.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import controller.Command;
import house.Service.HouseService;

public class GetDongCommand implements Command {
	
	HouseService houseService;
	
	public GetDongCommand() {
		houseService = HouseService.getInstance();
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject reJson) throws ServletException, IOException {
		String sido = json.get("sido").getAsString();
		String gugun = json.get("gugun").getAsString();
		List<String> list = houseService.getDong(sido, gugun);
		
		if(list!=null) {
			JsonArray array = new JsonArray();
			for(String dong: list) {
				array.add(dong);
			}
			reJson.add("dongList", array);
		} else {
			reJson.addProperty("msg", "get Dong fail ~");
		}
	}

}
