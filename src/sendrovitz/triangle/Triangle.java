package sendrovitz.triangle;

public class Triangle {

	private Integer height;
	private StringBuilder info;
	
	public Triangle(Integer height){
		this.info = new StringBuilder();
		if(height>=2){
		this.height= height;
		
		
		int spaces = height-1;
		int midSpaces = 1;
		
		for (int i=0; i<spaces;i++){
		     info.append(" "); 
		    }
		info.append("*\n");
		spaces--;
		//sec line
		if(height>2){
		for (int k=0; k<spaces;k++){
		     info.append(" "); 
		    }
		info.append('*');
		for (int l=0; l<midSpaces; l++){
		     info.append(" ");
		    }
		info.append("*\n");
		}
		
		//rest of lines
		for(int n=0; n<height-3;n++){
		     spaces--;
		     
		     //opening spaces
		     for (int o=0; o<spaces; o++){
		      info.append(" "); 
		     }
		     
		     //star
		     info.append('*');
		     
		     //middle spaces
		     midSpaces+=2;
		     for (int p=0; p<midSpaces; p++){
		      info.append(" "); 
		     }
		     
		     //star
		     info.append("*\n");
		    
		    }
		   
		    for(int r=0; r<(2*height)-1;r++){
		     info.append('*'); 
		    }
		    
		  }
	
	else{
		info.append("invalid height");
	}
	}
	
	public String toString(){
		return info.toString();
 
	}
	public static void main(String args[]){
		Triangle triangle = new Triangle(3);
		System.out.println(triangle.toString());
	}
}