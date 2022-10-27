package com.sulongx.algorithm.primary.linked;

import sun.awt.geom.AreaOp;

/**
 * @author sulongx
 * @title 链表基本操作练习
 * @details
 * @date 2022/10/19
 */
public class SingleLinkedDemoTest {


    /**
     * 链表反转
     * @param node
     * @return
     */
    public SingleNode inverseHead(SingleNode node){
        //新建一个头节点
        SingleNode head = new SingleNode(9999, null);
        head.next = node;

        //从目标节点的第二个元素开始头插法建立链表
        SingleNode cur = node.next;
        node.next = null;
        SingleNode next = null;

        while (cur != null){
            next = cur.next;
            cur.next = head.next;
            head.next = cur;

            cur = next;
        }

        return head.next;
    }



    /**
     * 链表中环检测
     * @param header
     */
    public void loopCheck(SingleNode header){

    }


    /**
     * 有序链表合并
     * @param header1
     * @param header2
     * @return
     */
    public SingleNode merge(SingleNode header1, SingleNode header2){
        return null;
    }


    /**
     * 删除链表倒数第N个节点
     * @param header
     * @param index
     * @return
     */
    public boolean deleteNodeTail(SingleNode header, int index){
        return false;
    }

    /**
     * 求链表的中间节点
     * @param header
     * @return
     */
    public SingleNode middleNode(SingleNode header){
        return null;
    }

    private final class SingleNode{

        private int data;

        private SingleNode next;

        public SingleNode(int data, SingleNode next) {
            this.data = data;
            this.next = next;
        }


        public boolean addNext(SingleNode node){
            this.next = node;
            return true;
        }



        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public SingleNode getNext() {
            return next;
        }

        public void setNext(SingleNode next) {
            this.next = next;
        }
    }
}
