package sample;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

public class ProxyChecker {

	public static final String HOST = "35.200.88.224";
	public static final int PORT = 8080;
	public static final String ID = "user1";
	public static final String PW = "hogehoge";
	public static final String URL = "https://www.yahoo.co.jp/";

	public static void main(String[] args) {

		HttpHost proxy = new HttpHost(HOST, PORT);
		RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(HOST, PORT), new UsernamePasswordCredentials(ID, PW));
		HttpClient httpClient = HttpClientBuilder.create()
				.setDefaultRequestConfig(requestConfig)
				.setDefaultCredentialsProvider(credsProvider)
				.build();

		try {
			HttpGet get = new HttpGet(URL);
			get.setHeader("Content-Type", "text/html; charset=Shift_JIS");
			final HttpResponse res = httpClient.execute(get);
			System.out.println(res.getStatusLine().getStatusCode());

			//HttpEntity httpEntity = res.getEntity();
			//System.out.println(EntityUtils.toString(httpEntity));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
