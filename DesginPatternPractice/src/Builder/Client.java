package Builder;

public class Client {

    public static void main(String[] args) {
        Student student = Student.getBuilder()
                .setId(1L)
                .setName("Rajat")
                .setPsp(89.0)
                .setAge(21)
                .build();

        System.out.println(student);
    }
}
