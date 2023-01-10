import java.io.*;
import java.util.*;
import java.net.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class GoogleQuery {
	public String searchKeyword, title, results;
	public String url, content;
	public static String citeUrl;
	public static KeywordList keywordList;
	public ArrayList<String> relative;

	public GoogleQuery(String searchKeyword) throws UnsupportedEncodingException {
		keywordList = new KeywordList();
		this.searchKeyword = searchKeyword;
		int num = 50;
		this.url = "http://www.google.com/search?q=" + searchKeyword + "&oe=utf8&num="+num;
	}

	private String fetchContent() throws IOException {

		String retVal = "";

		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bf = new BufferedReader(inReader);
		String line = null;

		while ((line = bf.readLine()) != null) {
			retVal = retVal + line;
		}
		return retVal;
	}

	public HashMap<String, String> query() throws IOException, MalformedURLException, FileNotFoundException {

		if (content == null) {
			content = fetchContent();
		}
		
		HashMap<String, String> retVal = new HashMap<String, String>();
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		
		//relative keyword
		Elements lis2 = doc.select("div");
		lis2 = lis2.select("span").select(".lRVwie");
		relative = new ArrayList<String>();
		for(Element rel: lis2) {
			relative.add(rel.text());
		}
		
		for (Element li : lis) {
			try {
				citeUrl = li.select("a").get(0).attr("href").substring(7);
				title = li.select("a").get(0).select(".vvjwJb").text();
				if (title.equals("")) {
					continue;
				}
				
				WebPage rootPage = new WebPage(citeUrl, title);
				WebTree tree = new WebTree(rootPage);
				
				ArrayList<Keyword> lst = new ArrayList<Keyword>();
				this.addKeyword(lst);
			    
				tree.setPostOrderScore(lst);
				System.out.println();
				tree.eularPrintTree();

				if(WebTree.result.nodeScore != 0) {
					keywordList.getList().add(WebTree.result);
				}

				System.out.println(citeUrl);
				System.out.println("-----------------------------");
			} catch (Exception e) {
//				System.out.println("Skip: " + e.getMessage());
				continue;
			}
		}
		
		keywordList.sort();
		Collections.reverse(keywordList.lst);
		keywordList.show();

		for (Result result : keywordList.lst) {
			retVal.put(result.name, result.url);
		}

		return retVal;
	}
	
	public void addKeyword(ArrayList<Keyword> lst) {
		lst.add(new Keyword("飲食", 10));
		lst.add(new Keyword("肥胖", 10));
	    lst.add(new Keyword("營養", 10));
	    lst.add(new Keyword("卡路里", 8));
	    lst.add(new Keyword("藥", 8));
	    lst.add(new Keyword("疾病", 6));
	    lst.add(new Keyword("運動", 6));
	    lst.add(new Keyword("三高", 4));
	    lst.add(new Keyword("免疫力", 4));
	    lst.add(new Keyword("病毒", 2));
	    lst.add(new Keyword("熱量", 8));
	    lst.add(new Keyword("疫苗", 6));
	    lst.add(new Keyword("早餐", 5));
	    lst.add(new Keyword("中餐", 5));
	    lst.add(new Keyword("晚餐", 5));
	    lst.add(new Keyword("睡覺", 4));
	    lst.add(new Keyword("打針", 2));
	    lst.add(new Keyword("衛教", 8));
	    lst.add(new Keyword("糖尿病", 2));
	    lst.add(new Keyword("感冒", 4));
	    lst.add(new Keyword("活動", 3));
	    lst.add(new Keyword("少糖", 2));
	    lst.add(new Keyword("少鹽", 2));
	    lst.add(new Keyword("少油脂", 2));
	    lst.add(new Keyword("均衡", 8));
	    lst.add(new Keyword("水果", 4));
	    lst.add(new Keyword("蔬菜", 4));
	    lst.add(new Keyword("天然", 2));
	    lst.add(new Keyword("維生素C", 1));
	    lst.add(new Keyword("睡眠", 1));
	    
	    lst.add(new Keyword("diet", 10));
	    lst.add(new Keyword("fat", 10));
	    lst.add(new Keyword("nutrition", 10));
	    lst.add(new Keyword("calories", 8));
	    lst.add(new Keyword("medicine", 8));
	    lst.add(new Keyword("disease", 6));
	    lst.add(new Keyword("sport", 6));
	    lst.add(new Keyword("immunity", 4));
	    lst.add(new Keyword("virus", 2));
	    lst.add(new Keyword("vaccine", 6));
	    lst.add(new Keyword("breakfast", 5));
	    lst.add(new Keyword("lunch", 5));
	    lst.add(new Keyword("dinner", 5));
	    lst.add(new Keyword("sleep", 4));
	    lst.add(new Keyword("inject", 2));
	    lst.add(new Keyword("health education", 8));
	    lst.add(new Keyword("diabetes", 2));
	    lst.add(new Keyword("cold", 4));
	    lst.add(new Keyword("activity", 3));
	    lst.add(new Keyword("less sugar", 2));
	    lst.add(new Keyword("less salt", 2));
	    lst.add(new Keyword("less fat", 2));
	    lst.add(new Keyword("balanced", 8));
	    lst.add(new Keyword("fruits", 4));
	    lst.add(new Keyword("vegetable", 4));
	    lst.add(new Keyword("natural", 2));
	    lst.add(new Keyword("vitamin C", 1));
	    
	    lst.add(new Keyword("ダイエット", 10));
	    lst.add(new Keyword("肥満", 10));
	    lst.add(new Keyword("栄養", 10));
	    lst.add(new Keyword("カロリー", 8));
	    lst.add(new Keyword("薬", 8));
	    lst.add(new Keyword("疾患", 6));
	    lst.add(new Keyword("スポーツ", 6));
	    lst.add(new Keyword("元気", 4));
	    lst.add(new Keyword("免疫", 4));
	    lst.add(new Keyword("ウイルス", 2));
	    lst.add(new Keyword("熱", 8));
	    lst.add(new Keyword("ワクチン", 6));
	    lst.add(new Keyword("朝ごはん", 5));
	    lst.add(new Keyword("ランチ", 5));
	    lst.add(new Keyword("晩ごはん", 5));
	    lst.add(new Keyword("寝る", 4));
	    lst.add(new Keyword("バランスの取れた", 2));
	    lst.add(new Keyword("フルーツ", 4));
	    lst.add(new Keyword("野菜", 4));
	    lst.add(new Keyword("ナチュラル", 2));
	}

}