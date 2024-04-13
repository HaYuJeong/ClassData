# 00_Webapps : 뷰 배포소스가 있는곳
# 01_Approval_Docker : spring 배포소스가 있는곳

# 1) 프론트 배포 : Vue 배포
    1-1) Vue 배포본 실행 명령어 : 
        npm run build
# 2) 프론트 배포본 -> WebServer 프로그램에 배포됨
#   WebServer(웹서버) : html, js, css 등을 가지고 웹브라우저에 보여주는 전용 프로그램(성능개선 캐싱기능, 라우팅기능 등)
#  vs WAS(애플리케이션 서버) : 자바 프로그램을 웹에 게시하는 프로그램
#   WebServer(웹서버) 종류 : 아파치 웹서버(무료), nginx(무료, 성능 좋음)
#   WAS               종류 : 톰캣(무료), springboot(톰캣 내장되어 있음)

# 3) 프론트 도커 빌드 준비
    - 00_Webapps/conf/conf.d/default.conf : nginx 설정파일 준비
    - 00_Webapps/html/*(뷰배포본복사)      : 뷰(vue) 배포본 복사
    - Dockerbuild                         : 도커 이미지 제작 파일(프론트엔드 도커파일)

# 4) 벡엔드 배포본 -> WAS 프로그램에 배포 : springboot (WAS 내장) -> springboot 배포본 제작
    - 인텔리제이에서 오른쪽 탭 gradle 아이콘 클릭
    - tasks - build - bootWar : 배포 명령어(내장 톰캣과 함께 배포본 생성)
    => 배포본 경로 : 오른쪽 build - libs : 안에 배포본이 생성됨 xx.war

# 5) 벡엔드 도커 빌드 준비
    - 01_Approval_Docker/Wallet_orcl : 클라우드 DB 전자지갑 복사
    - 01_Approval_Docker             : xx.war 파일 복사
    - Dockerfile                     : 도커 빌드 파일 준비 
# 6) 도커-컴포즈 실행 명령어
    - docker-compose up