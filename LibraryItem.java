package edu.monmouth.library;

public interface LibraryItem {

	public String getTitle();
	public boolean isAvailable();
	public void borrowItem();
	public void returnItem();
}
