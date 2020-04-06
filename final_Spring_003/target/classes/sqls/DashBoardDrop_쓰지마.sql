-- 모든 제약 조건 삭제
ALTER TABLE dashboard DROP CONSTRAINT downer_fk;
ALTER TABLE dashgrade DROP CONSTRAINT dgdno_fk;

ALTER TABLE dashmember DROP CONSTRAINT dmdno_fk;
ALTER TABLE dashmember DROP CONSTRAINT dmmid_fk;
ALTER TABLE dashmember DROP CONSTRAINT dmdgno_fk;

ALTER TABLE widget DROP CONSTRAINT wdno_fk;
ALTER TABLE widget DROP CONSTRAINT wowner_fk;

ALTER TABLE wrule DROP CONSTRAINT wrmid_fk;

ALTER TABLE wfile DROP CONSTRAINT wfwno_fk;

ALTER TABLE wmemo DROP CONSTRAINT wmwno_fk;
ALTER TABLE wmemo DROP CONSTRAINT wmdno_fk;


-- member 테이블
drop sequence memberseq;
drop table member;

-- dashboard 테이블
drop sequence dashboardseq;
drop table dashboard;

-- dashgrade 테이블
drop sequence dashgradeseq;
drop table dashgrade;

-- dashmember 테이블
drop sequence dashmemberseq;
drop table dashmember;

-- widget 테이블
drop sequence widgetseq;
drop table widget;

-- wrule 테이블

-- wfile 테이블
drop sequence wfileseq;
drop table wfile;

-- wmemo 테이블
drop sequence wmemoseq;
drop table wmemo;