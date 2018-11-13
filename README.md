## 게시판
- 구현
    - 게시물 작성(insert)
    - 게시물 삭제(delete)
    - 게시물 변경(update)
    - 게시물 목록(list)
    - 게시글 읽기(read)
- 개발환경
    - Spring boot 2.1.0,
    - gradle build 2.0,
    - jdk 1.8
- 사용 라이브러리
    - jquery 3.3.1
    - jquery validate 1.18.0
    - apache common StringUtils
    - gson
    - springboot-devtools

## 실행환경

#### Window
- JDK 1.8 version 필요
- 콘솔창에서 프로젝트 압축 푼 폴더로 이동 후 다음 명령어 실행

```
gradlew.bat build
gradlew.bat run
```

#### macOS
- JDK 1.8 version 필요
- 콘솔창에서 프로젝트 압축 푼 폴더로 이동 후 다음 명령어 실행

```
$ gradlew build
$ gradlew run
```

## 주요 패키지 구조
```
board/
└── src/
    └── main/
        ├── java/
        |    └── board/
        |        ├── code/
        |        ├── config/
        |        ├── controller/
        |        ├── dao/
        |        ├── domain/
        |        └── util/
        ├── resources/
        └── webapp/
            ├── WEB-INF/ 
            └── resources/
```

- WEB-INF/data/ 하위에 게시물 파일이 저장됩니다.



