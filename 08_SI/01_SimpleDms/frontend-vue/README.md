# 노드 설치 : (했으면 생략)
  - 윈도우 : 구글 node.js 검색 
  - 맥용   : 구글 node.js 검색 
# 실행 : npm run serve
# 중단 : ctrl + c (연타)
# node_modules 폴더 재생성 : npm install
# 뷰(vue) 의 첫화면 : App.vue (화면에 서로 연결됨 : 컴포넌트끼리 연결)

# 1) 작업 : 
#  1-1) 부트스트랩 넣기 : public/index.html (cdn 넣기)
#  1-2) views/basic/dept 등 폴더 생성 & 뷰 컴포넌트(페이지) 생성
#      - vue 템플릿 단축키 : vueInit
#  1-3) 라우터 작업 : router/index.js - 페이지 & url 연결 작업
#  1-4) HeaderCom 작업 진행 : App.vue 넣기
#  1-5) axios 라이브러리 설치 : 필수
     - npm install axios
     - 설치확인 : package.json(vs build.gradle) 의 dependencies 부분
        (vue 의 라이브러리 설치 목록)
#  1-6) utils/http-common.js : spring 과 통신을 위한 설정
     - axios 기본설정(spring ip 설정)
     - 문서종류 : json 
#  1-7) services/basic/DeptService.js : 공통함수(CRUD함수들 정의)
    - 전체조회 : getAll() - http.get("컨트롤러url"); // get 방식
#  1-8) 페이징 라이브러리 : bootstrap-vue-3 라이브러리 설치
    - npm uninstall bootstrap-vue    (설치제거) 
    - npm install bootstrap-vue-3
    - 설치확인 : package.json(vs build.gradle) 의 dependencies 부분
        (vue 의 라이브러리 설치 목록)
#  1-9) 전체조회 페이지 코딩 : 
    - 경로 : views/basic/dept/DeptList.vue
    - (1) 화면 디자인 : b-pagination 태그 등 사용
    - (2) vue 바인딩 , 공통페이징함수 제작
          공통전체조회 함수 실행 (비동기 : async ~ await) 
#  1-10) npm bootstrap@5.2.3 설치 
    - npm install bootstrap@5.2.3
    - main.js : 
        (1) // TODO: bootstrap import
            import 'bootstrap/dist/js/bootstrap.bundle'
            import 'bootstrap/dist/css/bootstrap.min.css'