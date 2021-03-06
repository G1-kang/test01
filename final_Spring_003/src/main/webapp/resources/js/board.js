/**
 * http://usejsdoc.org/
 */


let userInfo;
let dashboardInfo = {};
let addClient;
let client;

function selectDashBoardLoad(){
	
	dashboardInfo = {};	
	
	xhrLoad('get', 'board/select' + (arguments[0]? '/'+arguments[0] : '') , null, (res)=>{
		
		const jsonObj = JSON.parse(res);
		
		dashboardInfo.dashboard = jsonObj;
		
		const dashboard = document.querySelector('.dashboard');
		dashboard.dataset.dno = jsonObj.dno;
		
		const dashOpenItem = document.querySelectorAll('._dashItem');
		
		const selectDash = document.querySelector('.selectDash');
		
		
		dashOpenItem.forEach(item=>{
			
			
			if(Number(item.dataset.dno) === jsonObj.dno){
				
				if(selectDash) selectDash.classList.remove('selectDash');
				item.classList.add('selectDash');
			}
			
		});
		
		xhrLoad('get', 'board/rules', null, (res)=>{
			
			dashboardInfo.dashgrade = JSON.parse(res);
			
			xhrLoad('get', 'board/members', null, (res)=>{
				
				dashboardInfo.dashmember = JSON.parse(res);
				
			});
			
		}, false);
		
		const widgetArea = dashboard.querySelector('.widgetArea');
		
		dashboardInfo.dashmember.forEach(member=>{
			if(member.mid === userInfo.mid){
				userInfo.dashmember = member;
				return;
			}
		});
		
		dashboardInfo.dashgrade.forEach(grade=>{
			if(grade.dgno === userInfo.dashmember.dgno){
				userInfo.dashgrade = grade;
				return;
			}
		});
		
		
		// 위젯 로드
		widgetload();
		
		// widget 추가 감지 웹 소켓
		addClient = client.subscribe('/sub/wadd/'+ dashboardInfo.dashboard.dno, (res)=>{
			
			const addInfo = JSON.parse(res.body);
			
			let widgetCheck = false;
			widgets.forEach(widget=>{
				
				if(widget.info.wno === addInfo.wno){
					widgetCheck = true;
				}
				
			});
			
			if(!widgetCheck){
				let ruleCheck = false;
				
				const widgetArea = document.querySelector('#widgetArea');
				
				function addWidget(addInfo){
					xhrLoad('get', 'widget/'+ addInfo.wno, null, (res)=>{
						const w = widgetSettingFun(JSON.parse(res));
						motionOnOff(w, 1, false, { onOff : 'on', opactiy : { num0 : 0, num1: 1 } }, {
							after : (o)=>{
								widgetArea.appendChild(o);
							}
						}, (o)=>{
							o.style.transitionDuration = '';
						});
						
						w.mouseEventFun();
						w.scaleEventFun();
						w.contextMenuAddFun();
						w.cateFun();
						widgetGradeCheck(w);
					});
				}
				
				addInfo.rules.forEach(rule=>{

					if(rule.mid === userInfo.mid){
						ruleCheck = true;
						addWidget(addInfo);
						return;
					}

				});

				if(!ruleCheck){
					const dggrade = userInfo.dashgrade.dggrade;
					addInfo.rules.forEach(rule=>{
						if(rule.wrmax >= dggrade && rule.wrmin <= dggrade){
							addWidget(addInfo);
							return;
						}
					});
				}
			}
		});
		
		console.log(userInfo);
		console.log(dashboardInfo);
		
	},{});
	
}

function openDashBoardList(){
	xhrLoad('get', 'board/selectList', null, (res)=>{
		const jsonObj = JSON.parse(res);
		
		
		const _ul = document.querySelector('._ul');
		const nickName_li = document.querySelector('.nickName_li');
		
		const _dashLi = addObject(null, 'li', '_dashLi', false, (o)=>{
			_ul.insertBefore(o, nickName_li);
			
			const _dashUl = addObject(o, 'ul', '_dashUl', true, (o)=>{
				
				jsonObj.forEach(item => {
					
					addObject(o, 'li', '_dashItem', true, (o)=>{
						
						o.dataset.dno = item.dno;
						o.style.float.left;
						o.innerHTML = `${item.dtitle}<a href="#" onclick = "return false;"><span class="dashboardCloseBtn">X</span></a>`;
						
						const dashboardCloseBtn = o.querySelector('.dashboardCloseBtn');
						
						dashboardCloseBtn.addEventListener('click', (e)=>{
							
							e.preventDefault();
							e.stopPropagation();
							
							const dashItem = e.target.parentNode.parentNode;
							const dno = dashItem.dataset.dno;
							
							xhrLoad('get', 'board/dashboardClose', { dno }, (res)=>{
								
								const resNo = Number(res)
								
								if(resNo > 0){
									const dashboard = document.querySelector('.dashboard');
									
									if(dashboard.dataset.dno === dno){
										
										// 웹 소켓 연결 해제
										addClient.unsubscribe();
										widgetWebSocketClose(widgets);
										
										selectDashBoardLoad();
									}
									
									dashItem.remove();
									
									
								} else {
									location.href = '/dashboard';
								}
								
							});
							
						});
						
						o.addEventListener('click', (e)=>{
							
							e.preventDefault();
							e.stopPropagation();
							
							// 웹 소켓 연결 해제
							addClient.unsubscribe();
							widgetWebSocketClose(widgets);
							
							selectDashBoardLoad(e.target.dataset.dno);
							
						});
					});
					
				});
				
				
			});
			
		});
		
	});
}

window.onload = ()=>{
	const sock = new SockJS('broker');
	client = Stomp.over(sock);
	client.connect({},()=>{
	
		userInfo = headerFun();

		const con = document.querySelector('#content');

		const dashboard = addObject(con, 'div', 'dashboard', true, (o)=>{
			o.innerHTML = `
				<div style = "display:block; position:relative; top :0; left:0; width:100%; height:34px;"></div>
				<div id = "widgetArea"></div>
				`;
		});

		openDashBoardList();
		selectDashBoardLoad();

		const widgetArea = document.querySelector('#widgetArea');


		// scroll bug fix
		widgetArea.addEventListener('scroll', function() {

			if(widgetArea.scrollHeight - widgetArea.clientHeight - Math.floor(widgetArea.scrollTop) <= 1){
				widgetArea.scrollTop = widgetArea.scrollTop-3;
			}

		});


		contextMenuFun(widgetArea, {
			'new' : {
				'새 위젯 만들기' : ()=>{
					widgetAddAndModify();
				}
			},
			'dashboardInfo' : {
				'대시보드 자세히 보기' : ()=>{}
			},
			'letter' : {
				'쪽지함 보기' : ()=>{},
				'쪽지 작성' : ()=>{}
			},
			'alram' : {
				'말람함 보기' : ()=>{}
			},
			'myInfo' : {
				'내 정보 보기' : ()=>{},
				'로그아웃' : ()=>{logout();}
			}

		});
	});
};