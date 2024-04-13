# 도커 이미지 배포본 다운로드 : jdk(자바) + 리눅스
FROM openjdk:17-alpine
# oracle cloud 를 위한 전자지갑 파일을 복사
# springboot application.properties 전자지갑 주소와 일치시킬것
COPY Wallet_orcl /Users/eWallet/Wallet_orcl/
# springboot 배포본을 도커이미지안에 복사
COPY Approval.war Approval.war
# 벡엔드 배포본 실행 명령어 : 컨테이너 실행될때 1번
# java -jar Approval.war
CMD ["java", "-jar", "Approval.war"]