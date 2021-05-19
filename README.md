# Assignment2
<!--  #수평선  -->
사전과제2번(KEY시스템) : 업무별 KEY발급시스템

<!--  #목록   -->
## 1. 요구사항
+ 보험업무시스템에서 필요한 각 서비스별 필요한 KEY를 관리하는 시스템 개발
+ 새로운 KEY정보 등록/ 각 KEY별 새로운 KEY를 하나 발급기능이 필요함
+ KEY는 문자형 1종, 숫자행 2종(MySql기반, Generic기반)으로 생성됨

## 2. 개발환경




## 3. 설계 및 개발방향

### 3.1 설계방향
  + 인스턴스기반으로 KEY를 생성하도록 설계(다양한 업무를 위한 확장성 중심으로 설계)
  + KEY는 업무코드+년도+숫자형(문자형) 조합으로 생성. 
     (단순KEY가 아는 의미가 있는 코드를 부가하는 이유는 실물서식이나 현장에도 구분이 용이하도록 하기위함)  
  + KEY이력정보테이블을 통하여 KEY발급 트랜드를 분석할수 있도록 함.

### 3.2 개발방향
  + 문자형 : 실시간 문자형KEY발급시 성능상 문제가 예상됨. 코드테이블을 생성하여 일괄생성후 온라인으로 해당 테이블조회하여 사용
  + 숫자형+GenericKeyGenerator :  숫자형은 KEY원부테이블에서 최종Seq값을 활용하여 프로그램으로 키생성
  + 숫자형+MysqlKeyGenerator : 숫자형으로 동일하나 Mysql함수를 활용하여 생성
  
## 4. DB구성

### 4-1. 데이터모델
![ERD](https://user-images.githubusercontent.com/84136543/118784212-c997b780-b8ca-11eb-88bf-730368bc60c9.PNG)
KEY_MST와 KEY_HIST_INFO는 1:n의 관계를 가짐. STRING_KEY_POOL_INFO는 코드테이블임.
KEY_MST만으로 KEY발급이 가능함(단독만으로 운영시 속도에 유리). 데이터분석을 위해 발급이력을 저장하여 활용할 수 있음.

### 4-2. 테이블
+ KEY원부(KEY_MST) : KEY를 구성할 수 있는 기준테이블. 
  - KEY업무구분코드(keyBizCfcd) : KEY를 사용하는 업무구분     ex)CT01 : Quote Number 
  - 변경회차(chtms) : 변경회차, 
  - 적용시기(applBgdt)  : 적용시작일자
  - 적용종기(applEnddt) : 적용종료일자
  - KEY접두사(keyPrifix) : 업무를 대표할수 있는 접두사, KEY의 앞에 위치함    ex)Quote Number :QT21000000001 
  - KEY상세설명(keyDesc) : 해당 KEY를 사용하는 업무의 상세정보
  - KEY길이(keyLen) : KEY의 길이. 'KEY = KEY접두사+KEY길이'로 구성    ex)keyLen =10 ,Key = QT21+ 0000000001(10자리)
  - KEY유형(type) : 01-문자형  02-숫자형+GenericKeyGenerator  03-숫자형+MysqlKeyGenerator
  - 발급형식코드(genCfcd) : 숫자형Only. 접두사에 표현형태구분  1- Prifix+년도(두자리)+KeySeq  2 - Prifix+년도(YYYY)+KeyNum+KeySeq
  - 최종KEY일련번호(lstKeySeq) :  KEY를 생성시 연산에 필요한 기준. 숫자형의 경우 lstKeySeq+1를 통해 신규키생성, 문자형의 경우 해당항목을 기준으로 테이블을 조회함
  - 최종KEY번호(lstKeyNum) : 발급한 최종KEY번호
   
   
+ KEY_HIST_INFO(KEY이력정보) : KEY의 발급과 관련된 다양한 분석을 위한 테이블 
  -KEY업무구분코드(keyBizCfcd)
  -변경회차(chtms)
  -KEY번호(keyNum) : 실제로 사용하는 KEY
  -KEY일련번호(keySeq) 
  -KEY발급시간일시(keyRgtTimeStamp) : KEY발급시간일시
  -시스템이름(systemName) : KEY를 발급한 서비스정보   ex)com.app.KeyAppTest7.issueNewKey
  -시스템사용IP(systemUsedIP) : KEY를 발급한 IP주소  ex)192.168.219.120
  *분석방향에 따라 다향한 팩터를 추가가능
  
+ STRING_KEY_POOL_INFO(문자키POOL정보) : 문자형KEY 발급을 위한 코드성테이블
  -KEY일련번호(keySeq)
  -KEY업무구분코드(keyBizCfcd)
  -KEY번호(keyNum)
  -KEY발급시간일시(keyRgtTimeStamp)
  
  

## 5. 프로그램구조

### 5-1. 전체구조
![프로그램구조](https://user-images.githubusercontent.com/84136543/118784423-f77cfc00-b8ca-11eb-9eb1-4f595167e4ff.PNG)


### 5-2. 패키지별 기능설명
+PC(Process Component) : 외부에 노출되어 제공하는 서비스컴포넌트. 비지니스프로세스가 담긴다
+EC(Entity Component)  : 비지니스로직이 담긴 단위 컴포넌트
+UC(Utility Component) : 비지니스로직이 없는 간단한 함수형 컴포넌트. 
+DAO(Data Access Object) : DB에 접근하는 객체
+DTO(Data Transfer Object) : 데이터 교환을 위한 객체

### 5-3. 주요서비스 및 컴포넌트 설명
+KEY관리PC(KeyMgmtPC)
  -신규KEY번호발급(issueNewKey)
  -NEW KEY정보등록(rigsterNewKeyInfo)
  -문자형KEY생성(makeStringKey)

+KEY팩토리EC(KeyFactoryEC)
+문자KEY발급EC(StringKeyGenEC)
+숫자KEY발급EC(NumericKeyGenEC)
+KEY번호핸들러(KeyNumHandlerUC)



## 7. 단위테스트 및 결과



## 7. 빌드 및 실행방법
## 8. 구현시 고민 및 제약
