package PhamHao.DAandA.linkedlist;

public class Main {
    public static void main(String[] args) {
       DoublyLinkedList doublyLL = new DefaultDoublyLinkedList();

        doublyLL.add("WILL YOU PROVE WORTHY?");
        doublyLL.add("PROBALY NOTTTTT!!!!");
        doublyLL.add("PROBALY YES!!!!");
        System.out.println(doublyLL.toString());
        doublyLL.addFirst("FIRST");
        doublyLL.addLast("LAST");
        System.out.println(doublyLL.toString());
        System.out.println(doublyLL.contains("PROBALY YES!!!!"));
        System.out.println(doublyLL.indexOf("PROBALY NOTTTTT!!!!"));
        doublyLL.removeAt(3);
        System.out.println(doublyLL.toString());
    }
}