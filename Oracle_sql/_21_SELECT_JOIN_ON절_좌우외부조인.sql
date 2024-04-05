
--[ON 절]
--; 가독성이 우수하다
--  (조인조건을 명시적으로 표현)
--등가 조인과 비등가 조인을 모두 표현할 수 있다
  
--1) 각 사원의 근무부서를 검색하세요
--등가 조인

SELECT d.dno, dname, eno, ename 
FROM emp e 
    JOIN dept d ON e.dno = d.dno;



--2) 개발 업무를 담당하는 사원의 급여 등급을 검색하세요
--비등가 조인

SELECT grade, sal, eno, ename 
FROM emp 
    JOIN salgrade ON sal BETWEEN losal AND hisal 
WHERE job = '개발';


 
 아래처럼 해도 실행은 된다. 하지만
 아래처럼 조인과 관련없는 job='개발'같은 일반 조건을 
 ON 절에 쓰는 것은 안하는 것이 좋다.
 왜냐하면 일반조건과 조인조건의 기준이 모호해지기 때문에
 그래서 위처럼 비등가조인은 ON 절에, 일반 조건은 WHERE 절에
 주는 것이 가독성 면에서 좋다.

 SELECT job, grade, sal, eno, ename 
FROM emp 
    JOIN salgrade ON sal BETWEEN losal AND hisal 
    AND job = '개발';

 
-- 3) 직원의 부서명과 급여등급을 검색하세요
--원칙을 정하는 것이 좋다
--예를 들어 등가 조인은 USING 절을
--비등가 조인은 ON 절을 사용한다
--이런 식으로 원칙을 정해놓으면 나중에 가독성이 좋아진다
SELECT grade, sal, dno, dname, eno, ename 
FROM dept 
    JOIN emp USING(dno) 
    JOIN salgrade ON sal BETWEEN losal AND hisal 
ORDER BY grade;



--4) 직원의 이름과 담당 관리자이름을 검색하세요
-- 자기참조 조인 : ON 절로 표현
--e : 사원테이블
--m : 관리자테이블
SELECT e.eno 사번, e.ename 사원명, m.eno 관리자사번, m.ename 관리자명 
FROM emp e 
    JOIN emp m ON e.mgr = m.eno;



[좌우 외부 조인(Left Right Full Outer Join)]
; (+)기호로 하는 외부조인은 둘중에 한쪽에만 사용가능하다
  그러나 여기서는 마치 양쪽에 (+)를 한 것 같은 표현이 가능하다
  a. (+) : POS부서는 존재, POS에 부서원이 없을 때
           dept.dno=emp.dno(+)
  b. 부서원이 없는 POS부서 존재
     홍길동 신입사원의 부서가 배정되기 전
     이럴 경우는 FULL JOIN을 사용해야 한다.
     
5) 홍길동 사원을 추가해보자

-- 그냥 검색했을 때는 POS도 안 나오고, 트럼프/홍road동도 안 나온다.
-- 양쪽 조건이 일치하지 않기 때문에
-- POS 부서에는 해당 부서 사번을 가진 사원이 null,
-- 트럼프/홍road동은 소속 부서 번호가 null
SELECT e.eno, ename, d.dno, dname 
FROM emp e 
JOIN dept d ON e.dno = d.dno;


--소속 사원이 없는 부서까지 출력
-- 어느쪽을 전체 출력할 것인가?
-- LEFT OUTER JOIN / LEFT JOIN : 왼쪽 테이블의 컬럼을 전체 출력
-- RIGHT OUTER JOIN / RIGHT JOIN : 오른쪽 테이블의 컬럼 전체 출력
SELECT e.eno, ename, d.dno, dname 
FROM emp e 
RIGHT OUTER JOIN dept d ON e.dno = d.dno;


--사원들의 부서를 검색하라
--사원이 아직 부서배정을 못받은 사원까지 출력
SELECT e.eno, ename, d.dno, dname 
FROM emp e 
LEFT OUTER JOIN dept d ON e.dno = d.dno;


--사원이 없는 부서 : POS
--부서가 없는 사원 : 홍길동
--둘 다 출력하려면
SELECT e.eno, ename, d.dno, dname 
FROM emp e 
FULL OUTER JOIN dept d ON e.dno = d.dno;


--LEFT JOIN 은 왼쪽 테이블의 모든 정보를 출력하겠다
--LEFT 에 기준을 맞추겠다


--RIGHT JOIN 은 오른쪽 테이블의 모든 정보를 출력하겠다
--즉 , RIGHT 테이블에 기준을 맞추겠다


--6) 사원 이름과 소속 부서를 검색하세요
--OUTER 는 써도 되고 생략해도 됨
--FULL OUTER JOIN
--LEFT OUTER JOIN
--RIGHT OUTER JOIN

















