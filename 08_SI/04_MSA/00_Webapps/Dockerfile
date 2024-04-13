# 도커 이미지 다운로드명 : nginx 1.23 버전 다운로드
FROM nginx:1.23.3
# rm : 삭제 
# 도커 이미지(nginx)의 /etc/nginx/conf.d의 폴더를 삭제하겠다.
RUN rm -rf /etc/nginx/conf.d
# 내가 만든 conf 폴더를 도커이미지(nginx) /etc/nginx 폴더에 복사하겠다.
# 사용법 : COPY 윈도우폴더 도커이미지폴더 : 윈도우폴더 내용을 도커이미지폴더로 복사하겠다.
COPY conf /etc/nginx
# 내가 만든 html 폴더를 도커이미지(nginx) /usr/share/nginx/html 폴더에 복사하겠다.
# 참고) nginx 웹서버의 기본 경로(/) : usr/share/nginx/html
# 예) http://localhost:8080/ => nginx 의 기본경로 : 여기를 검색창에 치면 vue의 첫화면이 보인다.
# vue 내장에 웹 서버가 있는것. 배포하면 테스팅 웹 서버를 쓸 수 없어서 복사한것
COPY html /usr/share/nginx/html
# nginx 웹서버를 실행하는 명령어. 도커 이미지 제작 후 컨테이너가 실행될 때 1번 실행됨
# nginx 명령어 : nginx -g daemon off (daemon off는 옵션)
CMD ["nginx", "-g", "daemon off;"]