[BETWEEN 연산자]
--1) 급여가 1000에서 2000이내인 사원
  -- 1000이상 2000이하
SELECT *
 FROM emp
 WHERE sal >= 100 AND sal <= 2000;

SELECT *
 FROM emp
 WHERE sal BETWEEN 1000 AND 2000;


--2) 급여가 2000에서 1000사이 사원
--작은 조건이 반드시 먼저 나와야 한다
 -- 검색이 안된다
SELECT *
 FROM emp
 WHERE sal BETWEEN 2000 AND 1000;

--3) 10, 20번 부서 사원
SELECT *
 FROM emp
 WHERE dno BETWEEN '10' AND '20'
 ORDER BY dno;

SELECT *
 FROM emp
 WHERE dno='10' OR dno='20'
 ORDER BY dno;



--4) 1992년에서 1996년 사이에 입사한 사원을 검색하세요
'1992/01/01' => '1992/01/01:00:00:00'
'1996/12/31' => '1996/12/31:00:00:00'
만약 1996년 12월 31일 11시 20분에 30초에 입사한 사원은 검색에서 제외
되는 결과가 나타난다

-- 날짜 검색시 날짜 포맷의 변경이 필요하다.

ALTER SESSION SET nls_date_format='YYYY/MM/DD';

SELECT *
 FROM emp
 WHERE hdate BETWEEN '1992/01/01' AND '1996/12/31';




--시분초를 비교할 수 있는 포맷으로 변경한다


--정확한 비교를 위해 아래처럼 한다
ALTER SESSION SET nls_date_format='YYYY/MM/DD:HH24:MI:SS';

SELECT *
 FROM emp
 WHERE hdate BETWEEN '1992/01/01:00:00:00' AND '1996/12/31:23:59:59';


[IN 연산자]

--5) 10, 20번 부서 사원
SELECT *
 FROM emp
 WHERE dno IN('10', '20')
 ORDER BY dno;


--6) 개발이나 회계 업무를 담당하는 사원
SELECT *
 FROM emp
 WHERE job IN ('개발', '회계')
 ORDER BY job;

