# Maven + Jacoco

## 메이븐 빌드툴과 플러그인 Jacoco 를 써보았다

- 간단한 클래스를 만들었다.
- 메소드 들도 만들었다.
- 테스트 코드들도 만들었다.
- 커버리지 테스트이기에 테스트 코드들 많이 만들었다.

## 필요
- IJ 2019.2.4
- maven
- jdk 12
- jacoco 0.8.4

### 실행 (테스트 커버리지 확인)
1. mvn clean
2. mvn compile
3. mvn test
4. /target/site/jacoco/index.html