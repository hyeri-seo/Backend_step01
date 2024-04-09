
1) 화학과를 제외하고 학생들의 평점 평균을 검색하세요
SELECT major 전공,
         AVG(avr) "평점 평균"
FROM student
GROUP BY major
HAVING major != '화학';


2) 화학과를 제외한 각 학과별 평균 평점 중에 평점이 2.0 이상인 정보를 검색하세요
SELECT major 전공,
        AVG(avr) "평점 평균"
FROM student
GROUP BY major
HAVING major != '화학' AND AVG(avr) >= 2.0;

3) 기말고사 평균이 60점 이상인 학생의 정보를 검색하세요(학번과 기말고사 평균)
SELECT sno 학번,
        AVG(result) "기말고사 평균"
FROM student 
JOIN score USING(sno) 
GROUP BY sno 
HAVING AVG(result) >= 60;

4) 강의 학점수가 3학점 이상인 교수의 정보를 검색하세요(교수번호, 이름과 담당 학점수)
SELECT pno, pname, st_num 
FROM professor
JOIN course USING(pno) 
GROUP BY pno, pname, st_num 
HAVING st_num >= 3;


