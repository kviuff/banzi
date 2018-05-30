package com.bootdo.socket;

import java.io.UnsupportedEncodingException;

public class ByteUtils {

    private static String hexStr = "0123456789ABCDEF";

    private final static char[] mChars = "0123456789ABCDEF".toCharArray();
    private final static String mHexStr = "0123456789ABCDEF";

    public static String BinaryToHexString(String binaryString) {
//        if (TextUtils.isEmpty(binaryString) || binaryString.length() % 4 != 0) {
//            return null;
//        }
        String hexString = "";
        int bit = 0;
        for (int i = 0; i < binaryString.length(); i += 4) {
            bit = 0;
            for (int j = 0; j < 4; j++) {
                String x = binaryString.substring(i + j, i + j + 1);
                bit += Integer.parseInt(x) << (4 - j - 1);
            }
            hexString += Integer.toHexString(bit);
        }
        return hexString.toString();
    }

    public static String hexString2binaryString(String hexString) {
//        if (TextUtils.isEmpty(hexString)) {
//            return null;
//        }
        String binaryString = "";
        for (int i = 0; i < hexString.length(); i++) {
            //截取hexStr的一位
            String hex = hexString.substring(i, i + 1);
            //通过toBinaryString将十六进制转为二进制
            String binary = Integer.toBinaryString(Integer.parseInt(hex, 16));
            //因为高位0会被舍弃，先补上4个0
            String tmp = "0000" + binary;
            //取最后4位，将多补的0去掉
            binaryString += tmp.substring(tmp.length() - 4);
        }
        return binaryString;
    }


    /**
     * 将二进制转换为十六进制字符输出
     *
     * @param bytes
     * @return
     */
    public static String BinaryToHexString(byte[] bytes) {

        String result = "";
        String hex = "";
        for (int i = 0; i < bytes.length; i++) {
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
            result += hex;
        }
        System.out.println(result);
        return result;
    }

    public static String byteToHexStr(byte[] src){
        if (src == null || src.length <= 0) {
            return null;
        }

        char[] res = new char[src.length * 2]; // 每个byte对应两个字符
        final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[src[i] >> 4 & 0x0f]; // 先存byte的高4位
            res[j++] = hexDigits[src[i] & 0x0f]; // 再存byte的低4位
        }

        return new String(res);
    }


    /**
     * 将十六进制转换为字节数组
     *
     * @param hexString
     * @return
     */
    public static byte[] HexStringToBinary(String hexString) {
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length() / 2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位
        byte low = 0;//字节低四位

        for (int i = 0; i < len; i++) {
            //右移四位得到高位
            high = (byte) ((hexStr.indexOf(hexString.charAt(2 * i))) << 4);
            low = (byte) hexStr.indexOf(hexString.charAt(2 * i + 1));
            bytes[i] = (byte) (high | low);//高地位做或运算
        }
        return bytes;
    }

    //高位在前，低位在后
    public static byte[] int2bytes(int num) {
        byte[] result = new byte[4];
        result[0] = (byte) ((num >>> 24) & 0xff);//说明一
        result[1] = (byte) ((num >>> 16) & 0xff);
        result[2] = (byte) ((num >>> 8) & 0xff);
        result[3] = (byte) ((num >>> 0) & 0xff);
        return result;
    }

    //高位在前，低位在后
    public static int bytes2int(byte[] bytes) {
        int result = 0;
        if(bytes.length == 4){
            int a = (bytes[0] & 0xff) << 24;//说明二
            int b = (bytes[1] & 0xff) << 16;
            int c = (bytes[2] & 0xff) << 8;
            int d = (bytes[3] & 0xff);
            result = a | b | c | d;
        }
        return result;
    }

    public static String bytesToAscii(byte[] bytes, int offset, int dateLen) {
        if ((bytes == null) || (bytes.length == 0) || (offset < 0) || (dateLen <= 0)) {
            return null;
        }
        if ((offset >= bytes.length) || (bytes.length - offset < dateLen)) {
            return null;
        }

        String asciiStr = null;
        byte[] data = new byte[dateLen];
        System.arraycopy(bytes, offset, data, 0, dateLen);
        try {
            asciiStr = new String(data, "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
        }
        return asciiStr;
    }

    public static String bytesToAscii(byte[] bytes, int dateLen) {
        return bytesToAscii(bytes, 0, dateLen);
    }

    public static String bytesToAscii(byte[] bytes) {
        return bytesToAscii(bytes, 0, bytes.length);
    }

    public static String ConvertFromUtf32(int utf32)
    {
        if (utf32 < 0 || utf32 > 1114111 || (utf32 >= 55296 && utf32 <= 57343))
        {
            return null;
        }
        if (utf32 < 65536)
        {
            return String.valueOf((char)utf32);
        }
        utf32 -= 65536;
        return new String(new char[]
                {
                        (char)(utf32 / 1024 + 55296),
                        (char)(utf32 % 1024 + 56320)
                });
    }

    /**
     * 字符串转换成十六进制字符串
     * @param str String 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str){
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();

        for (int i = 0; i < bs.length; i++){
            sb.append(mChars[(bs[i] & 0xFF) >> 4]);
            sb.append(mChars[bs[i] & 0x0F]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    /**
     * int转byte 小端
     * @param a
     * @return
     */
    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
    //转16位数字
    /*public static short getUint16(String value, int fromBase)
    {
        if (fromBase != 2 && fromBase != 8 && fromBase != 10 && fromBase != 16)
        {
            return 0;
        }
        int num = ParseNumbers.StringToInt(value, fromBase, 4608);
        if (num < 0 || num > 65535)
        {
            return 0;
        }
        return (short)num;
    }*/
    //校验
    //校验
    public static String checksum(String str)
    {
        String[] data = str.split(" ");
        int total = 0;
        for(int i=0;i<data.length;i++){
            total += Integer.parseInt(data[i], 16);
        }
        /**
         * 用256求余最大是255，即16进制的FF
         */
        int mod = total % 256;
        System.out.println("total="+total + "  取反："+~total + " integer:"+Integer.toHexString(~total) + " 自定义:"+BinaryToHexString(intToByteArray(~total)));
        System.out.println("mod="+mod + "  取反："+~mod + " integer:"+Integer.toHexString(~mod) + " 自定义:"+BinaryToHexString(intToByteArray(~mod)));
        return BinaryToHexString(intToByteArray(~mod));
    }
    //转32位
    public static long getUint32(long l){ return l & 0x00000000ffffffff; }

    /**
     * 注释：字节数组到short的转换！
     *
     * @param b
     * @return
     */
    public static short byteToShort(byte[] b) {
        short s = 0;
        short s0 = (short) (b[0] & 0xff);// 最低位
        short s1 = (short) (b[1] & 0xff);
        s1 <<= 8;
        s = (short) (s0 | s1);
        return s;
    }

    /**
     * 小端 byte转int
     * @param res
     * @return
     */
    public static int byte2Uint16(byte[] res) {
        // 一个byte数据左移24位变成0x 000000，再右移8位变成0x00 0000
        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00);
        return targets;
    }
}
