package com.getsong.mockito.annotation;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 8/10/2019 9:14 PM
 */
public class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else {
      int carry = 0;
      ListNode pre = new ListNode(0);
      ListNode cur = pre;
      do {
        int sum = carry + (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
        carry = sum / 10;
        cur.next = new ListNode(sum % 10);
        cur = cur.next;
        if (l1 != null) {
          l1 = l1.next;
        }
        if (l2 != null) {
          l2 = l2.next;
        }
      } while (l1 != null || l2 != null || carry > 0);
      return pre.next;
    }
  }
}

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}
