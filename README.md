# Textmining-Service

Smart Meeting Assistant 어플리케이션의 한글 형태소분석기 기반의 Textmining 서비스


### 사용 프레임워크(런타임 및 버전 상세 필요)
* Spring-Boot(Ver 3.9.0.RELEASE)
* Maven(Ver 4.0.0)

### 프로젝트 구조
JSON 형식의 HTTP POST 요청을 받으면 키워드 추출 후 JSON 형식으로 반환

### 로컬 구동
Sprint-Boot를 통해 구동

이후 http://localhost:8080 에서 테스트

### 기능

#### 1. 키워드 추출기능

http://localhost:8080/keyword

위 주소로 아래 포맷에 맞게 POST 요청을 보내면 결과값을 반환

#### Input Format 예시

{
"speech" : "Paragraph",
"division" : "Any division"
}

#### Output Format 예시
{
"keywords" : ["keyword1", "keyword2", "keyword3"],
"division" : "Any division"
}