import java.io.*;
import java.util.*;

public class WebNode {
	public WebNode parent;
	public ArrayList<WebNode> children;
	public WebPage webPage;	
	public double nodeScore;
	
	public WebNode(WebPage webPage){
		this.webPage = webPage;
		this.children = new ArrayList<WebNode>();
	}
	
	public void setNodeScore(ArrayList<Keyword> keywords) throws IOException{
		webPage.setScore(keywords);
		nodeScore = webPage.score;
		
		for(WebNode child : children){
			nodeScore += child.nodeScore;
		}
	}
	
	public void addChild(WebNode child){
		this.children.add(child);
		child.parent = this;
	}
	
	public boolean isTheLastChild(){
		if(this.parent == null) return true;
		ArrayList<WebNode> siblings = this.parent.children;
		
		return this.equals(siblings.get(siblings.size() - 1));
	}
	
	public int getDepth(){
		int retVal = 1;
		WebNode currNode = this;
		while(currNode.parent!=null){
			retVal ++;
			currNode = currNode.parent;
		}
		return retVal;
	}
}