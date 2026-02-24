# 프로젝트 명세서: VibeApp

VibeApp은 게시판 기능을 포함한 Spring Boot 기반 웹 애플리케이션입니다.

## 1. 프로젝트 개요
- **프로젝트 명**: VibeApp
- **설명**: Spring Boot와 Thymeleaf를 이용한 기본적인 게시판 애플리케이션입니다.
- **주요 목적**: 기능 중심의 패키지 구조와 비즈니스 로직을 구현하여 확장성 있는 웹 구조를 학습하고 시연합니다.

## 2. 개발 환경 설정
- **JDK 버전**: JDK 25
- **언어**: Java 25
- **프레임워크**: Spring Boot 4.0.1

## 3. 빌드 및 종속성 관리
- **빌드 도구**: Gradle 9.3.0
- **DSL**: Groovy DSL (`build.gradle`)
- **주요 의존성 (Dependencies)**:
  - `spring-boot-starter-web`: 웹 서비스 구축
  - `spring-boot-starter-thymeleaf`: 뷰 템플릿 엔진
  - `spring-boot-starter-test`: 테스트 환경 구축

## 4. 아키텍처 및 패키지 구조
프로젝트는 계층형이 아닌 **기능 중심(Feature-centric)** 패키지 구조를 채택하고 있습니다.

- **패키지 구성**: `com.example.vibeapp`
  - `.home`: 홈 화면 관련 컨트롤러
  - `.post`: 게시판 관련 컨트롤러, 서비스, 리포지토리, 도메인 모델
- **역할 분담**:
  - `Controller`: 사용자 요청 매핑 및 뷰 반환
  - `Service`: 비즈니스 로직 수행
  - `Repository`: 데이터 접근 및 관리 (인메모리 방식)
  - `Post`: 게시글 엔티티(Entity) 데이터 정의

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
