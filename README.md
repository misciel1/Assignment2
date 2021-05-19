# Assignment2
<!--  #수평선  -->
사전과제2번(KEY시스템) : 업무별 KEY발급시스템

<!--  #목록   -->
## 1. 요구사항
+ 보험업무시스템에서 필요한 각 서비스별 필요한 KEY를 관리하는 시스템 개발
+ 새로운 KEY정보 등록/ 각 KEY별 새로운 KEY를 하나 발급기능이 필요함
+ KEY는 문자형 1종, 숫자행 2종(MySql기반, Generic기반)으로 생성됨

## 2. 설계 및 개발방향

### 2.1 설계방향
  + 인스턴스기반으로 KEY를 생성하도록 설계(다양한 업무를 위한 확장성 중심으로 설계)
  + KEY는 업무코드+년도+숫자형(문자형) 조합으로 생성. 
     (단순KEY가 아는 의미가 있는 코드를 부가하는 이유는 실물서식이나 현장에도 구분이 용이하도록 하기위함)  

### 2.2 개발방향
  + 문자형 : 업무별로 16자리 코드를 일괄로 생성하여 DB테이블에 저장하여 필요시 사용(필요시 추가생성가능)
  + 숫자형+GenericKeyGenerator :  숫자형은 KEY원부테이블에서 최종Seq값을 활용하여 프로그램으로 키생성
  + 숫자형+MysqlKeyGenerator : 숫자형으로 동일하나 Mysql함수를 활용하여 생성
  
## 3. DB구성

### 3-1. 데이터모델
![ERD](https://user-images.githubusercontent.com/84136543/118784212-c997b780-b8ca-11eb-88bf-730368bc60c9.PNG)
KEY_MST와 KEY_HIST_INFO는 1:n의 관계를 가짐. STRING_KEY_POOL_INFO는 코드테이블임.

### 3-2. 테이블
+ KEY원부(KEY_MST)
  - KEY업무구분코드(keyBizCfcd) : KEY를 사용하는 업무구분 ex)CT01 : Quote Number 
  - 변경회차(chtms) : 변경회차, 
  - 적용시기(applBgdt)  : 적용시작일자
  - 적용종기(applEnddt) : 적용종료일자
  - KEY접두사(keyPrifix) : 업무를 대표할수 있는 접두사, KEY의 앞에 위치함  ex)Quote Number :QT21000000001 
  - KEY상세설명(keyDesc) :
  - KEY길이(keyLen) :
  - KEY유형(type) :
  - 발급구분코드(genCfcd) :
  - 최종KEY일련번호(lstKeySeq) :
  - 최종KEY번호(lstKeyNum) :
   
   
+ KEY_HIST_INFO(KEY이력정보)
+ STRING_KEY_POOL_INFO(문자키POOL정보)

## 2. 개발환경
## 3. 개발프레임워크 구성
## 6. 프로그램구조
![프로그램구조](https://user-images.githubusercontent.com/84136543/118784423-f77cfc00-b8ca-11eb-9eb1-4f595167e4ff.PNG)
## 7. 단위테스트 및 결과
## 7. 빌드 및 실행방법
## 8. 구현시 고민 및 제약
