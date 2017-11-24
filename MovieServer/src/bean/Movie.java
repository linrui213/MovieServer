package bean;

public class Movie {
	private int id;
	private String title;
	private String imgurl;
	private String videourl;
	private int category;
	private int adult;
	private float score;
	private int comments;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getVideourl() {
		return videourl;
	}
	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getAdult() {
		return adult;
	}
	public void setAdult(int adult) {
		this.adult = adult;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", imgurl=" + imgurl
				+ ", videourl=" + videourl + ", category=" + category
				+ ", adult=" + adult + ", score=" + score + ", comments="
				+ comments + "]";
	}
	
	

}
