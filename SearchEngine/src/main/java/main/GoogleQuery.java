package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery 
{
	public String searchKeyword;
	public String url;
	public String content;
//	public PriorityQueue<WebNode> heap;
	public GoogleQuery(String searchKeyword)
	{
		this.searchKeyword = searchKeyword;
		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=10";
	}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";
		
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		
		InputStreamReader inReader = new InputStreamReader(in,"utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line = bufReader.readLine())!=null)
		{
			retVal += line;
		}
		return retVal;
	}
	
	public HashMap<String, String> query() throws IOException
	{
		if(content==null)
		{
			content= fetchContent();
		}
		
		HashMap<String, String> retVal = new HashMap<String, String>();
		
		Document doc = Jsoup.parse(content);
		System.out.println(doc.text());
		
		//select particular element(tag) which you want
		Elements lis = doc.select("div");
//		 System.out.println(lis);
		lis = lis.select(".kCrYT");
		// System.out.println(lis.size());
		
		for(Element li : lis)
		{
			try 
			{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(title.equals("")) {
					continue;
				}
				
				WebPage rootPage = new WebPage(citeUrl.substring(7), title);		
				WebTree tree = new WebTree(rootPage);
				
				ArrayList<Keyword> lst = new ArrayList<Keyword>();
				lst.add(new Keyword("飲食", 10));
				lst.add(new Keyword("肥胖", 10));
				lst.add(new Keyword("營養", 10));
				lst.add(new Keyword("熱量", 8));
				lst.add(new Keyword("藥", 8));
				lst.add(new Keyword("疾病", 6));
				lst.add(new Keyword("運動", 6));
				lst.add(new Keyword("三高", 4));
				lst.add(new Keyword("免疫力", 4));
				lst.add(new Keyword("病毒", 2));
				
//				tree.setPostOrderScore(lst);
//				tree.eularPrintTree();
				
				
				System.out.println("Title: " + title + "\nurl: " + citeUrl.substring(7) + "\n");
				//put title and pair into HashMap
				retVal.put(title, citeUrl);
			
			} catch (IndexOutOfBoundsException e) {
//				e.printStackTrace();
			}

		}

		return retVal;
	}
	
}