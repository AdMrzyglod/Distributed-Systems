package org.tools;




public class MessageTools {

    public static String generateStringMessage(Message msg){
        return "type:" + msg.getType() + "|content:" + msg.getContent();
    }

    public static Message parseStringMessage(String text) {
        String[] parts = text.split("\\|",2);
        String type = parts[0].split(":",2)[1];
        String content = parts[1].split(":",2)[1];
        return new Message(type, content);
    }

}
