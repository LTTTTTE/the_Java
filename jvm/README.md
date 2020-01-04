# JVM

## 질문

- JDK 벤더는 Oracle, 아마존 말고 어떤 단체가 있으며 정책 이외의 차이가 있는가?

- Oracle JDK 11 이외에 전부 무료인데 왜 각 벤더들마다 JDK 를 배포하는가?

- Garbage Collector 의 종류와 동작과정의 차이

- JIT 컴파일러에 대한 자세한 설명


## 답변

- JDK 종류에는 Oracle JDK 와 OpenJDK 가있는데 OpenJDK 도 Oracle 주도하고 지원한다. (오픈소스 하고자 시작했단다)

- OpenJDK 의 라이센스는 GPL 기반이며 벤더에의해 바뀌지 않는다.

- OpenJDK 기반하여 여러 업체들이 OpenJDK 구현체들을 제작하여 배포하는데 그 업체들은 Oracle 의 유료 라이센스인 OCTLA 에 가입해야한다.

- 각 업체별 OpenJDK 는 Oracle 의 TCK 인증을 통과해야 한다.

- #### OpenJDK 종류
    - AdoptOpenJDK (TCK 미인증, 호환, 커뮤니티기반)
    - Amazon Corretto (아마존)
    - Azul Zulu (Azul)
    - Red Hat OpenJDK

- OpenJDK 를 만든 회사들도 자사 OpenJDK 에 살 붙여서 따로 유료버전 팔아먹는다 (Enterprise)

- http://www.opennaru.com/java/openjdk-oracle-redhat-ibm-azul/
- https://openjdk.java.net/faq/
- https://okky.kr/article/490213
- https://blog.ex-em.com/1196

<hr>

- #### GC 의 기본 동작원리 (Generational GC)
    - GC 도 Thread 로 돈다. 
    - GC 돌시 모든 Thread 가 멈춘다. (Stop The World)
    - Heap 을 크게 두 공간으로 나누고 최신객체 저장공간(Young), 오래된 객체 저장공간(Old)으로 나눈다.
        - 최신공간의 갯수 > 오래된공간의 갯수
        - 최신공간의 사이즈 < 오래된 공간의 사이즈
    - 최신공간이 꽉찼을 경우에 GC가 최신공간만 돌면 되기에 멈추는 시간이 짧아진다.
        - Minor Garbage Collect
        - GC 는 최신공간 전부를 비워서 단편화 현상 제거
    - 객체들은 GC가 돌아서 살때마다 오래된 객체 공간으로 보내진다.
    - 오래된 공간도 꽉차면 GC가 한번 돌아줘야한다. 사이즈가 크기때문에 멈추는 시간이 길다.
        - Full Garbage Collect
        
- #### GC 의 종류와 동작 차이

- ##### Serial Garbage Collector
    - 32bit JVM 기본 GC
    - 단일 Thread 로 GC 가 돈다.
    - 위 기본 동작원리와 같다.

- ##### Throughput Collector
    - 64bit JVM 기본 GC
    - 멀티 Thread 로 GC 가 돈다.
    - 위 기본 동작원리와 같다.
    
- ##### Concurrent Mark & Sweep Collector (CMS Garbage Collector)
    - 멀티 Thread 로 GC 가 돈다.
    - 최신공간 정리는 위 기본동작원리와 같다.
    - GC 시 모든 Thread 를 멈추게 하는 대신 백그라운드 에서 GC 가 돌며 오래된 공간을 자주 비운다.
        - CPU 점유율 ++
    - 오래된 공간은 전부 비워지며 청소되는것이 아니기에 단편화 현상이 생긴다.
    - 단편화 현상이 심해질시 단편화 제거를 위해 오래된 공간을 Serial GC 방식으로 한번 돈다.
        - 모든 Thread 를 길게 멈추게 하는 현상이 발생
    
- ##### Garbage First Collector (G1 Garbage Collector)
    - 멀티 Thread 로 GC 가 돈다.
    - Heap 을 좀더 세분화 한 영역으로 나눈다.
    - 최신공간 정리는 위 기본동작원리와 같다. (+ 세분화 된 영역단위)
    - 오래된 공간 정리도 CMS GC 와 원리 가 같다. 그러나,
        - 세분화 된 영역단위로 이루어 지기에 통째로 영역을 날려버린다.
        - 살아남은 객체는 다른영역에 이쁘게 보낸다.
        - CMS GC 의 단편화 현상을 방지한다.
        
- #### Heap size 별 권장되는 GC
    - Small size Heap -> Serial GC
    - Mid size Heap -> Throughput GC, CMS GC 
    - Large size Heap (>= 4GB) -> G1 GC
    
- https://okky.kr/article/379036
- https://free-strings.blogspot.com/2016/02/jvm-garbage-collector.html
- https://d2.naver.com/helloworld/1329

<hr>

#### JIT Compiler (Interpreter)

- JVM 은 기본적으로 HotSpot JVM 이다.
    - 프로그램 내 전체 코드중 일부 코드만 자주 사용된다.
    - 그 일부 코드를 빠르게 실행한다면 전체 코드가 빠르게 실행된다.
    - JIT Compiler 는 그 일부코드(byte)를 기계어(Native)로 직접 컴파일후 Cache 화 한다.
    - JVM 은 Byte 코드를 interpreter 로 실행하다가 자주사용되는 Cache Native 코드를 사용한다.
    
- http://techknowdger.blogspot.com/2015/01/java-jit-compiler.html
- https://www.slipp.net/wiki/display/SLS/%231+Java+Compiler
- https://m.blog.naver.com/2feelus/220738480797 (좋음)