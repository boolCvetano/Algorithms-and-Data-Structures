// Задача 3. Преуреди листа
 //Дадена е еднострано поврзана листа L0 → L1 → 
//→Ln−1 → Ln. Преуредете
// ги jазлите во листата така што новата листа ´ке биде : L0 → Ln → L1 → Ln−1 →
 //L2 →Ln−2
// Влез: Во првата линиjа е даден броjот на елементи n. Во втората линиjа се
 //даваат броевите во листата одделени со празно место.
//Излез: На излез треба да се испечати преуредената листа
// Пример.
 //Влез:
// 5
// 1 2 3 4 5
// Излез:
// 1->5->2->4->3

import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<Integer> list = new SLL<Integer>();
        for(int i = 0; i < n; i++){
            list.insertLast(sc.nextInt());
        }
        transform(list);
        System.out.println(list);
    }
    static void transform(SLL<Integer> list){
        SLLNode<Integer> tmp = list.getFirst();
        while(tmp!=null && tmp.succ!=null && tmp.succ.succ!=null){
            SLLNode<Integer> tmp2 = tmp;
            while(tmp2.succ.succ!=null){
                tmp2 = tmp2.succ;
            }
            SLLNode<Integer> ins = new SLLNode<Integer>(tmp2.succ.element,tmp.succ);
            tmp2.succ = null;
            tmp.succ = ins;
            tmp = tmp.succ.succ;
        }
    }
}
 class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Kreiranje na prazna lista
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
        while (tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp.element;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "->" + tmp.element;
            }
        } else
            ret = "Prazna lista!!!";
//            EN version
//            ret = "Empty list!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadeniot jazol e null");
//            EN version
//            System.out.println("Given node is null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);
            } else {
                System.out.println("Elementot ne postoi vo listata");
//                EN version
//                System.out.println("Element does not exist in the list");
            }
        } else {
            System.out.println("Listata e prazna");
//            EN version
//            System.out.println("The list is empty");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
//            EN version
//            System.out.println("The list is empty");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
//                EN version
//                System.out.println("Element does not exist in the list");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
//            EN version
//            System.out.println("The list is empty");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
//                EN version
//                System.out.println("Element does not exist in the list");
            }
        } else {
            System.out.println("Listata e prazna");
//            EN version
//            System.out.println("The list is empty");
        }
        return null;
    }

    public void merge(SLL<E> in) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        } else {
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while (tmp != null) {
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}
 class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}
