# 프로젝트 명세서: VibeApp

VibeApp은 게시판 기능을 포함한 Spring Boot 기반 웹 애플리케이션입니다.

## 1. 프로젝트 개요
- **프로젝트 명**: VibeApp
- **설명**: Spring Boot와 Thymeleaf를 이용한 기본적인 게시판 애플리케이션입니다.
- **주요 목적**: 기능 중심의 패키지 구조와 비즈니스 로직을 구현하여 확장성 있는 웹 구조를 학습하고 시연합니다.

## 2. 개발 환경 설정
- **JDK 버전**: JDK 25
- **Language**: Java 25
- **Spring Boot Version**: 4.0.1
- **Build Tool**: Gradle (Groovy DSL)
- **Database**: H2 Database (File Mode: `./data/testdb`)
- **Persistence Framework**: MyBatis
- **View Engine**: Thymeleaf

## 3. 빌드 및 종속성 관리
- **주요 의존성 (Dependencies)**:
  - `spring-boot-starter-web`: 웹 서비스 구축
  - `spring-boot-starter-validation`: Bean Validation 지원
  - `mybatis-spring-boot-starter`: MyBatis 연동 지원
  - `h2`: H2 Database 지원
  - `spring-boot-starter-thymeleaf`: 뷰 템플릿 엔진
  - `spring-boot-starter-test`: 테스트 환경 구축

## 4. 아키텍처 및 패키지 구조
- 프로젝트는 기능 중심 패키지 구조와 **DTO 패턴**을 채택하고 있습니다.

- **패키지 구성**: `com.example.vibeapp`
  - `.home`: 홈 화면 관련 컨트롤러
  - `.post`: 게시판 관련 컨트롤러, 서비스, 리포지토리, 도메인 모델
- **역할 분담**:
  - `Controller`: DTO를 통한 데이터 수신 및 `@Valid`를 이용한 입력값 검증 수행
  - `Service`: 비즈니스 로직 수행 및 DTO <-> Entity 변환 처리
  - `Repository`: 데이터 접근 및 관리 (인메모리 방식)
  - `DTO`: `PostCreateDto`, `PostUpdateDto`, `PostResponseDto`, `PostListDto`를 통해 계층 간 데이터 전송
- **데이터 유효성 검사**:
  - `jakarta.validation`을 사용하여 제목(필수, 최대 100자) 등에 대한 제약 조건 적용

## 5. 구현된 주요 기능
- **홈 화면**: 애플리케이션 진입점 제공
- **게시판 기능**:
  - 게시글 작성 및 목록 조회 (최신순 정렬)
  - **게시글 페이징 처리**: 페이지당 5개의 게시글 출력 및 네비게이션 제공
  - 게시글 상세 조회 및 조회수(더미) 관리
  - 게시글 수정 및 수정 시간 기록
  - 게시글 삭제 기능

## 6. 설정 및 뷰 템플릿
- **구성 파일**: YAML (`src/main/resources/application.yml`)
- **뷰 템플릿**: `src/main/resources/templates`
  - `/home`: 홈 관련 템플릿 (`home.html`)
  - `/post`: 게시글 관련 템플릿 (`posts.html`, `post_detail.html` 등)

---
> [!NOTE]
> 이 프로젝트는 기능 중심 구조를 통해 각 기능별 독립성을 높였으며, 실무 관례에 따른 네이밍 컨벤션을 준수합니다.
