DROP TABLE wboard [DROP CONSTRAINTS] WBOARD_PK

ALTER TABLE wboard DROP PRIMARY KEY WBOARD_PK

DROP SEQUENCE wboard_seq

CREATE SEQUENCE wboard_seq

--시퀀스를 해당 위치에다가 넣어 두기 

CREATE TABLE wboard
(
	wbtodono NUMBER NOT NULL, --위젯보드 해야할일 전체 번호
	wno NUMBER NOT NULL, --위젯번호
	dno NUMBER NOT NULL, --고유넘버(모든사람이 공통으로 가짐),위젯이 속한 대시보드 테이블 번호 
	dgno NUMBER NOT NULL, --대시보드 맴버 권한 그룹 넘버 
	mid VARCHAR2(500) NOT NULL, -- 추가해야할 거 같음 ~ 실험 완성후 갖다 붙이기 
	wbtodo VARCHAR2(4), --Y : 한일 N : 해야 할 일 , 전체할일은 
	wbtitle VARCHAR2(1000), 
	wbcontent VARCHAR2(1000),
	wfno_list VARCHAR2(1000), --파일 테이블 번호 
	wbstartdate DATE NOT NULL,
	wbenddate DATE,
	wbcolor VARCHAR2(50), -- 하일 게시판 색상 
	CONSTRAINT WBOARD_PK PRIMARY KEY(wbtodono)
)

--fk 참조 키 만들기 not yet
ALTER TABLE wboard 
ADD CONSTRAINTS fk_wbmid FOREIGN KEY (mid) 
REFERENCES member(mid);


INSERT INTO wboard VALUES
(wboard_seq.NEXTVAL,1,1,1,'a','N','고양이 간식사러 가기 ','6시간 마다 사료가 나오는 통이 있으면 좋겠다 ',NULL,SYSDATE,NULL,NULL)

INSERT INTO wboard VALUES
(wboard_seq.NEXTVAL,1,1,2,'a','N','샤워하기 ','아침마다 혹은 저녁마다 ',NULL,SYSDATE,NULL,NULL)

INSERT INTO wboard VALUES
(wboard_seq.NEXTVAL,1,1,3,'b','N','친구만나기 건대3번출','배팅이 성공하였따',NULL,SYSDATE,NULL,NULL)

INSERT INTO wboard VALUES
(wboard_seq.NEXTVAL,1,1,3,'b','Y','밥먹기','다이소에 가자',NULL,SYSDATE,NULL,NULL)

INSERT INTO wboard VALUES
(wboard_seq.NEXTVAL,1,1,3,'b','Y','할일제목','할일내용',NULL,SYSDATE,NULL,NULL)

INSERT INTO wboard VALUES
(wboard_seq.NEXTVAL,2,1,1,'a','N','할일제목','할일내용',NULL,SYSDATE,NULL,NULL)

INSERT INTO wboard VALUES
(wboard_seq.NEXTVAL,2,1,1,'a','N','lsdkfjlskdjsdkfjlasdkjflkdjlkfajlsdkfjlsdkjflksjdlf','할일내용',NULL,SYSDATE,NULL,NULL)



SELECT * FROM WBOARD;

--원하는 달의 날짜만 추출 
SELECT TO_CHAR(wbenddate,'yyyymmdd') FROM WBOARD WHERE mid = 'b'and TO_CHAR(wbstartdate,'yyyymm')='202004';

delete from wboard where wbtodono = 2;

select to_char(wbstartdate,'yyyy-mm-dd') FROM WBOARD;
select * from wboard where wno =1;

update wboard set wbtitle='수정수'where wbtodono =3;


--썸머노트 안씁니다
drop SEQUENCE  "SUMMERBOARD_SEQ";

CREATE SEQUENCE  "SUMMERBOARD_SEQ"
MINVALUE 1 MAXVALUE 9999999999999999999999999999 
INCREMENT BY 1 START WITH 1 NOCACHE;


-- 위젯 파일
select * from wfile;

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





