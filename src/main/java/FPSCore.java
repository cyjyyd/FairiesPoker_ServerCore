import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
public class FPSCore {

	public static void main(String[] args) throws SocketException {
		System.out.println("FPServer Program Started!");
		DBUtil db = new DBUtil();
		System.out.println("Connecting to sql Server database……");
		db.getConnection();
		udpreceive();
	}
	public static void udpreceive() throws SocketException
	{

        try
        {
        	DatagramSocket ds = new DatagramSocket(13588);
        System.out.println("UDP Connection Module Started");
        while(true) {
         byte [] bbuf = new byte [1024];
         DatagramPacket dp = new DatagramPacket(bbuf,bbuf.length);
         try {
			ds.receive(dp);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.print("Failed to receive data");
		}
         System.out.println(dp.getAddress().getHostAddress()+":"+dp.getPort()+"::"+new String(dp.getData()));
         String dat = new String(dp.getData());
         dataprocess(dat,ds);
        }
        }
        catch(Exception e){
        	System.out.println("UDP服务启动失败！清检查端口是否已经被占用！");
        }
	}
	public static void dataprocess(String data,DatagramSocket ds)
	{
		Map hdata = JSONObject.parseObject(data);
		if(hdata.get("Type").equals("Login"))
		{
			login(ds,hdata);
		}
		else if (hdata.get("Type").equals("Register"))
		{
			reg(ds,hdata);
		}
	}
	public static void login(DatagramSocket ds,Map dat)
	{
		System.out.println("Login Request received!");
		
		
	}
	public static void reg(DatagramSocket ds,Map dat)
	{
		System.out.println("Register Request Received");
		
	}
}
