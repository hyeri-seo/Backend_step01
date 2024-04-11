[세션 2]

--4) 세션1이 COMMIT을 완료하지 않았으므로
--    Undo segment(즉, 갱신 이전 값)을
--    보여준다.
SELECT sno, sname, avr
FROM student
WHERE sname = '마초';

6) 세션 1이 COMMIT 을 완료하였고
  세션 2에서 마초의 평점을 확인한다
SELECT sno, sname, avr
FROM student
WHERE sname = '마초';


-- *트랜잭션에 의한 대기 현상 확인
 
-- 3) 마초의 학과를 검색한다.
  세션 1은 학과를 '사회'로 변경하였지만
  COMMIT 을 하지 않았기 때문에
  세션 2는 이전의 정보인 '화학'으로 
  보여지게 된다.
 SELECT sno, sname, major
 FROM student
 WHERE sname = '마초';
 
 4) 세션 1이 독점 잠금을 걸어놓은 상태에서
   세션 2가 학과를 '경제'로 변경을 시도한다
   그러나 세션1이 독점 잠금을 걸어놓았기 때문에
   명령이 완료되지 못하고 대기 상태에 진입한다
 UPDATE student
 SET major = '경제'
 WHERE sname = '마초';
 
 7) 세션1이 COMMIT 하였으므로 대기가 풀려
 경제로 갱신이 가능하였다.
 하지만 아직 COMMIT은 완료되지 않은 상태이므로
 Undo segment 에서 데이터를 가져오게 된다.
 그리고 세션2는 마초의 행에 독점 잠금을 걸어
 놓은 상태이다
 SELECT sno, sname, major
 FROM student
 WHERE sname = '마초';
 
 
 
   * 데드락(Dead Lock)을 발생하고 RDBMS의 
  처리 과정을 확인한다
 
 3) 장각의 학과를 경제학과로 갱신한다
   장각의 레코드 행에 독점 잠금이 발생한다
 UPDATE student
 SET major = '경제'
 WHERE sname = '장각';
 
 4) 세션1이 이미 독점 잠금을 걸어놓은 상태인
 줄을 모르고, 관우의 학과를 경제로 바꾸려고
 시도한다
 UPDATE student
 SET major = '경제'
 WHERE sname = '관우';
 
 6) 장각은 [세션2]에서 독점 잠금을 걸고 
  있으므로 대기 상태가 되어야 하나 이렇게
  되면 [세션1]과 [세션2]가 모두 대기 상태가
  되어 데드락에 빠지게 된다.
  이 때 현재 세션에 의해 잠겨있는 리소스를 
  요청한 [세션2] 트랜잭션을 ROLLBACK 시킨다.
  ROLLBACK 은 트랜잭션의 모든 과정을 취소
  시키는 것은 아니고 데드락을 발생시킨 문장만
  ROLLBACK 한다.
  
 
 
 
 


