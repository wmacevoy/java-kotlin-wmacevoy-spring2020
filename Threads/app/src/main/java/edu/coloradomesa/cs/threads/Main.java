package edu.coloradomesa.cs.threads;

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

    public String toString() {
        return "message(to: " + address + ", " + content + ")";
    }
}



class Messages extends Thread {
    Messages() {
        start();
    }
    LinkedList< Message > queue = new LinkedList<Message>();
    Object queueMutex = new Object();
    void addMessage(Message message) {
        Message copy = message.copy();
        synchronized (queueMutex) {
            queue.add(copy);
            queueMutex.notifyAll(); // other threads care about this change
        }
    }
    boolean done() {
        synchronized (queueMutex) {
            return queue.isEmpty();
        }
    }

    @Override
    public void run() {
        for (;;) {
            boolean empty = true;
            Message oldest = null;
            synchronized (queueMutex) {
                empty = queue.isEmpty();
                if (!empty) {
                    oldest = queue.removeFirst();
                } else {
                    try {
                        queueMutex.wait(); // wait for other threads to update state...
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (!empty) {
                System.out.println("sending " + oldest + "...");
                try {
                    Thread.sleep((int)(Math.random()*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                oldest.sent = true;
                System.out.println(oldest.toString() + "sent.");
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
    boolean done() {
        return messages.done();
    }
}
