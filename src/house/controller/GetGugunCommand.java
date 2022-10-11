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

public class GetGugunCommand implements Command {
	
	HouseService houseService;
	
	public GetGugunCommand() {
		houseService = HouseService.getInstance();
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject reJson) throws ServletException, IOException {
		String sido = json.get("sido").getAsString();
		List<String> list = houseService.getGugun(sido);
		
		if(list!=null) {
			JsonArray array = new JsonArray();
			for(String gugun: list) {
				array.add(gugun);
			}
			reJson.add("gugunList", array); // json {"gugun":[o,o,o]} 이런 형태 !
		} else {
			reJson.addProperty("msg", "get Gugun fail ~");
		}
	}

}
