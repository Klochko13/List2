import java.security.DrbgParameters;
import java.util.concurrent.ExecutionException;

public class List {
    private class Element {

        private int Data;
        private Element Next;
        private Element Prev;

        private static int count = 0;

        public int getData() {
            return Data;
        }

        public Element getNext() {
            return Next;
        }

        public Element getPrev() {
            return Prev;
        }

        public void setData(int data) {
            Data = data;
        }

        public void setNext(Element next) {
            Next = next;
        }

        public void setPrev(Element prev) {
            Prev = prev;
        }

        public Element(int Data) {
            this.Data = Data;
            count++;
            System.out.println("EConstructor:\t" + Integer.toHexString(hashCode()));
        }

        public Element(int Data, Element Next) {
            this.Data = Data;
            this.Next = Next;
            count++;
            System.out.println("EConstructor:\t" + Integer.toHexString(hashCode()));
        }

        protected void finalize() {
            //super.finalize();
            count--;
            System.out.println("EDestructor:\t" + Integer.toHexString(hashCode()));
        }
    }

    private Element Head, Tail;
    private int size;

    public Element getHead() {
        return Head;
    }

    public Element getTail() {
        return Tail;
    }

    public int getSize() {
        return size;
    }

    public List() {
        Head = Tail = null;
        size = 0;
        System.out.println("LConstructor:\t" + Integer.toHexString(hashCode()));
    }

    public void push_front(int Data) {
        if (Head == null && Tail == null) {
            Head = Tail = new Element(Data);
        } else {
            Head = Head.Prev = new Element(Data, Head);
        }
        size++;
    }

    public void push_back(int Data) {
        if (Head == null && Tail == null) {
            Head = Tail = new Element(Data);
        } else {
            Element New = new Element(Data);
            New.setPrev(Tail);
            Tail.setNext(New);
            Tail = New;
        }
        size++;
    }

    //Removing elements:
    public void pop_front() {
        if (Head == null && Tail == null) return;
        else if (Head == Tail) {
            Head.finalize();
            Head = Tail = null;

        } else {
            Head = Head.Next;
            Head.Prev.finalize();
            Head.Prev = null;
        }
        size--;
    }

    public void pop_back() {
        if (Head == null && Tail == null) return;
        if (Head == Tail) {
            Tail.finalize();
            Head = Tail = null;
        } else {
            Tail = Tail.Prev;
            Tail.Next.finalize();
            Tail.Next = null;
        }
        size--;
    }

    //Methods:

    public void insert(int data, int position) { ;
        Element temp = new Element(data);
        if (position == 1) {
            push_front(data);
        }
        else {
            Element current = Head;
            int currPosition = 1;
            while (current != null && currPosition < position) {
                current = current.Next;
                currPosition++;
            }
            if (current == null) {
                push_back(data);
            }
            else {
                temp.Next = current;
                temp.Prev = current.Prev;
                current.Prev.Next = temp;
                current.Prev = temp;
            }
        }
        size++;
    }

    public void erase(int pos) {
        if (Head == null) {
            return;
        }

        if (pos == 1) {
            pop_front();
            return;
        }

        Element current = Head;
        int count = 1;

        while (current != null && count != pos) {
            current = current.Next;
            count++;
        }

        if (current == null) {
            System.out.println("Position wrong");
            return;
        }

        if (current == Tail) {
            pop_back();
            return;
        }

        current.Prev.Next = current.Next;
        current.Next.Prev = current.Prev;
        current.Prev = null;
        current.Next = null;
        size--;
    }

    public void clear() {
        while (Head != null) pop_front();
    }

    public void print() {
        for (Element Temp = Head; Temp != null; Temp = Temp.Next)
            System.out.print(Temp.Data + "\t");
        System.out.println("\nКоличество элементов списка: " + size);
        System.out.println("Общее количество элементов: " + Element.count);
    }

    public void reverse_print() {
        for (Element Temp = Tail; Temp != null; Temp = Temp.Prev)
            System.out.print(Temp.Data + "\t");
        System.out.println("\nКоличество элементов списка: " + size);
        System.out.println("Общее количество элементов: " + Element.count);
    }
}