package chenyuan.leetcode.s2;

/**
 * Created by chenyuan on 2017/11/17.
 *
 * Add two numbers
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val;}
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean dec = false;
        ListNode sumList = null;
        ListNode p = null;
        while (l1 != null || l2 != null) {
            int sum = (l1 != null ? l1.val : 0) +
                    (l2 != null ? l2.val : 0);
            if (dec) sum += 1;
            dec = sum - 10 >= 0;
            ListNode newNode = new ListNode(dec ? sum - 10 : sum);
            if (sumList == null) {
                sumList = newNode;
                p = sumList;
            } else {
                p.next = newNode;
                p = p.next;
            }
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        if (dec)
            p.next = new ListNode(1);
        return sumList;
    }

    public ListNode solve(Object[] args) {
        return addTwoNumbers((ListNode)args[0], (ListNode)args[1]);
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
//        l1.next = new ListNode(8);
//        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);

        AddTwoNumbers solution = new AddTwoNumbers();
        ListNode sumList = addTwoNumbers(l1, l2);
        while (sumList != null) {
            System.out.println(sumList.val);
            sumList = sumList.next;
        }
    }

}
