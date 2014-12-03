package sendrovitz.morsecode;

import java.util.HashMap;
import java.util.Map;



public class MorseCode {
	private Map<String, String> map;
	private Map<String, String> map2;
	private String array[][];
	private StringBuilder builder;

	public MorseCode(){
		String[][] array = new String[][] {{"A","B","C","D","E","F","G","H","I",
			"J","K","L","M","N","O","P","Q","R","S","T","U","V",
			"W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"," "},
			{".-","-...","-.-.","-..",".","..-.","--.","....","..",
				".---","-.-",".-..","--","-.","---",".--.","--.-",".-.",
				"...","-","..-","...-",".--","-..-","-.--","--..","-----",".----","..---",
				"...--","....-",".....","-....","--...","---..","----.", "/"}};
		this.array= array;	
		StringBuilder builder = new StringBuilder();
		this.builder = builder;
		
		Map<String, String> map = new HashMap<String, String>();
		this.map = map;
		
		for(int i=0; i<array[0].length; i++){
			map.put(array[0][i], array[1][i]);
	}

		Map<String, String> map2 = new HashMap<String, String>();
		this.map2 = map2;
		
		for(int i=0; i<array[0].length; i++){
			map2.put(array[1][i], array[0][i]);
	}
	}
	
	public String toMorseCode(String text){
		char[] characters = text.toCharArray();
		
		for(int i=0; i<characters.length; i++){
			String a = Character.toString(characters[i]);
			String code = map.get(a);
			builder.append(code);
			if(i<characters.length-1){
				builder.append(" ");
			}
			
		}
		
//		this.text=text;
//		StringBuilder builder = new StringBuilder();
//
//		for(int textPointer=0; textPointer<this.text.length(); textPointer++){
//			for(int arrayPointer= 0; arrayPointer<array[0].length; arrayPointer++){
//				if(array[0][arrayPointer].equalsIgnoreCase(text.substring(textPointer, textPointer+1))){
//					builder.append(array[1][arrayPointer]);
//					if(textPointer!= text.length()-1){
//						builder.append(" ");
//					}
//					break;
//				}
//			}
//		}
	return builder.toString();
	}


	public String toPlainText(String code){
		
		String[] split=code.split(" ");
		
		for(String s : split){
			String text = map2.get(s);
			builder.append(text);
		}

//		for(int codePointer=0; codePointer<split.length; codePointer++){
//			for(int arrayPointer= 0; arrayPointer<array[0].length; arrayPointer++){
//				if(array[1][arrayPointer].equalsIgnoreCase(split[codePointer])){
//					builder.append(array[0][arrayPointer]);
//					break;
//				}
//			}
//		}

		return builder.toString();
	}

	public static void main(String args[]){
		MorseCode morse = new MorseCode();
		for(int i=0; i<morse.array.length;i ++){
			for(int j=0; j<morse.array[i].length; j++){
				System.out.print(morse.array[i][j]);
			}
			System.out.println();
		}
		System.out.println(morse.toPlainText("... --- ..."));
	}

}