

1) 학생중에 동명이인을 검색한다
SELECT s1.sno, s1.sname, s2.sno, s2.sname 
FROM student s1, student s2 
WHERE s1.sname = s2.sname 
AND s1.sno != s2.sno;

2) 전체 교수 명단과 교수가 담당하는 과목의 이름을 학과 순으로 검색한다
SELECT p.section 학과, p.pno 교수번호, p.pname 교수명, c.cname "담당 과목" 
FROM professor p, course c 
WHERE p.pno = c.pno (+) 
ORDER BY p.section;

3) 이번 학기 등록된 모든 과목과 담당 교수의 학점 순으로 검색한다
SELECT c.st_num 학점, c.cno 과목번호, c.cname 과목명, p.pno 교수번호, p.pname 교수명 
FROM professor p, course c 
WHERE p.pno (+)= c.pno
ORDER BY c.st_num;