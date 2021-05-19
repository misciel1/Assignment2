# Assignment2
<!--  #수평선  -->
사전과제2번(KEY시스템) : 업무별 KEY발급시스템
</br>  
</br>  


<!--  #목록   -->
## 1. 요구사항
+ 보험업무시스템에서 필요한 각 서비스별 필요한 KEY를 관리하는 시스템 개발
+ 새로운 KEY정보 등록/ 각 KEY별 새로운 KEY를 하나 발급기능이 필요함
+ KEY는 문자형 1종, 숫자행 2종(MySql기반, Generic기반)으로 생성됨
</br>  

  
  
## 2. 개발환경 및 프레임워크
+ OS : Windows 10
+ Language : JAVA 1.8
+ IDE : Eclipse IDE for Java Developers  2021-03 (4.19.0)
+ DB: MySQL 5.7
+ SQL Framework : mybatis-3.5.7
</br>  
 



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
</br>   
 
 
 
## 4. DB구성

### 4-1. 데이터모델
![ERD](https://user-images.githubusercontent.com/84136543/118784212-c997b780-b8ca-11eb-88bf-730368bc60c9.PNG)

KEY_MST와 KEY_HIST_INFO는 1:n의 관계를 가짐. STRING_KEY_POOL_INFO는 코드테이블임.
KEY_MST만으로 KEY발급이 가능함(단독만으로 운영시 속도에 유리). 데이터분석을 위해 발급이력을 저장하여 활용할 수 있음.

### 4-2. 테이블
+ **KEY원부(KEY_MST)** KEY를 구성할 수 있는 기준테이블. 
  - KEY업무구분코드(keyBizCfcd) : KEY를 사용하는 업무구분     ex)CT01 : Quote Number 
  - 변경회차(chtms) : 변경회차, 
  - 적용시기(applBgdt)  : 적용시작일자
  - 적용종기(applEnddt) : 적용종료일자
  - KEY접두사(keyPrifix) : 업무를 대표할수 있는 접두사, KEY의 앞에 위치함    ex)Quote Number :QT21000000001 
  - KEY상세설명(keyDesc) : 해당 KEY를 사용하는 업무의 상세정보
  - KEY길이(keyLen) : KEY의 길이. 'KEY = KEY접두사+KEY길이'로 구성    ex)keyLen =10 ,Key = QT21+ 0000000001(10자리)
  - KEY유형(type) : 01-문자형  02-숫자형+GenericKeyGenerator  03-숫자형+MysqlKeyGenerator
  - 발급형식코드(genCfcd) : 숫자형Only. 접두사에 표현형태구분  01- Prifix+년도(두자리)+KeySeq  02 - Prifix+년도(YYYY)+KeyNum+KeySeq
  - 최종KEY일련번호(lstKeySeq) :  KEY를 생성시 연산에 필요한 기준. 숫자형의 경우 lstKeySeq+1를 통해 신규키생성, 문자형의 경우 해당항목을 기준으로 테이블을 조회함
  - 최종KEY번호(lstKeyNum) : 발급한 최종KEY번호
   
   
+ **KEY_HIST_INFO(KEY이력정보)** - KEY의 발급과 관련된 다양한 분석을 위한 테이블 
  - KEY업무구분코드(keyBizCfcd)
  - 변경회차(chtms)
  - KEY번호(keyNum) : 실제로 사용하는 KEY
  - KEY일련번호(keySeq) 
  - KEY발급시간일시(keyRgtTimeStamp) : KEY발급시간일시
  - 시스템이름(systemName) : KEY를 발급한 서비스정보   ex)com.app.KeyAppTest7.issueNewKey
  - 시스템사용IP(systemUsedIP) : KEY를 발급한 IP주소  ex)192.168.219.120
  
  
+ **STRING_KEY_POOL_INFO(문자키POOL정보)** - 문자형KEY 발급을 위한 코드성테이블
  - KEY일련번호(keySeq)
  - KEY업무구분코드(keyBizCfcd)
  - KEY번호(keyNum)
  - KEY발급시간일시(keyRgtTimeStamp)
 </br>  
 
  

