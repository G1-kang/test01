-- 맴버
CREATE TABLE member
(
    mno            NUMBER            NOT NULL, 
    mid            VARCHAR2(500)     NOT NULL, 
    mnick          VARCHAR2(1000)    NOT NULL, 
    mname          VARCHAR2(100)     NOT NULL, 
    mpw            VARCHAR2(2000)    NOT NULL, 
    memail         VARCHAR2(3000)    NULL, 
    maddr          VARCHAR2(4000)    NULL, 
    mphone         VARCHAR2(100)     NULL, 
    mabout         CLOB              NULL, 
    mimgpath       VARCHAR2(2000)    NULL, 
    mgrade         VARCHAR2(100)     NOT NULL, 
    mcreatedate    DATE              NOT NULL, 
    mdel           VARCHAR2(5)       NOT NULL, 
    CONSTRAINT MEMBER_PK PRIMARY KEY (mno)
)
/
select * from member;

CREATE SEQUENCE member_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER member_AI_TRG
BEFORE INSERT ON member 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT member_SEQ.NEXTVAL
    INTO :NEW.mno
    FROM DUAL;
END;
/

DROP TRIGGER member_AI_TRG;
/

--DROP SEQUENCE member_SEQ;
/

COMMENT ON TABLE member IS '회원 테이블'
/

/
COMMENT ON COLUMN member.mno IS '회원 정보'

COMMENT ON COLUMN member.mid IS '회원 아이디'
/

COMMENT ON COLUMN member.mnick IS '회원 닉네임'
/

COMMENT ON COLUMN member.mname IS '회원 이름'
/

COMMENT ON COLUMN member.mpw IS '회원 비밀번호'
/

COMMENT ON COLUMN member.memail IS '회원 이메일'
/

COMMENT ON COLUMN member.maddr IS '회원 주소'
/

COMMENT ON COLUMN member.mphone IS '회원 전화 번호'
/

COMMENT ON COLUMN member.mabout IS '회원 소개'
/

COMMENT ON COLUMN member.mimgpath IS '회원 사진 패스'
/

COMMENT ON COLUMN member.mgrade IS '회원 등급'
/

COMMENT ON COLUMN member.mcreatedate IS '회원 가입일'
/

COMMENT ON COLUMN member.mdel IS 'Check(Y,N)'
/


ALTER TABLE member
	ADD (mplatform varchar(50))
/

ALTER TABLE member
    ADD CONSTRAINT UC_mnick UNIQUE (mnick)
/

ALTER TABLE member
	ADD CONSTRAINT UC_mname_memail_mplatform UNIQUE (mname, memail, mplatform)
/

ALTER TABLE member
    ADD CONSTRAINT UC_mid UNIQUE (mid)
/



-- 대시보드
CREATE TABLE DashBoard
(
    dno            NUMBER            NOT NULL, 
    dtitle         VARCHAR2(3000)    NOT NULL, 
    mid            VARCHAR2(2000)    NOT NULL, 
    ddesc          CLOB              NULL, 
    dcreatedate    DATE              NOT NULL, 
    dmodifydate    DATE              NULL, 
    ddel           VARCHAR2(5)       NOT NULL, 
    CONSTRAINT DASHBOARD_PK PRIMARY KEY (dno)
)
/

CREATE SEQUENCE DashBoard_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER DashBoard_AI_TRG
BEFORE INSERT ON DashBoard 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT DashBoard_SEQ.NEXTVAL
    INTO :NEW.dno
    FROM DUAL;
END;
/

DROP TRIGGER DashBoard_AI_TRG;
/

--DROP SEQUENCE DashBoard_SEQ;
/

COMMENT ON TABLE DashBoard IS '대시보드 테이블'
/

COMMENT ON COLUMN DashBoard.dno IS '대시보드 테이블 번호'
/

COMMENT ON COLUMN DashBoard.dtitle IS '대시보드 명칭'
/

COMMENT ON COLUMN DashBoard.mid IS '대시보드 소유주'
/

COMMENT ON COLUMN DashBoard.ddesc IS '대시보드 설명'
/

COMMENT ON COLUMN DashBoard.dcreatedate IS '대시보드 생성일'
/

