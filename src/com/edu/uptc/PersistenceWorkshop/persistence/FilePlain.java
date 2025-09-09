package com.edu.uptc.PersistenceWorkshop.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.edu.uptc.PersistenceWorkshop.config.*;
import com.edu.uptc.PersistenceWorkshop.constants.*;

public class FilePlain {
	protected Config config;

	public FilePlain() {
		config = Config.getInstance();
	}

	private String readFile(String rutaNombre) {
		StringBuilder contenido = new StringBuilder();
		try {
			FileReader fr = new FileReader(rutaNombre);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				contenido.append(linea).append(CommonConstants.BREAK_LINE);
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contenido.toString();
	}

	public void writeFile(String rutaNombre, String content) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaNombre))) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected List<String> reader(String rutaNombre) {
		List<String> output = new ArrayList<>();
		StringTokenizer tokens = new StringTokenizer(this.readFile(rutaNombre), CommonConstants.BREAK_LINE);
		while (tokens.hasMoreElements()) {
			output.add(tokens.nextToken());
		}
		return output;
	}

	protected void writer(String rutaNombre, List<String> file) {
		StringBuilder strContent = new StringBuilder();
		file.forEach(record -> strContent.append(record).append(CommonConstants.BREAK_LINE));
		writeFile(rutaNombre, strContent.toString());
	}

}
