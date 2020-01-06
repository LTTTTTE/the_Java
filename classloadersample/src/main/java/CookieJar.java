import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CookieJar {
    public static final int MAX_COOKIE_SIZE = 100;

    private Stack<Cookie> cookies;

    public CookieJar() {
        this.cookies = new Stack<>();
    }

    public boolean addCookie() {
        if (isNotFull()) {
            System.out.println("쿠키를 한개 넣었당");
            return this.cookies.add(new Cookie());
        }
        System.out.println("꽉차서 못넣었당");
        return false;
    }

    public boolean addCookies(int size) {
        return IntStream.range(0, size).allMatch(x -> addCookie());
    }

    public void addFullCookies() {
        Stream.iterate(0, i -> i + 1)
                .map(x -> addCookie())
                .filter(x -> !x)
                .limit(1)
                .forEach(x -> System.out.println("쿠키통을 꽉체움"));
    }

    public boolean eatCookie() {
        if (isEmpty()) {
            System.out.println("없어서 못먹었당");
            return false;
        }
        System.out.println("쿠키를 한개 먹었당");
        return this.cookies.pop() != null;
    }

    public boolean eatCookies(int size) {
        return IntStream.range(0, size).allMatch(x -> eatCookie());
    }

    public void eatAllCookies() {
        Stream.iterate(0, i -> i + 1)
                .map(x -> eatCookie())
                .filter(x -> !x)
                .limit(1)
                .forEach(x -> System.out.println("쿠키를 다먹음"));
    }

    public int cookieSize() {
        return this.cookies.size();
    }

    public boolean isEmpty() {
        return cookies.isEmpty();
    }

    public boolean isNotFull() {
        return cookieSize() < MAX_COOKIE_SIZE;
    }
}
