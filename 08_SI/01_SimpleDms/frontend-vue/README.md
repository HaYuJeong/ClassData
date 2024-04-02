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