COMMENT ON COLUMN DashBoard.dmodifydate IS '대시보드 수정일'
/

COMMENT ON COLUMN DashBoard.ddel IS '대시보드 삭제 유무'
/

ALTER TABLE DashBoard
    ADD CONSTRAINT FK_DashBoard_mid_member_mid FOREIGN KEY (mid)
        REFERENCES member (mid)
/

-- 대시 룰

CREATE TABLE DashGrade
(
    dgno       NUMBER            NOT NULL, 
    dno        NUMBER            NOT NULL, 
    dggrade    NUMBER            NOT NULL, 
    dgalias    VARCHAR2(1000)    NOT NULL, 
    CONSTRAINT DASHGRADE_PK PRIMARY KEY (dgno)
)
/

CREATE SEQUENCE DashGrade_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER DashGrade_AI_TRG
BEFORE INSERT ON DashGrade 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT DashGrade_SEQ.NEXTVAL
    INTO :NEW.dgno
    FROM DUAL;
END;
/

DROP TRIGGER DashGrade_AI_TRG;
/

--DROP SEQUENCE DashGrade_SEQ;
/

COMMENT ON TABLE DashGrade IS '대시보드 권한 설정 테이블'
/

COMMENT ON COLUMN DashGrade.dgno IS '대시보드 맴버 등급 테이블 번호'
/

COMMENT ON COLUMN DashGrade.dno IS '대시보드 테이블 번호'
/

COMMENT ON COLUMN DashGrade.dggrade IS '0~9999'
/

COMMENT ON COLUMN DashGrade.dgalias IS '등급 별칭'
/

ALTER TABLE DashGrade
    ADD CONSTRAINT FK_DashGrade_dno_DashBoard_dno FOREIGN KEY (dno)
        REFERENCES DashBoard (dno) on delete cascade
/

ALTER TABLE DashGrade
	ADD (dgcolor varchar(200))
/

-- 대시 맴버
drop table dashmember
delete dashmember
select * from dashmember where dno='2'
CREATE TABLE DashMember
(
    dmno    NUMBER            NOT NULL, 
    dno     NUMBER            NOT NULL, 
    mid     VARCHAR2(2000)    NOT NULL, 
    dgno    NUMBER            NOT NULL,
    CONSTRAINT DASHMEMBER_PK PRIMARY KEY (dmno)
)
/

CREATE SEQUENCE DashMember_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER DashMember_AI_TRG
BEFORE INSERT ON DashMember 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT DashMember_SEQ.NEXTVAL
    INTO :NEW.dmno
    FROM DUAL;
END;
/

DROP TRIGGER DashMember_AI_TRG;
/

--DROP SEQUENCE DashMember_SEQ;
/

COMMENT ON TABLE DashMember IS '대시보드 맴버 테이블'
/

COMMENT ON COLUMN DashMember.dmno IS '대시보드맴버 테이블 번호'
/

COMMENT ON COLUMN DashMember.dno IS '대시보드 번호'
/

COMMENT ON COLUMN DashMember.mid IS '대시보드 참여 유저 아이디'
/

COMMENT ON COLUMN DashMember.dgno IS '대시보드 참여 유저 등급'
/

ALTER TABLE DashMember
    ADD CONSTRAINT FK_DashMember_dno_DashBoard_dn FOREIGN KEY (dno)
        REFERENCES DashBoard (dno) on delete cascade
/

ALTER TABLE DashMember
    ADD CONSTRAINT FK_DashMember_mid_member_mid FOREIGN KEY (mid)
        REFERENCES member (mid) on delete cascade
/

ALTER TABLE DashMember
    ADD CONSTRAINT FK_DashMember_dgno_DashGrade_d FOREIGN KEY (dgno)
        REFERENCES DashGrade (dgno)
/

ALTER TABLE DashMember
    ADD CONSTRAINT UC_dno_mid_dgno UNIQUE (dno,mid,dgno)
/

ALTER TABLE DashMember
	ADD (dmcolor varchar(200))
/


