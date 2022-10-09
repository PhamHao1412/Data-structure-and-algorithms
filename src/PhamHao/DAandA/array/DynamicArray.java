package PhamHao.DAandA.array;

import java.util.Iterator;

public class DynamicArray <T>  implements Iterable<T> {
    //Đầu tiên ta sẽ tạo ra 1 cái type T static array , type T tại vì nó là generic mình muốn implement mọi thứ trong này điều là type cẩ
   private T[] arr;
    // Capacity ở đây là cái độ dài hiện tâị của cái arr
   private int capacity = 0;
   //Size ở đây là số lượng các element thực tế trong cái arr
   private int size =0;
   //Nếu như minh new 1 cái dynanmicArray mà ở dây mình không input cái capacity vào thì mình sẽ cho cái size ban đầu nó là 10 luôn
    public DynamicArray()
    {
        this(0);
    }
    //Tao sẽ tạo 1 cái constructor
   public DynamicArray(int capacity){
        //Trong đây ta sẽ check , nếu mà cái capacity nó âm thì sẽ throw cái exception!!
        if(capacity <0) throw new IllegalArgumentException("Capacity cannot be negative: "+capacity);
        //Nếu mà capacity lớn hơn không
        this.capacity=capacity;
        //Ở đây mnình sẽ cho cái arr  bangvới cái size là capacity và mình sẽ set nó bằng với cái arr ở trên
       //Tại vì ở đây mình đang dùng type T nên sẽ new ra 1 cái object
        arr = (T[]) new Object[capacity];
   }
   //Lấy cái size
   public int size(){
        return this.size;
   }
   //Check rỗng xem size có element trong đó hay không
   public boolean isEmpty(){
        return size()==0;
   }
    //Khi mà ta lấy 1 các element trong 1 cái arr có kiểu dữ liệu là T thì ở dây cũng phải ra lại element type T
   public T get(int index){
        return arr[index];
   }
   //Ở đây mình sẽ set index để có thể biết được vị trí của element
   public void set(int index , T element)
   {
       arr[index]= element;
   }

