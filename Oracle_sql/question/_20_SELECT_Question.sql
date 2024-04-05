--NATURAL JOIN / USING 절로 해결

1) 송강 교수가 강의하는 과목을 검색한다
SELECT pname, cname 
FROM course 
    NATURAL JOIN professor 
WHERE pname = '송강';

SELECT pname, cname 
FROM course 
    JOIN professor 
    USING (pno)
WHERE pname = '송강';



2) 화학 관련 과목을 강의하는 교수의 명단을 검색한다
SELECT *
FROM professor 
    NATURAL JOIN course 
WHERE cname LIKE '%화학';

SELECT *
FROM professor 
    JOIN course 
    USING (pno) 
WHERE cname LIKE '%화학';



3) 학점이 2학점인 과목과 이를 강의하는 교수를 검색한다
SELECT st_num, cname, pname 
FROM course 
    NATURAL JOIN professor 
WHERE st_num = 2;

SELECT st_num, cname, pname 
FROM course 
    JOIN professor 
    USING (pno) 
WHERE st_num = 2;



4) 화학과 교수가 강의하는 과목을 검색한다
SELECT cname, pname, section 
FROM course 
    NATURAL JOIN professor
WHERE section = '화학';

SELECT cname, pname, section 
FROM course 
    JOIN professor 
    USING (pno) 
WHERE section = '화학';



5) 화학과 1학년 학생의 기말고사 성적을 검색한다
SELECT major, syear, sno, sname, cno, cname, result 
FROM score 
    NATURAL JOIN student 
    NATURAL JOIN course 
WHERE major = '화학' 
    AND syear = 1;

SELECT major, syear, sno, sname, cno, cname, result 
FROM score 
    JOIN student USING (sno) 
    JOIN course USING (cno) 
WHERE major = '화학' 
    AND syear = 1;



6) 일반화학 과목의 기말고사 점수를 검색한다
SELECT cname, result 
FROM score 
    NATURAL JOIN course 
WHERE cname = '일반화학';

SELECT cname, result 
FROM score 
    JOIN course 
    USING (cno) 
WHERE cname = '일반화학';



7) 화학과 1학년 학생의 일반화학 기말고사 점수를 검색한다
SELECT major, syear, sno, sname, cname, result 
FROM score 
    NATURAL JOIN student 
    NATURAL JOIN course 
WHERE major = '화학' 
    AND syear = 1
    AND cname = '일반화학';

SELECT major, syear, sno, sname, cname, result 
FROM score 
    JOIN student USING (sno) 
    JOIN course USING (cno) 
WHERE major = '화학' 
    AND syear = 1
    AND cname = '일반화학';



8) 화학과 1학년 학생이 수강하는 과목을 검색한다
SELECT DISTINCT cname 
FROM course 
    NATURAL JOIN student 
WHERE major = '화학' 
    AND syear = 1;

