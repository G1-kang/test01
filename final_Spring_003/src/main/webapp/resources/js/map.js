
function startMap(_map,_menu){
// 마커를 담을 배열입니다
var markers = [];

var mapContainer = _map, // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();  

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

// 키워드로 장소를 검색합니다
//searchPlaces();

var body = document.body;

//키워드 검색을 요청하는 함수입니다
function searchPlaces() {

	console.log('여기까지 들어오나??');

	//var keyword = document.getElementById('keyword').value;
	var keyword = _menu.querySelector('#keyword').value;
	
	console.log(keyword);

	if (!keyword.replace(/^\s+|\s+$/g, '')) {
        boxFun('키워드를 입력해주세요!', true, null, false, 'keywordNull', null, true);

        return true;
    }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( keyword, placesSearchCB); 
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        boxFun('검색 결과가 존재하지 않습니다.', true, null, false, 'search', null, true);

    } else if (status === kakao.maps.services.Status.ERROR) {

        boxFun('검색 오류', true, null, false, 'error', null, true);

    }
}

// 검색 결과 목록
function displayPlaces(places) {

    var listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds(), 
    listStr = '';
    
    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    // removeMarker();
    
    for (var i=0; i<places.length; i++) {

        // 마커를 생성하고 지도에 표시합니다
    
    	var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x);        
         // marker = addMarker(placePosition, i),
        var itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
	
	var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info">' +
                '   <h5 class="name">' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span class="addr">' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>'; 
    }
                 
      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';           

    el.innerHTML = itemStr;
    el.className = 'item';

    var placePosition = new kakao.maps.LatLng(places.y, places.x);
    // click시 마커 추가
    el.addEventListener('click',function(){
    	
        let wmapkeyword = document.getElementsByClassName("name")[index].innerText;
        let wmapaddr = document.getElementsByClassName("addr")[index].innerText;
        let wmapjibun = document.getElementsByClassName("jibun gray")[index].innerText;
        let wmaplat = placePosition.getLat().toString();
        let wmaplng = placePosition.getLng().toString();  
       

        addMemo(wmapkeyword, wmapaddr, wmapjibun, wmaplat, wmaplng);
		
    });
    
    return el;
    
}



function addMemo(wmapkeyword, wmapaddr, wmapjibun, wmaplat, wmaplng){
	let textarea = addObject(null, 'textarea', 'memo', false);

    let btn = addObject(null, 'button', 'grayBtn', false, (o)=>{

    	o.innerHTML = '확인';
    	o.addEventListener('click', (o)=>{
    		
    		let wmapmemo = document.querySelector('.memo').value;
    		let boxOpen = document.querySelector('.memopopup');
//    		console.log(wmapmemo);
//    		console.log(document.querySelector('.memopopup'));
            
//    		motionOnOff(boxOpen, 1, true, { setting : 'onDefault' }, null, (o)=>{
//    			o.remove();
//    			
//    		});
    		boxOpen.remove();
    		
    		var markerPosition  = new kakao.maps.LatLng(wmaplat, wmaplng); 
    		var marker = addMarker(markerPosition);
    		
    		saveData(wmapkeyword, wmapaddr, wmapjibun, wmaplat, wmaplng, wmapmemo, marker);
    		    		
    	});
    });
    
    boxFun('메모 추가', true, [textarea, btn], false, 'memopopup', null, true);
}


function addMarker(position){
     
     var marker = new kakao.maps.Marker({
			position: position
		});
		marker.setMap(map);
		markers.push(marker);
		
		return marker;
}

function removeMarker(fakeMarker,marker, markerDiv){
	fakeMarker.remove();
	markerDiv.remove();
	marker.setMap(null);
	//location.reload();
}


// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title, addr, jibun, memo) {
    var content = '<div style="padding:5px;z-index:1;"><p>' + title + '</p><p>' + addr + '</p><p>' + jibun + '</p><p>' + memo + '</p></div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}

