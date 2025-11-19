		for(Meeting toCheck : occupied.get(month).get(day)){
			if(start >= toCheck.getStartTime() && start <= toCheck.getEndTime()){
				busy=true;
			}else if(end >= toCheck.getStartTime() && end <= toCheck.getEndTime()){
				busy=true;
			}
		}

    
