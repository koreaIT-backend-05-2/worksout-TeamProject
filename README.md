# worksout-TeamProject

> worksout 쇼핑몰 홈페이지 클론코딩 입니다.  
> FrameWork는 Spring Boot이며, JAVA로 개발한 쇼핑몰입니다.  
> 쇼핑몰에서 구현한 기능은 다음과 같습니다.

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


## 프로젝트 배포 및 기존 사이트



[해당 사이트](https://www.worksout.co.kr/)  
</br>
[배포 사이트]()

>배포 AWS를 통한 배포
___

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
