package kz.helloapp.test;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class ApiClient {

    public static enum RequestType {GET, POST}

    public static String doGet(String url) {
        return doRequest(url, RequestType.GET);
    }

    public static String doPost(String url) {
        return doRequest(url, RequestType.POST);
    }


    public static String doRequest(String url, RequestType requestType) {
        try {
            // Create a new HTTP Client
            DefaultHttpClient client = new DefaultHttpClient();
            // Setup the get request

            HttpRequestBase request;
            if (requestType == RequestType.GET) {
                request = new HttpGet(url);
            } else {
                request = new HttpPost(url);
            }

            // Execute the request in the client
            HttpResponse response = client.execute(request);
            // Grab the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent(), "UTF-8"));

            StringBuilder buf = new StringBuilder();
            while (true) {
                String line = reader.readLine();

                if (line == null) break;

                buf.append(line).append("\n");
            }

            String respText = buf.toString();


            // Instantiate a JSON object from the request response

            return respText;

        } catch (Exception e) {
            // In your production code handle any errors and catch the individual exceptions
            e.printStackTrace();
        }

        return null;
    }


    public static class RequestBuilder {

        private static final Map<String, String> params = new TreeMap<>();

        private final String url;
        private final String token;

        private RequestBuilder(String scheme, String host, int port, String contextPath, String token) {
            this.token = token;
            url = scheme + "://" + host + (port != 80 ? ":" + port : "") + "/" + contextPath;
        }

        private RequestBuilder(String url, String token) {
            this.url = url;
            this.token = token;
        }


        public RequestBuilder param(String name, String value) {
            params.put(name, value);
            return this;
        }

        public String build() {
            StringBuilder paramBuf = new StringBuilder();
            int i = 0;
            for (String key : params.keySet()) {
                paramBuf.append(key).append("=").append(params.get(key));
                if (++i < params.size()) {
                    paramBuf.append("&");
                }
            }
            String params = paramBuf.toString();

            StringBuilder urlBuf = new StringBuilder();
            urlBuf.append(url).append("?");
            urlBuf.append(params);

            if (token != null) {
                String hash = buildHash(params, token);
                urlBuf.append("&h=").append(hash);

            }

            return urlBuf.toString();
        }

        public static String encode(String text) {
            try {
                return URLEncoder.encode(text, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }

        private static String buildHash(String params, String token) {
            try {
                String data = params + "&token=" + token;
                return calcHash(data);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return null;
        }

        private static String calcHash(String data)
                throws NoSuchAlgorithmException, UnsupportedEncodingException {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = data.getBytes("UTF-8");
            byte[] digest = md.digest(bytes);
            return toHex(digest);
//            return Base64.encodeBase64String(digest);
        }

        public static String toHex(String arg) {
            return toHex(arg.getBytes());
        }

        public static String toHex(byte[] bytes) {
            return String.format("%040x", new BigInteger(bytes));
        }

        public static RequestBuilder create(String scheme, String host, int port, String contextPath, String token) {
            return new RequestBuilder(scheme, host, port, contextPath, token);
        }

        public static RequestBuilder create(String url, String token) {
            return new RequestBuilder(url, token);
        }

        public static RequestBuilder create(String url) {
            return new RequestBuilder(url, null);
        }
    }
}
