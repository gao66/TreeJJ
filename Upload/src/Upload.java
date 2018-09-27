import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

public class Upload {
	
	public static void main(String[] args)throws Exception {
		String url="http://192.168.31.172/download/upload/abc.jpg";
		 Map<String,String> map = null;
		String filePath = "E:/timg.jpg";
		File file = new File(filePath);
		InputStream is = new FileInputStream(file);
		byte [] bts = new byte [10240000];
		int len = is.read(bts);
		byte [] body_data = Arrays.copyOf(bts, len);
		String charset = "UTF-8";
		
		doPostSubmitBody(url, map, filePath, body_data, charset);
	}
	
	public static String doPostSubmitBody(String url, Map<String, String> map,
	            String filePath, byte[] body_data, String charset) throws MalformedURLException {
	
		// TODO Auto-generated method stub
		URL urlObj = new URL(url);
	
		
		 // 设置三个常用字符串常量：换行、前缀、分界线（NEWLINE、PREFIX、BOUNDARY）；
        final String NEWLINE = "\r\n"; // 换行，或者说是回车
        final String PREFIX = "--"; // 固定的前缀
        final String BOUNDARY = "#"; // 分界线，就是上面提到的boundary，可以是任意字符串，建议写长一点，这里简单的写了一个#
        HttpURLConnection httpConn = null;
        BufferedInputStream bis = null;
        DataOutputStream dos = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
        // 调用URL对象的openConnection()方法，创建HttpURLConnection对象；
        httpConn = (HttpURLConnection) urlObj.openConnection();
        // 调用HttpURLConnection对象setDoOutput(true)、setDoInput(true)、setRequestMethod("POST")；
        httpConn.setDoInput(true);
        httpConn.setDoOutput(true);
        httpConn.setRequestMethod("POST");
        // 设置Http请求头信息；（Accept、Connection、Accept-Encoding、Cache-Control、Content-Type、User-Agent），不重要的就不解释了，直接参考抓包的结果设置即可
        httpConn.setUseCaches(false);
        httpConn.setRequestProperty("Connection", "Keep-Alive");
        httpConn.setRequestProperty("Accept", "*/*");
        httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        httpConn.setRequestProperty("Cache-Control", "no-cache");
        // 这个比较重要，按照上面分析的拼装出Content-Type头的内容
        httpConn.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + BOUNDARY);
        // 这个参数可以参考浏览器中抓出来的内容写，用chrome或者Fiddler抓吧看看就行
        httpConn.setRequestProperty(
                "User-Agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30)");
        // 调用HttpURLConnection对象的connect()方法，建立与服务器的真实连接；
        httpConn.connect();

        // 调用HttpURLConnection对象的getOutputStream()方法构建输出流对象；
        dos = new DataOutputStream(httpConn.getOutputStream());
        
       
        // 获取表单中上传控件之外的控件数据，写入到输出流对象（根据上面分析的抓包的内容格式拼凑字符串）；
        if (map != null && !map.isEmpty()) { // 这时请求中的普通参数，键值对类型的，相当于上面分析的请求中的username，可能有多个
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey(); // 键，相当于上面分析的请求中的username
                String value = map.get(key); // 值，相当于上面分析的请求中的sdafdsa
                dos.writeBytes(PREFIX + BOUNDARY + NEWLINE); // 像请求体中写分割线，就是前缀+分界线+换行
                dos.writeBytes("Content-Disposition: form-data; "
                        + "name=\"" + key + "\"" + NEWLINE); // 拼接参数名，格式就是Content-Disposition: form-data; name="key" 其中key就是当前循环的键值对的键，别忘了最后的换行 
                dos.writeBytes(NEWLINE); // 空行，一定不能少，键和值之间有一个固定的空行
                dos.writeBytes(URLEncoder.encode(value.toString(), charset)); // 将值写入
                // 或者写成：dos.write(value.toString().getBytes(charset));
                dos.writeBytes(NEWLINE); // 换行
            } // 所有循环完毕，就把所有的键值对都写入了
        }

        // 获取表单中上传附件的数据，写入到输出流对象（根据上面分析的抓包的内容格式拼凑字符串）；
        if (body_data != null && body_data.length > 0) {
            dos.writeBytes(PREFIX + BOUNDARY + NEWLINE);// 像请求体中写分割线，就是前缀+分界线+换行
            String fileName = filePath.substring(filePath
                    .lastIndexOf(File.separatorChar) + 1); // 通过文件路径截取出来文件的名称，也可以作文参数直接传过来
            // 格式是:Content-Disposition: form-data; name="请求参数名"; filename="文件名"
            // 我这里吧请求的参数名写成了uploadFile，是死的，实际应用要根据自己的情况修改
            // 不要忘了换行
            dos.writeBytes("Content-Disposition: form-data; " + "name=\""
                    + "uploadFile" + "\"" + "; filename=\"" + fileName
                    + "\"" + NEWLINE);
            // 换行，重要！！不要忘了
            dos.writeBytes(NEWLINE);
            dos.write(body_data); // 上传文件的内容
            dos.writeBytes(NEWLINE); // 最后换行
        }
        dos.writeBytes(PREFIX + BOUNDARY + PREFIX + NEWLINE); // 最后的分割线，与前面的有点不一样是前缀+分界线+前缀+换行，最后多了一个前缀
        dos.flush();

        // 调用HttpURLConnection对象的getInputStream()方法构建输入流对象；
        byte[] buffer = new byte[8 * 1024];
        int c = 0;
        // 调用HttpURLConnection对象的getResponseCode()获取客户端与服务器端的连接状态码。如果是200，则执行以下操作，否则返回null；
        if (httpConn.getResponseCode() == 200) {
            bis = new BufferedInputStream(httpConn.getInputStream());
            while ((c = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, c);
                baos.flush();
            }
        }
        // 将输入流转成字节数组，返回给客户端。
        return new String(baos.toByteArray(), charset);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (dos != null)
                dos.close();
            if (bis != null)
                bis.close();
            if (baos != null)
                baos.close();
            httpConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return null;
		
	}

}
