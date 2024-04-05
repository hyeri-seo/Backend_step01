--연결연산자와 중복제거

--1) 사원의 이름을 급여 또는 업무와 함께 검색하세요
SELECT ename, sal, job
 FROM emp;

--이름은 중복 가능성이 있으므로 관습적으로 사번을 함께 검색한다
SELECT eno, ename, sal, job
 FROM emp;
 
SELECT eno||' '||ename||' '||sal||' '||job "사원 정보"
 FROM emp;

--2) 연결 연산자 사용시 주의할 것
--연산자 우선순위에서 +보다 ||가 높다

--현재 월급에서 100을 올렸을 때 월급
SELECT eno||' '||ename||' '||sal+100 "월급+100"
 FROM emp;

SELECT eno||' '||ename||' '||(sal+100) "월급+100"
 FROM emp;

--3) 중복 제거
--직원들의 업무는 어떤 것이 있는지 검색한다(업무의 종류를 검색)
SELECT job
 FROM emp;

SELECT DISTINCT job
 FROM emp;