//오른쪽클릭시 창 생성
function clickMenu(marker, mapno, mapmemo, realMarker, markerDiv){
	
	contextMenuFun(marker,{
		'delete' : {
			'삭제' : function(){
				
				let deleteBtn = addObject(null, 'button', 'grayBtn', false, (o)=>{
					o.innerHTML = '삭제';
					o.addEventListener('click', ()=>{
						let boxOpen = document.querySelector('.deletePopup');
						boxOpen.remove();
						$.ajax({
							url: 'delete',
							accept: 'application/json',
							method: 'post',
							contentType: 'application/json; charset=utf-8;',
							async: false,
							data: JSON.stringify(mapno),
							success: function(res){
								if(res){
									removeMarker(marker, realMarker, markerDiv);
									
									boxFun('삭제되었습니다!', true, null, false, 'deleteSuccess', null, true);
									
								} else {
									boxFun('삭제 실패', true, null, false, 'deleteFail', null, true);
								}
							},
							error: function(){
								boxFun('삭제 에러', true, null, false, 'deleteError', null, true);
							}
						});
					})
				});
				boxFun('삭제하시겠습니까?', true, [deleteBtn], false, 'deletePopup', null, true);
			}
		},
		'update' : {
			'메모 수정' : function(){
				//var newMemo = prompt("메모 수정 : ", mapmemo.memo);
				let textarea = addObject(null, 'textarea', 'updateMemo', false, (o)=>{
					o.innerHTML = mapmemo.memo;
				});
				
				let updateBtn = addObject(null, 'button', 'grayBtn', false, (o)=>{
					o.innerHTML = '수정';
					o.addEventListener('click', ()=>{
						
						let newMemo = document.querySelector('.updateMemo').value;
						let boxOpen = document.querySelector('.updatePopup');
						boxOpen.remove();
						var dd = {
								wmapmemo: newMemo,
								wmapno: mapno.temp
						}
						console.log(dd);
						
						$.ajax({
							url: 'update',
							//accept: 'application/json',
							method: 'post',
							//contentType: 'application/json; charset=utf-8;',
							async: false,
							data: dd,
							success: function(res){
								if(res) {
									
									$.ajax({
										url: 'marker',
										accept: 'application/json',
										method: 'post',
										contentType: 'application/json; charset=utf-8;',
										async: false,
										data: JSON.stringify(),
										success: function(data){
											data.forEach(function(item){
												removeMarker(marker, realMarker, markerDiv);
											});
										},
										error: function(){
											boxFun('마커 생성 실패', true, null, false, 'markerError2', null, true);
										},
										complete: function(){
											//openMarker();
											alert('살려줘!!!');
										}
									});
									
									boxFun('수정 완료', true, null, false, 'updateSucc', null, true);
								} else {
									boxFun('수정 실패', true, null, false, 'updateFail', null, true);
								}
							},
							error: function(){
								boxFun('수정 에러', true, null, false, 'updateError', null, true);
							}
						});
						
					});
				});
				boxFun('메모 수정', true, [textarea, updateBtn], false, 'updatePopup', null, true);
			}
		}		
	}
)};


 
// ajax function
function saveData(wmapkeyword, wmapaddr, wmapjibun, wmaplat, wmaplng, wmapmemo, marker){
	
	const d = {
			wmapkeyword,
			wmapaddr,
			wmapjibun,
			wmaplat,
			wmaplng,
			wmapmemo
		};
	
	$.ajax({
		url: 'map',
		accept : 'application/json',
		method: 'post',
		contentType : 'application/json; charset=utf-8;',
		async: false,
		data: JSON.stringify(d),
		success: function(res){
						
			if(res){
				boxFun('저장!', true, null, false, 'savedPopup', (o)=>{
					//openMarker();
				}, true);
				
			} else {
				boxFun('이미 저장된 장소입니다.', true, null, false, 'existsPopup', null, true);
			}
		},
		error: function(){
			boxFun('다시 선택해주세요!', true, null, false, 'saveError', null, true);
		}
	});	
}


//window.onload = openMarker;
openMarker();

function openMarker(){
	
	$.ajax({
		url: 'marker',
		accept: 'application/json',
		method: 'post',
		contentType: 'application/json; charset=utf-8;',
		async: false,
		data: JSON.stringify(),
		success: function(data){
			// var res = JSON.parse(data);
			// console.log(data);
			
			data.forEach(function(item){
				let markerPositionAjax  = new kakao.maps.LatLng(item.wmaplat, item.wmaplng); 

				let markerAjax = addMarker(markerPositionAjax);
				let divAjax = markerAjax.pd.parentNode;
				let qsAjax = divAjax.querySelector('img');
				let chkAjax = addObject(divAjax, 'div', 'map_test', true, (o)=>{
					o.style.position = 'absolute';
					o.style.width = qsAjax.style.width;
					o.style.height = qsAjax.style.height;
				});
				
				
				//console.log("item.wmapno : " + item.wmapno);
				let mapno = {temp:item.wmapno};
				let mapmemo = {memo: item.wmapmemo}
				
				clickMenu(chkAjax, mapno, mapmemo, markerAjax, divAjax);
				
				chkAjax.onmouseover =  function () {
	                displayInfowindow(markerAjax, item.wmapkeyword, item.wmapaddr, item.wmapjibun, item.wmapmemo);
	            };

	            chkAjax.onmouseout =  function () {
	                infowindow.close();
	            };
	            
	            //console.dir(markerAjax);
				
	            console.log(item.wmapkeyword);
	            console.log(item.wmaplat);
	            console.log(item.wmaplng);
			});
		},
		error: function(){
			boxFun('마커 생성 실패', true, null, false, 'markerError', null, true);
		},
		complete: function(){
			document.getElementById('testbtn').addEventListener('click',function(){
				searchPlaces();
			});
		}
	});
}
}