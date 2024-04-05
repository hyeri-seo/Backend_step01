[Self Join - 자기 참조 조인]
; emp 사원을 관리하는 관리자
  ex) 김연아 사원의 관리자(사수) - 0301 이승철
  ex) 부서의 상위부서
  
1) 각 사원을 관리하는 관리자(사수)의 이름을 검색하세요.
e1 : 사원 테이블
e2 : 관리자 테이블
WHERE e1.mgr=e2.eno : 사원의 관리자 = 관리자 사번
; e1의 매니저는 e2의 어떤 사원이니?





[Outer Join - 외부 조인]
2) 각 부서별로 사원을 검색한다
 일반 조인
 




--7개 부서가 존재


--모든 사원들은 6개 부서에 소속


--1개 부서(POS)는 사원이 없다


 외부 조인(Outer Join)
ex) 부서는 존재하지만 소속부서원이 없는 경우
  일반 조인을 사용하면 부서가 나타나지 않는다.
  이 때 '외부 조인'을 사용하면 소속부서원이 없는
  부서도 표시되게 된다
  (+)는 데이터가 부족한 컬럼에 적어야 한다.
     의 의미는 '빈 공간(null)'이라도 추가해라.
 ; 외부 조인의 가장 중요한 의미는 출력데이터의 신뢰성 제공이다.
   양쪽의 데이터가 일치하지 않을 때 외부 조인을 통해
   모두 표현하므로 업무상 신뢰를 높일 수 있다.
 ; (+)는 양쪽중에 1군데만 쓸 수 있다.
   그렇다면 부서에 사원이 없거나
   신입사원이 부서 배정을 못받은 경우에는
   이것을 사용하지 않는다.
   추후 해결하겠다.

부서는 존재하나 부서에 소속된 사원이 없으므로
부서 정보에 대응되는 사원쪽에 null 값을 추가(+) 하겠다.

















