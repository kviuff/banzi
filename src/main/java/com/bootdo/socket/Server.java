package com.bootdo.socket;

import com.bootdo.common.utils.CRC16;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 测试socket
 */
public class Server {

	
	public static void main(String[] args) {
        int type = 1;

        byte[] t = new byte[14];
        byte[] checkCrc = new byte[12];
        byte[] resultByte = ByteUtils.toBytes("5CCF7FB2370A");
        checkCrc[0] = resultByte[0];
        checkCrc[1] = resultByte[1];
        checkCrc[2] = resultByte[2];
        checkCrc[3] = resultByte[3];
        checkCrc[4] = resultByte[4];
        checkCrc[5] = resultByte[5];
        checkCrc[6] = 0x0E;
        checkCrc[7] = 0x00;
        checkCrc[8] = 0x03;
        if(type == 1){
            checkCrc[9] = 0x01;
        }else if(type == 2){
            checkCrc[9] = 0x02;
        }else if(type == 3){
            checkCrc[9] = 0x03;
        }else if(type == 4){
            checkCrc[9] = 0x04;
        }else if(type == 5){
            checkCrc[9] = 0x05;
        }else if(type == 6){
            checkCrc[9] = 0x06;
        }
        checkCrc[10] = (byte)0xff;
        checkCrc[11] = (byte)0xff;
        String crcStr = CRC16.crc16(checkCrc);
        System.out.println(crcStr);
        System.arraycopy(checkCrc, 0, t, 0, checkCrc.length);
        byte[] crc = ByteUtils.toBytes(crcStr);
        t[12] = crc[0];
        t[13] = crc[1];
        System.out.println(ByteUtils.byteToHexStr(t));
        /*try {
			runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
	
	private static void runServer() throws IOException {  
        ServerSocket ss=new ServerSocket(8000);
        Socket s=ss.accept();
        System.out.println(s.getLocalSocketAddress().toString());  
        InputStream in=s.getInputStream();
        
        
        OutputStream out =s.getOutputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));  
        
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());;
        PrintWriter bufferedWriter=new PrintWriter(out,true);  
        byte[] sendOpen = {
				(byte)0xfd, 0x2c, (byte)0x88, 0x3a, 0x5a, (byte)0xdd, 0x17, (byte)0x80, (byte)0xaa, 0x25,
				0x5c, 0x70, 0x13, 0x71, 0x68, 0x33, 0x0d, 0x0a
		};

		byte[] sendClose = {
				0x5a, 0x47, 0x76, 0x5b, 0x06, 0x57, 0x6d, 0x2f, (byte)0xb9, (byte)0xdc,
				(byte)0xf3, (byte)0x90, 0x51, (byte)0x99, (byte)0xce, 0x7b, 0x0d, 0x0a
		};
		String sendCloseStr = "5a47765b06576d2fb9dcf3905199ce7b0d0a";
        int i = 0;
        while (true) {
        	i++;
            String line=null;

            //bufferedWriter.println(byte2);//��ͻ����������
            //bufferedWriter.println(byte3);//��ͻ����������
            //bufferedWriter.println(byte4);//��ͻ����������
            line=bufferedReader.readLine();//��ȡ�ͻ��˴���������


            dos.flush();

        }
    }  
}
