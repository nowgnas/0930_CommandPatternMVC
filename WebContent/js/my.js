window.onload = function(){
	let loginId = $.cookie("loginId");
	
	if(loginId){
		login(loginId);
	}
}

$(document).on("click", "#loginBtn", login);
$(document).on("click", "#logoutBtn", logout);
$(document).on("click", "#memberInsertBtn", signup);
//$(document).on("click", "#checkIdBtn", checkid);

$(document).on("change","#sido",getGugun);
$(document).on("change","#gugun",getDong);

$(document).on("change","#dong", selectDong);
$(document).on("change","#year", selectYear);
$(document).on("change","#month", selectMonth);
$(document).on("click","#houseDealInfoBtn",getHouseDealInfo);


let month;
function selectMonth() {
	month=$("#month option:selected").val();
}

let year;
function selectYear() {
	year=$("#year option:selected").val();
}

let dong;
function selectDong() {
	dong=$("#dong option:selected").val();
}



async function selectDong() {	
	dong=$("#dong option:selected").val()
}

async function getHouseDealInfo() {	
	console.log(sido,gugun,dong,year,month);
	if (sido==undefined || gugun==undefined || dong==undefined || year==undefined || month==undefined) {	
		alert("검색 조건을 모두 선택해 주세요");
	}else{
		let data = JSON.stringify({ sign: "getHouseDealInfo", sido,gugun,dong,year,month});
		data = await fetch("main", { method: "POST", body: data });
		data = await data.text();
		console.log(data);
		data = JSON.parse(data);
		console.log(data);
		let houseDealInfoListTable=`  <table class="table table-hover">
								    <thead>
								      <tr>
								        <th>no</th>
								        <th>dong</th>
								        <th>roadName</th>
								        <th>apartmentName</th>
								        <th>floor</th>
								        <th>area</th>
								        <th>dealAmount</th>
								      </tr>
								    </thead><tbody>`;
		data.houseDealInfoList.forEach(function (item, index) {
			item=JSON.parse(item);
			houseDealInfoListTable += `  <tr onclick="alert(${item.lat}+':'+${item.lng})">
								        <th>${item.no}</th>
								        <th>${item.dong}</th>
								        <th>${item.roadName}</th>
								        <th>${item.apartmentName}</th>
								        <th>${item.floor}</th>
										<th>${item.area}</th>
								        <th>${item.dealAmount}</th>
								      </tr>`;
		});
		
		houseDealInfoListTable += `</tbody></table>`;

		$("#contentTopDiv").html(houseDealInfoListTable);		 
		
	} 
}


$(document).on("change","#gugun",getDong);

let gugun;
async function getDong() {
	gugun=$("#gugun option:selected").val();
	//alert(gugun);
	
	if (gugun!=="구군") {	
		let data = JSON.stringify({ sign: "getDong", sido,gugun});
		data = await fetch("main", { method: "POST", body: data });
		data = await data.text();
		console.log(data);
		data = JSON.parse(data);
		console.log(data);
		let options=`<option value="">동</option>`;
		data.dongList.forEach(function (item, index) {
			options += `<option value="${item}">${item}</option>`;
		});

		$("#dong").html(options);		 
		
	} 
}

let sido;
async function getGugun() {
	sido=$("#sido option:selected").val();
	//alert(sido);
	
	if (sido.length>2) {	
		let data = JSON.stringify({ sign: "getGugun", sido});
		data = await fetch("main", { method: "POST", body: data });
		data = await data.text();
		console.log(data);
		data = JSON.parse(data);
		console.log(data);
		let options=`<option value="">구군</option>`;
		data.gugunList.forEach(function (item, index) {
			options += `<option value="${item}">${item}</option>`;
		});

		$("#gugun").html(options);		 
		
	} 
}

async function logout(){
	data = JSON.stringify({sign:"logout"});
	await fetch("main", {method:"POST", body:data});
	
	$.removeCookie("loginId");
	location.reload();

}

async function login(id){
	let data;
	if(typeof id=="string"){ // 로그인 되어있는 경우
		data = JSON.stringify({sign:"login", id});
	}else{ // 로그인 하는 경우
		let id = $("#id").val();
		let pw = $("#pw").val();
		data = JSON.stringify({sign:"login", id, pw});
	}
	
	data = await fetch("main", {method:"POST", body:data});
	data = await data.text();
	data = JSON.parse(data);
	
	if(data.loginId){
		$.cookie("loginId", data.loginId);
		$("#loginDiv").html(`<div class="text-white">${data.loginId} <button class="btn btn-primary" id="logoutBtn">logout</button></div>
`);
	} else{
		alert(data.msg);
		$.removeCookie("loginId");
	}
}

async function signup(){
	let id = document.querySelector("#id").value;
    let pw = document.querySelector("#pw").value;
    let name = document.querySelector("#name").value;
    let email = document.querySelector("#email").value;
    let phone = document.querySelector("#phone").value;
    
    if(!(/[0-9]/).test(pw)) {
		$("#pwCheck").text("숫자가 포함되어야 합니다.");
	}
	else if(!(/[a-z]/).test(pw)) {
		$("#pwCheck").text("소문자가 포함되어야 합니다.")
	}
	else if(!(/[A-Z]/).test(pw)) {
		$("#pwCheck").text("대문자가 포함되어야 합니다.")
	}
	else if(!(/[~!@#$%^&*()_+|<>?:{}]/).test(pw)) {
		$("#pwCheck").text("특수문자가 포함되어야 합니다.")
	}else{
		let id = $("#id").val();
		let pw = $("#pw").val();
		let name = $("#name").val();
		data = JSON.stringify({sign:"signup", id, pw, name});
		alert("회원가입에 성공하였습니다");
	}
	
	data = await fetch("main", {method:"POST", body:data});
	data = await data.text();
	data = JSON.parse(data);

}

//async function checkid(){
//	let id = $("#id").val();
//	data = JSON.stringify({sign:"checkid", id});
//	
//	if(data==1){
//		alert("사용 가능한 아이디입니다.");
//	}  else{
//		alert("사용 할 수 없는 아이디입니다.")
//	}
//
//	
//	data = await fetch("main", {method:"POST", body:data});
//	data = await data.text();
//	data = JSON.parse(data);
//
//}