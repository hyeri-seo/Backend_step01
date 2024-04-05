--별명을 이용한 검색

--1) 각 사원의 이름과 담당업무를 검색해서 결과를 보고한다
SELECT eno, ename, job
FROM emp;

SELECT eno 사번, ename 사원명, job "담당 업무"
FROM emp;

SELECT eno AS 사번, ename AS 사원명, job AS 담당업무
FROM emp;

--별명에 띄어쓰기가 있으면 쌍따옴표로 묶어야 한다
--2) 각 사원의 급여와 1년간 수급하는 급여를 검색하라(보너스 제외)
SELECT *
 FROM emp;

SELECT eno 사번, ename 사원명, sal 월급, sal*12 "1년간 급여"
 FROM emp;

--3) 사원의 연봉을 검색한다(연봉 = sal*12 + comm)
SELECT eno 사번, ename 사원명, sal 월급, sal*12+comm 연봉
 FROM emp;

--DB에서 null 은 '알 수 없다', '정해지지 않았다'
--null 을 통계조작의 의도적 오류로 많이 사용한다
--null 은 둘중에 하나로 정해야 한다
--  1) null -> 0으로 치환  : 선택
--  2) null 포함 컬럼을 계산대상에서 제외

--4) NVL 함수
SELECT eno 사번, ename 사원명, sal 월급, sal*12+NVL(comm,0) 연봉
 FROM emp;
