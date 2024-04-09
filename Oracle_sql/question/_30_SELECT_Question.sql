
1) 3학년 학생의 학과별 평점 평균과 분산 및 편차를 검색하세요
SELECT major 학과,
        AVG(avr) "학과별 평점 평균",
        VARIANCE(avr) "학과별 평점 분산",
        STDDEV(avr) "학과별 평점 편차"
FROM student 
WHERE syear = 3 
GROUP BY major;

2) 화학과 학년별 평균 평점을 검색하세요
SELECT syear 학년,
        AVG(avr) "학년별 평점 평균" 
FROM student 
WHERE major = '화학'
GROUP BY syear;

3) 각 학생별 기말고사 평균을 검색하세요
SELECT sno 학번, 
        sname 학생명,
        major 전공, 
        syear 학년, 
        AVG(result) "기말고사 평균" 
FROM score 
JOIN student USING(sno) 
GROUP BY sno, sname, major, syear;

4) 각 학과별 학생 수를 검색하세요
SELECT major 학과, 
        COUNT(*) "학생 수"
FROM student 
GROUP BY major;

5) 화학과와 생물학과 학생 4.5 환산 평점의 평균을 각각 검색하세요
SELECT major 전공, 
        AVG(avr/4.0*4.5) "4.5 환산 평점의 평균"
FROM student  
GROUP BY major;

6) 부임일이 10년 이상 된 직급별(정교수, 조교수, 부교수) 교수의 수를 
   검색하세요
SELECT orders 직급,
        COUNT(*) "교수의 수"
FROM professor 
WHERE TRUNC(sysdate, 'YYYY') - TRUNC(hiredate, 'YYYY') >= 10 
GROUP BY orders;

7) 과목명에 화학이 포함된 과목의 학점수 총합을 검색하세요
SELECT cname 과목명, 
        SUM(st_num) "학점수 총합"
FROM course 
WHERE cname LIKE '%화학'
GROUP BY cname;

8) 화학과 학생들의 기말고사 성적을 성적순으로 검색하세요
SELECT major 전공, 
        sno 학번,
        sname 학생명,
        result "기말고사 성적"
FROM score 
JOIN student s USING(sno)
WHERE major = '화학' 
ORDER BY result DESC;

SELECT major 전공, 
        result "기말고사 성적"
FROM score 
JOIN student s USING(sno)
WHERE major = '화학' 
GROUP BY major, result
ORDER BY result DESC;


9) 학과별 기말고사 평균을 성적순으로 검색하세요
SELECT major 학과,
        AVG(result) "기말고사 평균"
FROM score 
JOIN student USING(sno) 
GROUP BY major 
ORDER BY AVG(result);