/** https://www.hackerrank.com/challenges/insert-a-node-into-a-sorted-doubly-linked-list */
static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        
        if (head == null) {
            return newNode;
        }
        if (data < head.data) {
            newNode.next = head;
            head.prev = newNode;
            return newNode;
        }

        DoublyLinkedListNode potentialPrev = head;
        while (!isInTheRightPlace(potentialPrev, data)) {
            potentialPrev = potentialPrev.next;
        }

        newNode.next = potentialPrev.next;
        potentialPrev.next = newNode;
        newNode.prev = potentialPrev;
        
        return head;
    }

    private static boolean isInTheRightPlace(DoublyLinkedListNode current, int data) {
        return current.data <= data && 
            (current.next == null || current.next.data >= data);
    }
