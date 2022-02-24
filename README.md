# hanghae_magazine
항해99 주특기 PBL 2-3주차 과제
- 인스타그램 기능 구현

# 프로젝트 기본 정보
**1. 제작 기간**
- 2022.02.18 ~ 2022.02.24 : 백엔드 개발
<br>

**2. 프로젝트 인원**
- 백엔드 : 1명
<br>

**3. 개발 도구**
- IDE
  - Intelli J
  
- Language
  - Java8  

- Framework
  - Spring boot
  - Hibernate (JPA / Spring Data JPA)
  
- Database
  - H2
  - MySql
  <br>

# 프로젝트 요구사항
1. 회원 가입 페이지
    - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
    - 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
    - 비밀번호 확인은 비밀번호와 정확하게 일치하기
    
2. 로그인 페이지
    - 로그인 버튼을 누른 경우 닉네임과 비밀번호가 데이터베이스에 등록됐는지 확인한 뒤, 하나라도 맞지 않는 정보가 있다면 "닉네임 또는 패스워드를 확인해주세요"라는 메세지를 프론트엔드에서 띄워줄 수 있도록 예외처리 하기
    
3. 로그인 검사
    - 로그인 하지 않은 사용자도, 게시글 목록 조회는 가능하도록 하기
    - 로그인하지 않은 사용자가 좋아요 버튼을 눌렀을 경우, "로그인이 필요합니다." 라는 메세지를 프론트엔드에서 띄워줄 수 있도록 예외처리 하기
    - 로그인 한 사용자가 로그인 페이지 또는 회원가입 페이지에 접속한 경우 "이미 로그인이 되어있습니다."라는 메세지로 예외처리하기
    
4. CORS 해결하기

5. N+1 문제 해결하기

# DB 설계

![image](https://user-images.githubusercontent.com/96904426/155471543-0c6c5370-c497-4da0-9952-46361b9e4d2c.png)

User:Post -> 1:N 양방향 연관관계<br>
Post:Like -> 1:N 양방향 연관관계<br>
User:Like -> 1:N 양방향 연관관계<br>

# API 설계

![image](https://user-images.githubusercontent.com/96904426/155475522-11765cad-0702-4bd0-ab91-8be5be4e4124.png)
<br>
![image](https://user-images.githubusercontent.com/96904426/155475585-d78b0ded-19fd-4fdb-ac67-e00b731f3665.png)



