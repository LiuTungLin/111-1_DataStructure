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
		lst.add(new Keyword("??????", 10));
		lst.add(new Keyword("??????", 10));
	    lst.add(new Keyword("??????", 10));
	    lst.add(new Keyword("?????????", 8));
	    lst.add(new Keyword("???", 8));
	    lst.add(new Keyword("??????", 6));
	    lst.add(new Keyword("??????", 6));
	    lst.add(new Keyword("??????", 4));
	    lst.add(new Keyword("?????????", 4));
	    lst.add(new Keyword("??????", 2));
	    lst.add(new Keyword("??????", 8));
	    lst.add(new Keyword("??????", 6));
	    lst.add(new Keyword("??????", 5));
	    lst.add(new Keyword("??????", 5));
	    lst.add(new Keyword("??????", 5));
	    lst.add(new Keyword("??????", 4));
	    lst.add(new Keyword("??????", 2));
	    lst.add(new Keyword("??????", 8));
	    lst.add(new Keyword("?????????", 2));
	    lst.add(new Keyword("??????", 4));
	    lst.add(new Keyword("??????", 3));
	    lst.add(new Keyword("??????", 2));
	    lst.add(new Keyword("??????", 2));
	    lst.add(new Keyword("?????????", 2));
	    lst.add(new Keyword("??????", 8));
	    lst.add(new Keyword("??????", 4));
	    lst.add(new Keyword("??????", 4));
	    lst.add(new Keyword("??????", 2));
	    lst.add(new Keyword("?????????C", 1));
	    lst.add(new Keyword("??????", 1));
	    
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
	    
	    lst.add(new Keyword("???????????????", 10));
	    lst.add(new Keyword("??????", 10));
	    lst.add(new Keyword("??????", 10));
	    lst.add(new Keyword("????????????", 8));
	    lst.add(new Keyword("???", 8));
	    lst.add(new Keyword("??????", 6));
	    lst.add(new Keyword("????????????", 6));
	    lst.add(new Keyword("??????", 4));
	    lst.add(new Keyword("??????", 4));
	    lst.add(new Keyword("????????????", 2));
	    lst.add(new Keyword("???", 8));
	    lst.add(new Keyword("????????????", 6));
	    lst.add(new Keyword("????????????", 5));
	    lst.add(new Keyword("?????????", 5));
	    lst.add(new Keyword("????????????", 5));
	    lst.add(new Keyword("??????", 4));
	    lst.add(new Keyword("????????????????????????", 2));
	    lst.add(new Keyword("????????????", 4));
	    lst.add(new Keyword("??????", 4));
	    lst.add(new Keyword("???????????????", 2));
	}

}