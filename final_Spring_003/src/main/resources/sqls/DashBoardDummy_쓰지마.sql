-- member dummy

delete from member;

insert into member values(
	memberseq.nextval,
	'testuser1',
	'테스트유저1',
	'123123',
	null,
	null,
	null,
	'안녕하세요.',
	null,
	'user',
	sysdate,
	'N'
);

insert into member values(
	memberseq.nextval,
	'testuser2',
	'테스트유저2',
	'123123',
	null,
	null,
	null,
	'안녕하세요.2',
	null,
	'user',
	sysdate,
	'N'
);


insert into member values(
	memberseq.nextval,
	'testuser3',
	'테스트유저3',
	'123123',
	null,
	null,
	null,
	'안녕하세요.3',
	null,
	'user',
	sysdate,
	'N'
);


insert into member values(
	memberseq.nextval,'testuser4','테스트유저4','123123',null,null,null,'안녕하세요.4',null,'user',sysdate,'N'
);

insert into member values(
	memberseq.nextval,'testuser5','테스트유저5','123123',null,null,null,'안녕하세요.5',null,'user',sysdate,'N'
);


select * from member;

-- dashboard dummy

insert into dashboard values(
	dashboardseq.nextval,
	'테스트유저1의 대쉬보드1',
	'testuser1',
	'테스트유저1의 대쉬보드1를 환영합니다.',
	sysdate,
	null
);

insert into dashboard values(
	dashboardseq.nextval,'테스트유저1의 대쉬보드2','testuser1','테스트유저1의 대쉬보드2를 환영합니다.',sysdate,null
);

insert into dashboard values(
	dashboardseq.nextval,'테스트유저1의 대쉬보드3','testuser1','테스트유저1의 대쉬보드3를 환영합니다.',sysdate,null
);


select * from dashboard;

-- dashgrade dummy

delete from dashgrade;

-- 대쉬보드1 권한
insert into dashgrade values(dashgradeseq.nextval,1,3,'사장');
insert into dashgrade values(dashgradeseq.nextval,1,2,'팀장');
insert into dashgrade values(dashgradeseq.nextval,1,1,'사원');
insert into dashgrade values(dashgradeseq.nextval,1,0,'인턴');

-- 대쉬보드2 권한
insert into dashgrade values(dashgradeseq.nextval,2,2,'사장');
insert into dashgrade values(dashgradeseq.nextval,2,1,'매니저');
insert into dashgrade values(dashgradeseq.nextval,2,0,'알바');

-- 대쉬보드3 권한
insert into dashgrade values(dashgradeseq.nextval,3,1,'마스터');
insert into dashgrade values(dashgradeseq.nextval,3,0,'손님');


select * from dashgrade;



-- dashmember dummy

-- 대쉬보드1 맴버
insert into dashmember values(dashmemberseq.nextval,1,'testuser1',1);
insert into dashmember values(dashmemberseq.nextval,1,'testuser2',2);
insert into dashmember values(dashmemberseq.nextval,1,'testuser3',3);
insert into dashmember values(dashmemberseq.nextval,1,'testuser4',4);
insert into dashmember values(dashmemberseq.nextval,1,'testuser5',4);

-- 대쉬보드2 맴버
insert into dashmember values(dashmemberseq.nextval,2,'testuser1',5);
insert into dashmember values(dashmemberseq.nextval,2,'testuser3',6);
insert into dashmember values(dashmemberseq.nextval,2,'testuser4',7);

-- 대쉬보드3 맴버
insert into dashmember values(dashmemberseq.nextval,3,'testuser1',8);
insert into dashmember values(dashmemberseq.nextval,2,'testuser3',9);
insert into dashmember values(dashmemberseq.nextval,3,'testuser5',9);


-- widget dummy

-- 대쉬보드1 위젯1
insert into widget values(
	widgetseq.nextval,
	1, 
	'wmemo', 
	'위젯 테스트1', 
	'testuser1',
	0, 73, 250, 300, 0, 
	'#b9cdcb' , 
	null, 
	'absolute', 
	sysdate
);

-- 대쉬보드1 위젯2
insert into widget values(
	widgetseq.nextval,
	1, 
	'wmemo', 
	'위젯 테스트2', 
	'testuser2',
	333, 29, 250, 300, 0, 
	'#b9c5cb50' , 
	null, 
	'absolute', 
	sysdate
);

-- wrule dummy

-- 위젯1 권한
insert into wrule values(wruleseq.nextval,1,'individual', 7, 'testuser1',null, null);
insert into wrule values(wruleseq.nextval,1,'individual', 6, 'testuser2',null, null);
insert into wrule values(wruleseq.nextval,1,'individual', 6, 'testuser3',null, null);
insert into wrule values(wruleseq.nextval,1,'group', 4, null, 0, 9);

-- 위젯2 권한
insert into wrule values(wruleseq.nextval,2,'individual', 7, 'testuser2',null, null);

-- wmemo dummy


-- 대쉬보드1 위젯1 메모
insert into wmemo values( wmemoseq.nextval, 1, '대쉬보드1 위젯1 메모1', '콘텐츠 테스트 중입니다1', sysdate, null);
insert into wmemo values( wmemoseq.nextval, 1, '대쉬보드1 위젯1 메모2', '콘텐츠 테스트 중입니다2', sysdate, null);
insert into wmemo values( wmemoseq.nextval, 1, '대쉬보드1 위젯1 메모3', '콘텐츠 테스트 중입니다3.', sysdate, null);


-- 대쉬보드1 위젯2 메모
insert into wmemo values( wmemoseq.nextval, 2, '대쉬보드1 위젯2 메모1', '콘텐츠 테스트 중입니다1-1', sysdate, null);
insert into wmemo values( wmemoseq.nextval, 2, '대쉬보드1 위젯2 메모2', '콘텐츠 테스트 중입니다2-1', sysdate, null);

