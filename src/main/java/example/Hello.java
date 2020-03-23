package example;

import com.qcloud.scf.runtime.Context;
import com.qcloud.services.scf.runtime.events.APIGatewayProxyRequestEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class Hello {
    public String mainHandler(APIGatewayProxyRequestEvent event) throws IOException {
        String res="";
        StringBuffer stringBuffer= new StringBuffer();
        URL url = new URL("https://www.baidu.com/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();//创建实例连接指定URL上的内容
        try {
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStream os = connection.getOutputStream();
            String data="";
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(data.toString().getBytes());
            InputStream is = connection.getInputStream();//获取内容的字节流
            InputStreamReader inputStreamReader=new InputStreamReader(is);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            if (connection.getResponseCode() == 200) {
                while ((res = bufferedReader.readLine()) != null) {
                    stringBuffer.append(res);
                }
            }
            os.close();
            is.close();//关闭流
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  stringBuffer.toString();//打印 event, 获取参数见上面的注释
    }
}