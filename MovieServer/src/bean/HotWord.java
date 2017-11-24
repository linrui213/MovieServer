package bean;

public class HotWord {
	private int id;
	private String keyword;
	private int hot;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	@Override
	public String toString() {
		return "HotWords [id=" + id + ", keyword=" + keyword + ", hot=" + hot
				+ "]";
	}
	

}