   public void clear(){
        //Ở đây mình sẽ loop tất cả cá element trong cái arr
        for (int i=0;i<size;i++)
       {
           //và set tất cả về null
           arr[i] = null;
       }
        //Sau khi tất cả các element bằng null thì miình cũng set cái size lại bằng 0 luôn
        size = 0;
   }
   public void add(T element){
        //TRƯỜNG HỢP CÁI SIZE BỊ ĐẦY
        //Nếu mà cái size nó gần bằng cái capacity thì ta sẽ tăng cái size của capacity lên
       // Cái size không bao giờ được lớn hơn cái capacity nó chỉ có thể lớn các (capacity -1 )hoặc bằng capacity thôi
        if(size >= capacity -1)
        {
            //Nếu mà capacity bằng 0 thì capaticy bằng 1 .Tại vì trong trường hợp này muốn add thêm 1 element mà capacity bằng 0
            // thì sẽ k có chỗ để add cho nên ta sẽ cho capacity bằng 1
            if(capacity == 0 ) capacity =1;
            //Còn nếu mà capacity nó lớn hơn 0 rồi thì mình sẽ nhân đôi capacity lên. Mình muốn x2 capacity lên vì mình nghĩ bao nhiêu đấy sẽ đủ xài rồi!!!
            else{
                capacity*=2;
            }
           //Tiếp theo là đến cái size
            // Bản chất của stactic array thì ta không thể thay đổi cái size được cho nên là ta phải tạo ra 1 cái array mới
            T[] newArr = (T[]) new Object[capacity];
            //Tiếp theo chúng ta sẽ loop toàn bộ các element ở trong cái arr cũ và ta sẽ copy toàn bộ element của nó vào arr mới
            for(int i=0;i<arr.length;i++){
                //Sau vòng loop này chúng ta đã copy toàn bộ những element ở trong arr cũ vào trong arr mới
                newArr[i] = arr[i];
            }
            //Sau đó thì ta sẽ set cái new arr lên trên cái arr cũ
            arr =newArr;
        }
        //Còn trường hợp mà nếu không cần mở rộng cái sỉze thì ta sẽ tăng cái size lên và set cái element bằng cái size -1

        arr[ size++]=element;
   }
   public void removeAt(int removeIndex)
   {
       // Đầu tiên ta sẽ check xem nếu như cái index nó lớn hoặc bằng cái size hoặc là nó bé hơn 0 thì nó sẽ throw cái  outbound ngoại lệ
       if(removeIndex >=size || size <=0) throw new IndexOutOfBoundsException();
       //Tiếp theo ta sẽ tạo ra 1 cái arr mới với cái size bằng cái size -1
       //Tại vì chúng ta xóa 1 cái element, thì ta sẽ tạo ra 1 cái arr mới đúng như bằng cái size mà trừ cho 1
       T[] newArr = (T[]) new Object[size-1];
       //Ở trong vòng for ta sẽ có 2 cái biến là 1 oldArrIndex và 1 cái newArrIndex  2 cái đều bằng 0
       /// và ta cho cái arr cũ trong khoảng bé hơn size và cái 2 cái arr đều tăng lên
       for(int oldArrIndex =0 ,newArrIndex=0;oldArrIndex<size;oldArrIndex++,newArrIndex++){
           //Nếu mà cái oldArrIndex bằng với cái index cần xóa thì cái oldArrIndex sẽ vẫn ++ lên còn newArrIndex sẽ -- đi có nghĩa là nó không
           // tăng nữa để dừng lại và cho thằng oldArrIndex nó tiếp lục loop
            if(oldArrIndex == removeIndex) newArrIndex--;
            //còn không bằng với cái thằng index mà ta cần xóa thì nó sẽ tiếp tục loop và copy từ thằng cũ vào thằng mới
                // Việc cho newArrIndex  dừng lại như vậy để ta có thể copy từ thằng cũ mà không bị xóa mất element
            else newArr[newArrIndex] = arr[oldArrIndex];
        }
       //Tiếp theo là ta sẽ set cái arr mới lên cái arr cũ
       arr = newArr;
       //Ở đây ta sẽ gọp cái capacity xuống bằng với cái newArr để có thể tiết kiệm cái memory
       capacity = --size;
   }
   //Ở đây là hàm xóa 1 cái obj
   public void remove(Object object)
   {
       // Ta sẽ tạo ra 1 cái biến removeIndex và gán nó  bằng với cái hàm IndexOf của obj mà t đã implement ở bên dưới rồi
       int removeIndex = IndexOf(object);
       //Và ta sẽ dùng hàm removeAt xóa cái thằng obj đi
       removeAt(removeIndex);
   }
   //Hàm này t sẽ lấy index
   public int IndexOf(Object object) {
        //Ta sẽ loop toàn bộ mảng
        for(int i=0;i<size;i++){
            //Nếu mà cái object bằng null thì ta sẽ check thêm 1 điều khiện nữa
            if(object == null){
                //Là nếu cái vị trí cua obj bằng null thì trẻ trả về i
                if(arr[i] == null) return  i;
            }
            //Còn nếu mà obj không bằng null
            else{
                //thì ta sẽ check thêm là là nếu mà obj nó bằng với vị trí i trong mảng thì ta sẽ return i
                if(object.equals(arr[i])){
                        return  i;
                }
            }
        }
       //Còn nếu mà nó loop k tìm thấy obj thì sẽ return -1
        return  -1;
    }
    //Hàm này có nghĩa là check xem thử có thăng obj nào nằm trong cái arr của chúng ta ko
    public boolean contain(Object object){
      //Mình đã implement cái thằng IndexOf ở trên rồi
      //Cho nên là nó sẽ lấy cái thằng IndexOf của cái obj này mà nó khác -1  thì nghĩa là nó contain!!
        return IndexOf(object) !=-1;
    }

    @Override
    public Iterator<T> iterator() {
        //Khi mà chúng ta new 1 iterator thì auto nó sẽ bắt buộc ta implement hasNext và next
        return new Iterator<T>() {
            int index =0;
            @Override
            //Hàm này nó sẽ check có element tiếp theo hay không
            public boolean hasNext() {
               //Ở đây t sẽ check xem nó có bé hơn cái size hay không , nếu mà nó bé hơn cái size thì nó sẽ có thăng element tiếp theo
                return index < size();
            }

            @Override
            //Còn hàm này thì nó sẽ chuyển tới 1 element kết tiếp
            public T next() {

                return arr[index++];
                //Nếu như mình gọi cái thằng next thì nó sẽ return ra thằng index hiện tại sau đó nó cộng cái index lên
            }
        };

    }
    //Đây là hàm in ra màn hình theo kiểu mảng
    public String toString() {
        //Đầu tiên ta sẽ check xem nếu mà nó rỗng thì sẽ trả ra []
        if(isEmpty()) return "[]";
        else{

            StringBuilder sb = new StringBuilder(size);
            //Đầu tiên t sẽ append cái [ vào
            sb.append("[");
            //Sau đấy ta sẽ loop tới size -1 , tại vì cái element t k có dấu phẩy mà nó sẽ là dấu ]
            for(int i = 0; i< size-1 ; i++){
                sb.append(arr[i]).append(",");
            }
            //Sau khi loop xong vòng for thì sẽ append cái size-1 là element cuối cùng và append tiếp 1 cái ]
            sb.append(arr[size-1]).append("]");
            //sau khi xong thì t sẽ return ra tosing nó sẽ build ra cho chúng ta 1 cái chuỗi
            return sb.toString();
        }
    }
}