-- 위젯 테이블
CREATE TABLE widget
(
    wno              NUMBER            NOT NULL, 
    dno              NUMBER            NOT NULL, 
    wcategory        VARCHAR2(1000)    NOT NULL, 
    wtitle           VARCHAR2(2000)    NULL, 
    mid              VARCHAR2(2000)    NOT NULL, 
    wleft            number            NULL, 
    wtop             NUMBER            NULL, 
    wwidth           NUMBER            NULL, 
    wheight          NUMBER            NULL, 
    wzindex          NUMBER            NULL, 
    wcontentcolor    VARCHAR2(500)     NULL, 
    wtitlecolor      VARCHAR2(500)     NULL, 
    wposition        VARCHAR2(500)     NULL, 
    wcreatedate      date              NULL, 
    wdel             VARCHAR2(5)       NOT NULL, 
    CONSTRAINT WIDGET_PK PRIMARY KEY (wno)
)
/

CREATE SEQUENCE widget_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER widget_AI_TRG
BEFORE INSERT ON widget 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT widget_SEQ.NEXTVAL
    INTO :NEW.wno
    FROM DUAL;
END;
/

DROP TRIGGER widget_AI_TRG;
/

--DROP SEQUENCE widget_SEQ;
/

COMMENT ON TABLE widget IS '위젯 설정 테이블'
/

COMMENT ON COLUMN widget.wno IS '위젯 설정 테이블 번호'
/

COMMENT ON COLUMN widget.dno IS '위젯이 속한 대시보드 테이블 번호'
/

COMMENT ON COLUMN widget.wcategory IS '위젯 내용 종류'
/

COMMENT ON COLUMN widget.wtitle IS '위젯 제목'
/

COMMENT ON COLUMN widget.mid IS '위젯 소유주'
/

COMMENT ON COLUMN widget.wleft IS '위젯 X축'
/

COMMENT ON COLUMN widget.wtop IS '위젯 Y축'
/

COMMENT ON COLUMN widget.wwidth IS '위젯 가로 길이'
/

COMMENT ON COLUMN widget.wheight IS '위젯 세로 길이'
/

COMMENT ON COLUMN widget.wzindex IS '위젯 Z축'
/

COMMENT ON COLUMN widget.wcontentcolor IS '위젯 내용 배경색'
/

COMMENT ON COLUMN widget.wtitlecolor IS '위젯 제목 배경색'
/

COMMENT ON COLUMN widget.wposition IS '위젯 포지션'
/

COMMENT ON COLUMN widget.wcreatedate IS '위젯 생성시기'
/

COMMENT ON COLUMN widget.wdel IS '위젯 삭제 유무'
/

ALTER TABLE widget
    ADD CONSTRAINT FK_widget_dno_DashBoard_dno FOREIGN KEY (dno)
        REFERENCES DashBoard (dno) on delete cascade
/

ALTER TABLE widget
    ADD CONSTRAINT FK_widget_mid_member_mid FOREIGN KEY (mid)
        REFERENCES member (mid) on delete cascade
/

-- 위젯 룰

CREATE TABLE wrule
(
    wrno          NUMBER           NOT NULL, 
    wno           NUMBER           NOT NULL, 
    wrcategory    VARCHAR2(20)     NOT NULL, 
    wrrwd         NUMBER           NOT NULL, 
    mid           VARCHAR2(500)    NULL, 
    wrmin         NUMBER           NULL, 
    wrmax         NUMBER           NULL, 
    CONSTRAINT WRULE_PK PRIMARY KEY (wrno)
)
/

CREATE SEQUENCE wrule_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER wrule_AI_TRG
BEFORE INSERT ON wrule 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT wrule_SEQ.NEXTVAL
    INTO :NEW.wrno
    FROM DUAL;
END;
/

DROP TRIGGER wrule_AI_TRG;
/

--DROP SEQUENCE wrule_SEQ;
/

COMMENT ON TABLE wrule IS '위젯 룰'
/

COMMENT ON COLUMN wrule.wrno IS '위젯 룰 테이블 번호'
/

COMMENT ON COLUMN wrule.wno IS '소속 위젯 번호'
/

COMMENT ON COLUMN wrule.wrcategory IS 'group/individual'
/

COMMENT ON COLUMN wrule.wrrwd IS '4 : read / 6 : read&write / 7 : read & write & delete'
/

