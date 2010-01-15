package org.synergis

class Restaurant {
	
	static taxonomy = true
	
    static constraints = {
    	name(blank:false, maxSize:100)
		address(maxSize:500)
		city(maxSize:50)
		openingHours(maxSize:200)
		phoneNumber(maxSize:15)
		email(email:true)
		website(url:true)
		additionalComments(maxSize:1000)
    }
    
    String name
	String address
	String city
	String openingHours		
	String phoneNumber
	String email
	String website
	String additionalComments
}
