package javaP;
import java.util.Random;

import utils.fileReader.JSONFileReaderClient;


public class Array {
public static void main(String[] args) {
	int[] arr= {1,3,2,6,7,2,3,8};
	
	JSONFileReaderClient bb=new JSONFileReaderClient();
	System.out.println(bb.getBody());
}
}
