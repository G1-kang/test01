
-- member 테이블

create SEQUENCE memberseq;

create table member (
	mno number primary key,
	mid varchar2(500) UNIQUE not null,
	mnick varchar2(1000) UNIQUE not null,
	mpw varchar2(2000) not null,
	memail varchar2(3000),
	maddr varchar2(4000),
	mphone number,
	mabout clob,
	mimgpath varchar2(2000),
	mgrade varchar2(100) not null,
	mcreatedate date not null,
	mdel varchar2(5) not null,
	CONSTRAINT mdel_chk Check (mdel in ('Y','N'))
);
alter table member add(mname varchar2(300));

-- dashboard 테이블


create sequence dashboardseq;

create table dashboard(
	dno number primary key,
	dtitle varchar2(3000) not null,
	downer varchar2(2000) not null,
	ddesc clob,
	dcreatedate date not null,
	dmodifydate date,
	constraint downer_fk FOREIGN key(downer) REFERENCES member(mid)
);

-- dashgreade 테이블

create sequence dashgradeseq;

create table dashgrade (
	dgno number primary key,
	dgdno number not null,
	dggrade number not null,
	dgalias varchar2(1000) not null,
	constraint dgdno_fk foreign key(dgdno) REFERENCES dashboard(dno) on delete cascade
);
alter table dashgrade add constraint dg_uq unique(dgdno, dggrade, dgalias);

-- dashmember 테이블


create sequence dashmemberseq;
create table dashmember (
	dmno number primary key,
	dmdno number not null,
	dmmid varchar2(2000) not null,
	dmdgno number not null,
	constraint dmdno_fk foreign key(dmdno) REFERENCES dashboard(dno) on delete cascade,
	constraint dmmid_fk foreign key(dmmid) REFERENCES member(mid) on delete cascade,
	constraint dmdgno_fk foreign key(dmdgno) REFERENCES dashgrade(dgno)
);
alter table dashmember add constraint dm_uq unique(dmdno, dmmid, dmdgno);

-- widget 테이블

create sequence widgetseq;
create table widget (
	wno number primary key,
	wdno number not null,
	wcategory varchar2(1000) not null,
	wtitle varchar2(2000),
	wowner varchar2(2000) not null,
	wleft number not null,
	wtop number not null,
	wwidth number not null,
	wheight number not null,
	wzindex number,
	wcontextrgb varchar2(500),
	wtitlergb varchar2(500),
	wposition varchar2(500),
	wdate date,
	constraint wdno_fk foreign key(wdno) REFERENCES dashboard(dno) on delete cascade,
	constraint wowner_fk foreign key(wowner) REFERENCES member(mid)
);


-- wrule 테이블
create sequence wruleseq;

create table wrule(
	wrno number primary key,
	wrwno number not null,
	wrcate varchar2(20) not null,
	wrrwd number not null,
	wrmid varchar2(500) null,
	wrminno number null,
	wrmaxno number null,
	constraint wrmid_fk foreign key(wrmid) references member(mid) on delete cascade
);

-- wfile 테이블

create sequence wfileseq;
create table wfile (
	wfno number primary key,
	wfwno number not null,
	wfpath varchar2(2000) not null,
	wffakefname varchar2(1000) not null,
	wfrealfname varchar2(1000) not null,
	wfext varchar2(500) not null,
	constraint wfwno_fk foreign key(wfwno) REFERENCES widget(wno) on delete cascade
);


-- wmemo 테이블

create sequence wmemoseq;
create table wmemo (
	wmno number primary key,
	wmwno number not null,
	wmtitle varchar2(2000),
	wmcontent clob,
	wmcreatedate date not null,
	wmmodifydate date,
	constraint wmwno_fk foreign key(wmwno) references widget(wno) on delete cascade
);
