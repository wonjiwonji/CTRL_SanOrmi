-- 전체 삭제 시 FK 제약조건 체크 0
SET FOREIGN_KEY_CHECKS = 0;

-- 테이블 확인
SHOW TABLES;


-- MEMBER 
-- MEMBER TABLE 생성
CREATE TABLE MEMBER(
   ID VARCHAR(20) PRIMARY KEY,
   M_PW VARCHAR(30) NOT NULL,
   M_NAME VARCHAR(20) NOT NULL,
   M_ADDRESS VARCHAR(50),
   M_EMAIL VARCHAR(40) NOT NULL,
   M_REGDATE DATE NOT NULL, 
   M_BANCNT INT DEFAULT 0,
   M_BOARDCNT INT DEFAULT 0
);

-- MEMBER 테이블 삭제
DROP TABLE MEMBER;


-- BOARD
-- BOARD TABLE 생성
CREATE TABLE BOARD(
   B_NUM INT PRIMARY KEY AUTO_INCREMENT,
   B_ID VARCHAR(20) NOT NULL,
   B_TITLE VARCHAR(20) NOT NULL,
   B_CONTENT VARCHAR(300) NOT NULL,
   B_CNT INT DEFAULT 0,
   C_CNT INT DEFAULT 0,
   B_DATE DATE NOT NULL
);

-- BOARD 테이블 삭제
DROP TABLE BOARD;


-- BCOMMENT
-- BCOMMENT TABLE 생성
CREATE TABLE BCOMMENT (
   BC_NUM INT PRIMARY KEY AUTO_INCREMENT,
   BC_ID VARCHAR(20) NOT NULL,
   B_NUM INT NOT NULL,
   BC_CONTENT VARCHAR(300) NOT NULL,
   BC_GROUP INT NOT NULL,
   BC_SQE INT DEFAULT 0,
   BC_DATE DATE NOT NULL,
   FOREIGN KEY(B_NUM) REFERENCES BOARD(B_NUM) ON DELETE CASCADE
);

-- BCOMMENT 테이블 삭제
DROP TABLE BCOMMENT;


-- QNA
-- QNA TABLE 생성
CREATE TABLE QNA(
   Q_NUM INT PRIMARY KEY AUTO_INCREMENT,
   Q_ID VARCHAR(20) NOT NULL,
   Q_TITLE VARCHAR(20) NOT NULL,
   Q_CONTENT VARCHAR(300) NOT NULL,
   Q_CNT INT DEFAULT 0,
   C_CNT INT DEFAULT 0,
   Q_DATE DATE NOT NULL
);

-- QNA 테이블 삭제
DROP TABLE QNA;


-- QCOMMENT
-- QCOMMENT TABLE 생성
CREATE TABLE QCOMMENT (
   QC_NUM INT PRIMARY KEY AUTO_INCREMENT,
   QC_ID VARCHAR(20) NOT NULL,
   Q_NUM INT NOT NULL,
   QC_CONTENT VARCHAR(300) NOT NULL,
   QC_GROUP INT NOT NULL,
   QC_SQE INT DEFAULT 0,
   QC_DATE DATE NOT NULL,
   FOREIGN KEY(Q_NUM) REFERENCES QNA(Q_NUM) ON DELETE CASCADE
);

-- QCOMMENT 테이블 삭제
DROP TABLE QCOMMENT;


-- REPORT
-- REPORT TABLE 생성
 CREATE TABLE REPORT(
   R_NUM INT PRIMARY KEY AUTO_INCREMENT,
   B_NUM INT NOT NULL,
   R_ID VARCHAR(20) NOT NULL,
   R_TARGETID VARCHAR(20) NOT NULL,
   FOREIGN KEY(B_NUM) REFERENCES BOARD(B_NUM) ON DELETE CASCADE,
   FOREIGN KEY(R_TARGETID) REFERENCES MEMBER(ID) ON DELETE CASCADE
  );
  
-- REPORT 테이블 삭제  
DROP TABLE REPORT;


-- CRAWLING
-- CRAWLING TABLE 생성
CREATE TABLE CRAWLING (
	C_SQE INT PRIMARY KEY AUTO_INCREMENT,
	C_TITLE VARCHAR(100) NOT NULL,
	C_CONTENT VARCHAR(100) NOT NULL,
	C_DATE DATETIME NOT NULL
);

-- CRAWLING TABLE 삭제
DROP TABLE CRAWLING;