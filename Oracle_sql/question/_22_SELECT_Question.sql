

1) 각 과목의 과목명과 담당 교수의 교수명을 검색하라
SELECT c.cname, p.pname 
FROM course c 
    JOIN professor p 
    ON c.pno = p.pno;

2) 화학과 학생의 기말고사 성적을 모두 검색하라
SELECT sc.result 
FROM score sc 
    RIGHT JOIN student st 
    ON sc.sno = st.sno 
WHERE st.major = '화학';

3) 유기화학과목 수강생의 기말고사 시험점수를 검색하라
SELECT result 
FROM score sc 
    JOIN student st ON sc.sno = st.sno 
    JOIN course c ON sc.cno = c.cno 
WHERE c.cname = '유기화학';

4) 화학과 학생이 수강하는 과목을 담당하는 교수의 명단을 검색하라
SELECT st.major, st.sno, st.sname, p.* 
FROM course c 
    JOIN professor p ON p.pno = c.pno 
    JOIN score sc ON c.cno = sc.cno 
    JOIN student st ON st.sno = sc.sno 
WHERE st.major = '화학';

5) 모든 교수의 명단과 담당 과목을 검색한다
SELECT p.*, c.cname 
FROM professor p 
LEFT OUTER JOIN course c 
ON p.pno = c.pno;


6) 모든 교수의 명단과 담당 과목을 검색한다(단 모든 과목도 같이 검색한다)
SELECT p.*, c.cname 
FROM professor p 
FULL OUTER JOIN course c 
ON p.pno = c.pno;
