package com.tizz.xiaomeng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13371 on 2017/8/3.
 */

public class GlobalParams {

    public static int WIN_WIDTH;
    public static int WIN_HEIGHT;
    public static Map<String,User> user=new HashMap<String, User>();
    public static List<User> UserInfos=new ArrayList<User>();
    public static List<Meng> MengInfos=new ArrayList<Meng>();
    public static Map<String,Meng> Meng=new HashMap<String,Meng>();
    public static List<Quan> QuanInfos=new ArrayList<Quan>();
    public static Map<String,Quan> Quan=new HashMap<String,Quan>();
    public static boolean ishasPublicMsg=false;
}
