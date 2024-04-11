
1) 화학과 학생과 평점이 동일한 학생들을 검색하세요
SELECT * 
FROM student 
WHERE avr IN (SELECT avr 
            FROM student 
            WHERE major = '화학') 
    AND major != '화학';

2) 화학과 교수와 부임일이 같은 직원을 검색하세요
SELECT * 
FROM professor 
WHERE hiredate IN (SELECT hiredate 
                    FROM professor 
                    WHERE section = '화학') 
    AND section != '화학';

3) 화학과 학생과 같은 학년에서 평점이 동일한 학생들을 검색하세요
SELECT syear, avr, major, sno, sname  
FROM student 
GROUP BY syear, avr, major, sno, sname 
HAVING (syear, avr) IN (SELECT syear, avr 
                        FROM student 
                        WHERE major = '화학') 
    AND major != '화학';