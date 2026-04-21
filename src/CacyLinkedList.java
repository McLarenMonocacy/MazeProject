public class CacyLinkedList<T>{
    private class Record{
        T t;
        Record nextRecord;

        Record(T t){
            this.t = t;
        }
    }

    private Record head;
    private Record tail;
    private Record iterator;

    private boolean removeSuccessFlag;

    public void add (T t){
        if (t == null) return; //Don't allow null objects to be added
        Record newRecord = new Record(t);
        if (head == null){
            head = newRecord;
        }
        else {
            tail.nextRecord = newRecord;
        }
        tail = newRecord;
    }

    public void addFirst(T t){
        if (t == null) return;
        //Adds the object at the beginning of the list
        Record newRecord = new Record(t);
        newRecord.nextRecord = head;
        head = newRecord;
    }

    public T remove(T t) {
        //Returns T if T is removed from the list, otherwise returns null
        if (t == null) return null; //Cannot remove null
        removeSuccessFlag = false;
        head = remove(head,t);
        if (head == null) tail = null;
        if (removeSuccessFlag) return t;
        else return null;
    }
    private Record remove(Record recordToCheck, T t){
        if (recordToCheck.t.equals(t)){
            removeSuccessFlag = true;
            return recordToCheck.nextRecord; //We are the record to remove and thus the previous record's next record should be our next record
        }
        if (recordToCheck.nextRecord != null){ //If the next record is null then were are at the end of the list and have nothing left to check
            Record result = remove(recordToCheck.nextRecord, t); //Check the next record
            if (result == null) tail = recordToCheck; //We get null if the next record is both the record to remove and the tail thus we are the new tail
            recordToCheck.nextRecord = result;
        }
        return recordToCheck; //We aren't the record to remove and thus should stay as the previous record's next record
    }

    public T removeFirst(){
        //Removes the first object in the list
        if (head == null) return null;
        T output = head.t;
        head = head.nextRecord;
        if (head == null) tail = null;
        return output;
    }

    public T removeLast(){
        //Removes the last object in the list
        if (tail == null) return null;
        T output = tail.t;
        if (removeLast(head,tail)){//If true then the last record is also the first
            head = null;
            tail = null;
        }
        return output;
    }
    private boolean removeLast(Record recordToCheck, Record recordToRemove){
        if (recordToCheck == recordToRemove) {
            return true;
        }
        if (removeLast(recordToCheck.nextRecord, recordToRemove)){
            recordToCheck.nextRecord = null;
            tail = recordToCheck;
        }
        return false;
    }

    public T first(){
        //Returns the first object in the list
        if (head == null) return null;
        return head.t;
    }

    public T last(){
        //Returns the last object in the list
        if (tail == null) return null;
        return tail.t;
    }

    public boolean contains(T t){
        //returns true if the object is in the list
        return contains(head, t);
    }
    private boolean contains(Record record, T t){
        if (record == null) return false; //We are null and thus don't contain
        if (record.t.equals(t)) return true; //We are equal and thus contain
        return contains(record.nextRecord, t); //We aren't equal, check next record
    }

    public int length(){
        //Returns the length of the list
        return length(head);
    }
    private int length(Record record){
        if (record == null) return 0; //We aren't a record and thus are zero
        return length(record.nextRecord) + 1;//We are a record, add ourselves to the count
    }

    public void initIterator(){
        //Sets the iterator to the start of the list
        iterator = head;
    }

    public T next(){
        //Returns the next item in the list, null if at the end
        if (iterator == null) return null;
        T output = iterator.t;
        iterator = iterator.nextRecord;
        return output;
    }

    public boolean hasNext(){
        //returns true if the iterator has more elements (i.e. next() != null)
        return iterator != null;
    }
}
