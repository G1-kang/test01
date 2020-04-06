/**
 * http://usejsdoc.org/
 */

// 추가

function headerFun(){

	
	let userInfo;	
	
	xhrLoad('get', '/dashboard/userload', null, (res)=>{
		userInfo = JSON.parse(res);
		
		
		const header = document.querySelector('header');
		
		const header_div = addObject(header, 'div', '_header', true, (o)=>{
			
		});
		
		const header_ul = addObject(header_div, 'ul', '_ul', true, (o)=>{
			
		});
		
		const title_li = addObject(header_ul, 'li', 'title_li', true, (o)=>{
			o.innerHTML = '<a href="#" onclick="return false;">TASKTREE</a>';
			o.addEventListener('click', ()=>{
				location.href = '/dashboard';
			});
		});


		const nickName_li = addObject(header_ul, 'li', 'nickName_li', true, (o)=>{
			o.innerHTML = `${userInfo.mnick}`;
		});
		
		const letter_li = addObject(header_ul, 'li', 'letter_li', true, (o)=>{
			o.innerHTML = `<div style="
										overflow: hidden;
										background-color: #fff;
										border-radius: 3px;
										border: 1px solid #ccc;
										width: 25px;
										height: 20px;
										position: relative;
										top: 0px;">
    
										<div style="
										position: absolute;
										left: -1px;
										top: -19px;
										width: 25px;
										height: 25px;
										border: 1px solid #aaa;
										transform: rotate(45deg);"></div>
										
										<div style="
										position: absolute;
										bottom: 5px;
										left: 8.5px;
										width: 7px;
										height: 7px;
										border-radius: 50%;
										background-color: #a63232;">
										</div>
										</div>`;
			o.style.float = 'right';
			
			o.addEventListener('click', (e)=>{
				e.preventDefault();
				e.stopPropagation();
				
				e.target.setAttribute('disabled','true');
				const letterBox = document.querySelector('.letterBox');
				
				if(!letterBox) {
					const mainDiv = addObject(null, 'div', 'mainDiv', false, (o)=>{
						
						o.innerHTML=`<div class='dashlist'></div>			
									 <div class='msglist'></div>`;
						
						// 메세지 리스트 비동기 통신 가져오기
						printDashList(o);
						
					});
					
					// 쪽지 쓰기 버튼
					const bar = document.createElement('div');
					bar.classList.add('barButton');
					bar.classList.add('reply');
					bar.addEventListener('click', ()=>{
						writeMsg();
					})
					bar.innerHTML= '새글';

					
					const msgBox = boxFun('쪽지 함', true,[bar,mainDiv],false,'msgBox',(o)=>{
						
					},true );
					
					
				}
			});
		});
		
		const profile_li = addObject(header_ul, 'li', 'profile_li', true, (o)=>{
			o.innerHTML = `<div class="header_profile_img"style="background-image : url('${userInfo.mimgpath}');"></div>`
		});
		
	}, false);
	
	console.log(userInfo);
	
	// 변경
	if(userInfo.mplatform !== 'home'){
		userInfo.midstate = userInfo.mplatform + '에서 연동 중...';
	}
	//
	return userInfo;
};



let dashlist;
function printDashList (o){
	var divinner = "";
	
	xhrLoad('get', 'msg/dashlist',{'mid':`${userInfo.mid}`},(res)=>{
		 dashlist = JSON.parse(res);
		
			let sum = 0;
			
			for (let i = 0 ; i < dashlist.length; i++){
				sum+= dashlist[i].SUM;
			}
				divinner += `<div class='divinner' title='all'>
								<span title='all' >ALL</span> 
								<span title='all' class='sum'>${sum}</span>
							</div>`;
		
			for (let i = 0 ; i < dashlist.length ; i++){
				if (dashlist[i].SUM===0) divinner += `<div class='divinner' title='${dashlist[i].DNO}'>
														<span title='${dashlist[i].DNO}'>${dashlist[i].DNO}</span> 
														<span 'title='${dashlist[i].DNO}' class='hiddenspan sum'>${dashlist[i].SUM}</span>
													</div>`;
				else divinner += `<div class='divinner' title='${dashlist[i].DNO}'>
									<span title='${dashlist[i].DNO}'>${dashlist[i].DNO}</span> 
									<span title='${dashlist[i].DNO}' class='sum'>${dashlist[i].SUM}</span>
								</div>`;
			}
	}, false);

	
	o.children[0].innerHTML=divinner;
	
	var selectdno = o.firstElementChild.children;
	
	for(var i = 0 ; i<selectdno.length; i++){
		
		selectdno[i].addEventListener('click', invdmsg.bind(selectdno[i])); 	
	}
}

