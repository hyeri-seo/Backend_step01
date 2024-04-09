
<서브 쿼리를 사용하세요>


1) 관우보다 평점이 우수한 학생의 학번과 이름을 검색하세요
SELECT sno 학번, sname 이름, avr 평점 
FROM student 
WHERE avr > (SELECT avr 
            FROM student 
            WHERE sname = '관우') 
ORDER BY avr DESC;


2) 관우와 동일한 학년 학생 중에 평점이 사마감과 동일한 학생을 검색하세요
SELECT sno 학번, syear 학년, sname 이름, avr 평점 
FROM student 
WHERE syear = (SELECT syear 
                FROM student 
                WHERE sname = '관우') 
    AND avr = (SELECT avr 
                FROM student 
                WHERE sname = '사마감');


3) 관우보다 일반 화학과목의 학점이 더 낮은 학생의 명단을 학점과 검색하세요

SELECT st.sno 학번, sname 학생명, cname 과목명, grade 학점 
FROM student st, score sc, course c, scgrade 
WHERE st.sno = sc.sno AND c.cno = sc.cno 
    AND result BETWEEN loscore AND hiscore 
    AND grade > (SELECT grade 
                    FROM score sc, student st, course c, scgrade
                    WHERE sc.sno = st.sno AND sc.cno = c.cno 
                        AND result BETWEEN loscore AND hiscore 
                        AND sname = '관우' 
                        AND cname = '일반화학') 
AND cname = '일반화학';


4) 인원수가 가장 많은 학과를 검색하세요
SELECT major 학과, 
        COUNT(*) 인원수 
FROM student 
GROUP BY major 
HAVING COUNT(*) = (SELECT MAX(COUNT(*)) 
                    FROM student 
                    GROUP BY major);



5) 학생 중 기말고사 성적이 가장 낮은 학생의 정보를 검색하세요
SELECT st.*, sc.result 
FROM student st, score sc 
WHERE st.sno = sc.sno 
    AND result = (SELECT MIN(result) 
                FROM score);