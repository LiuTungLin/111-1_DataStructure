public class Result {
	
	public String name, url;
	public double nodeScore;
	
	public Result(String name,double nodeScore,String url){
		this.name = name;
		this.nodeScore = nodeScore;
		this.url = url;
	}
	
	public String toString(){
		return "["+nodeScore+", "+name+", "+url+"]" + "\n";
	}
	
}