package ua.com.alevel;

public class ExceptionMain {

    public static void main(String[] args) {
        DateTime parse = Parser.parse("28-12-734 17:45");
        System.out.println(parse);
        System.out.println(Parser.parse(parse));
    }
}
