서브 쿼리 - 다중 행, 다중 열

SELECT 컬럼, ...
FROM 테이블
WHERE 컬럼 <다중 행 연산자> (SELECT문 : Sub Query문);
1) 서브 쿼리에 여러 개의 행이 검색되는 쿼리를
   다중 행 서브 쿼리라고 한다
2) 다중 행 서브 쿼리는 다중 행 연산자를 이용한다
3) 다중 행 연산자의 종류
  IN : 검색된 값중에 하나만 일치하면 참이다
  ANY : 검색된 값중에 조건에 맞는 것이 하나 이상 있으면
        참이다
  ALL : 모든 검색된 값과 조건에 맞아야 참이다
  
1) 20번 부서원들과 동일한 관리자로부터 관리받는 사원을 검색한다
 a) 20번 부서원들의 관리자가 여러 명일 수 있다
 b) 서브쿼리의 결과가 몇 개일지 예측할 수 없는 경우
    다중 행 연산자를 이용하여 다중 행 서브쿼리를 작성한다
    
SELECT mgr 
FROM emp 
WHERE dno =  '20'; -- 0201, 0202, 1002

SELECT mgr, eno, ename 
FROM emp 
WHERE (mgr = '0201' OR mgr = '0202' OR mgr = '1002') 
AND dno!='20';

SELECT mgr, eno, ename 
FROM emp 
WHERE mgr IN ('0201', '0202', '1002') 
AND dno!='20';

-- 메인 쿼리가 실행되기 전에 서브 쿼리가 먼저 실행이 됨
SELECT mgr, eno, ename 
FROM emp 
WHERE mgr IN (SELECT DISTINCT mgr 
               FROM emp 
               WHERE dno =  '20') 
AND dno!='20';


2) 10번 부서원들보다 급여가 낮은 사원을 검색한다
 2-1) 10번 부서원들의 급여 검색
 2-2) 검색된 급여들로 낮은 지 비교한다
      ALL
      MIN
      
SELECT sal 
FROM emp 
WHERE dno = '10';

SELECT * 
FROM emp 
WHERE sal < (SELECT MIN(sal) 
            FROM emp 
            WHERE dno = '10');

SELECT * 
FROM emp 
WHERE sal < ALL(SELECT sal 
            FROM emp 
            WHERE dno = '10');



다중 행 연산자와 그룹 함수
다중 행 연산자인 ALL 이나 ANY 는 다음과 같이
그룹 함수를 이용해서 표현할 수 있다.
ALL : 모두 다 조건에 맞아야 함
ANY : 모두 중에 하나라도
컬럼 > ALL => 컬럼 > MAX : 가장 큰 값보다 크다
컬럼 < ALL => 컬럼 < MIN : 가장 작은 값보다 작다
컬럼 > ANY => 컬럼 > MIN : 가장 작은 값보다 크다
컬럼 < ANY => 컬럼 < MAX : 가장 큰 값보다 작다


3) 20번 부서원들과 보너스가 같은 사원을 검색한다
SELECT * 
FROM emp 
WHERE comm IN (SELECT comm 
               FROM emp 
               WHERE dno = '20');

--null 을 0으로 치환해서 검색할지는 판단을 내려야 한다
SELECT * 
FROM emp 
WHERE comm IN (SELECT NVL(comm, 0) 
               FROM emp 
               WHERE dno = '20');



다중 열 서브 쿼리
SELECT 컬럼,...
FROM 테이블
WHERE (컬럼1, 컬럼2, ...) IN (SELECT 문 : Sub Query 문);
1) 서브 쿼리의 SELECT 문에 여러 개의 컬럼을 검색한다
2) 여러 개의 컬럼을 검색하는 서브 쿼리 문을 이용할 때는
   반드시 비교 대상 컬럼과 1:1 대응되어야 한다
3) 다중 열 서브 쿼리에서 서브 쿼리의 검색 결과가 단지 하나의
  행이라면 '='연산자 사용이 가능하지만 되도록 'IN'연산자를
  사용한다.

4) 손하늘과 동일한 관리자의 관리를 받으면서 업무도 같은 사원을
검색한다.
SELECT mgr, job, dno, eno, ename 
FROM emp 
WHERE (mgr, job) = (SELECT mgr, job 
                     FROM emp 
                     WHERE ename = '손하늘') 
   AND ename != '손하늘';


손하늘 사원이 1명만 존재한다면 단일 행 서브쿼리로 변경할
수도 있다. 손하늘 사원이 여러 명이면 다중 열 서브쿼리로만
가능하다.
SELECT mgr, job, dno, eno, ename 
FROM emp 
WHERE (mgr, job) IN (SELECT mgr, job 
                     FROM emp 
                     WHERE ename = '손하늘') 
   AND ename != '손하늘';



5) 각 부서별로 최소 급여를 받는 사원의 정보를 검색한다
(이름, 급여)
 5-1) 부서별 최소 급여
 5-2) 해당 급여에 해당하는 사원

SELECT dno 부서번호, MIN(sal) "최소 급여" 
FROM emp 
GROUP BY dno;

SELECT dno, eno, ename, sal 
FROM emp 
WHERE (dno, sal) IN (SELECT dno 부서번호, MIN(sal) 
            FROM emp 
            GROUP BY dno);