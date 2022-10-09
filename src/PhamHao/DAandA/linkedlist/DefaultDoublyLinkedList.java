package PhamHao.DAandA.linkedlist;

import java.util.Iterator;

public class DefaultDoublyLinkedList<T> implements DoublyLinkedList<T>{
    private int size;
    private Node<T> head = null;
    private Node<T> tail = null;

    @Override
    public void clear() {
        //Đầu tiên ta sẽ tạo ra 1 cái Node có tên là  currentNode là cái node sẽ cho đi từ đầu danh sách là bắt đầu từ head
        Node<T> currentNode = head;
        //Ở đây tôi sẽ dùng điều khiện while nếu mà cái currentNode này mà nó khác null thì ta sẽ tiến hành clear nó bằng cách sau
        while(currentNode!=null)
        {
            // Ta sẽ tạo ra 1 cái nextNode và sẽ set nó bằng với cái nextNode của cái currentNode( currentNode.getNext())
            Node<T> nextNode=currentNode.getNext();
             /* Nếu các bạn chưa hiểu thì 1 cái Node ở trong Doubly linked list(Danh sách liên kết kép)
            nó sẽ gồm 3 cái element cơ bản là data,prev(là con trỏ chỉ đến phần tử ở sau),next(là con trỏ chỉ đến phần tử ở trước)
            clear currentNode ở đây có nghĩa ta sẽ set tất cả element của 1 cái Node về bằng null hết thì đồng nghĩa là cái node đó sẽ rỗng
            nếu mà rỗng hết thì có nghĩa nó đã được clear*/
            currentNode.setPrev(null);
            currentNode.setNext(null);
            currentNode.setData(null);
            //Cuối cùng là ta sẽ set lại cái currentNode bằng cái nextNode để nó xét tiếp,cứ như thế nó lập cho đến khi các node trong list nó rỗng thì thôi
            currentNode = nextNode;
        }
        /* Sau khi chúng ta đã clear các node trong list rồi , thì ta sẽ set cái head và tail về lại vị trí ban đầu
        ở đây t sẽ cho bằng null.Nếu không thì nó vẫn sẽ có 1 cái node khác vào cái node tail chẳng qua là các element của node là null thôi */
        head=tail=null;
        size=0;

    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void add(T element) {
        //Ở đây chúng ta add first hay add last gì cũng được
        addLast(element);
    }

    @Override
    public void addFirst(T element) {
    //Đầu ta sẽ check xem cái list nó có rỗng hay không
        if(isEmpty())
    {
        //Nếu mà rỗng thì cái head và tail sẽ bằng 1 cái Node mói và cái element trong Node mới ta sẽ set bằng null hết
        head = tail = new Node<>(element,null ,null);
    }
        else
    {
            /*Còn nếu mà không rỗng thì ta sẽ chèn vào đầu bằng cách tạo ra 1 cái node mới
             ở Node mới các element nó ta sẽ cho thằng prev bằng null có nghĩa là sau khi thằng node mơi đã được chèn vào đầu
             thì cái prev của nó sẽ chỉ ra giá trị null vì lúc này đó đã là head rồi thì không có thằng nào trước nó nữa vì thế prev sẽ bàng null
             Còn cái next của node mới ta sẽ cho bằng thằng head hiện tại.Bời vì ta muốn chèn vào đầu nên là cái next của thằng Node nó mới phải
             trỏ đến thằng head lúc đây ta sẽ add được 1 node mới vào đầu danh sách */
        Node<T> newNode = new Node<T>(element,null ,head  );
        head.setPrev(newNode);
        //Lúc này ta sẽ set cái Prev cua head hiện tại bằng cái newNode mà ta muốn add vào
        head=head.getPrev();
        //Sau đó t sẽ getPrev() có nghĩa là lấy cái newNode mà ta đã setPrev ở trên bằng với cái head.Vậy là ta đã chèn vào đầu xong
    }
    size++;
}

    @Override
    public void addLast(T element) {
        // ở chèn cuối cũng tương tự các bạn chỉ cần làm ngược lại thôi.Là ta sẽ set cái thằng prev của Node mới cần chèn sau bằng tail và cái next của nó bằng null
        if(isEmpty())
        {
            head=tail= new Node<>(element,null,null);
        }
        else
        {
            Node<T> newNode = new Node<>(element,tail,null);
            tail.setNext(newNode);
            tail=tail.getNext();
        }
        size++;
    }


    @Override
    public T peekFirst() {
        if(isEmpty()) throw new RuntimeException("Empty Linked list!");
       /* throw new RuntimeException ở đây dùng cho trường hợp ngoại lệ xảy ra trong thời gian chạy chương trình
         nếu mà IsEmpty nó rỗng thì sẽ in ra là "Empty Linked list" */
        return head.getData();
        //Nếu mà không rỗng thì ta sẽ return cái data của cái head là cái cái
    }
    @Override
    public T peekLast() {
        if(isEmpty()) throw new RuntimeException("Empty linked list!");
        return tail.getData();
        // Trường hợp ở đây cũng tương tự nếu mà không rỗng thì ta sẽ return cái data của cái tail là cái
    }

    @Override
    public T removeFirst() {
        if(isEmpty()) throw new RuntimeException("Empty linked list!");
        //Đầu tiên t sẽ khai  báo data kiểu T vì kiểu trả về ở đây là T
        //Vì ta đang removefirst nên sẽ cho data bằng với head để lấy data của thăng head
        T data = head.getData();
        //Vì ta muốn xóa thằng head nên ta sẽ move cái thằng head lên vị trí tiếp theo
        head = head.getNext();
        //Đồng thời ta cho cái cái size nó giảm xuống bời vì ta đã gỡ đi 1 phần tử của nó
        size--;
        //Khi mà ta giảm cái size xuông 1 nấc ta sẽ check lại cái link list
        //Nếu mà nó rỗng thì auto thằng head =null bởi vì ta đang xóa đầu mà nên là ta sẽ cho cái tail cũng sẽ cho nó bằng null
        //Nói đỡn giản thì khi ta chúng ta xóa 1 cái element đi thì cái list sẽ empty nên là cái head và tail nó phải bằng null
        if(isEmpty()) tail=null;
        /* Nếu như nó không rỗng mà thằng head của ta nó còn tồn tại, là cai list của ta có nhiều hơn 1 element
         có nghĩa là bây giờ cúng ta có thêm 1 cái head mới, thì ta có thể truy cập vào cái prev của nó và sẽ set nó bằng null */
        else head.setPrev(null);
        // Cuối ta sẽ return trả lại giá trị mà ở trên ta đã khai báo
        return data;
    }

    @Override
    public T removeLast() {
        //Ở phần xóa cuối này cũng sẽ tương tư như xóa đầu ở trên , bằng cách làm người lại thôi
        //Ở xóa đầu ta set cai cái prev của nó là null thì ở xóa cuối ta sẽ set next của tail là null
        if (isEmpty()) throw new RuntimeException("Empty linked list!");
        T data = tail.getData();
        tail = tail.getPrev();
        size--;
        if (isEmpty()) head = null;
        else tail.setNext(null);
        return data;
    }
    @Override
    public T remove(Node<T> node) {
        //Thay vì loop từng cái dể check xem cái prev và next có null hay không ở đây chúng ta sẽ dùng cách sau sẽ tiết kiệm thời gian hơn

     /*Đầu tiên ta sẽ tạo 1 cái node mới và cho cái node mới đó bằng với cái con trỏ prev và so sánh
     nếu mà nó bằng null tức nó là head thì chúng ta sẽ dùng removFirst */
        if (node.getPrev() == null) removeFirst();
    /* Tương tự như ở trên , ta cũng sẽ xét trường hợp nếu mà node mới trỏ đến con trỏ next bằng null thì nó sẽ là tail
        và ta sẽ dùng RemoveLast */
        if (node.getNext() == null) removeLast();


        //Ở bước tiếp theo chúng ta sẽ tiếp haành xóa 1 cái node

        //Ở đây ta sẽ cho cái next của cái prev của cái node bằng với cái next của  cái node
        node.getPrev().setNext(node.getNext());
        //Ở đây ta sẽ cho cái prev của  cái next của cái node bằng cái cái prev của cái node
        node.getNext().setPrev(node.getPrev());

        //Ở bước này ta sẽ tạo ra 1 cái T là data để lưu cái dữ liệu của Node
        T data = node.getData();
        //Sau khi remove 1 element ta sẽ giảm cái size xuống 1 nấc;
        size--;
        //Trước khi chúng ta return data, thì ta sẽ clean cái memory băng cách set các các element của node về bằng null
        node.setPrev(null);
        node.setNext(null);
        node.setData(null);
        node = null;
        //Và cuối cùng ta sẽ return data
        return data;
    }


        @Override
    public boolean remove(Object object) {
        //Đầu tiên ta sẽ tạo 1 cái currentNode đi từ head
        Node<T> currentNode = head;
        //Ở cái method này chúng ta sẽ remove 1 cái object cho nên là t sẽ xét là cái object có null hay không
        if(object==null){
            //Ở đây t dùng vòng while để loop cho thằng current nó đi hết cá node trong list nếu mà bằng null thì sẽ out ra khỏi cái loop
            while(currentNode!=null){
                //Nếu mà cái data của cái currentNode nó bằng null
                if(currentNode.getData()==null){
                    //thì ở dưới đây t sẽ remove cái currentNode đó
                    remove(currentNode);
                    return true;
                }
                //Còn nếu mà cái currentNode nó k có bằng null thì nó sẽ đi tiếp và lập tiếp đến khi nào null thì thôi
                currentNode=currentNode.getNext();

            }
        }else{
            //còn nếu mà cái object không bằng null, thì ta vẫn sẽ cho currentNode nó loop từng node tiếp
            while(currentNode!=null){
                //Còn ở đây nếu mà cái data của currentNode nó bằng với cái object
                if(currentNode.getData() ==object){
                    //thì lúc này ta sẽ xóa cái thằng currentNode đó đi
                    remove(currentNode);
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public T removeAt(int index) {
           /*Đối với việc xử lý index thì  ta phải luôn chắc chắn rằng phải này trong cái khoảng giới
        hạn của size nếu mà vượt qua thì ta sẽ dùng throw new...*/
        if(index < 0 || index >= size) throw new IllegalArgumentException();
        //Đầu tiên ta sẽ tạo 1 cái i để check vị trí hiện tại của currentNode
        int i;
        Node<T> currentNode;
        if(index < size / 2){
            //i sẽ bắt đầu từ 0
            i = 0;
            //ta sẽ cho thằng currentNode đi từ đầu
            currentNode = head;
            //ta sẽ loop đến khi nào i bằng index thì out loop
            while (i != index) {
            //khi vào loop ta sẽ cho thằng currentNode bằng cái next
             // có nghĩa là ta sẽ next rồi next , next tới khi nào thằng curretNode bằng index thì ngưng
                currentNode = currentNode.getNext();
                // mỗi lần getnext thì i sẽ tăng lên 1 nấc;
                i++;
            }
        }else{
            // i=size-1 là điểm bắt đầu của vòng lập nếu chúng ta đi từ tail
            i = size - 1;
            //ở trường hợp ngược lại thì ta sẽ bắt đầu từ tail đi ngược vào
            currentNode = tail;
            //vẫn loop tương tự như ở trên

            while (i != index) {
                // ta sẽ cho thằng currennode đi trở về sau đến khi nào bằng index thì ngưngg
                currentNode = currentNode.getPrev();
                // lúc này nó đi lùi về thì sẽ giảm i
                i--;
            }
        }
        // nếu mà bằng índex thì sẽ remove đồng thời trả về data của currentNode luôn
        return remove(currentNode);
    }
    @Override
    public int indexOf(Object object) {

        // Đầu tiên ta sẽ tạo 1 cái index = 0;
        int index = 0;
        // và currentNode đi từ đầu danh sách
        Node<T> currentNode = head;
        //Nếu mà cái object nó bằng null
        if(object == null)
        {
            //Thì ta sẽ check xem thằng currenNode nó có bằng null hay không
            while(currentNode!=null)
            {
                //Nếu mà cái data của currentNode bằng null
                if(currentNode.getData()==null)
                {
                    //thì ra sẽ return cái vị trí
                    return index;
                }
                //Nếu getData của thằng currentNode mà nó không null thì lúc này đó sẽ đến cái thằng getNext và i nó sẽ cộng lên
                //Vào sau đó nó lập lại cái vòng lập ở trên
                currentNode = currentNode.getNext();
                index++;
            }
        }
        else
        {   //Tương tự như ở trên ở trường hợp else ta vẫn tiếp tục loop
            while(currentNode!=null)
            {
                //Nếu mà data của currentNode bằng với cái object
                if(currentNode.getData()==object)
                {
                    //thì nó sẽ return cái vị trí
                    return index;
                }
                //Nếu getData của thằng currentNode mà nó không null thì lúc này đó sẽ đến cái thằng getNext
                //Vào sau đó nó lập lại cái vòng lập ở trên
                currentNode=currentNode.getNext();
                index++;
            }
        }
        //Nếu mà nó chạy hết vòng lập mà vẫn k tìm được thì ta sẽ return -1
        return -1;

    }

    @Override
    public boolean contains(Object object) {
        //Hàm này gọi ra có đang chưa cái object mà ta cần tìm k
        return indexOf(object) != -1;
        //nếu có thì trar về true còn k thì trả về false
    }
    @Override
    public Iterator<T> iterator() {
        //Cho các bạn nào chưa biết thì Iterator là một trong các cách giúp bạn duyệt qua (traverse) các phần tử của một Collection
        //Cho phép duyệt từ đầu đến cuối của 1 collection và cho phép xóa phần tử khi lặp 1 collection
        return new Iterator<>() {
            //Ở trong cai iterator chỉ có thằng hasnext và next thôi nên ta chỉ quan tâm đến thằng head
            //Nên là ta sẽ tao ra 1 cái currentNode = head
            private Node<T> currentNode = head;

            @Override
            //Ở hasNext này t chỉ cần check xem cái thằng next của currentNode có tồn tại hay k
            public boolean hasNext() {
                //Nếu mà nó khác null thì nó sẽ là hasNext
                return currentNode.getNext() != null;
            }

            @Override
            //Cái mothod next này nó trả ra cái T là cái data của currentNode
            // thì chúng ta sẽ phải move cái currentNode qua cái nextNode
            public T next() {
                T data = currentNode.getData();
                ;
                currentNode = currentNode.getNext();
                return null;

            }
        };
    }

    @Override
    //function này dung để in các cái data trong chuỗi giống array
    public String toString() {
        //Check nếu rỗng thì trả ra chuỗi k
        if(isEmpty()) return "[]";
        else{
            StringBuilder sb = new StringBuilder(size);
            sb.append("[ ");
            //Tạo 1 cái currentNode bắt đầu bằng head
            Node<T> currentNode = head;
            //Sau đó ta sẽ dùng while để loop 1
            while (currentNode!=null) {
                //Nếu mà nó khác null thì ta sẽ append cái data của thằng currentNode,cứ như thế nó thêm vào
                sb.append(currentNode.getData());
                //Nếu mà cái next của currentNode khác null có nghĩa là nó có phần tử đằng sau thì ta sẽ append dấu "," vào
                if (currentNode.getNext() != null) sb.append(", ");
                currentNode = currentNode.getNext();
            }
            sb.append(" ]");
            return sb.toString();
        }
    }
}