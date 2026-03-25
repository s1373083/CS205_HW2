package edu.monmouth.library;

public class Journal implements LibraryItem {

	private String title;
	private String editor;
	private int volume;
	private StatusType status;
	
	public Journal(String title, String editor, int volume, StatusType status) throws JournalException {
		setTitle(title);
		setEditor(editor);
		setVolume(volume);
		setStatus(status);
		

	}
		
		@Override
		public String getTitle() {
		return title;
	}
		public void setTitle(String title) throws JournalException {
			if (title == null || title.length() == 0) {
				throw new JournalException("Title can't be null");
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

		public String getEditor() {
			return editor;
		}
		
		public void setEditor(String editor) throws JournalException {
			if (editor == null || editor.length() == 0) {
				throw new JournalException("Editor can't be null");
			}
			this.editor = editor;
		}
		
		public int getVolume() {
			return volume;
		}
		
		public void setVolume(int volume) throws JournalException {
			if (volume< JournalConstants.MINVOLUME) {
				throw new JournalException("The book must be " + JournalConstants.MINVOLUME + " long.");
		}
		this.volume = volume;
}
		public StatusType getStatus() {
			return status;
			
		}
		
		public void setStatus(StatusType status) {
			this.status= status;
		}
		@Override 
		public boolean equals (Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || !(obj instanceof Journal)) {
				return false;
			}
			
			Journal other = (Journal) obj;
			return title.equals(other.title) && editor.equals(other.editor) && volume == other.volume; 
		}
		
		@Override
		public String toString() {
			return "Journal [Title: " +title+ ", Editor: " +editor+ ", Volume: " +volume+ ", Status: " +status+ "]";
		}
}