function invdmsg (){
	var o = this.parentElement.parentElement.children[1];
	
		while (o.firstChild) {
			o.removeChild(o.firstChild);}
		
		// 클릭했을 시 쪽지함 별 쪽지리스트 출력
		console.log("what's this here=====>"+this);
		var dno = this.getAttribute('title');	// divinner의 title(dno) 속성을 가져옴
												// -> 어느 쪽지함인지 구분함
		xhrLoad('get', 'msg/msgList',{'mid':`${userInfo.mid}`,'dno':dno}, (res)=>{
			let msglist = JSON.parse(res);

			
			for (let i = 0 ; i<msglist.length; i++){
				var listdiv = document.createElement("div");
				listdiv.setAttribute("class","msg");
				listdiv.innerHTML=`<a class='msgtitle'>${msglist[i].msgtitle}</a>
								<span class='msgfrom'>${msglist[i].msgfrom}</span>
								<span class='msgdate'>${msglist[i].msgdate}</span>
								<input type='hidden' id='msgno' value='${msglist[i].msgno}'/>
								<input type='hidden' id='msgopened' value='${msglist[i].msgopened}'/>`;
				
				if (msglist[i].msgopened==='0'){
					listdiv.setAttribute("class","opened");}	
				o.appendChild(listdiv);
				
				// 개별 쪽지
				listdiv.addEventListener("click", openMsgFunc);			
			}
			
		},false);
}

// 개별 쪽지

function openMsgFunc(e){
	
	e.preventDefault();
	e.stopPropagation();


	let msgopened = e.currentTarget.querySelector('#msgopened').getAttribute('value');
	let msgno = e.currentTarget.querySelector('#msgno').getAttribute('value');
	
	// 메세지 내용 가져오기
	
	xhrLoad('get', 'msg/openMsg',{'msgno': msgno},(res)=>{
		let openMsg;
		openMsg = JSON.parse(res);
		
		const contentDiv = document.createElement('div');
		contentDiv.setAttribute('class','openMsgDiv');
		

		contentDiv.innerHTML=`<span>* 쪽지 *</span> <div class='reply'>답장</div>
							  <div class='openMsgtitle'>${openMsg.msgtitle}</div>
							  <div class='openMsgfrom'>*from: ${openMsg.msgfrom}</div>
							  <div class='openMsgdate'>${openMsg.msgdate}</div>
							  <div class='openMsgcontent'>${openMsg.msgcontent}</div>
							  <div class='openMsgDno' hidden>${openMsg.dno}</div>`;
		
		contentDiv.querySelector('.reply').addEventListener('click', ()=>{
			writeMsg(openMsg);
		});
		
		const indvMsgBox = boxFun(null, false, [contentDiv], false, 'indvMsgBox', (o)=>{
	

		},true);
		
		// 읽음 표시 비동기 통신
		if (msgopened==='1')	xhrLoad('get', 'msg/msgopened',{'msgno':msgno},()=>{}, false);
		
	}, false);
	

	
	
	
	
}