## 5. 프로그램구조

### 5-1. 전체구조
+ 패키지경로 : /src/main/java/com/

![프로그램구조](https://user-images.githubusercontent.com/84136543/118784423-f77cfc00-b8ca-11eb-9eb1-4f595167e4ff.PNG)


### 5-2. 패키지별 기능설명
+ PC(Process Component) : 외부에 노출되어 제공하는 서비스컴포넌트. 비지니스프로세스가 담긴다
+ EC(Entity Component)  : 비지니스로직이 담긴 단위 컴포넌트
+ UC(Utility Component) : 비지니스로직이 없는 간단한 함수형 컴포넌트. 
+ DAO(Data Access Object) : DB에 접근하는 객체
+ DTO(Data Transfer Object) : 데이터 교환을 위한 객체

### 5-3. 주요서비스 및 컴포넌트 설명
+ KEY관리PC(KeyMgmtPC) - 신규 KEY발급, KEY정보등록등을 관리하는 서비스. 다른시스템에 의해 호출됨.
  - 신규KEY번호발급(issueNewKey) : KEY업무구분코드를 받아 신규KEY를 발급
  - NEW KEY정보등록(rigsterNewKeyInfo) : 새로운 업무에 관한 KEY정보를 등록
  - 문자형KEY생성(makeStringKey) : 문자형KEY를 일괄생성

+ KEY팩토리EC(KeyFactoryEC) - 업무별 KEY원부테이블의 기준에 따라 각 키유형별 발급함수호출
+ 문자KEY발급EC(StringKeyGenEC) -  문자형 KEY발급함수.UUID를 통하여 생성후 HashMAP을 통하여 중복체크함
+ 숫자KEY발급EC(NumericKeyGenEC) - 숫자형 KEY발급함수. 두가지 발급방식(MysqlKeyGenerator,GenericKeyGenerator)에 따라 Key번호생성
+ KEY번호핸들러(KeyNumHandlerUC) - 숫자형 KEY일련번호를 KEY길이에 맞게 가공  
</br>  



## 7. 단위테스트 및 결과

단위 테스트는 "src\main\java\com\app" 폴더 아래에 있는 파일들을 실행



제공되는 단위 테스트 파일은 아래와 같음

![newKey결과](https://user-images.githubusercontent.com/84136543/118821606-5dc94500-b8f2-11eb-8446-983f7983c5c6.PNG)
- KeyAppTest1.java : 신규KEY발급테스트- 숫자형Gneric
- KeyAppTest2.java : 신규KEY발급테스트- 숫자형MySql
- KeyAppTest3.java : 신규KEY발급테스트- 문자형
- KeyAppTest7.java : 멀티스레드 기반 테스트
- KeyAppTest8.java : 문자형KEY일괄생성 테스트
- KeyAppTest9.java : 새로운 KEY정보 등록 테스트
</br>  

## 8. 빌드 및 실행방법
+ Github의 소스에서 다운로드
+ 개발환경을 구성하여 아래 DB백업 설치후 실행
+ [DB백업 링크] https://drive.google.com/file/d/1aQ0rE6xr_UlI_aR71_LkMTTlPO_oPMP3/view?usp=sharing
</br>  

## 9. 구현시 고민
+ 실시간 문자KEY생성 방안에 대해 고민 
  - 발급시 중복체크에 성능문제
  - 배치프로그램을 통하여 일괄생성후 발급속도에 따라 추가생성이 필요할 것으로 보임
+ MysqlKeyGenerator설계시 고민
  - 최초 설계시 KEY구조를 KEY접두사+숫자형(문자형)로 정의하여 MysqlKeyGenerator설계시 제약이 많이 발생함
  - Mysql만의 함수를 사용하기위해 위 KEY구조가 적합하지 않아 일반적인 방식으로 처리함

