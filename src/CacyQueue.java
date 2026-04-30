public class CacyQueue<T> {
    private class Record{
        T t;
        Record nextRecord;
        Record(T t){
            this.t = t;
        }
    }

    private Record head;
    private Record tail;

    public void queue (T t){
        //Adds the object to the queue, does nothing if the object is null
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

    public T dequeue(){
        //Removes and returns the next object in the queue, will be null if the queue is empty
        if (head == null) return null;
        T output = head.t;
        head = head.nextRecord;
        if (head == null) tail = null;
        return output;
    }

    public T peek(){
        //Returns the next object in the queue, will be null if there is no next object
        if (head == null) return null;
        return head.t;
    }

    public int length(){
        //Returns the length of the queue
        return length(head);
    }
    private int length(Record record){
        if (record == null) return 0; //We aren't a record and thus are zero
        return length(record.nextRecord) + 1;//We are a record, add ourselves to the count
    }
}
