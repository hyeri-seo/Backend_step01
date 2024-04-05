SELECT * FROM emp;

SELECT *
FROM emp;

--테이블 구조 확인
DESC emp;

--현재 스키마(현재 계정)에 포함된 모든 테이블 검색
SELECT * FROM tab;

--emp의 구조와 데이터를 확인
--DESC emp;

--테이블의 정보가 아니라 간단한 수식연산  
 -- dual은 형식을 맞추기 위한 용어
SELECT 2+3 
FROM dual;

--학생의 이름을 검색해라
SELECT sname
FROM student;

SELECT sno, sname
FROM student;

--학생의 이름과 학과를 검색해라
SELECT sno, sname, major
FROM student;





