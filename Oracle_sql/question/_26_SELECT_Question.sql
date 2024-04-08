

<단일 행 함수를 이용하세요>

1) 교수들이 부임한 달에 근무한 일수는 몇 일인지 검색하세요
SELECT pno, pname, hiredate, 
        LAST_DAY(TRUNC(hiredate)) - TRUNC(hiredate) "첫 달 근무일수" 
FROM professor;

2) 교수들의 오늘까지 근무한 주가 몇 주인지 검색하세요
SELECT pno, pname, hiredate, 
        TRUNC((SYSDATE - hiredate) / 7) AS "오늘까지 근무한 주수" 
FROM professor;

3) 1991년에서 1995년 사이에 부임한 교수를 검색하세요
SELECT pno, pname, hiredate 
FROM professor 
WHERE hiredate 
    BETWEEN TO_DATE('1991-01-01', 'YYYY-MM-DD') 
    AND TO_DATE('1995-12-31', 'YYYY-MM-DD');

4) 학생들의 4.5 환산 평점을 검색하세요(단 소수 이하 둘째자리까지)
SELECT sno, sname, avr, ROUND(avr*4.5/4.0, 2) "4.5 환산 평점"
FROM student;
