package Prototype;


public class Client {
    public static void main(String[] args) {


        Student protoApril2022 = new Student();
        protoApril2022.setBatchName("April2022");
        protoApril2022.setAverageBatchPsp(89.0);

        IntelligentStudent protoIntelligentApril2022 = new IntelligentStudent();
        protoIntelligentApril2022.setAverageBatchPsp(100.0);
        protoIntelligentApril2022.setBatchName("IntelligentApril2022");

        Student payal = protoApril2022.clone();
        payal.setId(1L);
        payal.setName("Payal");
        payal.setPsp(90.0);

        Student rajat = protoIntelligentApril2022.clone();
        rajat.setPsp(100.0);
        rajat.setName("Rajat");
        rajat.setId(2L);

        System.out.println("debug");
    }
}
