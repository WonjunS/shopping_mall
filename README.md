# 쇼핑몰 개발 프로젝트

## 1. 개요
스프링 프레임워크를 사용한 프로젝트입니다. 쇼핑몰의 기본적인 기능들을 MySQL을 연동하여 구현했습니다.

## 2. 기술 스택 및 개발 환경
### 2.1. 기술 스택
**Front-End**
- Thymeleaf
- HTML, CSS, JS
- Bootstrap  


**Back-End**
- Java 11
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL   

### 2.2. 개발 환경
**OS**
- Windows  

**개발 환경 (IDE)**
- IntelliJ IDEA  

**개발 도구**
- 리비전 제어: Git
- 빌드 도구: Gradle  

**개발 언어**
- Java  

**형상 관리**
- GitHub

## 3. 데이터베이스 설계 (ERD)
![Shopping Mall (1)](https://user-images.githubusercontent.com/93713151/209469085-d85417eb-7cab-439f-941c-1d1ccd17a29f.png)  


## 4. 요구사항 분석
### 4.1. 회원가입 페이지
- 유효성 검사
  * 이메일은 이메일 형식을 적용해 확인
  * 입력된 이메일로 인증코드를 보내서 일치하는지 확인
  * 공백 혹은 빈칸이 있을 경우, "OOO는 필수 입력 값입니다."의 메세지 보여주기
  * 비밀번호는 최소 6자 이상으로 사용하게 하기

- 중복 검사
  * 데이터베이스에 이미 존재하는 이메일을 사용할 경우, "이미 존재하는 이메일입니다."의 메세지 보여주기
  * 모든 검사가 통과되었다면, 메인 페이지로 이동하기  


### 4.2. 로그인 페이지
- 로그인 하지 않은 경우 다음 페이지만 이용 가능
  * 회원가입 페이지
  * 로그인 페이지
  * 메인 페이지
  * 상품 페이지


- 로그인 검사
  * 이메일 혹은 비밀번호가 일치하지 않으면, "아이디 또는 비밀번호를 확인해주세요."의 메세지 보여주기
  * 모든 검사가 통과되면 메인 페이지로 이동

### 4.3. 메인 페이지

### 4.4. 주문 페이지

### 4.5. 장바구니

## 5. 실행 화면
### 1. 메인 페이지

### 2. 회원가입
![image](https://user-images.githubusercontent.com/93713151/210563800-5514d521-ec77-45f6-a1ce-055546d21399.png)  


### 3. 로그인
![image](https://user-images.githubusercontent.com/93713151/210563963-4289fd4d-8d25-46b5-ab57-3e7f13b0fef6.png)
