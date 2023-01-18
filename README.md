# 쇼핑몰 개발 프로젝트

## 1. 개요
- **프로젝트 명칭**: Shopping Mall
- **제작 기간**: 2022년 12월 1일 ~ 2023년 1월 10일
- **참여 인원**: 1명
- **개발 언어**: Java 11
- **프로젝트 소개**: 웹 개발의 기본 소양이라고 할 수 있는 쇼핑몰을 직접 개발하고 여러 기능들을 직접 구현해보기 위해 이 프로젝트를 시작했습니다. 필요한 기술들을 먼저 독학한 후에 제작한 프로젝트라 개발자로서의 기본적인 역량을 쌓는 데에 큰 도움이 되었던 프로젝트였기 때문에 개인적으로 만족했던 프로젝트입니다.   


## 2. 기술 스택
**Back-End**
- Java 11
- Spring Boot 2.7.5
- Spring Security
- Spring Data JPA
- MySQL  


**Front-End**
- Thymeleaf
- HTML / CSS
- JavaScript
- Bootstrap 4.6.2   


**Database**
- MySQL  


**Build Tool**
- Gradle


## 3. 데이터베이스 설계 (ERD)
![Shopping Mall (1)](https://user-images.githubusercontent.com/93713151/209469085-d85417eb-7cab-439f-941c-1d1ccd17a29f.png)  



## 4. 실행 화면
<details><summary>메인 페이지</summary>
  
  #### 메인화면 - 사용자 계정
  
  사용자 계정에서는 장바구니, 주문내역, 회원정보 등의 기능을 이용할 수 있습니다.
  ![image](https://user-images.githubusercontent.com/93713151/211207674-48b22b7e-bcbd-45c9-803e-abf153190295.png)

  
  #### 메인화면 - 관리자 계정
  
  관리자 계정에서는 사용자 계정에서 사용 가능한 기능 외에 회원관리, 상품 등록 및 수정 등의 기능 또한 이용할 수 있습니다.
  ![image](https://user-images.githubusercontent.com/93713151/211207708-1536ee76-ec77-4e8c-8ba3-706aabac6e47.png)
  
  
  
  #### 판매중인 상품의 모습
  
  판매중인 상품은 '판매중'이라는 마크를 확인할 수 있고, 상품을 주문하거나 장바구니에 담을수 있습니다.
  <img src="https://user-images.githubusercontent.com/93713151/211207133-7a560a71-6065-4a90-a806-5d0ef7eb7988.png" width="390px" height="400px">  
  
   
  #### 품절된 상품의 모습
  
  품절된 상품은 주문하기나 장바구니 기능을 이용할 수 없고, 상품 이름 위에 '품절'이라는 마크가 붙어 있습니다.
  <img src="https://user-images.githubusercontent.com/93713151/211207155-d3a6f109-2b72-49d9-bd2f-bb8cdc7a63d6.png" width="390px" height="400px">

</details>  



<details><summary>회원</summary>
  
  #### 회원가입
  
  쇼핑몰의 회원가입 페이지 입니다.
  ![image](https://user-images.githubusercontent.com/93713151/211153727-8cf352e0-586a-45e6-9712-abfe73f244a0.png)

  <img src="https://user-images.githubusercontent.com/93713151/211154272-a32e7091-6844-4a41-9cea-2ac2027697ae.png" width="390px" height="420px">
  
  <img src="https://user-images.githubusercontent.com/93713151/211154696-983973df-fd1b-4bf8-bff6-2afce08ff5a5.png" width="390px" height="420px">
  
  <img src="https://user-images.githubusercontent.com/93713151/211154823-d9e9438d-3b02-458f-ad8b-ed31dc7d75e7.png" width="390px" height="380px">
  
  <img src="https://user-images.githubusercontent.com/93713151/211154983-aa8f7e39-fef5-4871-b78e-ca917da64b2b.png" width="390px" height="450px">  
  
  위 사진들은 회원가입 페이지에서의 예외 처리 및 이메일 인증 기능을 확인할 수 있습니다.
  

  #### 로그인
  
  쇼핑몰의 로그인 페이지 입니다.
  
  ![image](https://user-images.githubusercontent.com/93713151/211155714-48c98253-6816-4910-98b3-433d3f0f24ee.png)   
  
  
  아이디 혹은 비밀번호가 틀렸을 경우 아래 메세지를 확인할 수 있습니다.
  ![image](https://user-images.githubusercontent.com/93713151/211155668-089332af-5903-419a-88ae-636627267e96.png)
  
  
  #### 회원정보
  
  회원정보를 확인하는 페이지입니다. 수정하기 버튼을 클릭해서 회원정보를 수정할 수 있습니다.
  

  <img src="https://user-images.githubusercontent.com/93713151/211830638-1b66f790-5b8c-4033-b4c3-1a3cd4dcd0ef.png" width="780px" height="700px">

  <img src="https://user-images.githubusercontent.com/93713151/211209735-5d2cd1e9-2afe-4d8c-8c09-0fd6b971163d.png" width="390px" height="180px">
  
  <img src="https://user-images.githubusercontent.com/93713151/211208464-ab9aebea-e159-4726-815c-413c442bdc6f.png" width="390px" height="180px">  



</details>  


<details><summary>상품</summary>
  
  #### 상품 등록 페이지
  <img src = "https://user-images.githubusercontent.com/93713151/211206330-4ade9b54-965e-40b5-81c4-3903b5a8116a.png" width="390px" height="400px">
  
  상품을 등록하는 페이지입니다. 상품의 이미지는 최대 5장까지 업로드 가능하며 상품을 등록하기 위해서는 이름, 가격, 수량, 최소 1장의 이미지등의 데이터를 입력해야합니다.
  
  
  #### 상품 수정 페이지
  ![image](https://user-images.githubusercontent.com/93713151/211207574-1e44b59c-c016-42c8-8691-800cebe899c0.png)
  
  상품을 수정하는 페이지입니다. 상품 리스트에서 상품 이름을 클릭하면 수정 페이지로 이동합니다.

  
  <img src = "https://user-images.githubusercontent.com/93713151/211206014-eb161a2f-1f0e-4f2d-95cc-e51f689da63d.png" width="390px" height="400px">

</details>  



<details><summary>주문</summary>

  #### 장바구니
  
  현재 장바구니에 담긴 상품들을 확인 할 수 있습니다. 체크박스로 주문할 상품들을 고를수 있으며 수량도 조절할 수 있습니다. X 버튼을 누르면 상품을 제거할 수 있고, 상품을 다 고르면 우측 하단에 총 주문 금액이 표시됩니다.
  ![image](https://user-images.githubusercontent.com/93713151/211208834-b2453148-cfc3-4b97-a4ec-091623cfd77a.png)
  
  
  
  #### 결제
  카카오페이를 활용하여 결제할 수 있습니다.
  ![image](https://user-images.githubusercontent.com/93713151/213204133-6188401d-a9b8-41d1-8bef-54cab4641c4b.png)   
  
  
  또 다른 옵션으로는 신용카드 결제가 있습니다.
  ![image](https://user-images.githubusercontent.com/93713151/213204273-5abba189-76f5-4745-ac8b-e27a2709a98f.png)



  
  #### 주문내역
  
  상품을 구매했을 때의 이력을 확인할 수 있습니다. 주문 취소 버튼을 클릭해서 주문을 취소할 수 있습니다.
  ![image](https://user-images.githubusercontent.com/93713151/211208867-946b0031-d1ec-421b-ae43-777131072ceb.png)   
  
  
  주문을 완료하면 회원은 본인이 지정한 이메일 계정으로 주문내역서를 받을수 있습니다.
  ![image](https://user-images.githubusercontent.com/93713151/211209622-581f6359-5a4e-43f9-94ca-3dc49e9cd408.png)

  
  
  주문을 취소할 경우에 "Cancelled" 라벨이 붙습니다.
  ![image](https://user-images.githubusercontent.com/93713151/211208899-388051b9-02e5-4fb9-a375-4bdb65b79581.png)


  
  
</details>


## 5. 보완사항 및 후기
### 향후 추가하고 싶은 기능
- 카카오페이, 신용카드, 무통장 입금 등을 포함한 결제 시스템 추가 (구현 완료)
- 네이버, 구글 같은 타 사이트 계정을 통한 회원가입 구현  


### 느낀점
이번 프로젝트 동안 쇼핑몰을 개발하기 위해 많은 자료들을 찾아서 공부하고 다른 개발자들이 작성해놓은 코드도 참고하면서 많은 것을 배울 수 있었던 프로젝트였다는 생각이 듭니다. 그룹 프로젝트로 진행할 수도 있었지만, 그전에 하나의 웹 프로그램을 프런트부터 백엔드까지 혼자서 전부 구현해보고 싶어서 이번 프로젝트를 진행하였습니다. 우리가 일상에서 당연하게 사용하고 있었던 회원가입, 로그인, 상품 주문, 장바구니 등의 기능들을 실제로 구현하려고 하니 생각보다 복잡하게 느껴졌고 어려움도 많이 겪었지만, 작은 단위의 기능부터 시작해서 로직의 전체적인 흐름을 따라서 구현해보니 스프링 웹 개발에 대한 전체적인 흐름을 파악할 수 있었습니다. 개발하는 동안 기본적인 기능이라도 확실하게 이해하기 위해 노력했고, 그 결과 이전보다 더 성장한 개발자가 되는 계기가 되었다고 생각합니다. 또한, 현재에 만족하지 않고 더 나은 개발자가 되기 위해 끊임없이 노력하고 다음에는 이번보다 더 퀄리티 있는 프로그램을 만들어 보고 싶다는 목표도 생겼습니다.