// 쪽지 쓰기 박스
function writeMsg(openMsg){
//	console.log('openMsg====>'+openMsg.dno);
	const msgSpan = document.createElement('span');
	msgSpan.setAttribute('class','msgSpan')
	msgSpan.innerHTML='* 쪽지 쓰기 *';
	
	let date = new Date();
	let year = date.getFullYear();
	let month = date.getMonth();
	let day = date.getDate();
	let hours = date.getHours();
	let minutes = date.getMinutes();
	let seconds = date.getSeconds();
	
	const msgform = document.createElement('form');
	msgform.setAttribute('id','msgForm');
	
	if (!openMsg){
		msgSpan.innerHTML='* 쪽지 쓰기 *';
		msgform.innerHTML=`	 <div class='msgDiv'><span id='msgCaption'>Title:</span> <input type='text' class='indvMsgtitle' name='msgtitle'/></div>
						  <div class='msgDiv'><span id='msgCaption'>Dashboard:</span> <select class='indvopenDno' name='dno'></select></div>
						  <div class='msgDiv'><span id='msgCaption'>To: </span><select class='indvopenMsgto' name='msgto'></select></div>
						  <div class='msgDiv'><textarea class='indvopenMsgcontent' name='msgcontent'></textarea></div>
						  
						  <input type='hidden' class='indvopenMsgfrom'  name='msgfrom'/ value='${userInfo.mid}'/>
						  <input type='hidden' class='indvopenMsgDate' name='msgdate' value="${year}년${month}월${day}일 ${hours}시${minutes}분"/>
						 </form>
						  <input type='button' value='send' onclick=sendForm();></input>`;
		var options = `<option value=''>---</option>`;
		for (let i = 0 ; i < dashlist.length; i++){
		options+=`<option value='${dashlist[i].DNO}'>${dashlist[i].DNO}</option>`}
		msgform.querySelector('.indvopenDno').innerHTML=options;
		msgform.querySelector('.indvopenDno').onchange = (e)=>{
			xhrLoad('get', 'msg/getDashMember',{'dno': e.target.value},(res)=>{
				openMsg = JSON.parse(res);
				options='';
				for(let i = 0 ; i < openMsg.length ; i++){
					options+=`<option value='${openMsg[i]}'>${openMsg[i]}</option>`
				}
				msgform.querySelector('.indvopenMsgto').innerHTML=options;
				
			}, false);
		}
	}else{
		msgSpan.innerHTML='* 답장 하기 *';
		msgform.innerHTML=`	 <div class='msgDiv'><span id='msgCaption'>Title:</span> <input type='text' class='indvMsgtitle' name='msgtitle'/></div>
			  <div class='msgDiv'><span id='msgCaption'>Dashboard:</span><input readonly class='indvopenDno' name='dno' value='${openMsg.dno}'/></div>
			  <div class='msgDiv'><span id='msgCaption'>To: </span><input readonly class='indvopenMsgto' name='msgto' value='${openMsg.msgfrom}'/></div>
			  <div class='msgDiv'><textarea class='indvopenMsgcontent' name='msgcontent'></textarea></div>
			  
			  <input type='hidden' class='indvopenMsgfrom'  name='msgfrom'/ value='${userInfo.mid}'/>
			  <input type='hidden' class='indvopenMsgDate' name='msgdate' value="${year}년${month}월${day}일 ${hours}시${minutes}분"/>
			 </form>
			  <input type='button' value='send' onclick=sendForm();></input>`;
	}
	
	const writeBox = boxFun(null, false, [msgSpan,msgform], false,'writeBox',(o)=>{
		o.classList.add('indvMsgBox');
	},true);
	
	
}

function sendForm(){
	
	var msgForm = document.querySelector('#msgForm');
	console.log('msgForm==>'+msgForm.msgto);
	
	var dto = {}
	
	for(let i = 0 ; i <msgForm.length; i++){
		dto[msgForm[i].name]=msgForm[i].value;
	}

	if (msgForm['msgto'].value==='') alert('수신자를 선택해주세요 ')
	else { 
		JSON.stringify(dto);
		
		xhrLoad('post', 'msg/write',dto,(res)=>{
			console.log(res);
			if (res === '1'){
				document.getElementsByClassName('writeBox')[0].remove();
				const confirmBox = boxFun('쪽지를 성공적으로 발송했습니다. ', false,null, false, 'confirmBox',()=>{
					
				},true);

			}
		
	}, false);
	}
}

