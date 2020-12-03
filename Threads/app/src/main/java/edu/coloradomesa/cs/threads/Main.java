package edu.coloradomesa.cs.threads;

import android.os.AsyncTask;

import java.util.LinkedList;

class Message {
    String address;
    String content;
    boolean sent;
    Message(String address, String content) {
        this.address =address;
        this.content =content;
        sent=false;
    }
    Message copy() {
        return new Message(this.address,this.content);
    }

    void send() {
        System.out.println("sending " + this + "...");
        try {
            Thread.sleep((int)(Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.sent = true;
        System.out.println(this.toString() + "sent.");
    }

    public String toString() {
        return "message(to: " + address + ", " + content + ")";
    }
}



class Messages extends Thread {
    Messages() {
        start();
    }
    private LinkedList< Message > queue = new LinkedList<Message>(); // shared resource
    private Object queueMutex = new Object(); // mutex object
    void addMessage(Message message) {
        Message copy = message.copy();
        synchronized (queueMutex) { // critical section
            queue.add(copy);
            queueMutex.notifyAll(); // other threads care about this change
        }
    }
    boolean done() {
        synchronized (queueMutex) {
            return queue.isEmpty();
            // does not change queue, so no "notifyAll" here.
        }
    }

    @Override
    public void run() {
        for (;;) {
            boolean empty = true;
            Message oldest = null;
            synchronized (queueMutex) { // critical section
                empty = queue.isEmpty();
                if (!empty) {
                    oldest = queue.removeFirst();
                    queueMutex.notifyAll();
                } else {
                    try {
                        queueMutex.wait(); // wait for other threads to update state...
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (!empty) {
                oldest.send();
            }
        }
    }

    public void waitUntilDone() {
        for (;;) {
            synchronized (queueMutex) { // critical section
                boolean empty = queue.isEmpty();
                if (empty) {
                    return;
                } else {
                    try {
                        queueMutex.wait(); // wait for other threads to update state...
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}

public class Main {
    Messages messages = new Messages();
    Message current = new Message("","");
    void setAddress(String address) { current.address = address; }
    void setContent(String content) { current.content = content; }
    void send() {
        messages.addMessage(current);
    }
    void send2() {
        Message copy = current.copy();
        AsyncTask.execute(()->{
            copy.send();
        });
    }
    boolean done() {
        return messages.done();
    }
    void waitUntilDone() { messages.waitUntilDone(); }
}
