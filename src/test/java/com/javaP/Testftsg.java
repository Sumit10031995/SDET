package com.javaP;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.testng.annotations.Test;

import net.jodah.failsafe.Failsafe;
import com.utils.retryPolicy.Retry;


public class Testftsg {
	public static void main(String args) {
		System.out.println(isPlural("foot"));
	
	}
	 public static boolean isPlural(String word) {
	        String[] pluralRules = {
	            "s$", "es$", "[^aeiou]y$", "[aeiou]y[s|ies]$", "[sxzh]$", "[^sxzhyu]$"
	        };

	        for (String rule : pluralRules) {
	            if (Pattern.matches(".*" + rule, word)) {
	                return true;
	            }
	        }
	        return false;
	    }
	     
}

