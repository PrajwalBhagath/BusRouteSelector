package HashSetWithChaining;

public class HashSetChain {
    public static void main(String[] args) {
        HashSetWithChaining<String> hs  = new HashSetWithChaining();
        System.out.println("initial Capacity of: "+hs.size);
        System.out.println("Adding 21 Savage, Kanye, Chance the rapper and travis scott");
        hs.add("21 Savage");
        hs.add("Kanye");
        hs.add("Chance The Rapper");
        hs.add("Travis Scott");
        hs.toString();
        System.out.println("removing 21 Savage");
        hs.remove("21 Savage");
        hs.toString();

        System.out.println("Adding drake and Eminem");
        hs.add("Drake");
        hs.add("Eminem");

        System.out.println(hs.toString());
        hs.add("J Cole");
        hs.add("Kendrick");
        hs.add("Kendrick");
        hs.add("Lil Wayne");
        hs.add("Post Malone");

        System.out.println(hs.toString());

        hs.remove("Kendrick");
        System.out.println(hs.toString());

        System.out.println("Does the set contain J Cole? \n"+hs.contains("J Cole"));

        hs.clear();
        System.out.println(hs.size);
    }



}
