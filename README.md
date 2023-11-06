# gym-website

중고 헬스용품 거래 사이트 + 헬스장 찾는 웹사이트

## 🖥️프로젝트 소개
중고 헬스용품 거래 사이트 + 헬스장 찾는 웹사이트

### ⚙️개발환경
- java 8
- Intellij
- Springboot(2.7)
- Mysql(5.x)

## 🔧주요기능

### 👨User부분

#### 로그인
- ID and PW로 검색 -> 있다면 로그인 성공
- 로그인시 세션에 loginCheck 생성 -> 로그인 되었을때와 안 됐을때 버튼 다르게하기 위해
- 로그아웃 -> 세션에서 loginCheck 삭제

#### 회원가입
- 아이디 중복 체크 -> ajax 사용. 아이디 검색해서 있다면 X
- 비밀번호 입력칸 + 비밀번화 확인칸 -> 비밀번호 일치하는지 확인

#### 코드
- [User.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/entity/User.java)
- [UserController.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/controller/UserController.java)
- [UserRepository.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/repository/UserRepository.java)
- [UserService.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/Service/UserService.java)


<details>
  <summary>상세 이미지 보기</summary>
  
  ![스크린샷 2023-11-06 110941](https://github.com/qhdlehfdl/gym-website/assets/74577699/94625d7e-7d9f-44d9-97e4-f93b79d8478a)
  ![스크린샷 2023-11-06 111137](https://github.com/qhdlehfdl/gym-website/assets/74577699/e78c0de7-65a4-42e5-9651-5cd5beb0872a)
</details>

-------------------

### 🏀Machine 부분

#### 상품등록
- 상품 올리기
- 부위, 브랜드, 가격, 사진, 상세설명 입력
- 상품페이지 맨 밑에 상품등록 버튼 있음

#### 상품페이지
- 상품 사진 확인 가능
- 부위, 브랜드, 가격 별로 검색 가능 (QueryDsl 사용)
- 장바구니 담았는지 확인 가능

#### 코드
- [Machine.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/entity/Machine.java)
- [MachineController.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/controller/MachineController.java)
- [MachineRepository.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/repository/MachineRepository.java)
- [MachineRepositoryCustorm.java(QueryDsl)](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/repository/MachineRepositoryCustom.java)
- [MachineRepositorySupport.java(QueryDsl)](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/repository/MachineRepositorySupport.java)
- [MachineService.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/Service/MachineService.java)

<details>
  <summary>상세 이미지 보기</summary>

  ![스크린샷 2023-11-06 112203](https://github.com/qhdlehfdl/gym-website/assets/74577699/5c211b95-5627-4293-8981-de84133969f0)
  ![스크린샷 2023-11-06 112302](https://github.com/qhdlehfdl/gym-website/assets/74577699/8cc82748-a3a6-44e7-b8c8-9ec20da240bd)
  ![스크린샷 2023-11-06 111957](https://github.com/qhdlehfdl/gym-website/assets/74577699/d7aac27d-0e6c-48b2-b561-a03666fa3bb4)
</details>

-------------------

### 🛒Cart 부분

#### 장바구니
- 제품 상세페이지에서 장바구니 개수 확인 가능
- 이미 장바구니에 있다면 버튼 색 다르게 -> 이미 장바구니에 있는 상태에서 한번 더 누르면 장바구니에서 삭제
- 장바구니에 들어있는 총 상품 가격 보여줌 

#### 코드
- [Cart.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/entity/Cart.java)
- [CartController.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/controller/CartController.java)
- [CartRepository.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/repository/CartRepository.java)
- [CartService.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/Service/CartService.java)

<details>
  <summary>상세 이미지 보기</summary>

  ![스크린샷 2023-11-06 112513](https://github.com/qhdlehfdl/gym-website/assets/74577699/94fd1961-adc0-4cb3-b890-f98619b95c0c)
</details>

-------------------

### 🏢Gym 부분

#### 업체 등록
- 업체 이름, 주소, 일일권 가격

#### 헬스장 찾기
- 네이버지도 api 사용
- 초기 지도 위치는 실제 현위치로 지정
- 지도에 헬스장 주소로 마커 표시
- 마커 클릭시 업체 정보 윈도우 나타남 -> 클릭시 업체정보 페이지 이동
- 마커 클러스터화
- 업체 사진 부분은 구현X

#### 코드
- [Gym.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/entity/Gym.java)
- [GymController.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/controller/GymController.java)
- [GymRepository.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/repository/GymRepository.java)
- [GymService.java](https://github.com/qhdlehfdl/gym-website/blob/main/src/main/java/com/study/shoppingmall/Service/GymService.java)


<details>
  <summary>상세 이미지 보기</summary>

  ![스크린샷 2023-11-06 112658](https://github.com/qhdlehfdl/gym-website/assets/74577699/b70ddfee-b8be-4c3f-9a6a-2c26e733479f)
  ![스크린샷 2023-11-06 112557](https://github.com/qhdlehfdl/gym-website/assets/74577699/eee748f1-7d53-4276-bcfb-a4ca701e8854)
  ![스크린샷 2023-11-06 112642](https://github.com/qhdlehfdl/gym-website/assets/74577699/d34f6c37-1542-4ddd-8fd7-5752ac648079)
</details>
