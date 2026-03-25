package edu.monmouth.library;


public class Book implements LibraryItem {

	
	private String title;
	private String author;
	private int pages;
	private StatusType status;
	
	public Book(String title, String author, int page, StatusType status) throws BookExceptions {
		setTitle(title);
		setAuthor(author);
		setPages(pages);
		setStatus(status);
		

	}
		
		@Override
		public String getTitle() {
		return title;
	}
		public void setTitle(String title) throws BookExceptions {
			if (title == null || title.length() == 0) {
				throw new BookExceptions("Title can't be null");
		}
			this.title = title;
		}
	
		@Override 
		public boolean isAvailable() {
			return status == StatusType.ONSHELF;
			
		}
		@Override
		public void borrowItem() {
			status = StatusType.BORROWED;
			
		}
		
		@Override
		public void returnItem() {
			status = StatusType.ONSHELF;
		}

		public String getAuthor() {
			return author;
		}
		
		public void setAuthor(String author) throws BookExceptions {
			if (author == null || author.length() == 0) {
				throw new BookExceptions("Author can't be null");
			}
			this.author = author;
		}
		
		public int getPages() {
			return pages;
		}
		
		public void setPages(int pages) throws BookExceptions {
			if (pages< BookConstants.MINPAGE) {
				throw new BookExceptions("The book must be " + BookConstants.MINPAGE + " pages long.");
		}
		this.pages = pages;
}
		public StatusType getStatus() {
			return status;
			
		}
		
		public void setStatus(StatusType status) {
			this.status= status;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || !(obj instanceof Book)) {
				return false;
			}
			
			Book other = (Book) obj;
				return title.equals(other.title) && author.equals(other.author); 
			}
		
		@Override
		public String toString() {
			return "Book [Title: " +title+ ", Author: " +author+ ", Pages: " +pages+ ", Status: " +status+ "]";
		}
}