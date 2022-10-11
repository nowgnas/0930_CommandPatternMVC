package house.dto;

import com.google.gson.JsonObject;

public class House {
    long no;
    String dong, roadName, apartmentName, lat, lng, floor, area, dealAmount;

    public House(long no, String dong, String roadName, String apartmentName, String lat, String lng, String floor, String area, String dealAmount) {
        this.no = no;
        this.dong = dong;
        this.roadName = roadName;
        this.apartmentName = apartmentName;
        this.lat = lat;
        this.lng = lng;
        this.floor = floor;
        this.area = area;
        this.dealAmount = dealAmount;
    }
    
    @Override
    public String toString() {
    	JsonObject json = new JsonObject();
    	json.addProperty("no", no);
    	json.addProperty("dong", dong);
    	json.addProperty("roadName", roadName);
    	json.addProperty("apartmentName", apartmentName);
    	json.addProperty("lat", lat);
    	json.addProperty("lng", lng);
    	json.addProperty("floor", floor);
    	json.addProperty("area", area);
    	json.addProperty("dealAmount", dealAmount);
    	return json.toString();
    }
    
    public House() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }
    
}