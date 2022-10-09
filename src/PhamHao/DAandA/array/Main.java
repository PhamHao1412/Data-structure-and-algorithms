package PhamHao.DAandA.array;

public class Main {
    public static void main(String[] args) {
        DynamicArray<String> dynamicArray = new DynamicArray<>();

        dynamicArray.add("H");
        dynamicArray.add("e");
        dynamicArray.add("l");
        dynamicArray.add("l");
        dynamicArray.add("o");
        dynamicArray.add("W");
        dynamicArray.add("o");
        dynamicArray.add("r");
        dynamicArray.add("l");
        dynamicArray.add("d");
       System.out.println(dynamicArray.toString());
        System.out.println(dynamicArray.get(2));
     System.out.println(dynamicArray.IndexOf("o"));
        dynamicArray.removeAt(4);
        System.out.println(dynamicArray.toString());






    }
}
