package com.cucumber;

import org.openqa.selenium.By;

public class QueryFunctions {

	public By query(String value) {
		return value.contains("//") ? By.xpath(value) : By.cssSelector(value);
	}

	public By queryById(String id) {
		return By.id(id);
	}
	
	public By queryByXpath(String xpath) {
		return By.id(xpath);
	}

	public By queryByName(String name) {
		return By.name(name);
	}

	public By queryByClass(String className) {
		return By.className(className);
	}

	public By queryByAttribute(String attribute, String value) {
		String selector = String.format(
				"//*[normalize-space(translate(@%1$s, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) = '%2$s']",
				attribute, value.toLowerCase());
		return this.query(selector);
	}
	

	public By queryByAttribute(String attribute, String value, Boolean partial) {
		if (!partial) {
			return this.queryByAttribute(attribute, value);
		} else {
			String selector = String.format(
					"//*[contains(normalize-space(translate(@%1%s, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) , '%2$s')]",
					attribute, value.toLowerCase());
			return this.query(selector);
		}
	}

	public By queryByText(String text) {
		String selector = String.format(
				"//*[normalize-space(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) = '%1$s']",
				text.toLowerCase());
		return this.query(selector);
	}

	public By queryByText(String text, Boolean partial) {
		if (!partial) {
			return this.queryByText(text);
		} else {
			String selector = String.format(
					"//*[contains(normalize-space(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')) , '%1$s')]",
					text.toLowerCase());
			return this.query(selector);
		}
	}
}