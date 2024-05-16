package a2_WassimDakka_40276235;

import java.io.Serializable;

public class Movie implements Serializable{
	
	
private int year;
private String title; 
private int duration;
private String genres;
private String rating;
private double score;
private String director;
private String actor1;
private String actor2;
private String actor3;


public Movie(int year, String title, int duration, String genres, String rating, double score, String director,
		String actor1, String actor2, String actor3) {
	
	this.year = year;
	this.title = title;
	this.duration = duration;
	this.genres = genres;
	this.rating = rating;
	this.score = score;
	this.director = director;
	this.actor1 = actor1;
	this.actor2 = actor2;
	this.actor3 = actor3;
}


public int getYear() {
	return year;
}


public void setYear(int year) {
	this.year = year;
}


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public int getDuration() {
	return duration;
}


public void setDuration(int duration) {
	this.duration = duration;
}


public String getGenres() {
	return genres;
}


public void setGenres(String genres) {
	this.genres = genres;
}


public String getRating() {
	return rating;
}


public void setRating(String rating) {
	this.rating = rating;
}


public double getScore() {
	return score;
}


public void setScore(double score) {
	this.score = score;
}


public String getDirector() {
	return director;
}


public void setDirector(String director) {
	this.director = director;
}


public String getActor1() {
	return actor1;
}


public void setActor1(String actor1) {
	this.actor1 = actor1;
}


public String getActor2() {
	return actor2;
}


public void setActor2(String actor2) {
	this.actor2 = actor2;
}


public String getActor3() {
	return actor3;
}


public void setActor3(String actor3) {
	this.actor3 = actor3;
}


@Override
public String toString() {
	return "Movie [year=" + year + ", title=" + title + ", duration=" + duration + ", genres=" + genres + ", rating="
			+ rating + ", score=" + score + ", director=" + director + ", actor1=" + actor1 + ", actor2=" + actor2
			+ ", actor3=" + actor3 + "]";
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Movie other = (Movie) obj;
	if (actor1 == null) {
		if (other.actor1 != null)
			return false;
	} else if (!actor1.equals(other.actor1))
		return false;
	if (actor2 == null) {
		if (other.actor2 != null)
			return false;
	} else if (!actor2.equals(other.actor2))
		return false;
	if (actor3 == null) {
		if (other.actor3 != null)
			return false;
	} else if (!actor3.equals(other.actor3))
		return false;
	if (director == null) {
		if (other.director != null)
			return false;
	} else if (!director.equals(other.director))
		return false;
	if (duration != other.duration)
		return false;
	if (genres == null) {
		if (other.genres != null)
			return false;
	} else if (!genres.equals(other.genres))
		return false;
	if (rating == null) {
		if (other.rating != null)
			return false;
	} else if (!rating.equals(other.rating))
		return false;
	if (Double.doubleToLongBits(score) != Double.doubleToLongBits(other.score))
		return false;
	if (title == null) {
		if (other.title != null)
			return false;
	} else if (!title.equals(other.title))
		return false;
	if (year != other.year)
		return false;
	return true;
}



}
