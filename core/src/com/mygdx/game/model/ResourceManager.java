//package com.mygdx.game.Model.SaveData;
//
//import com.mygdx.game.AtomicBomber;
//import com.mygdx.game.AtomicBomber;
//import com.mygdx.game.model.User;
//
//import java.io.*;
//import java.util.ArrayList;
//
//public class ResourceManger {
//    public static void save(Serializable data , String path) throws Exception{
//        try {
//            FileOutputStream fileOut = new FileOutputStream(path);
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(data);
//            out.close();
//            fileOut.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
//    }
//
//    public static Object load(String path) throws Exception{
//        try {
//            FileInputStream fileIn = new FileInputStream(path);
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            Object data = in.readObject();
//            in.close();
//            fileIn.close();
//            return data;
//        } catch (IOException i) {
//            i.printStackTrace();
//            return null;
//        }
//    }
//
//    public static void loadAndSave(String path) {
//        ArrayList<User> users;
//        try {
//            users = (ArrayList<User>) ResourceManger.load(path);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return;
//        }
//
//        for (User user : users) {
//            if (user.getUsername().equals(AtomicBomber.user.getUsername())) {
//                users.remove(user);
//                break;
//            }
//        }
//
//        users.add(AtomicBomber.user);
//
//        try {
//            ResourceManger.save(users , path);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
