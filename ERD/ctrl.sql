SHOW TABLES;

SET FOREIGN_KEY_CHECKS = 0;

SELECT * FROM MEMBER;

SELECT
   *
FROM
   MEMBER;

SELECT
   *
FROM
   BOARD;

SELECT
   *
FROM
   COMMENT;

SELECT
   *
FROM
   QNA;

SELECT
   *
FROM
   REPORT;
-- MEMBER
-- MEMBER TABLE 삭제
DROP TABLE MEMBER;

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

SELECT
   M.ID ,
   m.M_PW
FROM
   MEMBER M,
   BOARD B
WHERE
   M.ID = B.B_ID
   AND B.B_ID = '11111';

SELECT
   *
FROM
   board ;

SELECT
   *
FROM
   MEMBER;


-- BOARD TABLE 삭제
DROP TABLE BOARD;
-- BOARD TABLE 생성
CREATE TABLE BOARD(
   B_NUM INT PRIMARY KEY AUTO_INCREMENT,
   B_ID VARCHAR(20) NOT NULL,
   B_TITLE VARCHAR(20) NOT NULL,
   B_CONTENT VARCHAR(300) NOT NULL,
   B_CNT INT DEFAULT 0,
   C_CNT INT NULL DEFAULT 0,
   B_DATE DATE NOT NULL
);
-- COMMENT

-- BCOMMENT TABLE 삭제
DROP TABLE BCOMMENT;
-- BCOMMENT TABLE 생성
CREATE TABLE BCOMMENT (
   BC_NUM INT PRIMARY KEY AUTO_INCREMENT,
   BC_ID VARCHAR(20) NOT NULL,
   B_NUM INT,
   BC_CONTENT VARCHAR(300) NOT NULL,
   BC_GROUP INT NOT NULL,
   BC_SQE INT DEFAULT 0,
   BC_DATE DATE NOT NULL,
   FOREIGN KEY(B_NUM) REFERENCES BOARD(B_NUM) ON
DELETE
   CASCADE
);

-- QCOMMENT TABLE 삭제
DROP TABLE QCOMMENT;
-- QCOMMENT TABLE 생성
CREATE TABLE QCOMMENT (
   QC_NUM INT PRIMARY KEY AUTO_INCREMENT,
   QC_ID VARCHAR(20) NOT NULL,
   Q_NUM INT,
   QC_CONTENT VARCHAR(300) NOT NULL,
   QC_GROUP INT NOT NULL,
   QC_SQE INT DEFAULT 0,
   QC_DATE DATE NOT NULL,
   FOREIGN KEY(Q_NUM) REFERENCES QNA(Q_NUM) ON
DELETE
   CASCADE
);


-- QNA
-- QNA TABLE 삭제
DROP TABLE qna ;

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


-- REPORT
-- COMMENT TABLE 삭제
DROP TABLE REPORT;

-- REPORT TABLE 생성
 CREATE TABLE REPORT(
   R_NUM INT PRIMARY KEY AUTO_INCREMENT,
   B_NUM INT NOT NULL,
   R_ID VARCHAR(20) NOT NULL,
   R_TARGETID VARCHAR(20) NOT NULL,
   FOREIGN KEY(B_NUM) REFERENCES BOARD(B_NUM) ON
DELETE
   CASCADE,
   FOREIGN KEY(R_TARGETID) REFERENCES MEMBER(ID) ON
   DELETE
      CASCADE
  );

SHOW TABLES;