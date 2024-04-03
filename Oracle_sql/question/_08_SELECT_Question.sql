
1) 성적순으로 학생의 이름을 검색하라
SELECT sname 학생명, avr 평점 
FROM student 
ORDER BY avr DESC;

2) 학과별 성적순으로 학생의 정보를 검색하라
SELECT major 학과, sname 학생명, avr 평점 
FROM student 
ORDER BY major, avr DESC;

3) 학년별 성적순으로 학생의 정보를 검색하라
SELECT syear 학년, sname 학생명, avr 평점 
FROM student 
ORDER BY syear, avr DESC;

4) 학과별 학년별로 학생의 정보를 성적순으로 검색하라
SELECT major 학과, syear 학년, sname 학생명, avr 평점 
FROM student 
ORDER BY major, syear, avr DESC;

5) 학점순으로 과목 이름을 검색하라
SELECT st_num 학점, cname 과목명 
FROM course 
ORDER BY st_num DESC;

6) 각 학과별로 교수의 정보를 검색하라
SELECT * 
FROM professor 
ORDER BY section;

7) 지위별로 교수의 정보를 검색하라
SELECT * 
FROM professor 
ORDER BY orders;

8) 각 학과별로 교수의 정보를 부임일자 순으로 검색하라
SELECT * 
FROM professor 
ORDER BY section, hiredate;
