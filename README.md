# chickenFarm

양계장 관리 시스템을 위한 Spring Boot 기반 백엔드 API 서버입니다.

## 프로젝트 개요

양계장의 닭, 배치, 농장, 환경 설정, 센서 데이터, 예방접종, 위험 알림, 메모, 회원 관리 등을 종합적으로 관리하는 REST API를 제공합니다.

## 기술 스택

- **Java**: 17
- **Spring Boot**: 3.4.9
- **MyBatis**: 3.0.5
- **Database**: MariaDB
- **Build Tool**: Gradle

## 주요 라이브러리

- Spring Boot Starter Web - REST API 구현
- MyBatis Spring Boot Starter - 데이터베이스 매핑
- Lombok - 보일러플레이트 코드 감소
- Log4jdbc - SQL 쿼리 로깅
- MyBatis TypeHandlers JSR310 - LocalDateTime 지원
- Spring Boot DevTools - 개발 환경 자동 재시작

## 주요 기능

### 1. 닭 관리 (Chicken)
- 개별 닭의 정보 관리 (품종, 나이, 건강 상태 등)

### 2. 배치 관리 (Chicken Batch)
- 닭 배치(그룹) 단위 관리
- 자정마다 자동으로 나이 증가 (스케줄링)

### 3. 농장 관리 (Chicken Farm)
- 양계장 정보 및 설정 관리

### 4. 환경 설정 (Environment Settings)
- 농장 환경 설정값 관리

### 5. 농장 상태 (Farm Status)
- 실시간 농장 상태 모니터링

### 6. 온습도 센서 (TH Sensor)
- 온도 및 습도 센서 데이터 수집 및 조회

### 7. 예방접종 (Inoculation)
- 닭 예방접종 이력 관리

### 8. 위험 알림 (Danger Notice)
- 농장 내 위험 상황 알림 관리

### 9. 메모 (Note)
- 농장 관련 메모 및 기록 관리

### 10. 회원 관리 (Member)
- 사용자 계정 및 권한 관리

## 프로젝트 구조

```
chickenFarm/
├── src/
│   ├── main/
│   │   ├── java/com/backend/chickenFarm/
│   │   │   ├── chicken/              # 닭 관리
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── mapper/
│   │   │   │   └── dto/
│   │   │   ├── chicken_batch/        # 배치 관리
│   │   │   ├── chicken_farm/         # 농장 관리
│   │   │   ├── danger_notice/        # 위험 알림
│   │   │   ├── env_settings/         # 환경 설정
│   │   │   ├── farm_status/          # 농장 상태
│   │   │   ├── inoculation/          # 예방접종
│   │   │   ├── member/               # 회원 관리
│   │   │   ├── note/                 # 메모
│   │   │   ├── sensor_th/            # 온습도 센서
│   │   │   ├── ChickenFarmApplication.java
│   │   │   └── CorsConfig.java       # CORS 설정
│   │   └── resources/
│   │       ├── mapper/               # MyBatis XML 매퍼
│   │       │   ├── chicken-mapper.xml
│   │       │   ├── chicken-batch-mapper.xml
│   │       │   ├── chicken-farm-mapper.xml
│   │       │   ├── danger-notice-mapper.xml
│   │       │   ├── env-settings-mapper.xml
│   │       │   ├── farm-status-mapper.xml
│   │       │   ├── inoculation-mapper.xml
│   │       │   ├── member-mapper.xml
│   │       │   ├── note-mapper.xml
│   │       │   └── th-sensor-mapper.xml
│   │       ├── sql/
│   │       │   └── sample_data.sql   # 샘플 데이터
│   │       ├── application.properties
│   │       ├── log4jdbc.log4j2.properties
│   │       ├── logback.xml
│   │       └── mapper.xml
│   └── test/
├── build.gradle
└── settings.gradle
```

## 아키텍처

각 도메인은 계층형 아키텍처를 따릅니다:

- **Controller**: REST API 엔드포인트 정의
- **Service**: 비즈니스 로직 처리
- **Mapper**: MyBatis 인터페이스 (데이터베이스 접근)
- **DTO**: 데이터 전송 객체

## 시작하기

### 사전 요구사항

- JDK 17 이상
- MariaDB
- Gradle

### 데이터베이스 설정

1. MariaDB에 데이터베이스 생성
2. `src/main/resources/application.properties`에서 데이터베이스 연결 정보 설정
3. `src/main/resources/sql/sample_data.sql`로 샘플 데이터 로드 (선택사항)

### 실행 방법

```bash
# Windows
gradlew.bat bootRun

# Linux/Mac
./gradlew bootRun
```

### 빌드

```bash
# Windows
gradlew.bat build

# Linux/Mac 
./gradlew build
```

## 주요 설정

### 스케줄링
- `@EnableScheduling` 어노테이션으로 스케줄링 기능 활성화
- 매일 자정 닭의 나이 자동 증가

### CORS
- `CorsConfig.java`에서 CORS 정책 설정

### 로깅
- Log4jdbc를 통한 SQL 쿼리 로깅
- Logback을 통한 애플리케이션 로그 관리

## 개발 환경

- Spring Boot DevTools가 포함되어 있어 코드 변경 시 자동 재시작됩니다.
- 개발 중 SQL 쿼리가 콘솔에 출력되어 디버깅이 용이합니다.

## API 문서

각 컨트롤러는 다음과 같은 REST API를 제공합니다:
- Chicken API
- Chicken Batch API
- Chicken Farm API
- Danger Notice API
- Environment Settings API
- Farm Status API
- Inoculation API
- Member API
- Note API
- TH Sensor API

## 라이선스

이 프로젝트는 개인 프로젝트입니다.
