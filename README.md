# insta-timeline

## 프로젝트 설명

> 익명으로 24시간동안 유지되는 사진과 글을 공유할 수 있는 웹 어플리케이션입니다.

![image](https://user-images.githubusercontent.com/38418028/137746317-651481b3-ec26-4553-885d-f02e3a389e92.png)

## 프로젝트 실행

```bash
$ git clone https://github.com/Wooyongjeong/insta-timeline.git
$ ./gradlew build
$ cd build/libs && java -jar insta-timeline-0.0.1.SNAPSHOT.jar
```

실행 후 [http://localhost:8080](http://localhost:8080/)로 이동

## 기술 스택

<p align="center">
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white"/>
    <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=Thymeleaf&logoColor=white"/>
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>
    <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=white"/>
</p>

## 프로젝트 기능

### 사진 및 글 업로드

![image](https://user-images.githubusercontent.com/38418028/137746416-f52d3268-17c6-488d-8da3-c6bcf8a89bfe.png)

### 공유한 게시물 보기

![image](https://user-images.githubusercontent.com/38418028/137747622-c19f5ace-4149-4b83-ae7c-aec3f8a6d8ab.png)

### 24시간이 지난 게시글 삭제

1시간마다 게시글 중 24시간이 지난 게시글과 사진을 자동으로 삭제합니다.