COMMENT ON COLUMN wrule.mid IS 'individual 일 경우 개별 적용 룰'
/

COMMENT ON COLUMN wrule.wrmin IS 'group 일 경우 0~9999'
/

COMMENT ON COLUMN wrule.wrmax IS 'group 일 경우 0~9999'
/

ALTER TABLE wrule
    ADD CONSTRAINT FK_wrule_wno_widget_wno FOREIGN KEY (wno)
        REFERENCES widget (wno) on delete cascade
/

ALTER TABLE wrule
    ADD CONSTRAINT FK_wrule_mid_member_mid FOREIGN KEY (mid)
        REFERENCES member (mid)
/





-- 메모 기능

CREATE TABLE wmemo
(
    wmno           NUMBER            NOT NULL, 
    wno            NUMBER            NOT NULL, 
    wmcontent      CLOB              NULL, 
    wmwritedate    DATE              NOT NULL, 
    CONSTRAINT WMEMO_PK PRIMARY KEY (wmno)
)
/

CREATE SEQUENCE wmemo_SEQ
START WITH 1
INCREMENT BY 1;
/

--DROP SEQUENCE wmemo_SEQ;
/

COMMENT ON TABLE wmemo IS '메모 위젯'
/

COMMENT ON COLUMN wmemo.wmno IS '메모 테이블 번호'
/

COMMENT ON COLUMN wmemo.wno IS '소속 위젯 번호'
/

COMMENT ON COLUMN wmemo.wmcontent IS '메모 내용'
/

COMMENT ON COLUMN wmemo.wmwritedate IS '메모 작성일'
/

ALTER TABLE wmemo
    ADD CONSTRAINT FK_wmemo_wno_widget_wno FOREIGN KEY (wno)
        REFERENCES widget (wno) on delete cascade
/




-- 위젯 파일

CREATE TABLE wfile
(
    wfno          NUMBER            NOT NULL, 
    wno           NUMBER            NOT NULL, 
    wfpath        VARCHAR2(2000)    NOT NULL, 
    wffakename    VARCHAR2(1000)    NOT NULL, 
    wfrealname    VARCHAR2(1000)    NOT NULL, 
    wfext         VARCHAR2(500)     NOT NULL, 
    CONSTRAINT WFILE_PK PRIMARY KEY (wfno)
)
/

CREATE SEQUENCE wfile_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER wfile_AI_TRG
BEFORE INSERT ON wfile 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT wfile_SEQ.NEXTVAL
    INTO :NEW.wfno
    FROM DUAL;
END;
/

DROP TRIGGER wfile_AI_TRG;
/

--DROP SEQUENCE wfile_SEQ;
/

COMMENT ON TABLE wfile IS '위젯 파일 첨부 테이블'
/

COMMENT ON COLUMN wfile.wfno IS '파일 테이블 번호'
/

COMMENT ON COLUMN wfile.wno IS '소속 위젯 번호'
/

COMMENT ON COLUMN wfile.wfpath IS '파일 경로'
/

COMMENT ON COLUMN wfile.wffakename IS '파일저장첨부명'
/

COMMENT ON COLUMN wfile.wfrealname IS '파일실제첨부명'
/

COMMENT ON COLUMN wfile.wfext IS '파일첨부확장자'
/

ALTER TABLE wfile
    ADD CONSTRAINT FK_wfile_wno_widget_wno FOREIGN KEY (wno)
        REFERENCES widget (wno) on delete cascade
/


<<<<<<< HEAD
CREATE SEQUENCE MSGNOSEQ
START WITH 1
INCREMENT BY 1
;
DROP TABLE MSGTABLE;
drop sequence MSGNOSEQ;
--쪽지 
CREATE TABLE MSGTABLE(
		MSGNO 		NUMBER 			NOT NULL,
		MSGFROM 	VARCHAR2(100) 	NOT NULL,
		MSGTO 		VARCHAR2(200) 	NOT NULL,
		MSGDATE 	VARCHAR2(200) 	NOT NULL,
		MSGOPENED 	number		 	NOT NULL,
		DNO			NUMBER			NOT NULL,
		MSGCONTENT 	VARCHAR2(2000) 	NOT NULL,
		MSGTITLE 	VARCHAR2(500) 	NOT NULL,
	
		CONSTRAINT MSGTABLE_PK PRIMARY KEY (msgno)
);
SELECT * from MSGTABLE ;
DELETE MSGTABLE;

INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 0,1,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 1,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 0,3,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 1,1,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 0,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 1,3,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 0,1,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 0,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 0,3,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 0,1,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 0,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 1,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 1,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin01','jiaemin02', '2020/03/26', 1,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');

INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin02','jiaemin01', '2020/03/26', 0,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin02','jiaemin01', '2020/03/26', 1,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin02','jiaemin01', '2020/03/26', 0,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin02','jiaemin01', '2020/03/26', 1,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin02','jiaemin01', '2020/03/26', 1,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');
INSERT INTO MSGTABLE VALUES(MSGNOSEQ.NEXTVAL,'jiaemin02','jiaemin01', '2020/03/26', 0,2,'쪽지 테스트 입니다. ','쪽지 제목 입니다. ');

SELECT dno, sum(msgopened) 
from (select dno, msgopened from msgtable where msgto='jiaemin02' ) 
group by dno; 


SELECT * from (select * from msgtable where msgto='jiaemin02')  where dno='3';
UPDATE msgtable set msgopened ='Y' where msgno='17';

CREATE OR REPLACE TRIGGER msgtable_AI_TRG
BEFORE INSERT ON msgtable 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT msgtable_SEQ.NEXTVAL
    INTO :NEW.msgno
    FROM DUAL;
END;
/

--DROP TRIGGER msgtable_AI_TRG;
/

--DROP SEQUENCE msgtable_SEQ;
/

COMMENT ON TABLE msgtable IS '쪽지'
/

COMMENT ON COLUMN msgtable.msgno IS '쪽지 고유번호'
/

COMMENT ON COLUMN msgtable.msgfrom IS '쪽지 발송인'
/

COMMENT ON COLUMN msgtable.msgto IS '쪽지 수신인'
/

COMMENT ON COLUMN msgtable.msgdate IS '쪽지 보낸 날짜'
/

COMMENT ON COLUMN msgtable.msgopened IS '쪽지 개봉 여부(y/n)'
/

COMMENT ON COLUMN msgtable.dno IS '대쉬보드 테이블 번호'
/

COMMENT ON COLUMN msgtable.msgcontent IS '쪽지 내용'
/

COMMENT ON COLUMN msgtable.msgtitle IS '쪽지 제목'
/

ALTER TABLE msgtable
    ADD CONSTRAINT FK_msgtable_msgfrom_member_mid FOREIGN KEY (msgfrom)
        REFERENCES member (mid)
/

ALTER TABLE msgtable
    ADD CONSTRAINT FK_msgtable_msgto_member_mid FOREIGN KEY (msgto)
        REFERENCES member (mid)
/


=======


-- 위젯 채팅

CREATE TABLE wchat
(
    wcno      NUMBER            NOT NULL, 
    wno       NUMBER            NOT NULL, 
    wcpath    VARCHAR2(2000)    NOT NULL, 
    CONSTRAINT WCHAT_PK PRIMARY KEY (wcno)
)
/

CREATE SEQUENCE wchat_SEQ
START WITH 1
INCREMENT BY 1;
/


--DROP SEQUENCE wchat_SEQ;
/

COMMENT ON TABLE wchat IS '채팅'
/

COMMENT ON COLUMN wchat.wcno IS '채팅 고유번호'
/

COMMENT ON COLUMN wchat.wno IS '소속 위젯 번호'
/

COMMENT ON COLUMN wchat.wcpath IS '채팅 로그 패스'
/

ALTER TABLE wchat
    ADD CONSTRAINT FK_wchat_wno_widget_wno FOREIGN KEY (wno)
        REFERENCES widget (wno)
/

ALTER TABLE wchat
    ADD CONSTRAINT UC_wno UNIQUE (wno)
/

ALTER TABLE wchat
    ADD CONSTRAINT UC_wcpath UNIQUE (wcpath)
/
>>>>>>> 9e3442acd1cc365989de2f66fb3958623e070e38
