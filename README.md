# worksout-TeamProject

> worksout 쇼핑몰 홈페이지 클론코딩 입니다.  
> FrameWork는 Spring Boot이며, JAVA로 개발한 쇼핑몰입니다.  
> 쇼핑몰에서 구현한 기능은 다음과 같습니다.

</br>

* 회원가입 / 로그인
  * Validation을 이용한 유효성 검사
  * Security를 이용해 ROLE 등 접근처리
  * 회원가입 시 휴대폰 인증 api
  * PrincipalDetails를 통해 header에 user정보를 담은 후 로그인 유지 및 후에 마이 페이지, 구매하기 등 정보를 불러오는 데 이용
 
* 마이 페이지
  * 회원 정보를 확인할 수 있음
  * 회원가입 시에는 주소 입력하는 부분이 없지만, 마이페이지에서는 주소를 입력하여 새로 저장할 수 있음
  * 회원탈퇴 가능  
  
* 메인 페이지
  * 메인 페이지에 상품 중 몇개를 택하여 메인에 띄워놓음

* 문의하기
  * 카테고리별 문의 내용을 작성하여 관리자에게 문의를 보낼 수 있음
  * 이미지 파일을 선택하여 파일과 함께 문의 내용을 보낼 수 있음
  * 해당 내용은 관리자 페이지에서 확인 가능
 
* 관리자 페이지
  * user정보를 관리할 수 있음
  * 상품 등록 / 수정 / 삭제 가능
  * 문의하기에서 등록된 내용들을 확인할 수 있음

* 상품 카테고리
  * 남성 / 여성 / 기타 상품으로 나누어져 있음
  * 이미지 클릭시 해당 상품으로 이동 가능

* 상품 페이지
  * 상품의 사이즈, 가격, 기타 설명 등이 등록되어 있음
  * 장바구니, 관심상품 등록 가능하며 바로 구매도 가능
  * 사이즈를 선택해야만 장바구니나 구매하기 버튼이 나타남

* 장바구니
  * 상품의 수량은 최초에 1로 정해져 있지만 언제든지 수량 변경 가능
  * 수량 변경 및 체크박스 선택시 선택된 개수만큼 가격이 증감됨
  * 장바구니 상품 전체 삭제 및 부분 삭제 가능 
  * 체크박스 선택 후 회원구매 페이지로 이동 가능

* 구매하기
  * 아임포트 api를 통해 구매하기 연동
 </br>


## 기존 사이트



[해당 사이트](https://www.worksout.co.kr/)  
</br>


## 기술 스택

![html5](https://img.shields.io/badge/HTML5-red?style=flat-square&logo=HTML5&logoColor=white)
![CSS3](https://img.shields.io/badge/-CSS3-blue?style=flat-square&logo=CSS3)
![JavaScript](https://img.shields.io/badge/-JavaScript-f7df1e?style=flat-square&logo=JavaScript&logoColor=black)
</br>

![JAVA](https://img.shields.io/badge/-JAVA-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-%236DB33F?style=flat-square&logo=SpringBoot&logoColor=white)
![Spring Security](https://img.shields.io/badge/-Spring%20Security-%236DB33F?style=flat-square&logo=Spring%20Security&logoColor=white)
![Apache TomCat](https://img.shields.io/badge/-Apache%20TomCat-%23F8DC75?style=flat-square&logo=apacheTomcat&logoColor=black)
</br>

![MariaDB](https://img.shields.io/badge/-MariaDB-%23003545?style=flat-square&logo=MariaDB)
![Mybatis](https://img.shields.io/badge/-Mybatis-%23000000?style=flat-square)

</br>

## 구현한 기능
</br>

### 메인 페이지
</br>

<img src="https://user-images.githubusercontent.com/105491519/206981624-839fb022-9495-4011-badb-f0fb8a2e08c1.png" width="100%"/>

> 메인 이미지입니다.  
> header와 footer 그리고 각 카테고리에서 뽑아온 이미지를 메인에 나타냈습니다.

</br>

### 로그인 페이지
</br>

<img src="https://user-images.githubusercontent.com/105491519/206983004-a14daffe-cc3b-4a5d-9c59-458e06513c84.png" width="400px"/>
<img src="https://user-images.githubusercontent.com/105491519/206987941-f8249b84-e54e-4bc5-9edd-844a1344657e.png" width="400px"/>

> 로그인 페이지입니다.  
> Handler를 이용해 로그인 실패시 해당 이미지처럼 alert로 사용자에게 알려줍니다. 

</br>

### 회원가입 페이지
</br>

<img src="https://user-images.githubusercontent.com/105491519/206983645-2e36a9c8-1a86-4d7f-b97d-b4c3a6954607.png" width="400px"/><img src="https://user-images.githubusercontent.com/105491519/206983757-f237e41d-e60e-4446-869e-e1edcae4dd7e.png" width="400px"/>

> 회원가입 페이지입니다.  
> Validation을 통해 사용자가 필수사항을 기입하지 아니할 경우 해당 칸에 경고 표시를 하여 알려줍니다.

</br>

<img src="https://user-images.githubusercontent.com/105491519/206988560-c897890a-b2dc-4ad0-aa19-f6d2e204bac5.jpg" width="50%"/>

<img src="https://user-images.githubusercontent.com/105491519/206988686-bea47406-c3ae-4be6-924e-195d41e7dd0c.jpg" width="50%"/>

> 회원가입 중 휴대폰 인증입니다.  
> 휴대폰 번호를 기입하고 인증버튼을 누르면 문자로 인증번호가 날아오고, 해당 번호를 기입하면 버튼이 비활성화 됩니다.

</br>

### 마이페이지
</br>

<img src="https://user-images.githubusercontent.com/105491519/210759485-63ff03b5-476b-4d63-bb91-b85b8455c29c.png" width="50%"/>  

> 마이페이지 화면입니다.  
> 가입했던 회원 정보가 들어가 있고, 주소창을 채워 정보를 수정 가능하며 필요시에 회원 탈퇴도 가능합니다.

</br>

<img src="https://user-images.githubusercontent.com/105491519/210760386-d2d7ab48-700d-43df-b200-1dd47e5658cc.gif" width="50%"/>  
  
> 회원수정 입니다.  
> 필요한 정보를 수정한 후 수정 버튼을 누르면 DB에 저장된 유저정보가 수정됩니다.

</br>

<img src="https://user-images.githubusercontent.com/105491519/210761454-aab96229-2009-43f2-8180-7bf078fc94ad.gif" width="50%"/>  
  
> 회원탈퇴 입니다.  
> 회원탈퇴 버튼 클릭시 경고문구가 나오며, 이를 클릭하면 회원탈퇴가 되어 DB에 user정보가 삭제되고, 로그인이 되지 않습니다.  

</br>

### 문의하기

### 관리자 페이지



