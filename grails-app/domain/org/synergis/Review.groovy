package org.synergis

class Review {

    static constraints = {
    	rating(range:1..10)
		title(blank:false, maxSize:50)
		text(blank:false, maxSize:2000)
    }
    
    static belongsTo = [restaurant:Restaurant]
	
	String text
	String title
	Integer rating
}
